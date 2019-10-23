package com.jeecg.controller.look;
import com.jeecg.entity.look.MaterialWarehouIOLookEntity;
import com.jeecg.service.look.MaterialWarehouIOLookServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
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
 * @Description: 原料入库看板
 * @author zhangdaihao
 * @date 2019-09-29 00:25:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/materialWarehouIOLookController")
public class MaterialWarehouIOLookController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaterialWarehouIOLookController.class);

	@Autowired
	private MaterialWarehouIOLookServiceI materialWarehouIOLookService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 原料入库看板列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/look/materialWarehouIOLookNew");
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
	public void datagrid(MaterialWarehouIOLookEntity materialWarehouIOLook,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MaterialWarehouIOLookEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, materialWarehouIOLook, request.getParameterMap());
		this.materialWarehouIOLookService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除原料入库看板
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MaterialWarehouIOLookEntity materialWarehouIOLook, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		materialWarehouIOLook = systemService.getEntity(MaterialWarehouIOLookEntity.class, materialWarehouIOLook.getId());
		message = "原料入库看板删除成功";
		materialWarehouIOLookService.delete(materialWarehouIOLook);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加原料入库看板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MaterialWarehouIOLookEntity materialWarehouIOLook, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(materialWarehouIOLook.getId())) {
			message = "原料入库看板更新成功";
			MaterialWarehouIOLookEntity t = materialWarehouIOLookService.get(MaterialWarehouIOLookEntity.class, materialWarehouIOLook.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(materialWarehouIOLook, t);
				materialWarehouIOLookService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "原料入库看板更新失败";
			}
		} else {
			message = "原料入库看板添加成功";
			materialWarehouIOLookService.save(materialWarehouIOLook);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 原料入库看板列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MaterialWarehouIOLookEntity materialWarehouIOLook, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(materialWarehouIOLook.getId())) {
			materialWarehouIOLook = materialWarehouIOLookService.getEntity(MaterialWarehouIOLookEntity.class, materialWarehouIOLook.getId());
			req.setAttribute("materialWarehouIOLookPage", materialWarehouIOLook);
		}
		return new ModelAndView("com/jeecg/materialwarehousiolook/materialWarehouIOLook");
	}
	
	@RequestMapping(params="listData", method = RequestMethod.GET)
	@ResponseBody
	public List<MaterialWarehouIOLookEntity> listData(HttpServletRequest request) {
		String sql = "SELECT" +
				" w.receiving_order_number receivingOrderNumber," +
				" w.purchase_order_number purchaseOrderNumber," +
				" w.sales_order_number salesOrderNumber," +
				" w.material_code materialCode," +
				" w.material_name materialName," +
				" w.material_size materialSize," +
				" w.supplier_type supplierType," +
				" w.receive_number receiveNumber," +
				" w.insert_number insertNumber," +
				" w.insert_ratio insertRatio," +
				" w.insert_time insertTime" +
				" FROM" +
				" t_material_warehous_io_look w" +
				" WHERE" +
				" w.insert_ratio < 100" +
				" OR (" +
				" w.insert_ratio >= 100" +
				" AND w.insert_time >= (NOW() - INTERVAL 24 HOUR)" +
				")" +
				" ORDER BY" +
				" w.create_date" +
				" LIMIT 25";
		List<MaterialWarehouIOLookEntity> materialWarehouIOLookEntities = this.materialWarehouIOLookService.findListbySql(sql);
		return materialWarehouIOLookEntities;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		MaterialWarehouIOLookEntity task = materialWarehouIOLookService.get(MaterialWarehouIOLookEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody MaterialWarehouIOLookEntity materialWarehouIOLook, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MaterialWarehouIOLookEntity>> failures = validator.validate(materialWarehouIOLook);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		materialWarehouIOLookService.save(materialWarehouIOLook);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = materialWarehouIOLook.getId();
		URI uri = uriBuilder.path("/rest/materialWarehouIOLookController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody MaterialWarehouIOLookEntity materialWarehouIOLook) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MaterialWarehouIOLookEntity>> failures = validator.validate(materialWarehouIOLook);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		materialWarehouIOLookService.saveOrUpdate(materialWarehouIOLook);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		materialWarehouIOLookService.deleteEntityById(MaterialWarehouIOLookEntity.class, id);
	}
}
