package com.jeecg.util;

public class MathUtil {

    public static Double toDouble(Object val){
        if(val==null){
            return 0D;
        }
        return Double.valueOf(String.valueOf(val));
    }

    public static int toInt(Object val){
        if(val==null){
            return 0;
        }
        return toDouble(String.valueOf(val)).intValue();
    }
}
