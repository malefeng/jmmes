package com.jeecg.service.warehous;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.entity.warehous.MaterialWarehousNodeEntity;

public interface MaterialWarehousIOServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(MaterialWarehousIOEntity materialWarehousIO,
	        List<MaterialWarehousNodeEntity> materialWarehousNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MaterialWarehousIOEntity materialWarehousIO,
	        List<MaterialWarehousNodeEntity> materialWarehousNodeList);
	public void delMain (MaterialWarehousIOEntity materialWarehousIO);
}
