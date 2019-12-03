package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.warehous.CheckTaskServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.warehous.CheckTaskEntity;
import com.jeecg.entity.warehous.CheckNodeEntity;
@Service("checkTaskService")
@Transactional
public class CheckTaskServiceImpl extends CommonServiceImpl implements CheckTaskServiceI {

	
	public void addMain(CheckTaskEntity checkTask,
	        List<CheckNodeEntity> checkNodeList){
			//保存主信息
			this.save(checkTask);
		
			/**保存-盘点明细表*/
			for(CheckNodeEntity checkNode:checkNodeList){
				//外键设置
				checkNode.setCheckBatch(checkTask.getCheckBatch());
				this.save(checkNode);
			}
	}

	
	public void updateMain(CheckTaskEntity checkTask,
	        List<CheckNodeEntity> checkNodeList) {
		//保存订单主信息
		this.saveOrUpdate(checkTask);
		
		
		//===================================================================================
		//获取参数
		Object checkBatch0 = checkTask.getCheckBatch();
		//===================================================================================
		//1.查询出数据库的明细数据-盘点明细表
	    String hql0 = "from CheckNodeEntity where 1 = 1 AND checkBatch = ? ";
	    List<CheckNodeEntity> checkNodeOldList = this.findHql(hql0,checkBatch0);
		//2.筛选更新明细数据-盘点明细表
		for(CheckNodeEntity oldE:checkNodeOldList){
			boolean isUpdate = false;
				for(CheckNodeEntity sendE:checkNodeList){
					//需要更新的明细数据-盘点明细表
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-盘点明细表
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-盘点明细表
		for(CheckNodeEntity checkNode:checkNodeList){
			if(checkNode.getId()==null){
				//外键设置
				checkNode.setCheckBatch(checkTask.getCheckBatch());
				this.save(checkNode);
			}
		}
		
	}

	
	public void delMain(CheckTaskEntity checkTask) {
		//删除主表信息
		this.delete(checkTask);
		
		//===================================================================================
		//获取参数
		Object checkBatch0 = checkTask.getCheckBatch();
		//===================================================================================
		//删除-盘点明细表
	    String hql0 = "from CheckNodeEntity where 1 = 1 AND checkBatch = ? ";
	    List<CheckNodeEntity> checkNodeOldList = this.findHql(hql0,checkBatch0);
		this.deleteAllEntitie(checkNodeOldList);
	}
	
}