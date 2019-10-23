package com.jeecg.service.invoices;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.invoices.ProductionRequisitionEntity;
import com.jeecg.entity.invoices.ProductionRequisitionNodeEntity;
import com.jeecg.entity.invoices.ProductionRequisitionOrgNodeEntity;

public interface ProductionRequisitionServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ProductionRequisitionEntity productionRequisition,
	        List<ProductionRequisitionNodeEntity> productionRequisitionNodeList,List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ProductionRequisitionEntity productionRequisition,
	        List<ProductionRequisitionNodeEntity> productionRequisitionNodeList,List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList);
	public void delMain (ProductionRequisitionEntity productionRequisition);
}
