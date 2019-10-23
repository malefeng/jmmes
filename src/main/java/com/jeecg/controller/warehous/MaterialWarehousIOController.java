package com.jeecg.controller.warehous;

import com.alibaba.fastjson.JSON;
import com.jeecg.api.warehous.MaterialWarehousIODTO;
import com.jeecg.batch.MaterialInLookDataBatchRunnable;
import com.jeecg.entity.invoices.ProductionRequisitionEntity;
import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.entity.warehous.MaterialWarehousNodeEntity;
import com.jeecg.page.warehous.MaterialWarehousIOPage;
import com.jeecg.service.warehous.MaterialWarehousIOServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
 * @Description: 原料出入库列表
 * @author zhangdaihao
 * @date 2019-10-11 05:19:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/materialWarehousIOController")
public class MaterialWarehousIOController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaterialWarehousIOController.class);

	@Autowired
	private MaterialWarehousIOServiceI materialWarehousIOService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**
	 * 原料出入库列表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","repostDic","storageDic","unitDic","ioStateDic","suplDic");
		return new ModelAndView("com/jeecg/warehous/materialWarehousIOList");
	}


	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList")
	@ResponseBody
	public List<MaterialWarehousIOEntity> getListByMaterialSerino(String materialSerino,String takeMaterilNumber,String productionDispatchingNumber) throws Exception {

		StringBuilder hql = new StringBuilder("from MaterialWarehousIOEntity m  where 1=1");
		List param = new ArrayList();
		if(StringUtil.isNotEmpty(materialSerino)){
			hql.append(" and materialSerino = ?");
			param.add(materialSerino);
		}
		if(StringUtil.isNotEmpty(takeMaterilNumber)){
			hql.append(" and EXISTS( select materialSerino from MaterialWarehousNodeEntity n1 where n1.materialSerino = m.materialSerino and n1.prodReqOrderNumber = ? )");
			param.add(takeMaterilNumber);
		}
		if(StringUtil.isNotEmpty(productionDispatchingNumber)){
			hql.append(" and EXISTS ( select materialSerino from MaterialWarehousNodeEntity n2 where n2.materialSerino = m.materialSerino and n2.productionOrderNumber = ? )");
			param.add(productionDispatchingNumber);
		}
		return materialWarehousIOService.findHql(hql.toString(),param.toArray());
	}



	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(MaterialWarehousIOEntity materialWarehousIO,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MaterialWarehousIOEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, materialWarehousIO,request.getParameterMap());
		this.materialWarehousIOService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除原料出入库列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MaterialWarehousIOEntity materialWarehousIO, HttpServletRequest request) {
		List allEntitys = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		materialWarehousIO = systemService.getEntity(MaterialWarehousIOEntity.class, materialWarehousIO.getId());
		List<MaterialWarehousNodeEntity> materialWarehousNodeList = systemService.findByProperty(MaterialWarehousNodeEntity.class,"materialSerino",materialWarehousIO.getMaterialSerino());
		if(!ListUtils.isNullOrEmpty(materialWarehousNodeList)){
			allEntitys.add(materialWarehousNodeList);
		}
		message = "删除成功";
		materialWarehousIOService.deleteAllEntitie(allEntitys);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加原料出入库列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MaterialWarehousIOEntity materialWarehousIO,MaterialWarehousIOPage materialWarehousIOPage, HttpServletRequest request) {
		String message = null;
		List<MaterialWarehousNodeEntity> materialWarehousNodeList =  materialWarehousIOPage.getMaterialWarehousNodeList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(materialWarehousIO.getId())) {
			message = "更新成功";
			materialWarehousIOService.updateMain(materialWarehousIO, materialWarehousNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			materialWarehousIOService.addMain(materialWarehousIO, materialWarehousNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		//同步入库看板数据
		new MaterialInLookDataBatchRunnable(materialWarehousIO.getReceivingOrderNumber(),systemService).startBatch();
		return j;
	}

	@RequestMapping(value = "/apiSave",method = RequestMethod.POST)
	@ResponseBody
	public void apiSave(@RequestBody MaterialWarehousIODTO materialWarehousIODTO,HttpServletResponse response){
		logger.info("原料出入库请提交求报文："+ JSON.toJSONString(materialWarehousIODTO));
		MaterialWarehousIOEntity materialWarehousIOParam = materialWarehousIODTO.getMaterialWarehousIO();
		List<MaterialWarehousNodeEntity> materialWarehousNodeList = materialWarehousIODTO.getMaterialWarehousNodeList();
		if(materialWarehousIOParam!=null){
			MaterialWarehousIOEntity materialWarehousIOOld = materialWarehousIOService.findUniqueByProperty(MaterialWarehousIOEntity.class,"materialSerino",materialWarehousIOParam.getMaterialSerino());
			if(materialWarehousIOOld==null){
				if("1".equals(materialWarehousIOParam.getIoType())){
					//新增（入库）
					//库存量与入库数量相同
					materialWarehousIOParam.setWarehouseOutNumber(materialWarehousIOParam.getWarehousingNumber());
					//虚拟仓库为空
					materialWarehousIOParam.setVirtualRepositoryNumber("0");
					materialWarehousIOService.save(materialWarehousIOParam);
				}else{
					logger.error("新增入库时，库存状态应为入库");
				}
			}else if(!ListUtils.isNullOrEmpty(materialWarehousNodeList)){
				//库存状态
				materialWarehousIOOld.setIoType(materialWarehousIOParam.getIoType());
				//虚拟库存数量
				materialWarehousIOOld.setVirtualRepositoryNumber(materialWarehousIOParam.getVirtualRepositoryNumber());
				//虚拟仓库（车间）
				materialWarehousIOOld.setVirtualRepository(materialWarehousIOParam.getVirtualRepository());
				//当前登陆人
				TSUser sessionUser = ResourceUtil.getSessionUser();
				materialWarehousIOOld.setWarehouseOutPersonCode(sessionUser.getUserName());
				//最新出库时间
				materialWarehousIOOld.setWarehouseOutDate(new Date());
				//虚拟仓库
				if(materialWarehousNodeList.get(0)!=null){
					//从领料单获取车间信息
					ProductionRequisitionEntity productionRequisitionEntity = systemService.findUniqueByProperty(ProductionRequisitionEntity.class, "receiptCode", materialWarehousNodeList.get(0).getProdReqOrderNumber());
					materialWarehousIOOld.setVirtualRepository(productionRequisitionEntity.getRequisitionWorkshopCode());
				}
				systemService.updateEntitie(materialWarehousIOOld);
				// 保存子节点，需保证子节点原料编号必传
				for (MaterialWarehousNodeEntity materialWarehousNodeEntity:materialWarehousNodeList) {
					materialWarehousNodeEntity.setMaterialSerino(materialWarehousIOParam.getMaterialSerino());
					materialWarehousNodeEntity.setCode(materialWarehousIOParam.getMaterialCode());
					materialWarehousNodeEntity.setMaterialName(materialWarehousIOParam.getMaterialName());
					materialWarehousNodeEntity.setBatchNumber(materialWarehousIOParam.getBatchNumber());
					//虚拟仓库
					materialWarehousNodeEntity.setVirtualRepository(materialWarehousIOOld.getVirtualRepository());
					materialWarehousNodeEntity.setUnit(materialWarehousIOParam.getUnit());
					materialWarehousNodeEntity.setWarehouseOutDate(new Date());
					materialWarehousNodeEntity.setWarehouseOutPersonCode(materialWarehousIOParam.getWarehouseOutPersonCode());
				}
				systemService.batchSave(materialWarehousNodeList);
			}else{
				//请求数据不规范
				response.setStatus(406);
			}
			logger.info("原料出入库请提交成功");
			//同步入库看板数据
			new MaterialInLookDataBatchRunnable(materialWarehousIOParam.getReceivingOrderNumber(),systemService).startBatch();
		}
	}

	/**
	 * 原料出入库列表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MaterialWarehousIOEntity materialWarehousIO, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(materialWarehousIO.getId())) {
			materialWarehousIO = materialWarehousIOService.getEntity(MaterialWarehousIOEntity.class, materialWarehousIO.getId());
			req.setAttribute("materialWarehousIOPage", materialWarehousIO);
		}
		return new ModelAndView("com/jeecg/warehous/materialWarehousIO");
	}
	
	
	/**
	 * 加载明细列表[原料出库详情列表]
	 * 
	 * @return
	 */
	@RequestMapping(params = "materialWarehousNodeList")
	public ModelAndView materialWarehousNodeList(MaterialWarehousIOEntity materialWarehousIO, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object materialSerino0 = materialWarehousIO.getMaterialSerino();
		//===================================================================================
		//查询-原料出库详情列表
	    String hql0 = "from MaterialWarehousNodeEntity where 1 = 1 AND materialSerino = ? ";
		try{
		    List<MaterialWarehousNodeEntity> materialWarehousNodeEntityList = systemService.findHql(hql0,materialSerino0);
			req.setAttribute("materialWarehousNodeList", materialWarehousNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/warehous/materialWarehousNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(MaterialWarehousIOEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<MaterialWarehousIOEntity> listMaterialWarehousIOs = this.materialWarehousIOService.getListByCriteriaQuery(query,true);
		return Result.success(listMaterialWarehousIOs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		MaterialWarehousIOEntity task = materialWarehousIOService.get(MaterialWarehousIOEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody MaterialWarehousIOEntity materialWarehousIO, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MaterialWarehousIOEntity>> failures = validator.validate(materialWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		materialWarehousIOService.save(materialWarehousIO);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = materialWarehousIO.getId();
		URI uri = uriBuilder.path("/rest/materialWarehousIOController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody MaterialWarehousIOEntity materialWarehousIO) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MaterialWarehousIOEntity>> failures = validator.validate(materialWarehousIO);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		materialWarehousIOService.saveOrUpdate(materialWarehousIO);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		materialWarehousIOService.deleteEntityById(MaterialWarehousIOEntity.class, id);
	}
}
