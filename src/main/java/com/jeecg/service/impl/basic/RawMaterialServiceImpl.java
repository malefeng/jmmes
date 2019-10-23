package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.RawMaterialServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("rawMaterialService")
@Transactional
public class RawMaterialServiceImpl extends CommonServiceImpl implements RawMaterialServiceI {
	
}