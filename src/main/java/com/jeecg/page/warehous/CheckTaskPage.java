package com.jeecg.page.warehous;

import com.jeecg.entity.warehous.CheckNodeEntity;
import com.jeecg.entity.warehous.CheckTaskEntity;
import lombok.Data;

import java.util.List;

/**   
 * @Title: Entity
 * @Description: 盘点任务
 * @author zhangdaihao
 * @date 2019-11-13 23:39:45
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@Data
public class CheckTaskPage implements java.io.Serializable {
	/***盘点任务*/
	private CheckTaskEntity checkTask;
	/**盘点明细表*/
	private List<CheckNodeEntity> checkNodeList;
}
