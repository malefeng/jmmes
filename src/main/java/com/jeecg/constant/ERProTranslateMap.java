package com.jeecg.constant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ERProTranslateMap {
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
           put("receiptCode","FBillNo");//领料单编号
           put("requisitionDate","FDate");//领料日期
//           put("requisitionWorkshopCode","FWorkShopId");//领料车间
//           put("repositoryCode","FStockId0");//领料仓库
           put("repositoryManagerCode","FSTOCKERID");//仓管员
           put("acquirePersonCode","FPickerId");//领料人
           put("productionOrderNumber","FMoBillNo");//生产订单号
           put("productionDispatchingNumber","");//生产派工单号---》根据生产订单号查询生产用料清单接口的编号
           put("finishedCode","FParentMaterialId");//成品代码
           put("finishedName","");//成品名称
           put("plannedProductionQuantity","");//计划生产数量
           put("plannedStartDate","");//计划开工日期
           put("plannedCompletionDate","");//计划完工日期
           put("readPersonCode","");//读取人
           put("acquireTime","");//获取时间
           put("creatorCode","FCreatorId");//制单人
        }};

        TRANSLATE_HEAD_PROPERTY = new String[]{"receiptCode","requisitionDate"/*,"requisitionWorkshopCode","repositoryCode"*/,"repositoryManagerCode",
                "acquirePersonCode","creatorCode"};
        TRANSLATE_HEAD_PARAM = new String[]{"FBillNo","FDate"/*,"FWorkShopId","FStockId0"*/,"FSTOCKERID","FPickerId","FCreatorId"};
        TRANSLATE_HEAD_TYPE = new Class[]{String.class, Date.class,/*String.class,String.class,*/String.class,
                String.class,String.class,String.class};

        TRANSLATE_DETAIL = new HashMap(){{
            put("rawMaterialCode","FMaterialId");//原料代码
            put("rawMaterialName","FMaterialName");//原料名称
            put("rawMaterialSize","FSpecification");//规格型号
            put("unit","FUnitID");//单位
            put("rawMaterialNum","FActualQty");//数量
            put("batchNumber","FLot");//批号
            put("productionOrderNumber","FMoBillNo");//生产订单号
            put("finishedName","FParentMaterialId");//成品名称(产品编码)
        }};
        TRANSLATE_DETAIL_PROPERTY = new String[]{"rawMaterialCode","rawMaterialName","rawMaterialSize","unit","rawMaterialNum","productionOrderNumber","finishedName"};
        TRANSLATE_DETAIL_PARAM = new String[]{"FMaterialId","FMaterialName","FSpecification","FUnitID","FActualQty","FMoBillNo","FParentMaterialId"};
        TRANSLATE_DETAIL_TYPE = new Class[]{String.class,String.class,String.class,String.class,String.class,String.class,String.class};
    }
}
