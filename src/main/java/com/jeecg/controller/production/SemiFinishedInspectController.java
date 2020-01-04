package com.jeecg.controller.production;

import com.jeecg.entity.basic.SemiFinishedProductEntity;
import com.jeecg.entity.production.*;
import com.jeecg.page.production.SemiFinishedInspectPage;
import com.jeecg.service.basic.SemiFinishedProductServiceI;
import com.jeecg.service.production.SemiFinishedInspectServiceI;
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
import java.util.Date;
import java.util.List;
import java.util.Set;
/**   
 * @Title: Controller
 * @Description: 半成品首末检
 * @author zhangdaihao
 * @date 2019-10-11 04:48:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/semiFinishedInspectController")
public class SemiFinishedInspectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SemiFinishedInspectController.class);

	@Autowired
	private SemiFinishedInspectServiceI semiFinishedInspectService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private SemiFinishedProductServiceI semiFinishedProductServiceI;
	
	
	/**
	 * 半成品首末检列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/production/semiFinishedInspectList");
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
	public void datagrid(SemiFinishedInspectEntity semiFinishedInspect,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SemiFinishedInspectEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, semiFinishedInspect,request.getParameterMap());
		this.semiFinishedInspectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除半成品首末检
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SemiFinishedInspectEntity semiFinishedInspect, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		semiFinishedInspect = systemService.getEntity(SemiFinishedInspectEntity.class, semiFinishedInspect.getId());
		allEntitys.add(semiFinishedInspect);
		List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList =  systemService.findByProperty(SemiFinishedFirstInspectEntity.class,"semiFinishedCode",semiFinishedInspect.getSemiFinishedCode());
		List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList =  systemService.findByProperty(SemiFinishedLastInspectEntity.class,"semiFinishedCode",semiFinishedInspect.getSemiFinishedCode());
		if(!ListUtils.isNullOrEmpty(semiFinishedFirstInspectList)){
			allEntitys.addAll(semiFinishedFirstInspectList);
		}
		if(!ListUtils.isNullOrEmpty(semiFinishedLastInspectList)){
			allEntitys.addAll(semiFinishedLastInspectList);
		}
		message = "删除成功";
		semiFinishedInspectService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加半成品首末检
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SemiFinishedInspectEntity semiFinishedInspect,SemiFinishedInspectPage semiFinishedInspectPage, HttpServletRequest request) {
		String message = null;
		List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList =  semiFinishedInspectPage.getSemiFinishedFirstInspectList();
		List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList =  semiFinishedInspectPage.getSemiFinishedLastInspectList();
		if(semiFinishedLastInspectList!=null&&semiFinishedLastInspectList.size()>0) {
			Date date = null;
			SemiFinishedLastInspectEntity lastInspectEntity = new SemiFinishedLastInspectEntity();
			for (SemiFinishedLastInspectEntity semiFinishedLastInspect : semiFinishedLastInspectList) {
				if (date == null || date.compareTo(semiFinishedLastInspect.getLastInspectDate()) > 0) {
					lastInspectEntity = semiFinishedLastInspect;
				}
			}
			semiFinishedInspect.setResult(lastInspectEntity.getLastInspectResult() == 1 ? "OK" : "NG");
			semiFinishedInspect.setCount(lastInspectEntity.getCount());
			semiFinishedInspect.setQualifiedCount(lastInspectEntity.getQualifiedCount());
			semiFinishedInspect.setUnQualifiedCount(lastInspectEntity.getUnqualifiedCount());
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(semiFinishedInspect.getId())) {
			message = "更新成功";
			semiFinishedInspectService.updateMain(semiFinishedInspect, semiFinishedFirstInspectList,semiFinishedLastInspectList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			semiFinishedInspectService.addMain(semiFinishedInspect, semiFinishedFirstInspectList,semiFinishedLastInspectList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 半成品首末检列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SemiFinishedInspectEntity semiFinishedInspect, HttpServletRequest req) {
		SemiFinishedInspectEntity newSemiFinishedInspect = null;
		/*//检验半成品是否生产
		List<SemiFinishedProductionEntity> semiFinishedProductionEntities = semiFinishedInspectService.findByProperty(SemiFinishedProductionEntity.class, "semiFinishedSerino", semiFinishedInspect.getSemiFinishedCode());
		if(ListUtils.isNullOrEmpty(semiFinishedProductionEntities)){
			req.setAttribute("msg", "该半成品尚未投入生产");
		}else{*/
			//查询首末检信息
			if (StringUtil.isNotEmpty(semiFinishedInspect.getId())) {
				newSemiFinishedInspect = semiFinishedInspectService.getEntity(SemiFinishedInspectEntity.class, semiFinishedInspect.getId());
			}else if(StringUtil.isNotEmpty(semiFinishedInspect.getSemiFinishedCode())){
				newSemiFinishedInspect = semiFinishedInspectService.findUniqueByProperty(SemiFinishedInspectEntity.class,"semiFinishedCode", semiFinishedInspect.getSemiFinishedCode());
			}
			newSemiFinishedInspect = newSemiFinishedInspect==null?semiFinishedInspect:newSemiFinishedInspect;
			if(newSemiFinishedInspect!=null&&StringUtil.isEmpty(newSemiFinishedInspect.getInspectLogSheet())){
				SemiFinishedProductEntity semiFinishedProductEntity = semiFinishedProductServiceI.getBySemiFinishedSerino(newSemiFinishedInspect.getSemiFinishedCode());
				if(semiFinishedProductEntity!=null){
					//半成品首末检模板
					newSemiFinishedInspect.setInspectLogSheet(semiFinishedProductEntity.getFfCheckTemp());
				}
			}
			req.setAttribute("semiFinishedInspectMainPage", newSemiFinishedInspect);
		/*}*/
		return new ModelAndView("com/jeecg/production/semiFinishedInspect");
	}
	
	
	/**
	 * 加载明细列表[半成品首检]
	 * 
	 * @return
	 */
	@RequestMapping(params = "semiFinishedFirstInspectList")
	public ModelAndView semiFinishedFirstInspectList(SemiFinishedInspectEntity semiFinishedInspect, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object semiFinishedCode0 = semiFinishedInspect.getSemiFinishedCode();
		//===================================================================================
		//查询-半成品首检
	    String hql0 = "from SemiFinishedFirstInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
		try{
		    List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectEntityList = systemService.findHql(hql0,semiFinishedCode0);
			req.setAttribute("semiFinishedFirstInspectList", semiFinishedFirstInspectEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/semiFinishedFirstInspectList");
	}
	/**
	 * 加载明细列表[半成品末检]
	 * 
	 * @return
	 */
	@RequestMapping(params = "semiFinishedLastInspectList")
	public ModelAndView semiFinishedLastInspectList(SemiFinishedInspectEntity semiFinishedInspect, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object semiFinishedCode1 = semiFinishedInspect.getSemiFinishedCode();
		//===================================================================================
		//查询-半成品末检
	    String hql1 = "from SemiFinishedLastInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
		try{
		    List<SemiFinishedLastInspectEntity> semiFinishedLastInspectEntityList = systemService.findHql(hql1,semiFinishedCode1);
			req.setAttribute("semiFinishedLastInspectList", semiFinishedLastInspectEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/semiFinishedLastInspectList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(SemiFinishedInspectEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<SemiFinishedInspectEntity> listSemiFinishedInspects = this.semiFinishedInspectService.getListByCriteriaQuery(query,true);
		return Result.success(listSemiFinishedInspects);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SemiFinishedInspectEntity task = semiFinishedInspectService.get(SemiFinishedInspectEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SemiFinishedInspectEntity semiFinishedInspect, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedInspectEntity>> failures = validator.validate(semiFinishedInspect);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedInspectService.save(semiFinishedInspect);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = semiFinishedInspect.getId();
		URI uri = uriBuilder.path("/rest/semiFinishedInspectController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SemiFinishedInspectEntity semiFinishedInspect) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SemiFinishedInspectEntity>> failures = validator.validate(semiFinishedInspect);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		semiFinishedInspectService.saveOrUpdate(semiFinishedInspect);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		semiFinishedInspectService.deleteEntityById(SemiFinishedInspectEntity.class, id);
	}
}
