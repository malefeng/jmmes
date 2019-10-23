package com.jeecg.controller.warehous;

import com.jeecg.entity.warehous.FinishedWarehousReturnEntity;
import com.jeecg.service.warehous.FinishedWarehousReturnServiceI;
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
import java.net.URI;
import java.util.List;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 成品退货列表
 * @author zhangdaihao
 * @date 2019-10-11 05:43:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedWarehousReturnController")
public class FinishedWarehousReturnController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedWarehousReturnController.class);

	@Autowired
	private FinishedWarehousReturnServiceI finishedWarehousReturnService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 成品退货列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"custDic");
		return new ModelAndView("com/jeecg/warehous/finishedWarehousReturnList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedWarehousReturnEntity finishedWarehousReturn,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedWarehousReturnEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedWarehousReturn, request.getParameterMap());
		this.finishedWarehousReturnService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品退货列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedWarehousReturnEntity finishedWarehousReturn, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedWarehousReturn = systemService.getEntity(FinishedWarehousReturnEntity.class, finishedWarehousReturn.getId());
		message = "成品退货列表删除成功";
		finishedWarehousReturnService.delete(finishedWarehousReturn);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品退货列表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedWarehousReturnEntity finishedWarehousReturn, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedWarehousReturn.getId())) {
			message = "成品退货列表更新成功";
			FinishedWarehousReturnEntity t = finishedWarehousReturnService.get(FinishedWarehousReturnEntity.class, finishedWarehousReturn.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(finishedWarehousReturn, t);
				finishedWarehousReturnService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "成品退货列表更新失败";
			}
		} else {
			message = "成品退货列表添加成功";
			finishedWarehousReturnService.save(finishedWarehousReturn);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody FinishedWarehousReturnEntity finishedWarehousReturn){
		finishedWarehousReturnService.saveOrUpdate(finishedWarehousReturn);
	}

	/**
	 * 成品退货列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedWarehousReturnEntity finishedWarehousReturn, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedWarehousReturn.getId())) {
			finishedWarehousReturn = finishedWarehousReturnService.getEntity(FinishedWarehousReturnEntity.class, finishedWarehousReturn.getId());
			req.setAttribute("finishedWarehousReturnPage", finishedWarehousReturn);
		}
		return new ModelAndView("com/jeecg/warehous/finishedWarehousReturn");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage<List<FinishedWarehousReturnEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedWarehousReturnEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedWarehousReturnEntity> listFinishedWarehousReturns = this.finishedWarehousReturnService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedWarehousReturns);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedWarehousReturnEntity task = finishedWarehousReturnService.get(FinishedWarehousReturnEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedWarehousReturnEntity finishedWarehousReturn, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousReturnEntity>> failures = validator.validate(finishedWarehousReturn);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousReturnService.save(finishedWarehousReturn);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedWarehousReturn.getId();
		URI uri = uriBuilder.path("/rest/finishedWarehousReturnController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedWarehousReturnEntity finishedWarehousReturn) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousReturnEntity>> failures = validator.validate(finishedWarehousReturn);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousReturnService.saveOrUpdate(finishedWarehousReturn);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedWarehousReturnService.deleteEntityById(FinishedWarehousReturnEntity.class, id);
	}
}
