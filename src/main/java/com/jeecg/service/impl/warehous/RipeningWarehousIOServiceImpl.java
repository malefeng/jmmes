package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.warehous.RipeningWarehousIOServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ripeningWarehousIOService")
@Transactional
public class RipeningWarehousIOServiceImpl extends CommonServiceImpl implements RipeningWarehousIOServiceI {
	
}