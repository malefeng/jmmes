package com.jeecg.service.checktask;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.checktask.CheckTaskEntity;
import com.jeecg.entity.checktask.CheckNodeEntity;

public interface CheckTaskServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(CheckTaskEntity checkTask,
	        List<CheckNodeEntity> checkNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CheckTaskEntity checkTask,
	        List<CheckNodeEntity> checkNodeList);
	public void delMain (CheckTaskEntity checkTask);
}
