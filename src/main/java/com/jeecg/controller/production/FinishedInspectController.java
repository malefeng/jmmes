package com.jeecg.controller.production;

import com.jeecg.entity.basic.FinishedProductEntity;
import com.jeecg.entity.production.*;
import com.jeecg.page.production.FinishedInspectPage;
import com.jeecg.service.basic.FinishedProductServiceI;
import com.jeecg.service.production.FinishedInspectServiceI;
import com.jeecg.util.MathUtil;
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
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**   
 * @Title: Controller
 * @Description: 成品首末检
 * @author zhangdaihao
 * @date 2019-10-11 05:01:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedInspectController")
public class FinishedInspectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedInspectController.class);

	@Autowired
	private FinishedInspectServiceI finishedInspectService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private FinishedProductServiceI finishedProductServiceI;
	
	
	/**
	 * 成品首末检列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/production/finishedInspectList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FinishedInspectEntity finishedInspect,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedInspectEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedInspect,request.getParameterMap());
		this.finishedInspectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除成品首末检
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedInspectEntity finishedInspect, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedInspect = systemService.getEntity(FinishedInspectEntity.class, finishedInspect.getId());
		allEntitys.add(finishedInspect);
		List<FinishedFirstInspectEntity> finishedFirstInspectList =  systemService.findByProperty(FinishedFirstInspectEntity.class,"finishedCode",finishedInspect.getFinishedCode());
		List<FinishedLastInspectEntity> finishedLastInspectList =  systemService.findByProperty(FinishedLastInspectEntity.class,"finishedCode",finishedInspect.getFinishedCode());
		if(!ListUtils.isNullOrEmpty(finishedFirstInspectList)){
			allEntitys.addAll(finishedFirstInspectList);
		}
		if(!ListUtils.isNullOrEmpty(finishedLastInspectList)){
			allEntitys.addAll(finishedLastInspectList);
		}
		message = "删除成功";
		finishedInspectService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加成品首末检
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedInspectEntity finishedInspect,FinishedInspectPage finishedInspectPage, HttpServletRequest request) {
		String message = null;
		List<FinishedFirstInspectEntity> finishedFirstInspectList =  finishedInspectPage.getFinishedFirstInspectList();
		List<FinishedLastInspectEntity> finishedLastInspectList =  finishedInspectPage.getFinishedLastInspectList();
		if(finishedLastInspectList!=null&&finishedLastInspectList.size()>0){
			Date date = null;
			FinishedLastInspectEntity lastInspectEntity = new FinishedLastInspectEntity();
			for (FinishedLastInspectEntity finishedLastInspectEntity : finishedLastInspectList) {
				if(date==null||date.compareTo(finishedLastInspectEntity.getLastInspectDate())>0){
					lastInspectEntity = finishedLastInspectEntity;
				}
			}
			finishedInspect.setResult((lastInspectEntity.getLastInspectResult()!=null&&lastInspectEntity.getLastInspectResult()==1)?"OK":"NG");
			finishedInspect.setCount(MathUtil.toInt(lastInspectEntity.getCount()));
			finishedInspect.setQualifiedCount(MathUtil.toInt(lastInspectEntity.getQualifiedCount()));
			finishedInspect.setUnQualifiedCount(MathUtil.toInt(lastInspectEntity.getUnqualifiedCount()));
		}
		//获取批次号
		if(StringUtil.isNotEmpty(finishedInspect.getFinishedCode())){
			List<FinishedProductionEntity> finishedProductionEntityList = systemService.findByProperty(FinishedProductionEntity.class, "finishedSerino", finishedInspect.getFinishedCode());
			if(finishedProductionEntityList!=null&&finishedProductionEntityList.size()>0){
				finishedInspect.setBatchNo(finishedProductionEntityList.get(0).getFinishedBatch());
			}
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedInspect.getId())) {
			message = "更新成功";
			finishedInspectService.updateMain(finishedInspect, finishedFirstInspectList,finishedLastInspectList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			finishedInspectService.addMain(finishedInspect, finishedFirstInspectList,finishedLastInspectList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 成品首末检列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedInspectEntity finishedInspect, HttpServletRequest req,HttpServletResponse response) throws IOException {
		FinishedInspectEntity newfinishedInspect = null;
		/*//检验成品是否已投产
		List<FinishedProductionEntity> finishedProductionEntities = finishedInspectService.findByProperty(FinishedProductionEntity.class, "finishedSerino", finishedInspect.getFinishedCode());
		if(ListUtils.isNullOrEmpty(finishedProductionEntities)){
			req.setAttribute("mdg", "该成品尚未投产");
		}else{*/
			//查询成品首末检信息
			if (StringUtil.isNotEmpty(finishedInspect.getId())) {
				newfinishedInspect = finishedInspectService.getEntity(FinishedInspectEntity.class, finishedInspect.getId());
			}else if(StringUtil.isNotEmpty(finishedInspect.getFinishedCode())){
				try {
					newfinishedInspect = finishedInspectService.findUniqueByProperty(FinishedInspectEntity.class,"finishedCode",finishedInspect.getFinishedCode());
				} catch (Exception e) {
					response.setStatus(500);
					response.sendError(500,"成品首末检，成品代码："+finishedInspect.getFinishedCode()+"存在重复记录");
					e.printStackTrace();
				}
			}
			newfinishedInspect = newfinishedInspect == null?finishedInspect:newfinishedInspect;
			if(newfinishedInspect!=null&&StringUtil.isEmpty(newfinishedInspect.getInspectLogSheet())){
				FinishedProductEntity finishedProductEntity = finishedProductServiceI.getByFinishedSerino(finishedInspect.getFinishedCode());
				if(finishedProductEntity!=null){
					//首末检模板
					newfinishedInspect.setInspectLogSheet(finishedProductEntity.getFfCheckTemp());
				}
			}
			req.setAttribute("finishedInspectMainPage", newfinishedInspect);
		/*}*/
		return new ModelAndView("com/jeecg/production/finishedInspect");
	}
	
	
	/**
	 * 加载明细列表[成品首检]
	 * 
	 * @return
	 */
	@RequestMapping(params = "finishedFirstInspectList")
	public ModelAndView finishedFirstInspectList(FinishedInspectEntity finishedInspect, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspect.getFinishedCode();
		//===================================================================================
		//查询-成品首检
	    String hql0 = "from FinishedFirstInspectEntity where 1 = 1 AND finishedCode = ? ";
		try{
		    List<FinishedFirstInspectEntity> finishedFirstInspectEntityList = systemService.findHql(hql0,finishedCode0);
			req.setAttribute("finishedFirstInspectList", finishedFirstInspectEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/finishedFirstInspectList");
	}
	/**
	 * 加载明细列表[成品末检]
	 * 
	 * @return
	 */
	@RequestMapping(params = "finishedLastInspectList")
	public ModelAndView finishedLastInspectList(FinishedInspectEntity finishedInspect, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object finishedCode1 = finishedInspect.getFinishedCode();
		//===================================================================================
		//查询-成品末检
	    String hql1 = "from FinishedLastInspectEntity where 1 = 1 AND finishedCode = ? ";
		try{
		    List<FinishedLastInspectEntity> finishedLastInspectEntityList = systemService.findHql(hql1,finishedCode1);
			req.setAttribute("finishedLastInspectList", finishedLastInspectEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/production/finishedLastInspectList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FinishedInspectEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FinishedInspectEntity> listFinishedInspects = this.finishedInspectService.getListByCriteriaQuery(query,true);
		return Result.success(listFinishedInspects);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedInspectEntity task = finishedInspectService.get(FinishedInspectEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedInspectEntity finishedInspect, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedInspectEntity>> failures = validator.validate(finishedInspect);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedInspectService.save(finishedInspect);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedInspect.getId();
		URI uri = uriBuilder.path("/rest/finishedInspectController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedInspectEntity finishedInspect) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedInspectEntity>> failures = validator.validate(finishedInspect);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedInspectService.saveOrUpdate(finishedInspect);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedInspectService.deleteEntityById(FinishedInspectEntity.class, id);
	}
}
