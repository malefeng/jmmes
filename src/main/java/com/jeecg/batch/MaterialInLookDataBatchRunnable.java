package com.jeecg.batch;

import com.jeecg.entity.look.MaterialWarehouIOLookEntity;
import com.jeecg.util.MathUtil;
import org.apache.commons.collections.MapUtils;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;

import java.math.BigDecimal;
import java.util.*;

public class MaterialInLookDataBatchRunnable implements Runnable {

    private String receivingOrderNumber;

    private SystemService systemService;

    public MaterialInLookDataBatchRunnable(String receivingOrderNumber, SystemService systemService) {
        this.receivingOrderNumber = receivingOrderNumber;
        this.systemService = systemService;
    }

    @Override
    public synchronized void run() {
        String sql = "SELECT " +
                " T.RECEIPT_CODE 'RECEIVINGORDERNUMBER', " +
                " T.ORDER_NUMBER 'PURCHASEORDERNUMBER', " +
                " T.SALES_ORDER_NUMBER 'SALESORDERNUMBER', " +
                " N.RAW_MATERIAL_CODE 'MATERIALCODE', " +
                " N.RAW_MATERIAL_NAME 'MATERIALNAME', " +
                " N.RAW_MATERIAL_SIZE 'MATERIALSIZE', " +
                " S.SUPPLIER_ATTR 'SUPPLIERTYPE', " +
                " N.RECEIVENUMBER, " +
                " W.INSERTNUMBER, " +
                " T.CREATE_TIME " +
                "FROM " +
                " ( " +
                "  SELECT " +
                "   * " +
                "  FROM " +
                "   t_purchase_receipt_list " +
                "  WHERE " +
                "   RECEIPT_CODE = ? " +
                " ) T " +
                "INNER JOIN ( " +
                " SELECT " +
                "  SUM(ACTUAL_RECEIVED_NUMBER) 'RECEIVENUMBER', " +
                "  INSPECT_ID, " +
                "  RAW_MATERIAL_CODE, " +
                "  RAW_MATERIAL_NAME, " +
                "  RAW_MATERIAL_SIZE " +
                " FROM " +
                "  t_purchase_receipt_node " +
                " GROUP BY " +
                "  INSPECT_ID, " +
                "  RAW_MATERIAL_CODE " +
                ") N ON T.ID = N.INSPECT_ID " +
                "LEFT JOIN t_supplier_list S ON T.SUPPLIER_CODE = S.SUPPLIER_CODE " +
                "LEFT JOIN ( " +
                " SELECT " +
                "  SUM(WAREHOUSING_NUMBER) 'INSERTNUMBER', " +
                "  RECEIVING_ORDER_NUMBER, " +
                "material_code " +
                " FROM " +
                "  t_material_warehous_io_list " +
                "where RECEIVING_ORDER_NUMBER = ? " +
                " GROUP BY " +
                "  RECEIVING_ORDER_NUMBER, " +
                "  material_code " +
                ") W ON W.material_code = N.RAW_MATERIAL_CODE " +
                "GROUP BY " +
                " N.RAW_MATERIAL_CODE";
        List<Map<String, Object>> resultMap = this.systemService.findForJdbc(sql, this.receivingOrderNumber, this.receivingOrderNumber);

        if(!ListUtils.isNullOrEmpty(resultMap)){
            Map<String,Map> newDataMap = new HashMap();
            for (Map<String, Object> map :resultMap) {
                newDataMap.put(String.valueOf(map.get("MATERIALCODE")),map);
            }
            List<MaterialWarehouIOLookEntity> materialWarehouIOLookEntities = this.systemService.findByProperty(MaterialWarehouIOLookEntity.class, "receivingOrderNumber", this.receivingOrderNumber);
            //对于已有的看板数据进行更新或删除
            for (MaterialWarehouIOLookEntity materialWarehouIOLookEntity : materialWarehouIOLookEntities) {
                Map dataNodeMap = newDataMap.get(materialWarehouIOLookEntity.getMaterialCode());
                if(MapUtils.isNotEmpty(dataNodeMap)){
                    materialWarehouIOLookEntity.setReceiveNumber(String.valueOf(dataNodeMap.get("RECEIVENUMBER")));
                    materialWarehouIOLookEntity.setInsertNumber(String.valueOf(dataNodeMap.get("INSERTNUMBER")));
                    //完成率保留两位小数，百分制
                    Double ratio = new Double(materialWarehouIOLookEntity.getInsertNumber())*100/new Double(materialWarehouIOLookEntity.getReceiveNumber());
                    materialWarehouIOLookEntity.setInsertRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if(Double.valueOf(materialWarehouIOLookEntity.getInsertNumber())>=Double.valueOf(materialWarehouIOLookEntity.getReceiveNumber())){
                        materialWarehouIOLookEntity.setInsertTime(new Date());
                    }
                    newDataMap.remove(materialWarehouIOLookEntity.getMaterialCode());
                    this.systemService.updateEntitie(materialWarehouIOLookEntity);
                }else{
                    this.systemService.delete(materialWarehouIOLookEntity);
                }
            }
            //对于没有的进行新增
            if(MapUtils.isNotEmpty(newDataMap)) {
                List newDataList = new ArrayList();
                for (Map.Entry<String, Map> entry : newDataMap.entrySet()) {
                    Map dataNodeMap = entry.getValue();
                    MaterialWarehouIOLookEntity materialWarehouIOLookEntity = new MaterialWarehouIOLookEntity();
                    materialWarehouIOLookEntity.setCreateDate(new Date());
                    materialWarehouIOLookEntity.setReceivingOrderNumber(String.valueOf(dataNodeMap.get("RECEIVINGORDERNUMBER")));
                    materialWarehouIOLookEntity.setPurchaseOrderNumber(String.valueOf(dataNodeMap.get("PURCHASEORDERNUMBER")));
                    materialWarehouIOLookEntity.setSalesOrderNumber(String.valueOf(dataNodeMap.get("SALESORDERNUMBER")));
                    materialWarehouIOLookEntity.setMaterialCode(String.valueOf(dataNodeMap.get("MATERIALCODE")));
                    materialWarehouIOLookEntity.setMaterialName(String.valueOf(dataNodeMap.get("MATERIALNAME")));
                    materialWarehouIOLookEntity.setMaterialSize(String.valueOf(dataNodeMap.get("MATERIALSIZE")));
                    materialWarehouIOLookEntity.setSupplierType(String.valueOf(dataNodeMap.get("SUPPLIERTYPE")));
                    materialWarehouIOLookEntity.setReceiveNumber(String.valueOf(MathUtil.toInt(dataNodeMap.get("RECEIVENUMBER"))));
                    materialWarehouIOLookEntity.setInsertNumber(String.valueOf(MathUtil.toInt(dataNodeMap.get("INSERTNUMBER"))));
                    //完成率保留两位小数，百分制
                    Double ratio = MathUtil.toDouble(materialWarehouIOLookEntity.getInsertNumber())*100/MathUtil.toDouble(materialWarehouIOLookEntity.getReceiveNumber());
                    materialWarehouIOLookEntity.setInsertRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if(MathUtil.toDouble(materialWarehouIOLookEntity.getInsertNumber())>=MathUtil.toDouble(materialWarehouIOLookEntity.getReceiveNumber())){
                        materialWarehouIOLookEntity.setInsertTime(new Date());
                    }
                    newDataList.add(materialWarehouIOLookEntity);
                }
                this.systemService.batchSave(newDataList);
            }
        }
    }
}
