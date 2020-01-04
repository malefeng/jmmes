package com.jeecg.controller.production;

import com.jeecg.api.production.SemiFinishedProductionDTO;
import com.jeecg.entity.production.SemiFinishedProductionEntity;
import com.jeecg.entity.production.SemiFinishedProductionNodeEntity;
import com.jeecg.page.production.SemiFinishedProductionPage;
import com.jeecg.service.production.SemiFinishedProductionServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**   
 * @Title: Controller
 * @Description: 半成品生产列表
 * @author zhangdaihao
 * @date 2019-10-11 05:26:31
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/semiFinishedProductionController")
public class SemiFinishedProductionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SemiFinishedProductionController.class);

	@Autowired
	private SemiFinishedProductionServiceI semiFinishedProductionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 半成品生产列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","unitDic");
		return new ModelAndView("com/jeecg/production/semiFinishedProductionList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{semiFinishedSerino}")
	@ResponseBody
	public List<SemiFinishedProductionEntity> apiList(@PathVariable("semiFinishedSerino") String semiFinishedSerino){
		return semiFinishedProductionService.findByProperty(SemiFinishedProductionEntity.class,"semiFinishedSerino",semiFinishedSerino);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(SemiFinishedProductionEntity semiFinishedProduction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SemiFinishedProductionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, semiFinishedProduction,request.getParameterMap());
		this.semiFinishedProductionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除半成品生产列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SemiFinishedProductionEntity semiFinishedProduction, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		semiFinishedProduction = systemService.getEntity(SemiFinishedProductionEntity.class, semiFinishedProduction.getId());
		allEntitys.add(semiFinishedProduction);
		List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList =  systemService.findByProperty(SemiFinishedProductionNodeEntity.class,"semiFinishedSerino",semiFinishedProduction.getSemiFinishedSerino());
		if(!ListUtils.isNullOrEmpty(semiFinishedProductionNodeList)){
			allEntitys.addAll(semiFinishedProductionNodeList);
		}
		message = "删除成功";
		semiFinishedProductionService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加半成品生产列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SemiFinishedProductionEntity semiFinishedProduction,SemiFinishedProductionPage semiFinishedProductionPage, HttpServletRequest request) {
		String message = null;
		List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList =  semiFinishedProductionPage.getSemiFinishedProductionNodeList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(semiFinishedProduction.getId())) {
			message = "更新成功";
			semiFinishedProductionService.updateMain(semiFinishedProduction, semiFinishedProductionNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			semiFinishedProductionService.addMain(semiFinishedProduction, semiFinishedProductionNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody SemiFinishedProductionDTO semiFinishedProductionDTO){
		SemiFinishedProductionEntity semiFinishedProduction = semiFinishedProductionDTO.getSemiFinishedProduction();
		List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList = semiFinishedProductionDTO.getSemiFinishedProductionNodeList();
		if(StringUtil.isNotEmpty(semiFinishedProduction.getId())){
			semiFinishedProductionService.updateMain(semiFinishedProduction, semiFinishedProductionNodeList);
		}else{
			semiFinishedProductionService.addMain(semiFinishedProduction, semiFinishedProductionNodeList);
		}
	}

	/**
	 * 半成品生产列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SemiFinishedProductionEntity semiFinishedProduction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(semiFinishedProduction.getId())) {
			semiFinishedProduction = semiFinishedProductionService.getEntity(SemiFinishedProductionEntity.class, semiFinishedProduction.getId());
			req.setAttribute("semiFinishedProductionPage", semiFinishedProduction);
		}
		return new ModelAndView("com/jeecg/production/semiFinishedProduction");
	}
	
	
	/**
	 * 加载明细列表[半成品生产物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "semiFinishedProductionNodeList")
	public ModelAndView semiFinishedProductionNodeList(SemiFinishedProductionEntity semiFinishedProduction, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object semiFinishedSerino0 = semiFinishedProduction.getSemiFinishedSerino();
		//===================================================================================
		//查询-半成品生产物料详情
	    String hql0 = "from SemiFinishedProductionNodeEntity where 1 = 1 AND semiFinishedSerino = ? ";
		try{
		    List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeEntityList = systemService.findHql(hql0,semiFinishedSerino0);
			req.setAttribute("semiFinishedProductionNodeList", semiFinishedProductionNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/semiFinishedProductionNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(SemiFinishedProductionEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<SemiFinishedProductionEntity> listSemiFinishedProductions = this.semiFinishedProductionService.getListByCriteriaQuery(query,true);
		return Result.success(listSemiFinishedProductions);
	}
	
	@RequestMapping(params = "get", method = RequestMethod.GET)
	@ResponseBody
	public SemiFinishedProductionEntity get(String semiFinishedSerino) {
		return semiFinishedProductionService.findUniqueByProperty(SemiFinishedProductionEntity.class,"semiFinishedSerino",semiFinishedSerino);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SemiFinishedProductionEntity semiFinishedProduction, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedProductionEntity>> failures = validator.validate(semiFinishedProduction);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedProductionService.save(semiFinishedProduction);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = semiFinishedProduction.getId();
		URI uri = uriBuilder.path("/rest/semiFinishedProductionController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SemiFinishedProductionEntity semiFinishedProduction) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedProductionEntity>> failures = validator.validate(semiFinishedProduction);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedProductionService.saveOrUpdate(semiFinishedProduction);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		semiFinishedProductionService.deleteEntityById(SemiFinishedProductionEntity.class, id);
	}
}
