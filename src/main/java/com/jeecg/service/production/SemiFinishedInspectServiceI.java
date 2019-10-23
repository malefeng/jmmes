package com.jeecg.service.production;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.production.SemiFinishedInspectEntity;
import com.jeecg.entity.production.SemiFinishedFirstInspectEntity;
import com.jeecg.entity.production.SemiFinishedLastInspectEntity;

public interface SemiFinishedInspectServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SemiFinishedInspectEntity semiFinishedInspect,
	        List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList,List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SemiFinishedInspectEntity semiFinishedInspect,
	        List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList,List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList);
	public void delMain (SemiFinishedInspectEntity semiFinishedInspect);
}
