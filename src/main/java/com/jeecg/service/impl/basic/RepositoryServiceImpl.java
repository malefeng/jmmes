package com.jeecg.service.impl.basic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.RepositoryServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("repositoryService")
@Transactional
public class RepositoryServiceImpl extends CommonServiceImpl implements RepositoryServiceI {
	
}