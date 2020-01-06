package com.jeecg.constant;

import java.util.*;

public class ERPurTranslateMap {

    public static Map TRANSLATE_HEAD;
    public static String[] TRANSLATE_HEAD_PROPERTY;
    public static String[] TRANSLATE_HEAD_PARAM;
    public static Class[] TRANSLATE_HEAD_TYPE;
    public static Map TRANSLATE_DETAIL;
    public static String[] TRANSLATE_DETAIL_PROPERTY;
    public static String[] TRANSLATE_DETAIL_PARAM;
    public static Class[] TRANSLATE_DETAIL_TYPE;

    static {
        /*
        TRANSLATE_HEAD = new HashMap(){{
            //Result
            put("receiptCode","FBillNo");//收料单编号
            put("receivingCompanyCode","FStockOrgId");//收货组织
            put("purchasingCompanyCode","FPurOrgId");//采购组织
//            put("receiptDate","");//单据日期--采购日期
            put("receivingOrgCode","FReceiveDeptId");//收获部门
            put("purchasingOrgCode","FPurDeptId");//采购部门
            put("supplierCode","FSupplierId");//供应商
            put("receivingPersonCode","FReceiverId");//收货员
            put("purchasingPersonCode","FPurchaserId");//采购员
            put("verifierCode","FApproverId");//审核人
            put("verifyDate","FApproveDate");//审核日期
            put("createTime","FCreateDate");//单据创建日期
//            put("","");//采购订单号
//            put("","");//销售订单号
            put("acquireTime","FConfirmDate");//确认时间
            put("readPersonCode","FConfirmerId");//确认人
        }};
        */
        TRANSLATE_HEAD_PROPERTY = new String[]{"receiptCode","receivingCompanyCode","purchasingCompanyCode",
                "receivingOrgCode","purchasingOrgCode","supplierCode","receivingPersonCode","purchasingPersonCode","verifierCode","createTime","acquireTime","readPersonCode"};
        TRANSLATE_HEAD_PARAM = new String[]{"FBillNo","FStockOrgId","FPurOrgId","FReceiveDeptId","FPurDeptId",
                "FSupplierId","FReceiverId","FPurchaserId","FApproverId","FApproveDate","FCreateDate","FConfirmDate"};
        TRANSLATE_HEAD_TYPE = new Class[]{String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,Date.class, Date.class,String.class};

        /*
        TRANSLATE_DETAIL = new HashMap(){{
            //Result.ReceiveEntry.
            put("rawMaterialCode","FMaterialId");//物料编码
//            put("","");//物料代码
            put("rawMaterialName","FMaterialName");//物料名称
            put("rawMaterialSize","FMateriaModel");//规格型号
//            put("","FUnitId");//单位
            put("actualArrivalNumber","FActlandQty");//实到数量
            put("actualReceivedNumber","FActReceiveQty");//实收数量
//            put("","FRejectQty");//拒收数量
            put("repositoryCode","FStockID");//仓库
//            put("repositorySpace","FStockLocId");//库位
            put("inventoryStatus","FStockStatusId");//库存状态
            put("rejectionReason","FRejectReason");//拒收原因
        }};
        */
        TRANSLATE_DETAIL_PROPERTY = new String[]{"rawMaterialCode","rawMaterialName","rawMaterialSize","actualArrivalNumber","actualReceivedNumber","repositoryCode","inventoryStatus","rejectionReason","repositorySpace","attr1"};
        TRANSLATE_DETAIL_PARAM = new String[]{"FMaterialId","FMaterialName","FMateriaModel","FActlandQty","FActReceiveQty","FStockID","FStockStatusId","FRejectReason","FStockLocId","FSupplierLot"};
        TRANSLATE_DETAIL_TYPE = new Class[]{String.class,String.class,String.class,Double.class,Double.class,String.class,String.class,String.class,String.class,String.class};
    }
}
