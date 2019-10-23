package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.warehous.FinishedWarehousIOServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("finishedWarehousIOService")
@Transactional
public class FinishedWarehousIOServiceImpl extends CommonServiceImpl implements FinishedWarehousIOServiceI {
	
}