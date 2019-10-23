package com.jeecg.service.production;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.production.FinishedInspectEntity;
import com.jeecg.entity.production.FinishedFirstInspectEntity;
import com.jeecg.entity.production.FinishedLastInspectEntity;

public interface FinishedInspectServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FinishedInspectEntity finishedInspect,
	        List<FinishedFirstInspectEntity> finishedFirstInspectList,List<FinishedLastInspectEntity> finishedLastInspectList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FinishedInspectEntity finishedInspect,
	        List<FinishedFirstInspectEntity> finishedFirstInspectList,List<FinishedLastInspectEntity> finishedLastInspectList);
	public void delMain (FinishedInspectEntity finishedInspect);
}
