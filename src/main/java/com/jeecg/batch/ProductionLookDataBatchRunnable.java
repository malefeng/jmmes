package com.jeecg.batch;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.entity.look.ProductionParehousIOLookEntity;
import com.jeecg.util.MathUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.web.system.service.SystemService;

import java.util.*;

public class ProductionLookDataBatchRunnable implements Runnable{

    private static final Logger logger = Logger.getLogger(ProductionLookDataBatchRunnable.class);

    private String productionDispatchingNumber;

    private SystemService systemService;

    public ProductionLookDataBatchRunnable(String productionDispatchingNumber, SystemService systemService) {
        this.productionDispatchingNumber = productionDispatchingNumber;
        this.systemService = systemService;
    }

    @Override
    public synchronized void run() {
        String sql = "SELECT " +
                " r.finished_name productName, " +
                " r.planProdctNumber, " +
                " f.finished_size size, " +
                " if(w.productedNumber is null,0,productedNumber) productedNumber, " +
                " r.planned_completion_date planFinishDate, " +
                " f.finished_unit_code unit, " +
                " r.production_dispatching_number productionDispatchingNumber, " +
                " r.requisition_workshop_code workshop " +
                "FROM " +
                " ( " +
                "  SELECT " +
                "   finished_name, " +
                "   sum(planned_production_quantity) planProdctNumber, " +
                "   planned_completion_date, " +
                "   requisition_workshop_code, " +
                "   production_dispatching_number, " +
                "   finished_code " +
                "  FROM " +
                "   t_production_requisition_list " +
                "  WHERE " +
                "   production_dispatching_number = ? " +
                "group by production_dispatching_number,finished_code " +
                " ) r " +
                "LEFT JOIN ( " +
                " SELECT " +
                "  finished_code, " +
                "  finished_size, " +
                "  finished_unit_code " +
                " FROM " +
                "  t_finished_product_list " +
                ") f ON f.finished_code = r.finished_code " +
                "LEFT JOIN ( " +
                " SELECT " +
                "  production_dispatching_number, " +
                "  SUM(finished_number) productedNumber " +
                " FROM " +
                "  t_finished_warehous_io_list " +
                " WHERE " +
                "  production_dispatching_number = ? " +
                "GROUP BY production_dispatching_number,finished_code " +
                ") w ON r.production_dispatching_number = w.production_dispatching_number " +
                "GROUP BY " +
                " r.finished_code;";

        List<Map<String, Object>> resultMap = this.systemService.findForJdbc(sql, this.productionDispatchingNumber, this.productionDispatchingNumber);
        if(!ListUtils.isNullOrEmpty(resultMap)){
            Map<String,Map> newDataMap = new HashMap();
            for (Map<String, Object> map :resultMap) {
                newDataMap.put(String.valueOf(map.get("productionDispatchingNumber")),map);
            }
            List<ProductionParehousIOLookEntity> productionParehousIOLookEntities = this.systemService.findByProperty(ProductionParehousIOLookEntity.class, "productionDispatchingNumber", this.productionDispatchingNumber);
            //对于已有的看板数据进行更新或删除
            for (ProductionParehousIOLookEntity parehousIOLookEntity: productionParehousIOLookEntities) {
                Map dataNodeMap = newDataMap.get(parehousIOLookEntity.getProductionDispatchingNumber());
                if(MapUtils.isNotEmpty(dataNodeMap)){
                    parehousIOLookEntity.setPlanProdctNumber(MathUtil.toInt(dataNodeMap.get("planProdctNumber")));
                    parehousIOLookEntity.setProductedNumber(MathUtil.toInt(dataNodeMap.get("productedNumber")));
                    newDataMap.remove(parehousIOLookEntity.getProductionDispatchingNumber());
                    this.systemService.updateEntitie(parehousIOLookEntity);
                }else{
                    this.systemService.delete(parehousIOLookEntity);
                }
            }

            if(MapUtils.isNotEmpty(newDataMap)) {
                List newDataList = new ArrayList();
                for (Map.Entry<String, Map> entry : newDataMap.entrySet()) {
                    Map dataNodeMap = entry.getValue();
                    ProductionParehousIOLookEntity parehousIOLookEntity = new ProductionParehousIOLookEntity();
                    parehousIOLookEntity.setCreateDate(new Date());
                    parehousIOLookEntity.setProductName(String.valueOf(dataNodeMap.get("productName")));
                    parehousIOLookEntity.setPlanProdctNumber(MathUtil.toInt(dataNodeMap.get("planProdctNumber")));
                    parehousIOLookEntity.setProductedNumber(MathUtil.toInt(dataNodeMap.get("productedNumber")));
                    parehousIOLookEntity.setSize(String.valueOf(dataNodeMap.get("size")));
                    if(dataNodeMap.get("planFinishDate")!=null){
                        parehousIOLookEntity.setPlanFinishDate((Date)dataNodeMap.get("planFinishDate"));
                    }
                    parehousIOLookEntity.setUnit(String.valueOf(dataNodeMap.get("unit")));
                    parehousIOLookEntity.setProductionDispatchingNumber(String.valueOf(dataNodeMap.get("productionDispatchingNumber")));
                    parehousIOLookEntity.setWorkshop(String.valueOf(dataNodeMap.get("workshop")));
                    newDataList.add(parehousIOLookEntity);
                }
                this.systemService.batchSave(newDataList);
                logger.info("生产看板信息同步完成："+ JSONObject.toJSONString(newDataList));
            }
        }

    }

    public void startBatch(){
        logger.info("开始同步生产看板信息");
        Thread thread = new Thread(this);
        thread.start();
    }
}
