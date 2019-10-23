package com.jeecg.api.invoices;

import com.jeecg.entity.invoices.ProductionRequisitionEntity;
import com.jeecg.entity.invoices.ProductionRequisitionNodeEntity;
import com.jeecg.entity.invoices.ProductionRequisitionOrgNodeEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductionRequisitionDTO implements Serializable {
    ProductionRequisitionEntity productionRequisition;
    List<ProductionRequisitionNodeEntity> productionRequisitionNodeList;
    java.util.List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList;
}
