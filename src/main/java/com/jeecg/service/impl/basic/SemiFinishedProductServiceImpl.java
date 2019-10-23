package com.jeecg.service.impl.basic;

import com.jeecg.entity.basic.SemiFinishedProductEntity;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.service.basic.SemiFinishedProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import java.util.List;

@Service("semiFinishedProductService")
@Transactional
public class SemiFinishedProductServiceImpl extends CommonServiceImpl implements SemiFinishedProductServiceI {

    @Autowired
    private SystemService service;

    /**
     * 根据半成品编号查询半成品基础信息
     * @param semiFinishedSerino
     * @return
     */
    @Override
    public SemiFinishedProductEntity getBySemiFinishedSerino(String semiFinishedSerino){
        String hql = "from SemiFinishedProductEntity where semiFinishedCode = (select semiFinishedCode from SemiFinishedProductionEntity where semiFinishedSerino = ?)";
        List<SemiFinishedProductEntity> semiFinishedProductEntityList = service.findHql(hql, semiFinishedSerino);
        if(!ListUtils.isNullOrEmpty(semiFinishedProductEntityList)){
            return semiFinishedProductEntityList.get(0);
        }
        return null;
    }
}