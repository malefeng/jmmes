package com.jeecg.constant;

import java.util.HashMap;
import java.util.Map;

public class ERProTranslateMap {
    public static Map PRO_TRANSLATE_HEAD;
    public static Map PRO_TRANSLATE_DETAIL;

    static {
        PRO_TRANSLATE_HEAD = new HashMap(){{
           put("","");//领料日期
           put("","");//领料车间
           put("","");//领料仓库
           put("","");//仓管员
           put("","");//领料人
           put("","");//生产订单号
           put("","");//生产派工单号---》根据生产订单号查询生产用料清单接口的编号
           put("","");//成品代码
           put("","");//成品名称
           put("","");//计划生产数量
           put("","");//计划开工日期
           put("","");//计划完工日期
           put("","");//读取人
           put("","");//获取时间
           put("","");//制单人
        }};
        PRO_TRANSLATE_DETAIL = new HashMap(){{
            put("","");//原料代码
            put("","");//原料名称
            put("","");//规格型号
            put("","");//单位
            put("","");//数量
            put("","");//批号
            put("","");//生产订单号
            put("","");//成品名称
        }};
    }
}
