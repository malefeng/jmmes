package com.jeecg.controller.equipment;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.entity.qrcode.QRCodeEntity;
import com.jeecg.service.common.SequenceServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.commons.lang.StringUtils;
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

import com.jeecg.entity.equipment.EquipmentEntity;
import com.jeecg.service.equipment.EquipmentServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 设备列表
 * @author zhangdaihao
 * @date 2020-01-02 16:12:01
 * @version V1.0   
 *
 */
@Controller
	@RequestMapping("/equipmentController")
public class EquipmentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EquipmentController.class);

	@Autowired
	private EquipmentServiceI equipmentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private SequenceServiceI sequenceServiceI;
	


	/**
	 * 设备列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","suplDic");
		return new ModelAndView("com/jeecg/equipment/equipmentList");
	}

	@RequestMapping(params = "printList")
	public ModelAndView printList(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","suplDic");
		return new ModelAndView("com/jeecg/print/equipmentPrintList");
	}

	@RequestMapping(params = "getPrintData")
	@ResponseBody
	public List getPrintData(String id){
		List toSaveList = new ArrayList();
		List result = new ArrayList();
		generatePrintData(id, toSaveList, result);
		//保存打印记录
		systemService.batchSave(toSaveList);
		return result;
	}

	@RequestMapping(params = "rePrint")
	@ResponseBody
	public Object rePrint(String qrCode){
		QRCodeEntity qrCodeEntity = systemService.findUniqueByProperty(QRCodeEntity.class, "number", qrCode);
		if(null!=qrCodeEntity){
			String rawMaterialCode = qrCodeEntity.getCode();
			List result = new ArrayList();
			//明文key
			Object[] keys = new Object[5];
			//明文value
			Object[] values = new Object[5];
			//二维码类型
			keys[0] = "类型";
			values[0] = "99-设备";
			//二维码编号
			keys[1] = "二维码";
			values[1] = qrCodeEntity.getNumber();
			//设备编号
			keys[2] = "设备编号";
			values[2] = qrCodeEntity.getCode();
			//二维码类型
			keys[3] = "设备名称";
			values[3] = qrCodeEntity.getMaterialName();
			//二维码类型
			keys[4] = "设备型号";
			values[4] = qrCodeEntity.getMaterialSize();
			result.add(generateContent(qrCodeEntity.getCode().concat(",").concat(qrCode).concat(",").concat(qrCodeEntity.getQrCodeType()), keys, values));
			return result;
		}
		return null;
	}

	private void generatePrintData(String id, List toSaveList, List result) {
		EquipmentEntity equipmentEntity =systemService.get(EquipmentEntity.class,id);
		if(equipmentEntity!=null){
			//二维码代码
			String qrCode = sequenceServiceI.getqrCode("finishedItem");
			QRCodeEntity qrCodeEntity = new QRCodeEntity();
			qrCodeEntity.setNumber(qrCode);
			qrCodeEntity.setQrCodeType("9");
			qrCodeEntity.setCode(equipmentEntity.getEquipmentNumber());
			qrCodeEntity.setMaterialName(equipmentEntity.getEquipmentName());
			qrCodeEntity.setMaterialSize(equipmentEntity.getEquipmentSize());
			toSaveList.add(qrCodeEntity);
			//明文key
			Object[] keys = new Object[5];
			//明文value
			Object[] values = new Object[5];
			//二维码类型
			keys[0] = "类型";
			values[0] = "99-设备";
			//二维码编号
			keys[1] = "二维码";
			values[1] = qrCodeEntity.getNumber();
			//设备编号
			keys[2] = "设备编号";
			values[2] = qrCodeEntity.getCode();
			//二维码类型
			keys[3] = "设备名称";
			values[3] = qrCodeEntity.getMaterialName();
			//二维码类型
			keys[4] = "设备型号";
			values[4] = qrCodeEntity.getMaterialSize();

			result.add(generateContent(qrCodeEntity.getCode().concat(",").concat(qrCode).concat(",").concat(qrCodeEntity.getQrCodeType()), keys, values));
		}
	}


	private Map generateContent(String qrCode, Object[] keys, Object[] values) {
		Map contenMap;
		contenMap = new HashMap();
		contenMap.put("pubKey", StringUtils.join(keys, ","));
		contenMap.put("pubVal", StringUtils.join(values, ","));
		contenMap.put("secData", qrCode);
		return contenMap;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(EquipmentEntity equipment,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EquipmentEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, equipment, request.getParameterMap());
		this.equipmentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(value="/apiList/{equipmentNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<EquipmentEntity> list(@PathVariable("equipmentNumber") String equipmentNumber, HttpServletRequest request) {
		return this.equipmentService.findByProperty(EquipmentEntity.class, "equipmentNumber", equipmentNumber);
	}

	/**
	 * 删除设备列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EquipmentEntity equipment, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		equipment = systemService.getEntity(EquipmentEntity.class, equipment.getId());
		message = "设备列表删除成功";
		equipmentService.delete(equipment);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加设备列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EquipmentEntity equipment, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(equipment.getId())) {
			message = "设备列表更新成功";
			EquipmentEntity t = equipmentService.get(EquipmentEntity.class, equipment.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(equipment, t);
				equipmentService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "设备列表更新失败";
			}
		} else {
			message = "设备列表添加成功";
			equipmentService.save(equipment);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	public void apiSave(@RequestBody EquipmentEntity equipmentEntity){
		equipmentService.save(equipmentEntity);
	}

	/**
	 * 设备列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EquipmentEntity equipment, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(equipment.getId())) {
			equipment = equipmentService.getEntity(EquipmentEntity.class, equipment.getId());
			req.setAttribute("equipmentPage", equipment);
		}
		return new ModelAndView("com/jeecg/equipment/equipment");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(EquipmentEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<EquipmentEntity> listEquipments = this.equipmentService.getListByCriteriaQuery(query,true);
		return Result.success(listEquipments);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		EquipmentEntity task = equipmentService.get(EquipmentEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody EquipmentEntity equipment, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EquipmentEntity>> failures = validator.validate(equipment);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		equipmentService.save(equipment);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = equipment.getId();
		URI uri = uriBuilder.path("/rest/equipmentController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody EquipmentEntity equipment) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EquipmentEntity>> failures = validator.validate(equipment);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		equipmentService.saveOrUpdate(equipment);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		equipmentService.deleteEntityById(EquipmentEntity.class, id);
	}
}
