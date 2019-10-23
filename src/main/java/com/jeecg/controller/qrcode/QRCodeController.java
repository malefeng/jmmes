package com.jeecg.controller.qrcode;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.service.qrcode.QRCodeServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ListUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 二维码打印信息
 * @author zhangdaihao
 * @date 2019-10-06 01:40:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qRCodeController")
public class QRCodeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QRCodeController.class);

	@Autowired
	private QRCodeServiceI qRCodeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 二维码打印信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qrcode/qRCodeList");
	}


	@RequestMapping(value = "/apiList/{number}")
	@ResponseBody
	 public Object apiList(@PathVariable("number") String number){
		Map resultMap = new HashMap();
		String sql = "SELECT " +
				"   count(id) times, " +
				"   batch_no batchNo, " +
				"   qr_code_type qrCodeType " +
				"  FROM " +
				"   t_qr_code " +
				"  WHERE " +
				"   ( " +
				"    number = ? " +
				"    AND qr_code_type IN (1, 2, 5) " +
				"   ) " +
				"  OR ( " +
				"   number LIKE CONCAT(?,'__') " +
				"   AND qr_code_type IN (3, 4, 6, 7) " +
				"  ) " +
				"  GROUP BY " +
				"   qr_code_type";
		List<Map<String, Object>> mapList = systemService.findForJdbc(sql, number, number);
		if(!ListUtils.isNullOrEmpty(mapList)){
			for (Map<String, Object> map : mapList) {
				String qrCodeType= String.valueOf(map.get("qrCodeType"));
				if("1,2,5".contains(qrCodeType)){
					resultMap.put("batchNo",map.get("batchNo"));
				}else if("3,6".contains(qrCodeType)){
					resultMap.put("firstInspectTimes",map.get("times"));
				}else if("4,7".contains(qrCodeType)){
					resultMap.put("lastInspectTimes",map.get("times"));
				}
			}
		}
		return JSONObject.toJSON(resultMap);
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
	public void datagrid(QRCodeEntity qRCode,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QRCodeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qRCode, request.getParameterMap());
		this.qRCodeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除二维码打印信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(QRCodeEntity qRCode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qRCode = systemService.getEntity(QRCodeEntity.class, qRCode.getId());
		message = "二维码打印信息删除成功";
		qRCodeService.delete(qRCode);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加二维码打印信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(QRCodeEntity qRCode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(qRCode.getId())) {
			message = "二维码打印信息更新成功";
			QRCodeEntity t = qRCodeService.get(QRCodeEntity.class, qRCode.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(qRCode, t);
				qRCodeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "二维码打印信息更新失败";
			}
		} else {
			message = "二维码打印信息添加成功";
			qRCodeService.save(qRCode);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 二维码打印信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(QRCodeEntity qRCode, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qRCode.getId())) {
			qRCode = qRCodeService.getEntity(QRCodeEntity.class, qRCode.getId());
			req.setAttribute("qRCodePage", qRCode);
		}
		return new ModelAndView("com/jeecg/qrcode/qRCode");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(QRCodeEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<QRCodeEntity> listQRCodes = this.qRCodeService.getListByCriteriaQuery(query,true);
		return Result.success(listQRCodes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		QRCodeEntity task = qRCodeService.get(QRCodeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody QRCodeEntity qRCode, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<QRCodeEntity>> failures = validator.validate(qRCode);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		qRCodeService.save(qRCode);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = qRCode.getId();
		URI uri = uriBuilder.path("/rest/qRCodeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody QRCodeEntity qRCode) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<QRCodeEntity>> failures = validator.validate(qRCode);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		qRCodeService.saveOrUpdate(qRCode);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		qRCodeService.deleteEntityById(QRCodeEntity.class, id);
	}
}
