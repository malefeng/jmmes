package com.jeecg.batch;

import com.jeecg.entity.invoices.SalesReleaseOrgNodeEntity;
import com.jeecg.entity.look.FinishedWarehousIOLookEntity;
import com.jeecg.page.invoices.SalesReleaseOrderPage;
import com.jeecg.util.MathUtil;
import org.apache.commons.collections.MapUtils;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;

import java.math.BigDecimal;
import java.util.*;

public class FinishedOutLookDataBatchRunnable implements Runnable {
    private SalesReleaseOrderPage salesReleaseOrderPage;

    private SystemService systemService;

    public FinishedOutLookDataBatchRunnable(SalesReleaseOrderPage salesReleaseOrderPage, SystemService systemService) {
        this.salesReleaseOrderPage = salesReleaseOrderPage;
        this.systemService = systemService;
    }

    @Override
    public synchronized void run() {
        //新增看板数据
        List<FinishedWarehousIOLookEntity> newLook = new ArrayList();
        //已有看板数据
        List<FinishedWarehousIOLookEntity> finishedWarehousIOLookEntityList = systemService.findByProperty(FinishedWarehousIOLookEntity.class,"salesDeliveryOrderNumber",salesReleaseOrderPage.getReceiptCode());
        //成品信息
        List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList = salesReleaseOrderPage.getSalesReleaseOrgNodeList();
        //计划，实际发货数量
        Map<String,int[]> salesNumMap = new HashMap();
        //成品名称
        Map<String,String> salesNameMap = new HashMap();
        //型号
        Map<String,String> salesSizeMap = new HashMap();
        //按成品代码取出应发数量，实发数量，成品名称，型号
        for (SalesReleaseOrgNodeEntity salesReleaseOrgNode:salesReleaseOrgNodeList) {
            int[] num;
            if(salesNumMap.get(salesReleaseOrgNode.getFinishedCode())==null){
                salesNameMap.put(salesReleaseOrgNode.getFinishedCode(),salesReleaseOrgNode.getFinishedName());
                salesSizeMap.put(salesReleaseOrgNode.getFinishedCode(),salesReleaseOrgNode.getFinishedSize());
                num = new int[]{  MathUtil.toInt(salesReleaseOrgNode.getShouldSendNumber()),  MathUtil.toInt(salesReleaseOrgNode.getActualSendNumber())};
                salesNumMap.put(salesReleaseOrgNode.getFinishedCode(),num);
            }else{
                num = salesNumMap.get(salesReleaseOrgNode.getFinishedCode());
                num[0] +=   MathUtil.toInt(salesReleaseOrgNode.getShouldSendNumber());
                num[1] +=   MathUtil.toInt(salesReleaseOrgNode.getActualSendNumber());
            }
        }
        //对于已有的看板数据进行更新或删除
        if(!ListUtils.isNullOrEmpty(finishedWarehousIOLookEntityList)){
            for (FinishedWarehousIOLookEntity finishedWarehousIOLookEntity:finishedWarehousIOLookEntityList) {
                int[] num = salesNumMap.get(finishedWarehousIOLookEntity.getMaterialCode());
                if(num!=null){
                    finishedWarehousIOLookEntity.setShouldSendNumber(num[0]);
                    finishedWarehousIOLookEntity.setActualSendNumber(num[1]);
                    //完成率保留两位小数，百分制
                    Double ratio =  MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber()*100)/finishedWarehousIOLookEntity.getShouldSendNumber();
                    finishedWarehousIOLookEntity.setSendRatio(new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if(MathUtil.toDouble(finishedWarehousIOLookEntity.getActualSendNumber())>=MathUtil.toDouble(finishedWarehousIOLookEntity.getShouldSendNumber())){
                        finishedWarehousIOLookEntity.setSendFinishTime(new Date());
                    }
                    salesNumMap.remove(finishedWarehousIOLookEntity.getMaterialCode());
                    systemService.updateEntitie(finishedWarehousIOLookEntity);
                }else{
                    systemService.delete(finishedWarehousIOLookEntity);
                }
            }
        }
        //对于没有的进行新增
        if(MapUtils.isNotEmpty(salesNumMap)){
            for (Map.Entry<String, int[]> entry : salesNumMap.entrySet()) {
                int[] num = entry.getValue();
                FinishedWarehousIOLookEntity finishedWarehousIOLookEntity = new FinishedWarehousIOLookEntity();
                finishedWarehousIOLookEntity.setCreateDate(new Date());
                finishedWarehousIOLookEntity.setSalesDeliveryOrderNumber(salesReleaseOrderPage.getReceiptCode());
                finishedWarehousIOLookEntity.setDeliveryAdviceOrderNumber(salesReleaseOrderPage.getSendNoticeNumber());
                finishedWarehousIOLookEntity.setMaterialCode(entry.getKey());
                finishedWarehousIOLookEntity.setMaterialName(salesNameMap.get(entry.getKey()));
                finishedWarehousIOLookEntity.setMaterialSize(salesSizeMap.get(entry.getKey()));
                finishedWarehousIOLookEntity.setCustomerCode(salesReleaseOrderPage.getCustomerCode());
                finishedWarehousIOLookEntity.setShouldSendNumber(num[0]);
                finishedWarehousIOLookEntity.setActualSendNumber(num[1]);
                //完成率保留两位小数，百分制
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

    public void startBatch(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
