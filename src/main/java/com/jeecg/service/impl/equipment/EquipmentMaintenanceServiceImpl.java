package com.jeecg.service.impl.equipment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.equipment.EquipmentMaintenanceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("equipmentMaintenanceService")
@Transactional
public class EquipmentMaintenanceServiceImpl extends CommonServiceImpl implements EquipmentMaintenanceServiceI {
	
}