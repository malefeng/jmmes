package com.jeecg.controller.print;

import com.jeecg.entity.basic.SemiFinishedProductEntity;
import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.service.common.SequenceServiceI;
import com.jeecg.service.print.SemiFinishedProductPrintServiceI;
import com.jeecg.service.qrcode.QRCodeServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 半成品码
 * @author zhangdaihao
 * @date 2019-09-29 00:34:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/semiFinishedProductPrintController")
public class SemiFinishedProductPrintController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SemiFinishedProductPrintController.class);

	@Autowired
	private SemiFinishedProductPrintServiceI semiFinishedProductPrintService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private QRCodeServiceI qrCodeServiceI;
	@Autowired
	private SequenceServiceI sequenceServiceI;
	@Autowired
	private DictionaryUtil dictionaryUtil;


	/**
	 * 半成品码列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic","matTypeDic");
		return new ModelAndView("com/jeecg/print/semiFinishedProductPrintList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(SemiFinishedProductEntity semiFinishedProductPrint, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SemiFinishedProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, semiFinishedProductPrint, request.getParameterMap());
		this.semiFinishedProductPrintService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		semiFinishedProductPrintService.deleteEntityById(SemiFinishedProductEntity.class, id);
	}

	@RequestMapping(params = "getPrintData")
	@ResponseBody
	public Object getPrintData(String id, String batchNo, int firstTimes,int lastTimes,String takeMaterilNumber,String productionDispatchingNumber,String productionOrderNumber ){
		List toSaveList = new ArrayList();
		List result = new ArrayList();
		generatePrintData(id, batchNo, firstTimes, lastTimes, toSaveList, result,takeMaterilNumber,productionOrderNumber,productionDispatchingNumber);
		//保存打印记录
		qrCodeServiceI.batchSave(toSaveList);
		return result;
	}

	private void generatePrintData(String id, String batchNo, int firstTimes, int lastTimes, List toSaveList, List result,String takeMaterilNumber,String productionOrderNumber,String productionDispatchingNumber) {
		QRCodeEntity qrCodeEntity = new QRCodeEntity();
		SemiFinishedProductEntity semiFinishedProductEntity = semiFinishedProductPrintService.get(SemiFinishedProductEntity.class,id);
		//二维码代码待维护
		String qrCode = sequenceServiceI.getqrCode("semiFinished");
		//生产派工单
		qrCodeEntity.setOrderNumber(productionDispatchingNumber);
		qrCodeEntity.setNumber(qrCode);
		qrCodeEntity.setCode(semiFinishedProductEntity.getSemiFinishedCode());
		qrCodeEntity.setMaterialSize(semiFinishedProductEntity.getSemiFinishedSize());
		qrCodeEntity.setMaterialName(semiFinishedProductEntity.getSemiFinishedName());
		//二维码类型
		qrCodeEntity.setQrCodeType("2");
		qrCodeEntity.setMaterialType(semiFinishedProductEntity.getMaterialType());
		qrCodeEntity.setBatchNo(batchNo);
		toSaveList.add(qrCodeEntity);
		//半成品码信息
		//明文key
		Object[] keys = new Object[6];
		//明文value
		Object[] values = new Object[6];
		//二维码类型
		keys[0] = "类型";
		values[0] = "22-半成品";
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

		//二维码图片展示信息：编号,代码,领料单号,生产派工单,22
		String qrCodeStr = qrCode.concat(",").concat(semiFinishedProductEntity.getSemiFinishedCode()).concat(",").concat(takeMaterilNumber).concat(",").concat(productionDispatchingNumber).concat(",22");

		result.add(generateContent(qrCodeStr, keys, values));
		//半成品首检码信息
		for (int i = 0; i < firstTimes; i++) {
			qrCodeEntity  = qrCodeEntity.clone();
			//半成品编号规则+2位序列号，序列号从11开始
			values[2] = qrCode + (11+i);
			values[0] = 22+""+(11+i)+"-半成品首检码";
			qrCodeEntity.setNumber(qrCode + (11+i));
			qrCodeEntity.setQrCodeType("3");
			toSaveList.add(qrCodeEntity);
			result.add(generateContent(String.valueOf(values[2]), keys, values));
		}
		//半成品末检码信息
		for (int i = 0; i < lastTimes; i++) {
			qrCodeEntity  = qrCodeEntity.clone();
			//半成品编号规则+2位序列号，序列号从91开始
			values[2] = qrCode + (91+i);
			values[0] = 22+""+(91+i)+"-半成品末检码";
			qrCodeEntity.setNumber(qrCode + (91+i));
			qrCodeEntity.setQrCodeType("4");
			toSaveList.add(qrCodeEntity);
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
