package com.jeecg.controller.warehous;

import com.jeecg.entity.warehous.FinishedWarehousIOEntity;
import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.entity.warehous.WarehousIOChangeEntity;
import com.jeecg.service.warehous.WarehousIOChangeServiceI;
import com.jeecg.util.DictionaryUtil;
import com.jeecg.util.MathUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
import org.springframework.transaction.annotation.Transactional;
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
 * @Description: 调库调位
 * @author zhangdaihao
 * @date 2019-11-18 23:12:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/warehousIOChangeController")
public class WarehousIOChangeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WarehousIOChangeController.class);

	@Autowired
	private WarehousIOChangeServiceI warehousIOChangeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	//物资类型：1-物料，2-成品
	private final String MATER_KIND = "1";
	private final String FINISHED_KIND = "2";



	/**
	 * 调库调位列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"materType","repostDic","storageDic","userDic");
		return new ModelAndView("com/jeecg/warehous/warehousIOChangeList");
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
	public void datagrid(WarehousIOChangeEntity warehousIOChange,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WarehousIOChangeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, warehousIOChange, request.getParameterMap());
		this.warehousIOChangeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除调库调位
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WarehousIOChangeEntity warehousIOChange, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		warehousIOChange = systemService.getEntity(WarehousIOChangeEntity.class, warehousIOChange.getId());
		message = "调库调位删除成功";
		warehousIOChangeService.delete(warehousIOChange);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加调库调位
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@Transactional
	public AjaxJson save(WarehousIOChangeEntity warehousIOChange, HttpServletRequest request) {
		String message = "调库调位更新失败";
		AjaxJson j = new AjaxJson();
		//调整库位
		if(changeWarehouse(warehousIOChange)){
			if (StringUtil.isNotEmpty(warehousIOChange.getId())) {
				message = "调库调位更新成功";
				try {
					warehousIOChangeService.updateEntitie(warehousIOChange);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				message = "调库调位添加成功";
				warehousIOChangeService.save(warehousIOChange);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void apiSave(@RequestBody WarehousIOChangeEntity warehousIOChange){
		if(changeWarehouse(warehousIOChange)){
			if (StringUtil.isNotEmpty(warehousIOChange.getId())) {
				try {
					warehousIOChangeService.updateEntitie(warehousIOChange);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				warehousIOChangeService.save(warehousIOChange);
			}
		}
	}

	/**
	 * 调整库位
	 * @param warehousIOChange
	 */
	private boolean changeWarehouse(WarehousIOChangeEntity warehousIOChange) {
		String materType = warehousIOChange.getMaterialsType();
		String materSeiro = warehousIOChange.getMaterialsSerino();
		//变动数量
		int changeNum = MathUtil.toInt(warehousIOChange.getMaterialsNumber());
		if(MATER_KIND.equals(materType)){//原料
			MaterialWarehousIOEntity warehousIOEntity = warehousIOChangeService.findUniqueByProperty(MaterialWarehousIOEntity.class, "materialSerino", materSeiro);
			if(null != warehousIOEntity){
				warehousIOEntity.setWarehousePositionCode(warehousIOChange.getRepositoryCode());
				warehousIOEntity.setWarehouseSpaceCode(warehousIOChange.getStorageCode());
				warehousIOChangeService.updateEntitie(warehousIOEntity);
				return true;
			}
		}else if(FINISHED_KIND.equals(materType)){//成品
			FinishedWarehousIOEntity finishedWarehousIOEntity = warehousIOChangeService.findUniqueByProperty(FinishedWarehousIOEntity.class, "finishedSerino", materSeiro);
			if(null != finishedWarehousIOEntity){
				finishedWarehousIOEntity.setWarehousPosition(warehousIOChange.getRepositoryCode());
				finishedWarehousIOEntity.setWarehousSpace(warehousIOChange.getStorageCode());
				warehousIOChangeService.updateEntitie(finishedWarehousIOEntity);
				return true;
			}
		}
		return false;
	}

	/**
	 * 调库调位列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WarehousIOChangeEntity warehousIOChange, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(warehousIOChange.getId())) {
			warehousIOChange = warehousIOChangeService.getEntity(WarehousIOChangeEntity.class, warehousIOChange.getId());
			req.setAttribute("warehousIOChangePage", warehousIOChange);
		}
		return new ModelAndView("com/jeecg/warehous/warehousIOChange");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(WarehousIOChangeEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<WarehousIOChangeEntity> listWarehousIOChanges = this.warehousIOChangeService.getListByCriteriaQuery(query,true);
		return Result.success(listWarehousIOChanges);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WarehousIOChangeEntity task = warehousIOChangeService.get(WarehousIOChangeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WarehousIOChangeEntity warehousIOChange, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WarehousIOChangeEntity>> failures = validator.validate(warehousIOChange);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		warehousIOChangeService.save(warehousIOChange);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = warehousIOChange.getId();
		URI uri = uriBuilder.path("/rest/warehousIOChangeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WarehousIOChangeEntity warehousIOChange) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WarehousIOChangeEntity>> failures = validator.validate(warehousIOChange);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		warehousIOChangeService.saveOrUpdate(warehousIOChange);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		warehousIOChangeService.deleteEntityById(WarehousIOChangeEntity.class, id);
	}
}
