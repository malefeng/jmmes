package com.jeecg.service.invoices;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.invoices.SalesReleaseOrderEntity;
import com.jeecg.entity.invoices.SalesReleaseNodeEntity;
import com.jeecg.entity.invoices.SalesReleaseOrgNodeEntity;

public interface SalesReleaseOrderServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SalesReleaseOrderEntity salesReleaseOrder,
	        List<SalesReleaseNodeEntity> salesReleaseNodeList,List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SalesReleaseOrderEntity salesReleaseOrder,
	        List<SalesReleaseNodeEntity> salesReleaseNodeList,List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList);
	public void delMain (SalesReleaseOrderEntity salesReleaseOrder);
}
