package com.jeecg.service.impl.print;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.print.PurchaseReceiptNodeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("purchaseReceiptNodeService")
@Transactional
public class PurchaseReceiptNodeServiceImpl extends CommonServiceImpl implements PurchaseReceiptNodeServiceI {
	
}