package com.jeecg.util;

import org.jeecgframework.core.util.DateUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReflactUtil {

    public static boolean reflact(String[] propertys,Class[] types,Object o,List<String> res) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cla = o.getClass();
        for (int i = 0; i < res.size(); i++) {
            String methodName = "set"+ StringUtils.firstUpperCase(propertys[i]);
            Method method = cla.getMethod(methodName,types[i]);
            Object param = res.get(i);
            if(types[i] == Date.class){
                param = DateUtils.str2Date(res.get(i).replace("T"," "),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
            }else if(types[i] == Double.class){
                param = MathUtil.toDouble(res.get(i));
            }
            method.invoke(o,param);
        }
        return true;
    }
}
