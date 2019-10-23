package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.StorageServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("storageService")
@Transactional
public class StorageServiceImpl extends CommonServiceImpl implements StorageServiceI {
	
}