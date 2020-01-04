package com.jeecg.service.production;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.production.FinishedInspectItemEntity;
import com.jeecg.entity.production.FinishedInspectItemNodeEntity;

public interface FinishedInspectItemServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FinishedInspectItemEntity finishedInspectItem,
	        List<FinishedInspectItemNodeEntity> finishedInspectItemNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FinishedInspectItemEntity finishedInspectItem,
	        List<FinishedInspectItemNodeEntity> finishedInspectItemNodeList);
	public void delMain (FinishedInspectItemEntity finishedInspectItem);
}
