package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.SupplierServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl extends CommonServiceImpl implements SupplierServiceI {
	
}