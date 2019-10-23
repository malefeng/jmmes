package com.jeecg.service.basic;

import com.jeecg.entity.basic.SemiFinishedProductEntity;
import org.jeecgframework.core.common.service.CommonService;

public interface SemiFinishedProductServiceI extends CommonService{

    SemiFinishedProductEntity getBySemiFinishedSerino(String semiFinishedSerino);
}
