package com.jeecg.service.impl.print;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.print.SemiFinishedProductPrintServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("semiFinishedProductPrintService")
@Transactional
public class SemiFinishedProductPrintServiceImpl extends CommonServiceImpl implements SemiFinishedProductPrintServiceI {
	
}