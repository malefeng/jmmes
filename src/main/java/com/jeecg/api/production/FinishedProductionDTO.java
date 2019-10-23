package com.jeecg.api.production;

import com.jeecg.entity.production.FinishedProductionEntity;
import com.jeecg.entity.production.FinishedProductionNodeEntity;
import lombok.Data;

import java.util.List;

@Data
public class FinishedProductionDTO implements java.io.Serializable{
    private FinishedProductionEntity finishedProduction;
    private List<FinishedProductionNodeEntity> finishedProductionNodeList;
}
