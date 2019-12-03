package com.jeecg.controller.print;

import com.jeecg.entity.basic.FinishedProductEntity;
import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.service.common.SequenceServiceI;
import com.jeecg.service.print.FinishedProductPrintServiceI;
import com.jeecg.service.qrcode.QRCodeServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 成品码
 * @author zhangdaihao
 * @date 2019-09-29 00:34:10
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedProductPrintController")
public class FinishedProductPrintController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedProductPrintController.class);

	@Autowired
	private FinishedProductPrintServiceI finishedProductPrintService;
	@Autowired
	private QRCodeServiceI qrCodeServiceI;
	@Autowired
	private SequenceServiceI sequenceServiceI;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 成品码列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic","matTypeDic");
		return new ModelAndView("com/jeecg/print/finishedProductPrintList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedProductEntity finishedProductPrint, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedProductPrint, request.getParameterMap());
		this.finishedProductPrintService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "getPrintData")
	@ResponseBody
	public Object getPrintData(String id, String batchNo,int firstTimes,int lastTimes,String takeMaterilNumber,String productionOrderNumber,String productionDispatchingNumber){
		List toSave = new ArrayList();
		List result = new ArrayList();
		generatePrintData(id, batchNo, firstTimes, lastTimes, toSave, result,takeMaterilNumber,productionOrderNumber,productionDispatchingNumber);
		qrCodeServiceI.batchSave(toSave);
		return result;
	}

	private void generatePrintData(String id, String batchNo, int firstTimes,int lastTimes, List toSave, List result,String takeMaterilNumber,String productionOrderNumber,String productionDispatchingNumber) {
		QRCodeEntity qrCodeEntity = new QRCodeEntity();
		FinishedProductEntity finishedProductPrintEntity = finishedProductPrintService.get(FinishedProductEntity.class,id);
		//二维码代码待维护
		String qrCode = sequenceServiceI.getqrCode("finished");
		qrCodeEntity.setNumber(qrCode);
		qrCodeEntity.setOrderNumber(productionDispatchingNumber);
		qrCodeEntity.setCode(finishedProductPrintEntity.getFinishedCode());
		qrCodeEntity.setMaterialSize(finishedProductPrintEntity.getFinishedSize());
		qrCodeEntity.setMaterialName(finishedProductPrintEntity.getFinishedName());
		qrCodeEntity.setQrCodeType("5");
		qrCodeEntity.setMaterialType(finishedProductPrintEntity.getMaterialType());
		qrCodeEntity.setBatchNo(batchNo);
		toSave.add(qrCodeEntity);
		//明文key
		String[] keys = new String[6];
		//明文value
		String[] values = new String[6];
		//二维码类型
		keys[0] = "类型";
		values[0] = "33-成品";
		//批次
		keys[1] = "批次";
		values[1] = batchNo;
		//编号
		keys[2] = "编号";
		values[2] = qrCode;
		//代码
		keys[3] = "代码";
		values[3] = qrCodeEntity.getCode();
		//名称
		keys[4] = "名称";
		values[4] = qrCodeEntity.getMaterialName();
		//规格型号
		keys[5] = "规格型号";
		values[5] = qrCodeEntity.getMaterialSize();
		//二维码图片展示信息：编号,代码,领料单号,生产派工单号,33
		String qrCodeStr = qrCode.concat(",").concat(finishedProductPrintEntity.getFinishedCode()).concat(",").concat(takeMaterilNumber).concat(",").concat(productionDispatchingNumber).concat(",33");
		result.add(generateContent(qrCodeStr, keys, values));
		//同一个成品码输出两次
		result.add(generateContent(qrCodeStr, keys, values));

		//成品首检码信息
		for (int i = 0; i < firstTimes; i++) {
			qrCodeEntity  = qrCodeEntity.clone();
			//成品编号规则+2位序列号，序列号从11开始
			values[2] = qrCode + (11+i);
			values[0] = 33+""+(11+i)+"-成品首检码";
			qrCodeEntity.setNumber(qrCode + (11+i));
			//二维码类型：
			qrCodeEntity.setQrCodeType("6");
			toSave.add(qrCodeEntity);
			result.add(generateContent(String.valueOf(values[2]), keys, values));
		}
		//成品末检码信息
		for (int i = 0; i < lastTimes; i++) {
			qrCodeEntity = qrCodeEntity.clone();
			//成品编号规则+2位序列号，序列号从91开始
			values[2] = qrCode + (91 + i);
			values[0] = 33+""+(91 + i)+"-成品末检码";
			qrCodeEntity.setNumber(qrCode + (91 + i));
			qrCodeEntity.setQrCodeType("7");
			toSave.add(qrCodeEntity);
			result.add(generateContent(String.valueOf(values[2]), keys, values));
		}
	}




	private Map generateContent(String secData, Object[] keys, Object[] values) {
		Map contenMap;
		contenMap = new HashMap();
		contenMap.put("pubKey", StringUtils.join(keys, ","));
		contenMap.put("pubVal", StringUtils.join(values, ","));
		contenMap.put("secData", secData);
		return contenMap;
	}
}
