package com.jeecg.constant;

import java.util.HashMap;
import java.util.Map;

public class ERPurTranslateMap {

    public static Map PUR_TRANSLATE_HEAD;
    public static Map PUR_TRANSLATE_DETAIL;

    static {
        PUR_TRANSLATE_HEAD = new HashMap(){{
            //Result
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

        PUR_TRANSLATE_DETAIL = new HashMap(){{
            //Result.PUR_ReceiveEntry.
            put("rawMaterialCode","FMaterialId");//物料编码
//            put("","");//物料代码
            put("rawMaterialName","FMaterialName");//物料名称
            put("rawMaterialSize","FMateriaModel");//规格型号
//            put("","FUnitId");//单位
            put("actualArrivalNumber","FActlandQty");//实到数量
            put("actualReceivedNumber","FActReceiveQty");//实收数量
            put("","FRejectQty");//拒收数量
            put("repositoryCode","FStockID");//仓库
//            put("","");//库位
            put("inventoryStatus","FStockStatusId");//库存状态
            put("","FRejectReason");//拒收原因
        }};
    }
}
