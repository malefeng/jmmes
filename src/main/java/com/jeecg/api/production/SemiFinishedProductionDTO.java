package com.jeecg.api.production;

import com.jeecg.entity.production.SemiFinishedProductionEntity;
import com.jeecg.entity.production.SemiFinishedProductionNodeEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SemiFinishedProductionDTO implements Serializable {
    SemiFinishedProductionEntity semiFinishedProduction;
    List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList;
}
