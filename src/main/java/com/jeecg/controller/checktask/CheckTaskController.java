package com.jeecg.controller.checktask;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.jeecg.entity.checktask.CheckTaskEntity;
import com.jeecg.page.checktask.CheckTaskPage;
import com.jeecg.service.checktask.CheckTaskServiceI;
import com.jeecg.entity.checktask.CheckNodeEntity;
/**   
 * @Title: Controller
 * @Description: 盘点任务
 * @author zhangdaihao
 * @date 2019-11-12 23:12:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/checkTaskController")
public class CheckTaskController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CheckTaskController.class);

	@Autowired
	private CheckTaskServiceI checkTaskService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	
	/**
	 * 盘点任务列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/checktask/checkTaskList");
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
	public void datagrid(CheckTaskEntity checkTask,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CheckTaskEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkTask);
		this.checkTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除盘点任务
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CheckTaskEntity checkTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		checkTask = systemService.getEntity(CheckTaskEntity.class, checkTask.getId());
		message = "删除成功";
		checkTaskService.delete(checkTask);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加盘点任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CheckTaskEntity checkTask,CheckTaskPage checkTaskPage, HttpServletRequest request) {
		String message = null;
		List<CheckNodeEntity> checkNodeList =  checkTaskPage.getCheckNodeList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(checkTask.getId())) {
			message = "更新成功";
			checkTaskService.updateMain(checkTask, checkNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			checkTaskService.addMain(checkTask, checkNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 盘点任务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CheckTaskEntity checkTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(checkTask.getId())) {
			checkTask = checkTaskService.getEntity(CheckTaskEntity.class, checkTask.getId());
			req.setAttribute("checkTaskPage", checkTask);
		}
		return new ModelAndView("com/jeecg/checktask/checkTask");
	}
	
	
	/**
	 * 加载明细列表[盘点明细表]
	 * 
	 * @return
	 */
	@RequestMapping(params = "checkNodeList")
	public ModelAndView checkNodeList(CheckTaskEntity checkTask, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object checkBatch0 = checkTask.getCheckBatch();
		//===================================================================================
		//查询-盘点明细表
	    String hql0 = "from CheckNodeEntity where 1 = 1 AND checkBatch = ? ";
		try{
		    List<CheckNodeEntity> checkNodeEntityList = systemService.findHql(hql0,checkBatch0);
			req.setAttribute("checkNodeList", checkNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/checktask/checkNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(CheckTaskEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<CheckTaskEntity> listCheckTasks = this.checkTaskService.getListByCriteriaQuery(query,true);
		return Result.success(listCheckTasks);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		CheckTaskEntity task = checkTaskService.get(CheckTaskEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CheckTaskEntity checkTask, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CheckTaskEntity>> failures = validator.validate(checkTask);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		checkTaskService.save(checkTask);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = checkTask.getId();
		URI uri = uriBuilder.path("/rest/checkTaskController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody CheckTaskEntity checkTask) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CheckTaskEntity>> failures = validator.validate(checkTask);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		checkTaskService.saveOrUpdate(checkTask);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		checkTaskService.deleteEntityById(CheckTaskEntity.class, id);
	}
}
