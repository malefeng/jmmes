package com.jeecg.api.warehous;

import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.entity.warehous.MaterialWarehousNodeEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MaterialWarehousIODTO implements Serializable {
    MaterialWarehousIOEntity materialWarehousIO;
    List<MaterialWarehousNodeEntity> materialWarehousNodeList;
}
