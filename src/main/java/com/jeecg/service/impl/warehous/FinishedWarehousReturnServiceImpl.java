package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.warehous.FinishedWarehousReturnServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("finishedWarehousReturnService")
@Transactional
public class FinishedWarehousReturnServiceImpl extends CommonServiceImpl implements FinishedWarehousReturnServiceI {
	
}