package com.jeecg.service.impl.print;

import com.jeecg.service.print.FinishedProductPrintServiceI;
import org.jeecgframework.core.common.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("finishedProductPrintService")
@Transactional
public class FinishedProductPrintServiceImpl extends CommonServiceImpl implements FinishedProductPrintServiceI {
	
}