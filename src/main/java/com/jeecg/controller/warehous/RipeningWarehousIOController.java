package com.jeecg.controller.warehous;

import com.jeecg.entity.production.FinishedInspectItemEntity;
import com.jeecg.entity.production.FinishedProductionEntity;
import com.jeecg.entity.production.SemiFinishedProductionEntity;
import com.jeecg.entity.warehous.RipeningWarehousIOEntity;
import com.jeecg.service.warehous.RipeningWarehousIOServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
import org.springframework.transaction.annotation.Transactional;
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
 * @Description: 熟成出入库列表
 * @author zhangdaihao
 * @date 2019-10-10 23:23:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ripeningWarehousIOController")
public class RipeningWarehousIOController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RipeningWarehousIOController.class);

	@Autowired
	private RipeningWarehousIOServiceI ripeningWarehousIOService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	//熟成库存状态：出库
	private final String IOTYPE_OUT = "2";


	/**
	 * 熟成出入库列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","repostDic","storageDic","finshTypeDic","unitDic","finishioStateDic");
		return new ModelAndView("com/jeecg/warehous/ripeningWarehousIOList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList")
	@ResponseBody
	public List<RipeningWarehousIOEntity> getListByMaterialSerino(String productSerino,String productionOrderNumber,String takeMaterilNumber){
		StringBuilder hql = new StringBuilder(" from RipeningWarehousIOEntity where 1=1");
		List param = new ArrayList();
		if(StringUtil.isNotEmpty(productSerino)){
			hql.append(" and productSerino = ?");
			param.add(productSerino);
		}
		if(StringUtil.isNotEmpty(productionOrderNumber)){
			hql.append(" and productionOrderNumber = ?");
			param.add(productionOrderNumber);
		}
		if(StringUtil.isNotEmpty(takeMaterilNumber)){
			hql.append(" and takeMaterilNumber = ?");
			param.add(takeMaterilNumber);
		}
		return ripeningWarehousIOService.findHql(hql.toString(),param.toArray());
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(RipeningWarehousIOEntity ripeningWarehousIO,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RipeningWarehousIOEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ripeningWarehousIO, request.getParameterMap());
		this.ripeningWarehousIOService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除熟成出入库列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RipeningWarehousIOEntity ripeningWarehousIO, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ripeningWarehousIO = systemService.getEntity(RipeningWarehousIOEntity.class, ripeningWarehousIO.getId());
		message = "熟成出入库列表删除成功";
		ripeningWarehousIOService.delete(ripeningWarehousIO);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "outWareHous")
	@ResponseBody
	public AjaxJson outWareHous(String id, HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		RipeningWarehousIOEntity t = systemService.get(RipeningWarehousIOEntity.class, id);
		message = "成品出库成功";
		t.setRipeningStoreType(IOTYPE_OUT);
		try {
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "成品出库失败";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加熟成出入库列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@Transactional
	public AjaxJson save(RipeningWarehousIOEntity ripeningWarehousIO, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ripeningWarehousIO.getId())) {
			if(!couldRepening(ripeningWarehousIO)){
				message = "产品熟成时长不足，请稍后再试";
				j.setMsg(message);
				return j;
			}
			message = "熟成出入库列表更新成功";
			RipeningWarehousIOEntity t = ripeningWarehousIOService.get(RipeningWarehousIOEntity.class, ripeningWarehousIO.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ripeningWarehousIO, t);
				ripeningWarehousIOService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				//成品出库时，生成成品检验数据
				if("2".equals(ripeningWarehousIO.getRipeningProType())&&"2".equals(ripeningWarehousIO.getRipeningStoreType())){
					FinishedInspectItemEntity finishedInspectItemEntity = new FinishedInspectItemEntity();
					finishedInspectItemEntity.setFinishedCode(ripeningWarehousIO.getProductCode());
					finishedInspectItemEntity.setFinishedName(ripeningWarehousIO.getProductName());
					finishedInspectItemEntity.setProductionDispatchingNumber(ripeningWarehousIO.getProductionOrderNumber());
					finishedInspectItemEntity.setSalesOrderNumber(ripeningWarehousIO.getProductSerino());
					systemService.save(finishedInspectItemEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "熟成出入库列表更新失败";
			}
		} else {
			message = "熟成出入库列表添加成功";
			ripeningWarehousIOService.save(ripeningWarehousIO);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 判读是都可以熟成出库：true-可以，false-不可以
	 * @param ripeningWarehousIO
	 * @return
	 */
	private boolean couldRepening(RipeningWarehousIOEntity ripeningWarehousIO){
		if("2".equals(ripeningWarehousIO.getRipeningStoreType())){//熟成出库时，判断熟成时间是否满足
			if("2".equals(ripeningWarehousIO.getRipeningProType())){//成品出库
				FinishedProductionEntity finishedProductionEntity = systemService.findUniqueByProperty(FinishedProductionEntity.class, "finishedSerino", ripeningWarehousIO.getProductCode());
				Date createDate = finishedProductionEntity.getCreateDate();
				Double ripeningHours = finishedProductionEntity.getRipeningHours();
				//需要熟成，且熟成时间不为空
				if("1".equals(finishedProductionEntity.getNeedRipening())&&createDate!=null&&ripeningHours!=null){
					createDate = DateUtils.addMinutes(createDate, (int) (finishedProductionEntity.getRipeningHours()*60));
					return createDate.compareTo(new Date())>=0;
				}
			}else if("1".equals(ripeningWarehousIO.getRipeningProType())){
				SemiFinishedProductionEntity semiFinishedProductionEntity = systemService.findUniqueByProperty(SemiFinishedProductionEntity.class, "finishedSerino", ripeningWarehousIO.getProductCode());
				Date createDate = semiFinishedProductionEntity.getCreateDate();
				Double ripeningHours = semiFinishedProductionEntity.getRipeningHours();
				//需要熟成，且熟成时间不为空
				if("1".equals(semiFinishedProductionEntity.getNeedRipening())&&createDate!=null&&ripeningHours!=null){
					createDate = DateUtils.addMinutes(createDate, (int) (semiFinishedProductionEntity.getRipeningHours()*60));
					return createDate.compareTo(new Date())>=0;
				}
			}
		}
		return true;
	}

	/**
	 * 检测是否需要熟成
	 * @param productSerino
	 * @return
	 */
	@RequestMapping(params = "needRepening")
	@ResponseBody
	public AjaxJson needRepening(String productSerino){
		AjaxJson j = new AjaxJson();
		FinishedProductionEntity finishedSerino = systemService.findUniqueByProperty(FinishedProductionEntity.class, "finishedSerino", productSerino);
		if(finishedSerino!=null&&finishedSerino.getNeedRipening()==1){
			j.setSuccess(true);
			return j;
		}
		SemiFinishedProductionEntity semiFinishedProductionEntity = systemService.findUniqueByProperty(SemiFinishedProductionEntity.class, "semiFinishedSerino", productSerino);
		if(semiFinishedProductionEntity!=null&&semiFinishedProductionEntity.getNeedRipening()==1){
			j.setSuccess(true);
			return j;
		}
		j.setSuccess(true);
		j.setMsg("该产品不存在或无需熟成");
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody RipeningWarehousIOEntity ripeningWarehousIO,HttpServletResponse response) throws IOException {
		if(!couldRepening(ripeningWarehousIO)){
			response.sendError(202,"产品熟成时长不足，请稍后再试");
		}
		ripeningWarehousIOService.saveOrUpdate(ripeningWarehousIO);
	}

	/**
	 * 熟成出入库列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RipeningWarehousIOEntity ripeningWarehousIO, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ripeningWarehousIO.getId())) {
			ripeningWarehousIO = ripeningWarehousIOService.getEntity(RipeningWarehousIOEntity.class, ripeningWarehousIO.getId());
			req.setAttribute("ripeningWarehousIOPage", ripeningWarehousIO);
		}
		return new ModelAndView("com/jeecg/warehous/ripeningWarehousIO");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(RipeningWarehousIOEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<RipeningWarehousIOEntity> listRipeningWarehousIOs = this.ripeningWarehousIOService.getListByCriteriaQuery(query,true);
		return Result.success(listRipeningWarehousIOs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		RipeningWarehousIOEntity task = ripeningWarehousIOService.get(RipeningWarehousIOEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody RipeningWarehousIOEntity ripeningWarehousIO, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RipeningWarehousIOEntity>> failures = validator.validate(ripeningWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ripeningWarehousIOService.save(ripeningWarehousIO);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = ripeningWarehousIO.getId();
		URI uri = uriBuilder.path("/rest/ripeningWarehousIOController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody RipeningWarehousIOEntity ripeningWarehousIO) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RipeningWarehousIOEntity>> failures = validator.validate(ripeningWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ripeningWarehousIOService.saveOrUpdate(ripeningWarehousIO);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ripeningWarehousIOService.deleteEntityById(RipeningWarehousIOEntity.class, id);
	}
}
