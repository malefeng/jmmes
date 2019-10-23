package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.CommonCarrierServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("commonCarrierService")
@Transactional
public class CommonCarrierServiceImpl extends CommonServiceImpl implements CommonCarrierServiceI {
	
}