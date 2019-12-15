package com.jeecg.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static String firstUpperCase(String str) {
        return null==str||str.length()<1?"":str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static List<List> toList(String str){
        List result = new ArrayList();
        str = str.substring(1,str.length()-1);
        String[] inner = str.split("],\\[");
        if(inner.length>1){
            for (int i = 0; i < inner.length; i++) {
                String s = inner[i];
                s = s.replace("[","").replace("]","");
                String[] ins = s.split(",");
                List inList = new ArrayList();
                for (int i1 = 0; i1 < ins.length; i1++) {
                    inList.add(ins[i1]);
                }
                result.add(inList);
            }
        }else{
            str = str.substring(1,str.length()-1);
            final String finalStr = str;
            result.add(Arrays.asList(finalStr.split(",")));
        }
        return result;
    }
}
