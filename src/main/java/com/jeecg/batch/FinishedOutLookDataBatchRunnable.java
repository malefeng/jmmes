package com.jeecg.batch;

import com.jeecg.entity.look.FinishedWarehousIOLookEntity;
import com.jeecg.util.MathUtil;
import org.apache.commons.collections.MapUtils;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;

import java.math.BigDecimal;
import java.util.*;

public class FinishedOutLookDataBatchRunnable implements Runnable {

    private String receiptCode;

    private SystemService systemService;

    public FinishedOutLookDataBatchRunnable(String receiptCode, SystemService systemService) {
        this.receiptCode = receiptCode;
        this.systemService = systemService;
    }

    @Override
    public synchronized void run() {
        //新增看板数据
        List<FinishedWarehousIOLookEntity> newLook = new ArrayList();
        //已有看板数据
        List<FinishedWarehousIOLookEntity> finishedWarehousIOLookEntityList = systemService.findByProperty(FinishedWarehousIOLookEntity.class,"salesDeliveryOrderNumber",receiptCode);
        String sql = "SELECT " +
                " o.RECEIPT_CODE salesDeliveryOrderNumber, " +
                " o.SEND_NOTICE_NUMBER deliveryAdviceOrderNumber, " +
                " n.FINISHED_CODE materialCode, " +
                " n.finished_name materialName, " +
                " n.finished_size materialSize, " +
                " o.CUSTOMER_CODE customerCode, " +
                " count(*) shouldSendNumber, " +
                " sum( " +
                "  IF ( " +
                "   ISNULL(w.IO_TYPE), " +
                "   0, " +
                "   w.IO_TYPE " +
                "  ) " +
                " ) actualSendNumber, " +
                " w.IO_TYPE " +
                "FROM " +
                " t_sales_release_node n " +
                "INNER JOIN ( " +
                " SELECT " +
                "  * " +
                " FROM " +
                "  t_sales_release_order " +
                " WHERE " +
                "  RECEIPT_CODE = ? " +
                ") o ON n.RECEIPT_ID = o.id " +
                "LEFT JOIN ( " +
                " SELECT " +
                "  FINISHED_SERINO, " +
                " " +
                " IF (IO_TYPE = 2, 1, 0) IO_TYPE " +
                " FROM " +
                "  t_finished_warehous_io_list " +
                " WHERE " +
                "  SALES_DELIVERY_ORDER_NUMBER = ? " +
                ") w ON w.FINISHED_SERINO = n.FINISHED_SERINO " +
                "GROUP BY " +
                " n.FINISHED_CODE";
        List<Map<String, Object>> newDataList = systemService.findForJdbc(sql, receiptCode, receiptCode);
        if(!ListUtils.isNullOrEmpty(newDataList)){
            Map<Object,Map<String,Object>> newData  = new HashMap();
            for (Map<String,Object> map:newDataList) {
                if(MapUtils.isNotEmpty(map)){
                    newData.put(map.get("materialCode"),map);
                }
            }
            //对于已有的看板数据进行更新或删除
            if(!ListUtils.isNullOrEmpty(finishedWarehousIOLookEntityList)) {
                for (FinishedWarehousIOLookEntity finishedWarehousIOLookEntity : finishedWarehousIOLookEntityList) {
                    Map entryMap = newData.get(finishedWarehousIOLookEntity.getMaterialCode());
                    if(MapUtils.isNotEmpty(entryMap)){
                        finishedWarehousIOLookEntity.setActualSendNumber(MathUtil.toInt(entryMap.get("actualSendNumber")));
                        finishedWarehousIOLookEntity.setShouldSendNumber(MathUtil.toInt(entryMap.get("shouldSendNumber")));
                        //完成率保留两位小数，百分制
                        Double ratio = MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber())*100/MathUtil.toDouble(finishedWarehousIOLookEntity.getShouldSendNumber());
                        finishedWarehousIOLookEntity.setSendRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        if(MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber())>=MathUtil.toDouble(finishedWarehousIOLookEntity.getShouldSendNumber())){
                            finishedWarehousIOLookEntity.setSendFinishTime(new Date());
                        }
                        newData.remove(finishedWarehousIOLookEntity.getMaterialCode());
                        systemService.updateEntitie(finishedWarehousIOLookEntity);
                    }else{
                        systemService.delete(finishedWarehousIOLookEntity);
                    }
                }
            }

            //对于没有的进行新增
            if(MapUtils.isNotEmpty(newData)){
                for (Map.Entry<Object, Map<String, Object>> entry : newData.entrySet()) {
                    Map<String, Object> entryValue = entry.getValue();
                    FinishedWarehousIOLookEntity finishedWarehousIOLookEntity = new FinishedWarehousIOLookEntity();
                    finishedWarehousIOLookEntity.setCreateDate(new Date());
                    finishedWarehousIOLookEntity.setSalesDeliveryOrderNumber(String.valueOf(entryValue.get("salesDeliveryOrderNumber")));
                    finishedWarehousIOLookEntity.setDeliveryAdviceOrderNumber(String.valueOf(entryValue.get("deliveryAdviceOrderNumber")));
                    finishedWarehousIOLookEntity.setMaterialCode(String.valueOf(entry.getKey()));
                    finishedWarehousIOLookEntity.setMaterialName(String.valueOf(entryValue.get("materialName")));
                    finishedWarehousIOLookEntity.setMaterialSize(String.valueOf(entryValue.get("materialSize")));
                    finishedWarehousIOLookEntity.setCustomerCode(String.valueOf(entryValue.get("customerCode")));
                    finishedWarehousIOLookEntity.setShouldSendNumber(MathUtil.toInt(entryValue.get("shouldSendNumber")));
                    finishedWarehousIOLookEntity.setActualSendNumber(MathUtil.toInt(entryValue.get("actualSendNumber")));//完成率保留两位小数，百分制
                    Double ratio = MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber()*100)/finishedWarehousIOLookEntity.getShouldSendNumber();
                    finishedWarehousIOLookEntity.setSendRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if(MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber())>=MathUtil.toDouble(finishedWarehousIOLookEntity.getShouldSendNumber())){
                        finishedWarehousIOLookEntity.setSendFinishTime(new Date());
                    }
                    newLook.add(finishedWarehousIOLookEntity);
                }
                systemService.batchSave(newLook);
            }
            //清空缓存
            systemService.getSession().flush();
            systemService.getSession().clear();
        }
    }
}
