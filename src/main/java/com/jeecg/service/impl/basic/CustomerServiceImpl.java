package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.CustomerServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("customerService")
@Transactional
public class CustomerServiceImpl extends CommonServiceImpl implements CustomerServiceI {
	
}