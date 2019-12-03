package com.jeecg.util;

public class StringUtils {

    public static String firstUpperCase(String str) {
        return null==str||str.length()<1?"":str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
