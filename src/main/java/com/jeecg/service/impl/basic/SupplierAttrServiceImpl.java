package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.SupplierAttrServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("supplierAttrService")
@Transactional
public class SupplierAttrServiceImpl extends CommonServiceImpl implements SupplierAttrServiceI {
	
}