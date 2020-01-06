package com.jeecg.service.impl.common;

import com.jeecg.service.common.SequenceServiceI;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SequenceServiceImpl implements SequenceServiceI {

    @Autowired
    private SystemService systemService;
    @Autowired
    private ICommonDao dao;

    @Override
    public String nextVal(String key){
        SQLQuery sqlQuery = dao.getSession().createSQLQuery("select nextval('" + key + "')");
        List list = sqlQuery.list();
        return list!=null? String.valueOf(list.get(0)):null;
    }

    @Override
    public String getqrCode(String key){
        String result = "";
        String date = new SimpleDateFormat("yyMMdd").format(new Date());
        String sequnce = nextVal(key);
        if(StringUtils.isNotBlank(sequnce)){
            switch (key){
                case "material": result = "11"+date+StringUtils.leftPad(sequnce,4,"0"); break;
                case "semiFinished": result = "22"+date+StringUtils.leftPad(sequnce,4,"0"); break;
                case "finished": result = "33"+date+StringUtils.leftPad(sequnce,4,"0"); break;
                case "finishedItem": result = "99"+date+StringUtils.leftPad(sequnce,4,"0"); break;
                default: return result;
            }
        }
        return result;
    }
}
