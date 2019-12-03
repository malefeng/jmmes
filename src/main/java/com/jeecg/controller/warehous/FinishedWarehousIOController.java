package com.jeecg.controller.warehous;

import com.jeecg.batch.FinishedOutLookDataBatchRunnable;
import com.jeecg.batch.ProductionLookDataBatchRunnable;
import com.jeecg.entity.warehous.FinishedWarehousIOEntity;
import com.jeecg.service.warehous.FinishedWarehousIOServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
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
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 成品出入库
 * @author zhangdaihao
 * @date 2019-10-11 06:30:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedWarehousIOController")
public class FinishedWarehousIOController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedWarehousIOController.class);

	@Autowired
	private FinishedWarehousIOServiceI finishedWarehousIOService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;



	/**
	 * 成品出入库列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"ioStateDic","repostDic","currencyDic");
		return new ModelAndView("com/jeecg/warehous/finishedWarehousIOList");
	}

	@RequestMapping(value = "/apiList/{finishedSerino}")
	@ResponseBody
	public FinishedWarehousIOEntity apiList(@PathVariable("finishedSerino")String finishedSerino, HttpServletResponse response) throws IOException {
		try{
			return finishedWarehousIOService.findUniqueByProperty(FinishedWarehousIOEntity.class,"finishedSerino",finishedSerino);
		}catch (Exception e){
			response.setStatus(500);
			response.sendError(500,"成品库存中，成品编号："+finishedSerino+"存在重复数据");
		}
		return null;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedWarehousIOEntity finishedWarehousIO,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedWarehousIOEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedWarehousIO, request.getParameterMap());
		this.finishedWarehousIOService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品出入库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedWarehousIOEntity finishedWarehousIO, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedWarehousIO = systemService.getEntity(FinishedWarehousIOEntity.class, finishedWarehousIO.getId());
		message = "成品出入库删除成功";
		finishedWarehousIOService.delete(finishedWarehousIO);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		//同步生产看板数据
		new Thread(new ProductionLookDataBatchRunnable(finishedWarehousIO.getProductionDispatchingNumber(),systemService)).start();
		//同步成品出库看板数据
		new Thread(new FinishedOutLookDataBatchRunnable(finishedWarehousIO.getSalesDeliveryOrderNumber(),systemService)).start();
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品出入库
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedWarehousIOEntity finishedWarehousIO, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedWarehousIO.getId())) {
			message = "成品出入库更新成功";
			FinishedWarehousIOEntity t = finishedWarehousIOService.get(FinishedWarehousIOEntity.class, finishedWarehousIO.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(finishedWarehousIO, t);
				finishedWarehousIOService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "成品出入库更新失败";
			}
		} else {
			message = "成品出入库添加成功";
			finishedWarehousIOService.save(finishedWarehousIO);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		if("1".equals(finishedWarehousIO.getIoType())){
			//同步生产看板数据
			new Thread(new ProductionLookDataBatchRunnable(finishedWarehousIO.getProductionDispatchingNumber(),systemService)).start();
		}else if("2".equals(finishedWarehousIO.getIoType())){
			//同步成品出库看板数据
			new Thread(new FinishedOutLookDataBatchRunnable(finishedWarehousIO.getSalesDeliveryOrderNumber(),systemService)).start();
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody FinishedWarehousIOEntity finishedWarehousIO){
		finishedWarehousIOService.saveOrUpdate(finishedWarehousIO);
		if("1".equals(finishedWarehousIO.getIoType())){
			//同步生产看板数据
			new Thread(new ProductionLookDataBatchRunnable(finishedWarehousIO.getProductionDispatchingNumber(),systemService)).start();
		}else if("2".equals(finishedWarehousIO.getIoType())){
			//同步成品出库看板数据
			new Thread(new FinishedOutLookDataBatchRunnable(finishedWarehousIO.getSalesDeliveryOrderNumber(),systemService)).start();
		}
	}

	/**
	 * 成品出入库列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedWarehousIOEntity finishedWarehousIO, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedWarehousIO.getId())) {
			finishedWarehousIO = finishedWarehousIOService.getEntity(FinishedWarehousIOEntity.class, finishedWarehousIO.getId());
			req.setAttribute("finishedWarehousIOPage", finishedWarehousIO);
		}
		return new ModelAndView("com/jeecg/warehous/finishedWarehousIO");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedWarehousIOEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedWarehousIOEntity> listFinishedWarehousIOs = this.finishedWarehousIOService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedWarehousIOs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedWarehousIOEntity task = finishedWarehousIOService.get(FinishedWarehousIOEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedWarehousIOEntity finishedWarehousIO, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousIOEntity>> failures = validator.validate(finishedWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousIOService.save(finishedWarehousIO);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedWarehousIO.getId();
		URI uri = uriBuilder.path("/rest/finishedWarehousIOController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedWarehousIOEntity finishedWarehousIO) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousIOEntity>> failures = validator.validate(finishedWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousIOService.saveOrUpdate(finishedWarehousIO);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedWarehousIOService.deleteEntityById(FinishedWarehousIOEntity.class, id);
	}
}
