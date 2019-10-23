package com.jeecg.service.basic;

import com.jeecg.entity.basic.FinishedProductEntity;
import org.jeecgframework.core.common.service.CommonService;

public interface FinishedProductServiceI extends CommonService{

    FinishedProductEntity getByFinishedSerino(String finishedSerino);
}
