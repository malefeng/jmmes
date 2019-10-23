package com.jeecg.controller.print;

import com.jeecg.entity.basic.RawMaterialEntity;
import com.jeecg.entity.invoices.PurchaseReceiptEntity;
import com.jeecg.entity.invoices.PurchaseReceiptNodeEntity;
import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.service.common.SequenceServiceI;
import com.jeecg.service.print.PurchaseReceiptNodeServiceI;
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
 * @Description: 原料码信息
 * @author zhangdaihao
 * @date 2019-10-07 23:03:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/purchaseReceiptNodeController")
public class PurchaseReceiptNodeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PurchaseReceiptNodeController.class);

	@Autowired
	private PurchaseReceiptNodeServiceI purchaseReceiptNodeService;
	@Autowired
	private SequenceServiceI sequenceServiceI;
	@Autowired
	private DictionaryUtil dictionaryUtil;



	/**
	 * 原料码信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic","repostDic","storageDic","ioStateDic");
		return new ModelAndView("com/jeecg/print/purchaseReceiptNodePrintList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(PurchaseReceiptNodeEntity purchaseReceiptNode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PurchaseReceiptNodeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, purchaseReceiptNode, request.getParameterMap());
		this.purchaseReceiptNodeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "getPrintData")
	@ResponseBody
	public Object getPrintData(String id, String batchNo){
		List toSaveList = new ArrayList();
		List result = new ArrayList();
		generatePrintData(id, batchNo, toSaveList, result);
		//保存打印记录
		purchaseReceiptNodeService.batchSave(toSaveList);
		return result;
	}

	private void generatePrintData(String id, String batchNo, List toSaveList, List result) {
		PurchaseReceiptNodeEntity purchaseReceiptNodeEntity =purchaseReceiptNodeService.get(PurchaseReceiptNodeEntity.class,id);
		if(purchaseReceiptNodeEntity!=null){
			PurchaseReceiptEntity purchaseReceiptEntity = purchaseReceiptNodeService.get(PurchaseReceiptEntity.class,purchaseReceiptNodeEntity.getInspectId());
			List<RawMaterialEntity> rawMaterialList = purchaseReceiptNodeService.findByProperty(RawMaterialEntity.class,"rawMaterialCode",purchaseReceiptNodeEntity.getRawMaterialCode());
			if(rawMaterialList!=null&&rawMaterialList.size()>0){
				RawMaterialEntity rawMaterial = rawMaterialList.get(0);
				//生成打印信息
				QRCodeEntity qrCodeEntity = new QRCodeEntity();
				qrCodeEntity.setOrderNumber(purchaseReceiptEntity.getReceiptCode());
				qrCodeEntity.setCode(rawMaterial.getRawMaterialCode());
				qrCodeEntity.setMaterialSize(rawMaterial.getRawMaterialSize());
				qrCodeEntity.setMaterialName(rawMaterial.getRawMaterialName());
				qrCodeEntity.setQrCodeType("1");
				qrCodeEntity.setMaterialType(rawMaterial.getRawMaterialType());
				qrCodeEntity.setBatchNo(batchNo);
				//计算打印个数
				int times = (int)Math.ceil(purchaseReceiptNodeEntity.getActualReceivedNumber()/(double)rawMaterial.getRawMaterialNumber());
				for (int i = 0; i<times; i++){
					qrCodeEntity = qrCodeEntity.clone();
					//二维码代码待维护
					String qrCode = sequenceServiceI.getqrCode("material");
					qrCodeEntity.setNumber(qrCode);
					toSaveList.add(qrCodeEntity);
					//明文key
					Object[] keys = new Object[6];
					//明文value
					Object[] values = new Object[6];
					//二维码类型
					keys[0] = "类型";
					values[0] = "11-原料";
					//批次
					keys[1] = "批次";
					values[1] = batchNo;
					//编号
					keys[2] = "编号";
					values[2] = qrCode;
					//代码
					keys[3] = "代码";
					values[3] = rawMaterial.getRawMaterialCode();
					//名称
					keys[4] = "名称";
					values[4] = rawMaterial.getRawMaterialName();
					//规格型号
					keys[5] = "规格型号";
					values[5] = rawMaterial.getRawMaterialSize();

					result.add(generateContent(qrCode.concat(",").concat(rawMaterial.getRawMaterialCode()).concat(",11"), keys, values));
					//同一个原料码输出两次
					result.add(generateContent(qrCode.concat(",").concat(rawMaterial.getRawMaterialCode()).concat(",11"), keys, values));
				}
			}
		}
	}


	private Map generateContent(String qrCode, Object[] keys, Object[] values) {
		Map contenMap;
		contenMap = new HashMap();
		contenMap.put("pubKey", StringUtils.join(keys, ","));
		contenMap.put("pubVal", StringUtils.join(values, ","));
		contenMap.put("secData", qrCode);
		return contenMap;
	}
}
