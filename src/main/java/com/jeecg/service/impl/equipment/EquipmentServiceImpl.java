package com.jeecg.service.impl.equipment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.equipment.EquipmentServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl extends CommonServiceImpl implements EquipmentServiceI {
	
}