package com.jeecg.controller.production;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.entity.production.*;
import com.jeecg.util.DictionaryUtil;
import com.jeecg.util.MathUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.jeecg.page.production.FinishedInspectItemPage;
import com.jeecg.service.production.FinishedInspectItemServiceI;

/**
 * @Title: Controller
 * @Description: 成品检测
 * @author zhangdaihao
 * @date 2019-12-25 23:35:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedInspectItemController")
public class FinishedInspectItemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedInspectItemController.class);

	@Autowired
	private FinishedInspectItemServiceI finishedInspectItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 成品检测列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {

		dictionaryUtil.writeDicList(request,"checkState");
		return new ModelAndView("com/jeecg/production/finishedInspectItemList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedInspectItemEntity finishedInspectItem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedInspectItemEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedInspectItem);
		this.finishedInspectItemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品检测
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedInspectItemEntity finishedInspectItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedInspectItem = systemService.getEntity(FinishedInspectItemEntity.class, finishedInspectItem.getId());
		message = "删除成功";
		finishedInspectItemService.delete(finishedInspectItem);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品检测
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedInspectItemEntity finishedInspectItem,FinishedInspectItemPage finishedInspectItemPage, HttpServletRequest request) {
		String message = null;
		List<FinishedInspectItemNodeEntity> finishedInspectItemNodeList =  finishedInspectItemPage.getFinishedInspectItemNodeList();

		if(finishedInspectItemNodeList!=null&&finishedInspectItemNodeList.size()>0){
			Date date = null;
			FinishedInspectItemNodeEntity lastInspectEntity = new FinishedInspectItemNodeEntity();
			for (FinishedInspectItemNodeEntity finishedInspectItemNodeEntity : finishedInspectItemNodeList) {
				if(date==null||date.compareTo(finishedInspectItemNodeEntity.getInspectDate())>0){
					lastInspectEntity = finishedInspectItemNodeEntity;
				}
			}
			finishedInspectItem.setResult(lastInspectEntity.getInspectResult());
			finishedInspectItem.setCount(MathUtil.toInt(lastInspectEntity.getCount()));
			finishedInspectItem.setQualifiedCount(MathUtil.toInt(lastInspectEntity.getQualifiedCount()));
			finishedInspectItem.setUnqualifiedCount(MathUtil.toInt(lastInspectEntity.getUnqualifiedCount()));
		}
		//获取批次号
		if(StringUtil.isNotEmpty(finishedInspectItem.getFinishedCode())){
			List<FinishedProductionEntity> finishedProductionEntityList = systemService.findByProperty(FinishedProductionEntity.class, "finishedSerino", finishedInspectItem.getFinishedCode());
			if(finishedProductionEntityList!=null&&finishedProductionEntityList.size()>0){
				finishedInspectItem.setBatchNo(finishedProductionEntityList.get(0).getFinishedBatch());
			}
		}
		AjaxJson j = new AjaxJson();
		if(finishedInspectItemNodeList!=null&&finishedInspectItemNodeList.size()>0){
			//标记为已检验
			finishedInspectItem.setStatus("2");
		}
		if (StringUtil.isNotEmpty(finishedInspectItem.getId())) {
			message = "更新成功";
			finishedInspectItemService.updateMain(finishedInspectItem, finishedInspectItemNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			finishedInspectItemService.addMain(finishedInspectItem, finishedInspectItemNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 成品检测列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedInspectItemEntity finishedInspectItem, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedInspectItem.getId())) {
			finishedInspectItem = finishedInspectItemService.getEntity(FinishedInspectItemEntity.class, finishedInspectItem.getId());
			req.setAttribute("finishedInspectItemPage", finishedInspectItem);
		}
		return new ModelAndView("com/jeecg/production/finishedInspectItem");
	}
	
	
	/**
	 * 加载明细列表[成品检验明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "finishedInspectItemNodeList")
	public ModelAndView finishedInspectItemNodeList(FinishedInspectItemEntity finishedInspectItem, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspectItem.getFinishedCode();
		//===================================================================================
		//查询-成品检验明细
	    String hql0 = "from FinishedInspectItemNodeEntity where 1 = 1 AND finishedCode = ? ";
		try{
		    List<FinishedInspectItemNodeEntity> finishedInspectItemNodeEntityList = systemService.findHql(hql0,finishedCode0);
			req.setAttribute("finishedInspectItemNodeList", finishedInspectItemNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/finishedInspectItemNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedInspectItemEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedInspectItemEntity> listFinishedInspectItems = this.finishedInspectItemService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedInspectItems);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedInspectItemEntity task = finishedInspectItemService.get(FinishedInspectItemEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedInspectItemEntity finishedInspectItem, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedInspectItemEntity>> failures = validator.validate(finishedInspectItem);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedInspectItemService.save(finishedInspectItem);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedInspectItem.getId();
		URI uri = uriBuilder.path("/rest/finishedInspectItemController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedInspectItemEntity finishedInspectItem) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedInspectItemEntity>> failures = validator.validate(finishedInspectItem);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedInspectItemService.saveOrUpdate(finishedInspectItem);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedInspectItemService.deleteEntityById(FinishedInspectItemEntity.class, id);
	}
}
