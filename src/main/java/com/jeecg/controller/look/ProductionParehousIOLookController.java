package com.jeecg.controller.look;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.entity.look.ProductionParehousIOLookEntity;
import com.jeecg.service.look.ProductionParehousIOLookServiceI;
import com.jeecg.util.DictionaryUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 生产看板
 * @author zhangdaihao
 * @date 2019-10-02 01:31:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/productionParehousIOLookController")
public class ProductionParehousIOLookController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProductionParehousIOLookController.class);

	@Autowired
	private ProductionParehousIOLookServiceI productionParehousIOLookService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	


	/**
	 * 生产看板列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		dictionaryUtil.writeDicList(request,"unitDic");
		return new ModelAndView("com/jeecg/look/productionParehousIOLookNew");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProductionParehousIOLookEntity productionParehousIOLook,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductionParehousIOLookEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productionParehousIOLook, request.getParameterMap());
		this.productionParehousIOLookService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params="listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(String workshop) {
		String sql = "SELECT " +
				" p.product_name productName, " +
				"p.plan_prodct_number planProdctNumber, " +
				"p.size size, " +
				"p.producted_number productedNumber, " +
				"p.plan_finish_date planFinishDate, " +
				"p.unit unit, " +
				"p.production_dispatching_number productionDispatchingNumber, " +
				"p.workshop workshop " +
				"FROM " +
				" t_production_warehous_io_look p " +
				"WHERE " +
				" p.workshop = ? " +
				"ORDER BY " +
				" create_date desc " +
				"LIMIT 1";

		Map<String, Object> result = this.systemService.findOneForJdbc(sql,workshop);
		return JSONObject.toJSON(result);
	}
}
