package com.jeecg.service.impl.qrcode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.qrcode.QRCodeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("qRCodeService")
@Transactional
public class QRCodeServiceImpl extends CommonServiceImpl implements QRCodeServiceI {
	
}