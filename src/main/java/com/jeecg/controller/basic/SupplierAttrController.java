package com.jeecg.controller.basic;

import com.jeecg.entity.basic.SupplierAttrEntity;
import com.jeecg.service.basic.SupplierAttrServiceI;
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
 * @Description: 供应商属性
 * @author zhangdaihao
 * @date 2019-11-14 00:17:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/supplierAttrController")
public class SupplierAttrController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SupplierAttrController.class);

	@Autowired
	private SupplierAttrServiceI supplierAttrService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 供应商属性列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/basic/supplierAttrList");
	}


	@RequestMapping("/apiList")
	@ResponseBody
	public List<SupplierAttrEntity> apiList(){
		return this.supplierAttrService.getList(SupplierAttrEntity.class);
	}
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(SupplierAttrEntity supplierAttr,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SupplierAttrEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, supplierAttr, request.getParameterMap());
		this.supplierAttrService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除供应商属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SupplierAttrEntity supplierAttr, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		supplierAttr = systemService.getEntity(SupplierAttrEntity.class, supplierAttr.getId());
		message = "供应商属性删除成功";
		supplierAttrService.delete(supplierAttr);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加供应商属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SupplierAttrEntity supplierAttr, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(supplierAttr.getId())) {
			message = "供应商属性更新成功";
			SupplierAttrEntity t = supplierAttrService.get(SupplierAttrEntity.class, supplierAttr.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(supplierAttr, t);
				supplierAttrService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "供应商属性更新失败";
			}
		} else {
			message = "供应商属性添加成功";
			supplierAttrService.save(supplierAttr);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 供应商属性列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SupplierAttrEntity supplierAttr, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(supplierAttr.getId())) {
			supplierAttr = supplierAttrService.getEntity(SupplierAttrEntity.class, supplierAttr.getId());
			req.setAttribute("supplierAttrPage", supplierAttr);
		}
		return new ModelAndView("com/jeecg/basic/supplierAttr");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(SupplierAttrEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<SupplierAttrEntity> listSupplierAttrs = this.supplierAttrService.getListByCriteriaQuery(query,true);
		return Result.success(listSupplierAttrs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SupplierAttrEntity task = supplierAttrService.get(SupplierAttrEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SupplierAttrEntity supplierAttr, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SupplierAttrEntity>> failures = validator.validate(supplierAttr);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		supplierAttrService.save(supplierAttr);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = supplierAttr.getId();
		URI uri = uriBuilder.path("/rest/supplierAttrController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SupplierAttrEntity supplierAttr) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SupplierAttrEntity>> failures = validator.validate(supplierAttr);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		supplierAttrService.saveOrUpdate(supplierAttr);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		supplierAttrService.deleteEntityById(SupplierAttrEntity.class, id);
	}
}
