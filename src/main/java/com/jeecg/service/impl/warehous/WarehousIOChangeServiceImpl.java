package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.warehous.WarehousIOChangeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("warehousIOChangeService")
@Transactional
public class WarehousIOChangeServiceImpl extends CommonServiceImpl implements WarehousIOChangeServiceI {
	
}