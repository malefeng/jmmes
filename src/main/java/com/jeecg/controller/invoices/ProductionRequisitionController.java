package com.jeecg.controller.invoices;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.batch.ProductionLookDataBatchRunnable;
import com.jeecg.constant.ERPApiCodeEnum;
import com.jeecg.constant.ERProTranslateMap;
import com.jeecg.constant.ERSalTranslateMap;
import com.jeecg.entity.invoices.*;
import com.jeecg.entity.look.ProductionParehousIOLookEntity;
import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.page.invoices.ProductionRequisitionPage;
import com.jeecg.service.invoices.ProductionRequisitionServiceI;
import com.jeecg.util.*;
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
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 生产领料单
 * @author zhangdaihao
 * @date 2019-10-10 22:17:54
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/productionRequisitionController")
public class ProductionRequisitionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProductionRequisitionController.class);

	@Autowired
	private ProductionRequisitionServiceI productionRequisitionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	private final String FINISHED_PRINT = "finishedPrint";
	private final String SEMI_FINISHED_PRINT = "semiFinishedPrint";

	
	/**
	 * 生产领料单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"userDic","repostDic","workshopDic","suplDic");
		return new ModelAndView("com/jeecg/invoices/productionRequisitionList");
	}

	/**
	 * api列表数据查询接口
	 * @return
	 */
	@RequestMapping(value = "/apiList/{receiptCode}")
	@ResponseBody
	public Object apiList(@PathVariable("receiptCode") String receiptCode,HttpServletResponse response) throws IOException {
		Map result = new HashMap();
		ProductionRequisitionEntity productionRequisitionEntity = null;
		try {
			productionRequisitionEntity = productionRequisitionService.findUniqueByProperty(ProductionRequisitionEntity.class,"receiptCode",receiptCode);
		} catch (Exception e) {
			response.setStatus(500);
			response.sendError(500,"生产领料单，领料单号："+receiptCode+"存在重复数据");
			e.printStackTrace();
		}
		result.put("productionRequisitionEntity",productionRequisitionEntity);
		if(productionRequisitionEntity!=null){
			List<ProductionRequisitionNodeEntity> productionRequisitionNodeEntityList = productionRequisitionService.findByProperty(ProductionRequisitionNodeEntity.class, "inspectId", productionRequisitionEntity.getId());
			result.put("productionRequisitionNodeEntityList",productionRequisitionNodeEntityList);
		}
		return JSONObject.toJSON(result);
	}

	@RequestMapping(params = "getListByFinishedCode")
	@ResponseBody
	public Object getListByFinishedCode(String finishedCode){
		List<ProductionRequisitionEntity> productionRequisitionEntities = productionRequisitionService.findByProperty(ProductionRequisitionEntity.class, "finishedCode", finishedCode);
		return JSONObject.toJSON(productionRequisitionEntities);
	}

	/**
	 * 根据条件查询未销售的领料单信息
	 * @return
	 */
	@RequestMapping(params = "getUnproductedListByParam")
	@ResponseBody
	public Object getUnproductedListByParam(String finishedCode,String printType){
		StringBuilder hql = new StringBuilder("from ProductionRequisitionEntity r where 1=1 ");
		hql.append(" and not exists (select s.id from SalesReleaseNodeEntity s where s.productionOrderNumber = r.productionOrderNumber)");
		/*if(FINISHED_PRINT.equals(printType)){
			hql.append("and NOT EXISTS (select fp.id from FinishedProductionEntity fp where fp.takeMaterilNumber = r.receiptCode)");
		}else if(SEMI_FINISHED_PRINT.equals(printType)){
			hql.append(" and NOT EXISTS (select sp.id from SemiFinishedProductionEntity sp where sp.takeMaterilNumber = r.receiptCode)");
		}*/
		if(StringUtil.isNotEmpty(finishedCode)){
			hql.append(" and r.finishedCode = ").append(finishedCode);
		}
		return productionRequisitionService.findByQueryString(hql.toString());
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProductionRequisitionEntity productionRequisition,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductionRequisitionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productionRequisition,request.getParameterMap());
		this.productionRequisitionService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除生产领料单
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ProductionRequisitionEntity productionRequisition, HttpServletRequest request) {
		List allEntity = new ArrayList();
		String message = null;
		AjaxJson j = new AjaxJson();
		productionRequisition = systemService.getEntity(ProductionRequisitionEntity.class, productionRequisition.getId());
		List<ProductionRequisitionNodeEntity> requisitionNodeEntities = systemService.findByProperty(ProductionRequisitionNodeEntity.class, "inspectId", productionRequisition.getId());
		List<ProductionRequisitionOrgNodeEntity> requisitionOrgNodeEntities = systemService.findByProperty(ProductionRequisitionOrgNodeEntity.class, "inspectId", productionRequisition.getId());
		allEntity.add(productionRequisition);
		if(!ListUtils.isNullOrEmpty(requisitionNodeEntities)){
			allEntity.addAll(requisitionNodeEntities);
		}
		if(!ListUtils.isNullOrEmpty(requisitionOrgNodeEntities)){
			allEntity.addAll(requisitionOrgNodeEntities);
		}
		message = "删除成功";
		productionRequisitionService.deleteAllEntitie(allEntity);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		//删除生产看板
		List<ProductionParehousIOLookEntity> productionParehousIOLookEntities = systemService.findByProperty(ProductionParehousIOLookEntity.class, "productionDispatchingNumber", productionRequisition.getProductionDispatchingNumber());
		if(!ListUtils.isNullOrEmpty(productionParehousIOLookEntities)){
			systemService.deleteAllEntitie(productionParehousIOLookEntities);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加生产领料单
	 * 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ProductionRequisitionEntity productionRequisition,ProductionRequisitionPage productionRequisitionPage, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String message = null;
		AjaxJson j = new AjaxJson();
		List<ProductionRequisitionNodeEntity> productionRequisitionNodeList =  productionRequisitionPage.getProductionRequisitionNodeList();
		List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList =  productionRequisitionPage.getProductionRequisitionOrgNodeList();
		if(!ListUtils.isNullOrEmpty(productionRequisitionNodeList)){
			List<MaterialWarehousIOEntity> materialWarehousIOEntityList = new ArrayList<>(productionRequisitionNodeList.size());
			for (ProductionRequisitionNodeEntity productionRequisitionNodeEntity: productionRequisitionNodeList) {
				//对应的原料库存信息
				MaterialWarehousIOEntity materialWarehousIOEntity = null;
				try {
					materialWarehousIOEntity = productionRequisitionService.findUniqueByProperty(MaterialWarehousIOEntity.class, "materialSerino", productionRequisitionNodeEntity.getRawMaterialSerino());
				} catch (Exception e) {
					response.setStatus(500);
					response.sendError(500,"原料仓库中，原料编号："+productionRequisitionNodeEntity.getRawMaterialSerino()+"存在重复数据");
					e.printStackTrace();
				}
				if(materialWarehousIOEntity==null){
					message = "原料信息不存在";
					j.setMsg(message);
					return j;
				}
				//已配料
				materialWarehousIOEntity.setIoType("4");
				materialWarehousIOEntityList.add(materialWarehousIOEntity);
				productionRequisitionService.updateEntitie(materialWarehousIOEntity);
				productionRequisitionNodeEntity.setFinishedCode(productionRequisition.getFinishedCode());
				productionRequisitionNodeEntity.setFinishedName(productionRequisition.getFinishedName());
				productionRequisitionNodeEntity.setProductionOrderNumber(productionRequisition.getProductionOrderNumber());
				productionRequisitionNodeEntity.setProductionDispatchingNumber(productionRequisition.getProductionDispatchingNumber());
			}
		}
		if (StringUtil.isNotEmpty(productionRequisition.getId())) {
			message = "更新成功";
			productionRequisitionService.updateMain(productionRequisition, productionRequisitionNodeList,productionRequisitionOrgNodeList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			productionRequisitionService.addMain(productionRequisition, productionRequisitionNodeList,productionRequisitionOrgNodeList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		//同步看板数据
		new Thread(new ProductionLookDataBatchRunnable(productionRequisition.getProductionDispatchingNumber(),systemService)).start();
		j.setMsg(message);
		return j;
	}

	/**
	 * 生产领料单列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ProductionRequisitionEntity productionRequisition, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(productionRequisition.getId())) {
			productionRequisition = productionRequisitionService.getEntity(ProductionRequisitionEntity.class, productionRequisition.getId());
			req.setAttribute("productionRequisitionPage", productionRequisition);
		}
		return new ModelAndView("com/jeecg/invoices/productionRequisition");
	}

	@RequestMapping(params = "getErpData")
	@ResponseBody
	public String getErpData(String number){
		try {
			Map paramMap = new HashMap();
			String baseKeys = org.apache.commons.lang3.StringUtils.join(ERProTranslateMap.TRANSLATE_HEAD_PARAM,",");
			String detailKeys = org.apache.commons.lang3.StringUtils.join(ERProTranslateMap.TRANSLATE_DETAIL_PARAM,",");
			paramMap.put("FormId",ERPApiCodeEnum.PRO.getCode());
			paramMap.put("FieldKeys",baseKeys+","+detailKeys);
			paramMap.put("FilterString",String.format("FBillNo='%s'",number));
			String res = ERPApiUitl.list(JSONObject.toJSONString(paramMap));
			ProductionRequisitionEntity productionRequisitionEntity = new ProductionRequisitionEntity();
			List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeEntityList = new ArrayList<>();
			if(analysisErpData(res,productionRequisitionEntity,productionRequisitionOrgNodeEntityList)){
				productionRequisitionService.addMain(productionRequisitionEntity,new ArrayList(), productionRequisitionOrgNodeEntityList);
				return productionRequisitionEntity.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean analysisErpData(String res, ProductionRequisitionEntity productionRequisitionEntity, List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeEntityList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List<List> result = StringUtils.toList(res);
		String[] headKeys = org.apache.commons.lang3.StringUtils.join(ERProTranslateMap.TRANSLATE_HEAD_PROPERTY,",").split(",");
		String[] bodyKeys = org.apache.commons.lang3.StringUtils.join(ERProTranslateMap.TRANSLATE_DETAIL_PROPERTY,",").split(",");
		for (int i = 0; i < result.size(); i++) {
			List inList = result.get(i);
			if(headKeys.length+bodyKeys.length==inList.size()){
				if(productionRequisitionEntity.getReceiptCode()==null){
					ReflactUtil.reflact(headKeys,ERProTranslateMap.TRANSLATE_HEAD_TYPE,productionRequisitionEntity,inList.subList(0,headKeys.length));
					productionRequisitionEntity.setAcquireTime(new Date());
				}
				ProductionRequisitionOrgNodeEntity productionRequisitionOrgNodeEntity = new ProductionRequisitionOrgNodeEntity();
				ReflactUtil.reflact(bodyKeys,ERProTranslateMap.TRANSLATE_DETAIL_TYPE,productionRequisitionOrgNodeEntity,inList.subList(headKeys.length,inList.size()));
				productionRequisitionOrgNodeEntityList.add(productionRequisitionOrgNodeEntity);
			}

		}
		return true;
	}
	
	
	/**
	 * 加载明细列表[生产领料单配料物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "productionRequisitionNodeList")
	public ModelAndView productionRequisitionNodeList(ProductionRequisitionEntity productionRequisition, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = productionRequisition.getId();
		//===================================================================================
		//查询-生产领料单配料物料详情
	    String hql0 = "from ProductionRequisitionNodeEntity where 1 = 1 AND inspectId = ? ";
		try{
		    List<ProductionRequisitionNodeEntity> productionRequisitionNodeEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("productionRequisitionNodeList", productionRequisitionNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/invoices/productionRequisitionNodeList");
	}
	/**
	 * 加载明细列表[销售出库原始物料详情]
	 * 
	 * @return
	 */
	@RequestMapping(params = "productionRequisitionOrgNodeList")
	public ModelAndView productionRequisitionOrgNodeList(ProductionRequisitionEntity productionRequisition, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = productionRequisition.getId();
		//===================================================================================
		//查询-销售出库原始物料详情
	    String hql1 = "from ProductionRequisitionOrgNodeEntity where 1 = 1 AND inspectId = ? ";
		try{
		    List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("productionRequisitionOrgNodeList", productionRequisitionOrgNodeEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/invoices/productionRequisitionOrgNodeList");
	}
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(ProductionRequisitionEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<ProductionRequisitionEntity> listProductionRequisitions = this.productionRequisitionService.getListByCriteriaQuery(query,true);
		return Result.success(listProductionRequisitions);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ProductionRequisitionEntity task = productionRequisitionService.get(ProductionRequisitionEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ProductionRequisitionEntity productionRequisition, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProductionRequisitionEntity>> failures = validator.validate(productionRequisition);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		productionRequisitionService.save(productionRequisition);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = productionRequisition.getId();
		URI uri = uriBuilder.path("/rest/productionRequisitionController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ProductionRequisitionEntity productionRequisition) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProductionRequisitionEntity>> failures = validator.validate(productionRequisition);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		productionRequisitionService.saveOrUpdate(productionRequisition);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		productionRequisitionService.deleteEntityById(ProductionRequisitionEntity.class, id);
	}

	@RequestMapping(params = "dosing")
	@ResponseBody
	public Object dosing(String remainData,String requisitionWorkshopCode){
		List<ProductionRequisitionNodeEntity> result = new ArrayList<ProductionRequisitionNodeEntity>();
		if(StringUtil.isNotEmpty(remainData)) {
			Map<String, Integer> remainDataMap = JSONObject.parseObject(remainData, Map.class);
			String sql = "from MaterialWarehousIOEntity where ioType in ('1','3') and materialCode = ? order by virtualRepositoryNumber desc,warehousingDate";
			for (Map.Entry<String,Integer> entry: remainDataMap.entrySet()){
				int val = entry.getValue();
				List<MaterialWarehousIOEntity> materialWarehousIOEntityList = productionRequisitionService.findHql(sql, entry.getKey());
				for (int i = 0,len = materialWarehousIOEntityList.size(); i < len && val>0; i++) {
					MaterialWarehousIOEntity materialWarehousIOEntity = materialWarehousIOEntityList.get(i);
					int count = Integer.valueOf(materialWarehousIOEntity.getWarehouseOutNumber());
					//与领料车间相同的虚拟仓库原料才可以参与配料
					if(StringUtil.equals(materialWarehousIOEntity.getVirtualRepository(),requisitionWorkshopCode)){
						count += MathUtil.toInt(materialWarehousIOEntity.getVirtualRepositoryNumber());

					}
					ProductionRequisitionNodeEntity productionRequisitionNodeEntity = new ProductionRequisitionNodeEntity();
					//数量
					productionRequisitionNodeEntity.setRawMaterialNum(String.valueOf(val>=count?count:val));
					val -= count;
					productionRequisitionNodeEntity.setRawMaterialSerino(materialWarehousIOEntity.getMaterialSerino());
					productionRequisitionNodeEntity.setRawMaterialCode(materialWarehousIOEntity.getMaterialCode());
					productionRequisitionNodeEntity.setRawMaterialName(materialWarehousIOEntity.getMaterialName());
					productionRequisitionNodeEntity.setUnit(materialWarehousIOEntity.getUnit());
					productionRequisitionNodeEntity.setBatchNumber(materialWarehousIOEntity.getBatchNumber());
					productionRequisitionNodeEntity.setWarehousingDate(materialWarehousIOEntity.getWarehousingDate());
					result.add(productionRequisitionNodeEntity);
				}
			}
		}
		return result;
	}
}
