package com.jeecg.controller.production;

import com.jeecg.api.production.FinishedProductionDTO;
import com.jeecg.entity.production.FinishedInspectItemEntity;
import com.jeecg.entity.production.FinishedProductionEntity;
import com.jeecg.entity.production.FinishedProductionNodeEntity;
import com.jeecg.page.production.FinishedProductionPage;
import com.jeecg.service.production.FinishedProductionServiceI;
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
 * @Description: 成品生产列表
 * @author zhangdaihao
 * @date 2019-10-11 03:37:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedProductionController")
public class FinishedProductionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedProductionController.class);

	@Autowired
	private FinishedProductionServiceI finishedProductionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 成品生产列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","unitDic");
		return new ModelAndView("com/jeecg/production/finishedProductionList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{finishedSerino}")
	@ResponseBody
	public List<FinishedProductionEntity> apiList(@PathVariable("finishedSerino") String finishedSerino){
		return finishedProductionService.findByProperty(FinishedProductionEntity.class,"finishedSerino",finishedSerino);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedProductionEntity finishedProduction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedProductionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedProduction,request.getParameterMap());
		this.finishedProductionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品生产列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedProductionEntity finishedProduction, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedProduction = systemService.getEntity(FinishedProductionEntity.class, finishedProduction.getId());
		allEntitys.add(finishedProduction);
		List<FinishedProductionNodeEntity> finishedProductionNodeList =  systemService.findByProperty(FinishedProductionNodeEntity.class,"finishedSerino",finishedProduction.getFinishedSerino());
		if(!ListUtils.isNullOrEmpty(finishedProductionNodeList)){
			allEntitys.addAll(finishedProductionNodeList);
		}
		message = "删除成功";
		finishedProductionService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品生产列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedProductionEntity finishedProduction,FinishedProductionPage finishedProductionPage, HttpServletRequest request) {
		String message = null;
		List<FinishedProductionNodeEntity> finishedProductionNodeList =  finishedProductionPage.getFinishedProductionNodeList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedProduction.getId())) {
			message = "更新成功";
			finishedProductionService.updateMain(finishedProduction, finishedProductionNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			finishedProductionService.addMain(finishedProduction, finishedProductionNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			//如果不需要熟成，则生成成品检验数据
			if(!"1".equals(finishedProduction.getNeedRipening())){
				FinishedInspectItemEntity finishedInspectItemEntity = new FinishedInspectItemEntity();
				finishedInspectItemEntity.setFinishedCode(finishedProduction.getFinishedCode());
				finishedInspectItemEntity.setFinishedName(finishedProduction.getFinishedName());
				finishedInspectItemEntity.setProductionDispatchingNumber(finishedProduction.getProductionOrderNumber());
				finishedInspectItemEntity.setSalesOrderNumber(finishedProduction.getFinishedSerino());
				finishedInspectItemEntity.setStatus("1");
				systemService.save(finishedInspectItemEntity);
			}
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody FinishedProductionDTO finishedProductionDTO){
		FinishedProductionEntity finishedProduction = finishedProductionDTO.getFinishedProduction();
		List<FinishedProductionNodeEntity> finishedProductionNodeList = finishedProductionDTO.getFinishedProductionNodeList();
		if(StringUtil.isNotEmpty(finishedProduction.getId())){
			finishedProductionService.updateMain(finishedProduction, finishedProductionNodeList);
		}else{
			finishedProductionService.addMain(finishedProduction, finishedProductionNodeList);
		}
	}

	/**
	 * 成品生产列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedProductionEntity finishedProduction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedProduction.getId())) {
			finishedProduction = finishedProductionService.getEntity(FinishedProductionEntity.class, finishedProduction.getId());
			req.setAttribute("finishedProductionPage", finishedProduction);
		}
		return new ModelAndView("com/jeecg/production/finishedProduction");
	}
	
	
	/**
	 * 加载明细列表[成品生产物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "finishedProductionNodeList")
	public ModelAndView finishedProductionNodeList(FinishedProductionEntity finishedProduction, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object finishedSerino0 = finishedProduction.getFinishedSerino();
		//===================================================================================
		//查询-成品生产物料详情
	    String hql0 = "from FinishedProductionNodeEntity where 1 = 1 AND finishedSerino = ? ";
		try{
		    List<FinishedProductionNodeEntity> finishedProductionNodeEntityList = systemService.findHql(hql0,finishedSerino0);
			req.setAttribute("finishedProductionNodeList", finishedProductionNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/finishedProductionNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedProductionEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedProductionEntity> listFinishedProductions = this.finishedProductionService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedProductions);
	}
	
	@RequestMapping(params = "get", method = RequestMethod.GET)
	@ResponseBody
	public FinishedProductionEntity get(String finishedSerino) {
		return finishedProductionService.findUniqueByProperty(FinishedProductionEntity.class,"finishedSerino",finishedSerino);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedProductionEntity finishedProduction, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedProductionEntity>> failures = validator.validate(finishedProduction);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedProductionService.save(finishedProduction);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedProduction.getId();
		URI uri = uriBuilder.path("/rest/finishedProductionController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedProductionEntity finishedProduction) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedProductionEntity>> failures = validator.validate(finishedProduction);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedProductionService.saveOrUpdate(finishedProduction);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedProductionService.deleteEntityById(FinishedProductionEntity.class, id);
	}
}
