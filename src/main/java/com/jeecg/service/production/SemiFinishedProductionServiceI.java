package com.jeecg.service.production;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.production.SemiFinishedProductionEntity;
import com.jeecg.entity.production.SemiFinishedProductionNodeEntity;

public interface SemiFinishedProductionServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SemiFinishedProductionEntity semiFinishedProduction,
	        List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SemiFinishedProductionEntity semiFinishedProduction,
	        List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList);
	public void delMain (SemiFinishedProductionEntity semiFinishedProduction);
}
