package com.jeecg.constant;

import java.util.HashMap;
import java.util.Map;

public class ERSalTranslateMap {
    public static Map PRO_TRANSLATE_HEAD;
    public static Map PRO_TRANSLATE_DETAIL;

    static {
        PRO_TRANSLATE_HEAD = new HashMap(){{
            put("","");//发货通知单号
            put("","");//日期
            put("","");//结算币别
            put("","");//客户
            put("","");//交货地点
            put("","");//承运商
            put("","");//运输单号
            put("","");//仓管员
            put("","");//单据状态
            put("","");//销售员
            put("","");//读取人
            put("","");//获取时间
        }};

        PRO_TRANSLATE_DETAIL = new HashMap(){{
           put("",""); //成品编号
           put("",""); //成品代码
           put("",""); //成品名称
           put("",""); //规格型号
           put("",""); //批号
           put("",""); //单价
           put("",""); //含税单价
           put("",""); //仓库
           put("",""); //生产订单号
        }};
    }
}
