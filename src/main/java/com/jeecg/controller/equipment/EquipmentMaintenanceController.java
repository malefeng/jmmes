package com.jeecg.controller.equipment;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.util.DictionaryUtil;
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
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.entity.equipment.EquipmentMaintenanceEntity;
import com.jeecg.service.equipment.EquipmentMaintenanceServiceI;

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

/**   
 * @Title: Controller
 * @Description: 设备维护
 * @author zhangdaihao
 * @date 2020-01-02 16:11:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/equipmentMaintenanceController")
public class EquipmentMaintenanceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EquipmentMaintenanceController.class);

	@Autowired
	private EquipmentMaintenanceServiceI equipmentMaintenanceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 设备维护列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"maintType","maintWay","maintState","suplDic","userDic","maintRes");
		return new ModelAndView("com/jeecg/equipment/equipmentMaintenanceList");
	}


	@RequestMapping(value = "/apiList/{maintenanceBatch}")
	@ResponseBody
	public List apiList(@PathVariable("maintenanceBatch") String maintenanceBatch){
		return equipmentMaintenanceService.findByProperty(EquipmentMaintenanceEntity.class,"maintenanceBatch",maintenanceBatch);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(EquipmentMaintenanceEntity equipmentMaintenance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EquipmentMaintenanceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, equipmentMaintenance, request.getParameterMap());
		this.equipmentMaintenanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除设备维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EquipmentMaintenanceEntity equipmentMaintenance, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		equipmentMaintenance = systemService.getEntity(EquipmentMaintenanceEntity.class, equipmentMaintenance.getId());
		message = "设备维护删除成功";
		equipmentMaintenanceService.delete(equipmentMaintenance);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加设备维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EquipmentMaintenanceEntity equipmentMaintenance, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(equipmentMaintenance.getId())) {
			message = "设备维护更新成功";
			EquipmentMaintenanceEntity t = equipmentMaintenanceService.get(EquipmentMaintenanceEntity.class, equipmentMaintenance.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(equipmentMaintenance, t);
				equipmentMaintenanceService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "设备维护更新失败";
			}
		} else {
			message = "设备维护添加成功";
			equipmentMaintenanceService.save(equipmentMaintenance);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	public void apiSave(@RequestBody EquipmentMaintenanceEntity equipmentMaintenanceEntity){
		equipmentMaintenanceService.save(equipmentMaintenanceEntity);
	}

	/**
	 * 设备维护列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EquipmentMaintenanceEntity equipmentMaintenance, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(equipmentMaintenance.getId())) {
			equipmentMaintenance = equipmentMaintenanceService.getEntity(EquipmentMaintenanceEntity.class, equipmentMaintenance.getId());
			req.setAttribute("equipmentMaintenancePage", equipmentMaintenance);
		}
		return new ModelAndView("com/jeecg/equipment/equipmentMaintenance");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(EquipmentMaintenanceEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<EquipmentMaintenanceEntity> listEquipmentMaintenances = this.equipmentMaintenanceService.getListByCriteriaQuery(query,true);
		return Result.success(listEquipmentMaintenances);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		EquipmentMaintenanceEntity task = equipmentMaintenanceService.get(EquipmentMaintenanceEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody EquipmentMaintenanceEntity equipmentMaintenance, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EquipmentMaintenanceEntity>> failures = validator.validate(equipmentMaintenance);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		equipmentMaintenanceService.save(equipmentMaintenance);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = equipmentMaintenance.getId();
		URI uri = uriBuilder.path("/rest/equipmentMaintenanceController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody EquipmentMaintenanceEntity equipmentMaintenance) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EquipmentMaintenanceEntity>> failures = validator.validate(equipmentMaintenance);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		equipmentMaintenanceService.saveOrUpdate(equipmentMaintenance);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		equipmentMaintenanceService.deleteEntityById(EquipmentMaintenanceEntity.class, id);
	}
}
