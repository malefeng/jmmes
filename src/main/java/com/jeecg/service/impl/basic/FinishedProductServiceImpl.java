package com.jeecg.service.impl.basic;

import com.jeecg.entity.basic.FinishedProductEntity;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.FinishedProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import java.util.List;

@Service("finishedProductService")
@Transactional
public class FinishedProductServiceImpl extends CommonServiceImpl implements FinishedProductServiceI {

    @Autowired
    private SystemService service;

    /**
     * 根据成品编号查询成品基础信息
     * @param finishedSerino
     * @return
     */
    @Override
    public FinishedProductEntity getByFinishedSerino(String finishedSerino){
        String hql = "from FinishedProductEntity where finishedCode = (select finishedCode from FinishedProductionEntity where finishedSerino = ?)";
        List<FinishedProductEntity> finishedProductEntityList = service.findHql(hql, finishedSerino);
        if(!ListUtils.isNullOrEmpty(finishedProductEntityList)){
            return finishedProductEntityList.get(0);
        }
        return null;
    }
	
}