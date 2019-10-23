package com.jeecg.controller.look;
import com.jeecg.entity.look.FinishedWarehousIOLookEntity;
import com.jeecg.service.look.FinishedWarehousIOLookServiceI;
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
 * @Description: t_finished_warehous_io_look
 * @author zhangdaihao
 * @date 2019-09-29 00:22:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/finishedWarehousIOLookController")
public class FinishedWarehousIOLookController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinishedWarehousIOLookController.class);

	@Autowired
	private FinishedWarehousIOLookServiceI finishedWarehousIOLookService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * t_finished_warehous_io_look列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/look/finishedWarehousIOLookNew");
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
	public void datagrid(FinishedWarehousIOLookEntity finishedWarehousIOLook,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FinishedWarehousIOLookEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, finishedWarehousIOLook, request.getParameterMap());
		this.finishedWarehousIOLookService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除t_finished_warehous_io_look
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FinishedWarehousIOLookEntity finishedWarehousIOLook, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		finishedWarehousIOLook = systemService.getEntity(FinishedWarehousIOLookEntity.class, finishedWarehousIOLook.getId());
		message = "t_finished_warehous_io_look删除成功";
		finishedWarehousIOLookService.delete(finishedWarehousIOLook);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加t_finished_warehous_io_look
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FinishedWarehousIOLookEntity finishedWarehousIOLook, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(finishedWarehousIOLook.getId())) {
			message = "t_finished_warehous_io_look更新成功";
			FinishedWarehousIOLookEntity t = finishedWarehousIOLookService.get(FinishedWarehousIOLookEntity.class, finishedWarehousIOLook.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(finishedWarehousIOLook, t);
				finishedWarehousIOLookService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "t_finished_warehous_io_look更新失败";
			}
		} else {
			message = "t_finished_warehous_io_look添加成功";
			finishedWarehousIOLookService.save(finishedWarehousIOLook);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * t_finished_warehous_io_look列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FinishedWarehousIOLookEntity finishedWarehousIOLook, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(finishedWarehousIOLook.getId())) {
			finishedWarehousIOLook = finishedWarehousIOLookService.getEntity(FinishedWarehousIOLookEntity.class, finishedWarehousIOLook.getId());
			req.setAttribute("finishedWarehousIOLookPage", finishedWarehousIOLook);
		}
		return new ModelAndView("com/jeecg/finishedwarehousiolook/finishedWarehousIOLook");
	}
	
	@RequestMapping(params="listData", method = RequestMethod.GET)
	@ResponseBody
	public List<FinishedWarehousIOLookEntity> listData(HttpServletRequest request) {
		String sql = "SELECT " +
				" l.sales_delivery_order_number salesDeliveryOrderNumber, " +
				" l.delivery_advice_order_number deliveryAdviceOrderNumber, " +
				" l.material_code materialCode, " +
				" l.material_name materialName, " +
				" l.material_size materialSize, " +
				" c.customer_name customerCode, " +
				" l.should_send_number shouldSendNumber, " +
				" l.actual_send_number actualSendNumber, " +
				" l.send_ratio sendRatio, " +
				" l.send_finish_time sendFinishTime " +
				"FROM " +
				" t_finished_warehous_io_look l " +
				"LEFT JOIN t_customer_list c ON l.customer_code = c.customer_code " +
				"WHERE " +
				" l.send_ratio < 100 " +
				"OR ( " +
				" l.send_ratio >= 100 " +
				" AND l.send_finish_time >= (NOW() - INTERVAL 24 HOUR) " +
				") " +
				"ORDER BY " +
				" l.create_date " +
				"LIMIT 25";
		List<FinishedWarehousIOLookEntity> finishedWarehousIOLooks = this.finishedWarehousIOLookService.findListbySql(sql);
		return finishedWarehousIOLooks;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FinishedWarehousIOLookEntity task = finishedWarehousIOLookService.get(FinishedWarehousIOLookEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FinishedWarehousIOLookEntity finishedWarehousIOLook, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousIOLookEntity>> failures = validator.validate(finishedWarehousIOLook);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousIOLookService.save(finishedWarehousIOLook);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = finishedWarehousIOLook.getId();
		URI uri = uriBuilder.path("/rest/finishedWarehousIOLookController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FinishedWarehousIOLookEntity finishedWarehousIOLook) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FinishedWarehousIOLookEntity>> failures = validator.validate(finishedWarehousIOLook);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		finishedWarehousIOLookService.saveOrUpdate(finishedWarehousIOLook);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		finishedWarehousIOLookService.deleteEntityById(FinishedWarehousIOLookEntity.class, id);
	}
}
