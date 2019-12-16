package com.jeecg.controller.invoices;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.batch.MaterialInLookDataBatchRunnable;
import com.jeecg.constant.ERPApiCodeEnum;
import com.jeecg.constant.ERPurTranslateMap;
import com.jeecg.entity.invoices.PurchaseReceiptEntity;
import com.jeecg.entity.invoices.PurchaseReceiptNodeEntity;
import com.jeecg.entity.look.MaterialWarehouIOLookEntity;
import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.page.invoices.PurchaseReceiptPage;
import com.jeecg.service.invoices.PurchaseReceiptServiceI;
import com.jeecg.util.DictionaryUtil;
import com.jeecg.util.ERPApiUitl;
import com.jeecg.util.ReflactUtil;
import com.jeecg.util.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 采购收料单
 * @author zhangdaihao
 * @date 2019-10-09 22:26:35
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/purchaseReceiptController")
public class PurchaseReceiptController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PurchaseReceiptController.class);

	@Autowired
	private PurchaseReceiptServiceI purchaseReceiptService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 采购收料单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","depDic","suplDic");
		return new ModelAndView("com/jeecg/invoices/purchaseReceiptList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{receiptCode}")
	@ResponseBody
	public PurchaseReceiptEntity apiList(@PathVariable("receiptCode") String receiptCode){
		PurchaseReceiptEntity purchaseReceiptEntity = purchaseReceiptService.findUniqueByProperty(PurchaseReceiptEntity.class, "receiptCode", receiptCode);
		if(purchaseReceiptEntity!=null){
			List<QRCodeEntity> qrCodeEntityList =	purchaseReceiptService.findByProperty(QRCodeEntity.class,"orderNumber",purchaseReceiptEntity.getReceiptCode());
			if(!ListUtils.isNullOrEmpty(qrCodeEntityList)){
				StringBuilder qrCode = new StringBuilder(qrCodeEntityList.size());
				for(QRCodeEntity qrCodeEntity : qrCodeEntityList){
					qrCode.append(",").append(qrCodeEntity.getNumber());
				}
				purchaseReceiptEntity.setQrCodeStrs(qrCode.substring(1));
			}
		}
		return purchaseReceiptEntity;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(PurchaseReceiptEntity purchaseReceipt,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		request.getParameterMap();
		CriteriaQuery cq = new CriteriaQuery(PurchaseReceiptEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, purchaseReceipt,request.getParameterMap());
		this.purchaseReceiptService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}



	/**
	 * 删除采购收料单
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PurchaseReceiptEntity purchaseReceipt, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		purchaseReceipt = systemService.getEntity(PurchaseReceiptEntity.class, purchaseReceipt.getId());
		allEntitys.add(purchaseReceipt);
		List<PurchaseReceiptNodeEntity> purchaseReceiptNodeEntities = systemService.findByProperty(PurchaseReceiptNodeEntity.class, "inspectId", purchaseReceipt.getId());
		if(!ListUtils.isNullOrEmpty(purchaseReceiptNodeEntities)){
			allEntitys.addAll(purchaseReceiptNodeEntities);
		}
		message = "删除成功";
		purchaseReceiptService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		//删除入库看板
		List<MaterialWarehouIOLookEntity> materialWarehouIOLookEntities = systemService.findByProperty(MaterialWarehouIOLookEntity.class, "receivingOrderNumber", purchaseReceipt.getReceiptCode());
		if(!ListUtils.isNullOrEmpty(materialWarehouIOLookEntities)){
			systemService.deleteAllEntitie(materialWarehouIOLookEntities);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加采购收料单
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PurchaseReceiptEntity purchaseReceipt,PurchaseReceiptPage purchaseReceiptPage, HttpServletRequest request) {
		String message = null;
		List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList =  purchaseReceiptPage.getPurchaseReceiptNodeList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(purchaseReceipt.getId())) {
			message = "更新成功";
			purchaseReceiptService.updateMain(purchaseReceipt, purchaseReceiptNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			purchaseReceiptService.addMain(purchaseReceipt, purchaseReceiptNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		//同步入库看板数据
		new Thread(new MaterialInLookDataBatchRunnable(purchaseReceiptPage.getReceiptCode(),systemService)).start();
		return j;
	}

	/**
	 * 采购收料单列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PurchaseReceiptEntity purchaseReceipt, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(purchaseReceipt.getId())) {
			purchaseReceipt = purchaseReceiptService.getEntity(PurchaseReceiptEntity.class, purchaseReceipt.getId());
			req.setAttribute("purchaseReceiptPage", purchaseReceipt);
		}
		return new ModelAndView("com/jeecg/invoices/purchaseReceipt");
	}

	@RequestMapping(params = "getErpData")
	@ResponseBody
	public String getErpData(String number){
		try {
			Map paramMap = new HashMap();
			String baseKeys = org.apache.commons.lang3.StringUtils.join(ERPurTranslateMap.TRANSLATE_HEAD_PARAM,",");
			String detailKeys = org.apache.commons.lang3.StringUtils.join(ERPurTranslateMap.TRANSLATE_DETAIL_PARAM,",");
			paramMap.put("FormId",ERPApiCodeEnum.PUR.getCode());
			paramMap.put("FieldKeys",baseKeys+","+detailKeys);
			paramMap.put("FilterString",String.format("FBillNo='%s'",number));
			String res = ERPApiUitl.list(JSONObject.toJSONString(paramMap));
			PurchaseReceiptEntity purchaseReceipt = new PurchaseReceiptEntity();
			List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList = new ArrayList<>();
			if(analysisErpData(res,purchaseReceipt,purchaseReceiptNodeList)){
				purchaseReceiptService.addMain(purchaseReceipt, purchaseReceiptNodeList);
				return purchaseReceipt.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean analysisErpData(String res, PurchaseReceiptEntity purchaseReceipt, List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List<List> result = StringUtils.toList(res);
		String[] headKeys = org.apache.commons.lang3.StringUtils.join(ERPurTranslateMap.TRANSLATE_HEAD_PROPERTY,",").split(",");
		String[] bodyKeys = org.apache.commons.lang3.StringUtils.join(ERPurTranslateMap.TRANSLATE_DETAIL_PROPERTY,",").split(",");
		for (int i = 0; i < result.size(); i++) {
			List inList = result.get(i);
			if(headKeys.length+bodyKeys.length==inList.size()){
				if(purchaseReceipt.getCreateTime()==null){
					ReflactUtil.reflact(headKeys,ERPurTranslateMap.TRANSLATE_HEAD_TYPE,purchaseReceipt,inList.subList(0,headKeys.length));
				}
				PurchaseReceiptNodeEntity purchaseReceiptNodeEntity = new PurchaseReceiptNodeEntity();
				ReflactUtil.reflact(bodyKeys,ERPurTranslateMap.TRANSLATE_DETAIL_TYPE,purchaseReceiptNodeEntity,inList.subList(headKeys.length,inList.size()));
				purchaseReceiptNodeList.add(purchaseReceiptNodeEntity);
			}

		}
		return true;
	}

	/**
	 * 加载明细列表[采购收料单物料信息]
	 * 
	 * @return
	 */
	@RequestMapping(params = "purchaseReceiptNodeList")
	public ModelAndView purchaseReceiptNodeList(PurchaseReceiptEntity purchaseReceipt, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = purchaseReceipt.getId();
		//===================================================================================
		//查询-采购收料单物料信息
	    String hql0 = "from PurchaseReceiptNodeEntity where 1 = 1 AND inspectId = ? ";
		try{
		    List<PurchaseReceiptNodeEntity> purchaseReceiptNodeEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("purchaseReceiptNodeList", purchaseReceiptNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/invoices/purchaseReceiptNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(PurchaseReceiptEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<PurchaseReceiptEntity> listPurchaseReceipts = this.purchaseReceiptService.getListByCriteriaQuery(query,true);
		return Result.success(listPurchaseReceipts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		PurchaseReceiptEntity task = purchaseReceiptService.get(PurchaseReceiptEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody PurchaseReceiptEntity purchaseReceipt, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PurchaseReceiptEntity>> failures = validator.validate(purchaseReceipt);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		purchaseReceiptService.save(purchaseReceipt);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = purchaseReceipt.getId();
		URI uri = uriBuilder.path("/rest/purchaseReceiptController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody PurchaseReceiptEntity purchaseReceipt) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PurchaseReceiptEntity>> failures = validator.validate(purchaseReceipt);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		purchaseReceiptService.saveOrUpdate(purchaseReceipt);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		purchaseReceiptService.deleteEntityById(PurchaseReceiptEntity.class, id);
	}
}
