package com.jeecg.service.production;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.production.FinishedProductionEntity;
import com.jeecg.entity.production.FinishedProductionNodeEntity;

public interface FinishedProductionServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FinishedProductionEntity finishedProduction,
	        List<FinishedProductionNodeEntity> finishedProductionNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FinishedProductionEntity finishedProduction,
	        List<FinishedProductionNodeEntity> finishedProductionNodeList);
	public void delMain (FinishedProductionEntity finishedProduction);
}
