package com.jeecg.controller.basic;

import com.jeecg.entity.basic.SemiFinishedProductEntity;
import com.jeecg.service.basic.SemiFinishedProductServiceI;
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
 * @Description: 半成品信息
 * @author zhangdaihao
 * @date 2019-10-04 18:29:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/semiFinishedProductController")
public class SemiFinishedProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SemiFinishedProductController.class);

	@Autowired
	private SemiFinishedProductServiceI semiFinishedProductService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 半成品信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic","matTypeDic");
		return new ModelAndView("com/jeecg/basic/semiFinishedProductList");
	}

	/**
	 * api列表数据查询接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/apiList/{semiFinishedCode}")
	@ResponseBody
	public List<SemiFinishedProductEntity> apiList(@PathVariable("semiFinishedCode") String semiFinishedCode){
		return semiFinishedProductService.findByProperty(SemiFinishedProductEntity.class,"semiFinishedCode",semiFinishedCode);
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
	public void datagrid(SemiFinishedProductEntity semiFinishedProduct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SemiFinishedProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, semiFinishedProduct, request.getParameterMap());
		this.semiFinishedProductService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除半成品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SemiFinishedProductEntity semiFinishedProduct, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		semiFinishedProduct = systemService.getEntity(SemiFinishedProductEntity.class, semiFinishedProduct.getId());
		message = "半成品信息删除成功";
		semiFinishedProductService.delete(semiFinishedProduct);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加半成品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SemiFinishedProductEntity semiFinishedProduct, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(semiFinishedProduct.getId())) {
			message = "半成品信息更新成功";
			SemiFinishedProductEntity t = semiFinishedProductService.get(SemiFinishedProductEntity.class, semiFinishedProduct.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(semiFinishedProduct, t);
				semiFinishedProductService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "半成品信息更新失败";
			}
		} else {
			message = "半成品信息添加成功";
			semiFinishedProductService.save(semiFinishedProduct);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 半成品信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SemiFinishedProductEntity semiFinishedProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(semiFinishedProduct.getId())) {
			semiFinishedProduct = semiFinishedProductService.getEntity(SemiFinishedProductEntity.class, semiFinishedProduct.getId());
			req.setAttribute("semiFinishedProductPage", semiFinishedProduct);
		}
		return new ModelAndView("com/jeecg/basic/semiFinishedProduct");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(SemiFinishedProductEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<SemiFinishedProductEntity> listSemiFinishedProducts = this.semiFinishedProductService.getListByCriteriaQuery(query,true);
		return Result.success(listSemiFinishedProducts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SemiFinishedProductEntity task = semiFinishedProductService.get(SemiFinishedProductEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SemiFinishedProductEntity semiFinishedProduct, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedProductEntity>> failures = validator.validate(semiFinishedProduct);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedProductService.save(semiFinishedProduct);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = semiFinishedProduct.getId();
		URI uri = uriBuilder.path("/rest/semiFinishedProductController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SemiFinishedProductEntity semiFinishedProduct) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedProductEntity>> failures = validator.validate(semiFinishedProduct);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedProductService.saveOrUpdate(semiFinishedProduct);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		semiFinishedProductService.deleteEntityById(SemiFinishedProductEntity.class, id);
	}
}
