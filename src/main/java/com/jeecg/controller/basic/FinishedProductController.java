package com.jeecg.controller.basic;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.entity.basic.FinishedProductEntity;
import com.jeecg.service.basic.FinishedProductServiceI;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 成品信息
 * @author zhangdaihao
 * @date 2019-10-04 18:31:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedProductController")
public class FinishedProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedProductController.class);

	@Autowired
	private FinishedProductServiceI finishedProductService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 成品信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic","matTypeDic");
		return new ModelAndView("com/jeecg/basic/finishedProductList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{finishedCode}")
	@ResponseBody
	public List<FinishedProductEntity> apiList(@PathVariable("finishedCode") String finishedCode){
		return finishedProductService.findByProperty(FinishedProductEntity.class,"finishedCode",finishedCode);
	}

	/**
	 *
	 * @param finishedCode
	 * @param finishedName
	 * @return
	 */
	@RequestMapping(params = "getByParam")
	@ResponseBody
	public Object getByParam(String finishedCode, String finishedName){
		StringBuilder hql = new StringBuilder("from FinishedProductEntity where 1=1");
		List param = new ArrayList();
		if(StringUtil.isNotEmpty(finishedCode)){
			hql.append(" and finishedCode = ?");
			param.add(finishedCode);
		}
		if(StringUtil.isNotEmpty(finishedName)){
			hql.append(" and finishedName = ?");
			param.add(finishedName);
		}
		List<Object> objectList = finishedProductService.findHql(hql.toString(), param.toArray());
		return JSONObject.toJSON(objectList);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedProductEntity finishedProduct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedProduct, request.getParameterMap());
		this.finishedProductService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedProductEntity finishedProduct, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedProduct = systemService.getEntity(FinishedProductEntity.class, finishedProduct.getId());
		message = "成品信息删除成功";
		finishedProductService.delete(finishedProduct);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedProductEntity finishedProduct, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedProduct.getId())) {
			message = "成品信息更新成功";
			FinishedProductEntity t = finishedProductService.get(FinishedProductEntity.class, finishedProduct.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(finishedProduct, t);
				finishedProductService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "成品信息更新失败";
			}
		} else {
			message = "成品信息添加成功";
			finishedProductService.save(finishedProduct);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 成品信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedProductEntity finishedProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedProduct.getId())) {
			finishedProduct = finishedProductService.getEntity(FinishedProductEntity.class, finishedProduct.getId());
			req.setAttribute("finishedProductPage", finishedProduct);
		}
		return new ModelAndView("com/jeecg/basic/finishedProduct");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedProductEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedProductEntity> listFinishedProducts = this.finishedProductService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedProducts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedProductEntity task = finishedProductService.get(FinishedProductEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedProductEntity finishedProduct, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedProductEntity>> failures = validator.validate(finishedProduct);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedProductService.save(finishedProduct);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedProduct.getId();
		URI uri = uriBuilder.path("/rest/finishedProductController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedProductEntity finishedProduct) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedProductEntity>> failures = validator.validate(finishedProduct);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedProductService.saveOrUpdate(finishedProduct);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedProductService.deleteEntityById(FinishedProductEntity.class, id);
	}
}
