package com.jeecg.constant;

import java.util.HashMap;
import java.util.Map;

public class ERSalTranslateMap {
    public static Map TRANSLATE_HEAD;
    public static String[] TRANSLATE_HEAD_PROPERTY;
    public static String[] TRANSLATE_HEAD_PARAM;
    public static Class[] TRANSLATE_HEAD_TYPE;
    public static Map TRANSLATE_DETAIL;
    public static String[] TRANSLATE_DETAIL_PROPERTY;
    public static String[] TRANSLATE_DETAIL_PARAM;
    public static Class[] TRANSLATE_DETAIL_TYPE;

    static {
        TRANSLATE_HEAD = new HashMap(){{
            put("receiptCode","");//销售出库单号
            put("sendNoticeNumber","");//发货通知单号
            put("receiptDate","");//日期
            put("currency","");//结算币别
            put("customerCode","");//客户
            put("deliveryPoints","");//交货地点
            put("commonCarrierCode","");//承运商
            put("waybillNumber","");//运输单号
            put("repositoryManager","");//仓管员
            put("receiptState","");//单据状态
            put("salesmanCode","");//销售员
            put("readPersonCode","");//读取人
            put("acquireTime","");//获取时间
        }};

        TRANSLATE_HEAD_PROPERTY = new String[]{"receiptCode","sendNoticeNumber","receiptDate","currency","customerCode",
                "deliveryPoints","commonCarrierCode","waybillNumber","repositoryManager","receiptState","salesmanCode","readPersonCode","acquireTime"};
        TRANSLATE_DETAIL = new HashMap(){{
           put("finishedCode",""); //成品代码
           put("finishedName",""); //成品名称
           put("finishedSize",""); //规格型号
           put("supplierAttr",""); //供应商属性
           put("wideInWidth",""); //宽幅
           put("inventoryUnit",""); //库存单位
           put("shouldSendNumber",""); //应发数量
           put("actualSendNumber",""); //实发数量
           put("valuationUnit",""); //计价单位
           put("valuationNumber",""); //计价数量
           put("unitPrice",""); //单价
           put("unitPriceWithTax",""); //含税单价
           put("isFree",""); //是否赠品
           put("batchNumber",""); //批号
           put("repositoryCode",""); //仓库
           put("productionOrderNumber",""); //生产订单号
           put("repositoryStatus",""); //库存状态
           put("remark",""); //备注
        }};
    }
}
