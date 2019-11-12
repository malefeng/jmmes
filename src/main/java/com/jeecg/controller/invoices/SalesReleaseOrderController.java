package com.jeecg.controller.invoices;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.batch.FinishedOutLookDataBatchRunnable;
import com.jeecg.entity.invoices.SalesReleaseNodeEntity;
import com.jeecg.entity.invoices.SalesReleaseOrderEntity;
import com.jeecg.entity.invoices.SalesReleaseOrgNodeEntity;
import com.jeecg.entity.look.FinishedWarehousIOLookEntity;
import com.jeecg.entity.warehous.FinishedWarehousIOEntity;
import com.jeecg.page.invoices.SalesReleaseOrderPage;
import com.jeecg.service.invoices.SalesReleaseOrderServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
import java.net.URI;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 销售出库单
 * @author zhangdaihao
 * @date 2019-10-10 00:22:47
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/salesReleaseOrderController")
public class SalesReleaseOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SalesReleaseOrderController.class);

	@Autowired
	private SalesReleaseOrderServiceI salesReleaseOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 销售出库单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","currencyDic","custDic","carrierDic","orderStateDic");
		return new ModelAndView("com/jeecg/invoices/salesReleaseOrderList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{receiptCode}")
	@ResponseBody
	public Object apiList(@PathVariable("receiptCode") String receiptCode){
		Map result = new HashMap();
		SalesReleaseOrderEntity salesReleaseOrderEntity = salesReleaseOrderService.findUniqueByProperty(SalesReleaseOrderEntity.class, "receiptCode", receiptCode);
		result.put("salesReleaseOrderEntity",salesReleaseOrderEntity);
		if(salesReleaseOrderEntity!=null){
			List<SalesReleaseNodeEntity> salesReleaseNodeEntityList = salesReleaseOrderService.findByProperty(SalesReleaseNodeEntity.class, "receiptId", salesReleaseOrderEntity.getId());
			result.put("salesReleaseNodeEntityList",salesReleaseNodeEntityList);
		}
		return JSONObject.toJSON(result);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(SalesReleaseOrderEntity salesReleaseOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SalesReleaseOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, salesReleaseOrder,request.getParameterMap());
		this.salesReleaseOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除销售出库单
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SalesReleaseOrderEntity salesReleaseOrder, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		salesReleaseOrder = systemService.getEntity(SalesReleaseOrderEntity.class, salesReleaseOrder.getId());
		allEntitys.add(salesReleaseOrder);
		List<SalesReleaseNodeEntity> salesReleaseNodeList = systemService.findByProperty(SalesReleaseNodeEntity.class,"receiptId", salesReleaseOrder.getId());
		List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeEntityList = systemService.findByProperty(SalesReleaseOrgNodeEntity.class,"receiptId", salesReleaseOrder.getId());
		if(!ListUtils.isNullOrEmpty(salesReleaseNodeList)){
			allEntitys.addAll(salesReleaseNodeList);
		}
		if(!ListUtils.isNullOrEmpty(salesReleaseOrgNodeEntityList)){
			allEntitys.addAll(salesReleaseOrgNodeEntityList);
		}
		message = "删除成功";
		salesReleaseOrderService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		//删除成品出库看板
		List<FinishedWarehousIOLookEntity> finishedWarehousIOLookEntities = systemService.findByProperty(FinishedWarehousIOLookEntity.class, "salesDeliveryOrderNumber", salesReleaseOrder.getReceiptCode());
		if(!ListUtils.isNullOrEmpty(finishedWarehousIOLookEntities)){
			systemService.deleteAllEntitie(finishedWarehousIOLookEntities);
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params = "dosing")
	@ResponseBody
	public Object dosing(String remainData){
		if(StringUtil.isNotEmpty(remainData)){
			List<FinishedWarehousIOEntity> result = new ArrayList<FinishedWarehousIOEntity>();
			Map<String,Integer> remainDataMap = JSONObject.parseObject(remainData,Map.class);
			String hql = "from FinishedWarehousIOEntity where ioType = 1 and finishedCode = ? order by warehousingDate";
			Query query = systemService.getSession().createQuery(hql);
			for (Map.Entry<String,Integer> entry: remainDataMap.entrySet()) {
				query.setMaxResults(entry.getValue()).setParameter(0,entry.getKey());
				List<FinishedWarehousIOEntity> finishedWarehousIOEntities = query.list();
				result.addAll(finishedWarehousIOEntities);
			}
			return JSONObject.toJSON(result);
		}
		return null;
	}


	/**
	 * 添加销售出库单
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SalesReleaseOrderEntity salesReleaseOrder,SalesReleaseOrderPage salesReleaseOrderPage, HttpServletRequest request) {
		String message = null;
		List<SalesReleaseNodeEntity> salesReleaseNodeList =  salesReleaseOrderPage.getSalesReleaseNodeList();
		List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList =  salesReleaseOrderPage.getSalesReleaseOrgNodeList();
		//修改成品入库状态为已配货
		if(!ListUtils.isNullOrEmpty(salesReleaseNodeList)){
			StringBuilder finishedCodes = new StringBuilder();
			for(SalesReleaseNodeEntity salesReleaseNode:salesReleaseNodeList){
				//记录成品编号
				finishedCodes.append(",'").append(salesReleaseNode.getFinishedSerino()).append("'");
			}
			String update = "update t_finished_warehous_io_list set io_type = 5 where FINISHED_SERINO in ("+finishedCodes.substring(1)+")";
			systemService.executeSql(update);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(salesReleaseOrder.getId())) {
			message = "更新成功";
			salesReleaseOrderService.updateMain(salesReleaseOrder, salesReleaseNodeList,salesReleaseOrgNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			salesReleaseOrderService.addMain(salesReleaseOrder, salesReleaseNodeList,salesReleaseOrgNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		//同步成品出库看板数据
		new Thread(new FinishedOutLookDataBatchRunnable(salesReleaseOrder.getReceiptCode(),systemService)).start();
		return j;
	}

	/**
	 * 销售出库单列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SalesReleaseOrderEntity salesReleaseOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(salesReleaseOrder.getId())) {
			salesReleaseOrder = salesReleaseOrderService.getEntity(SalesReleaseOrderEntity.class, salesReleaseOrder.getId());
			req.setAttribute("salesReleaseOrderPage", salesReleaseOrder);
		}
		return new ModelAndView("com/jeecg/invoices/salesReleaseOrder");
	}
	
	
	/**
	 * 加载明细列表[销售出库配料物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "salesReleaseNodeList")
	public ModelAndView salesReleaseNodeList(SalesReleaseOrderEntity salesReleaseOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = salesReleaseOrder.getId();
		//===================================================================================
		//查询-销售出库配料物料详情
	    String hql0 = "from SalesReleaseNodeEntity where 1 = 1 AND receiptId = ? ";
		try{
		    List<SalesReleaseNodeEntity> salesReleaseNodeEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("salesReleaseNodeList", salesReleaseNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/invoices/salesReleaseNodeList");
	}
	/**
	 * 加载明细列表[销售出库原始物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "salesReleaseOrgNodeList")
	public ModelAndView salesReleaseOrgNodeList(SalesReleaseOrderEntity salesReleaseOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = salesReleaseOrder.getId();
		//===================================================================================
		//查询-销售出库原始物料详情
	    String hql1 = "from SalesReleaseOrgNodeEntity where 1 = 1 AND receiptId = ? ";
		try{
		    List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("salesReleaseOrgNodeList", salesReleaseOrgNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/invoices/salesReleaseOrgNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(SalesReleaseOrderEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<SalesReleaseOrderEntity> listSalesReleaseOrders = this.salesReleaseOrderService.getListByCriteriaQuery(query,true);
		return Result.success(listSalesReleaseOrders);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SalesReleaseOrderEntity task = salesReleaseOrderService.get(SalesReleaseOrderEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SalesReleaseOrderEntity salesReleaseOrder, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SalesReleaseOrderEntity>> failures = validator.validate(salesReleaseOrder);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		salesReleaseOrderService.save(salesReleaseOrder);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = salesReleaseOrder.getId();
		URI uri = uriBuilder.path("/rest/salesReleaseOrderController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SalesReleaseOrderEntity salesReleaseOrder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SalesReleaseOrderEntity>> failures = validator.validate(salesReleaseOrder);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		salesReleaseOrderService.saveOrUpdate(salesReleaseOrder);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		salesReleaseOrderService.deleteEntityById(SalesReleaseOrderEntity.class, id);
	}
}
