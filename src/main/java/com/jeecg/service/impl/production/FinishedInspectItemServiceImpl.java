package com.jeecg.service.impl.production;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.production.FinishedInspectItemServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.production.FinishedInspectItemEntity;
import com.jeecg.entity.production.FinishedInspectItemNodeEntity;
@Service("finishedInspectItemService")
@Transactional
public class FinishedInspectItemServiceImpl extends CommonServiceImpl implements FinishedInspectItemServiceI {

	
	public void addMain(FinishedInspectItemEntity finishedInspectItem,
	        List<FinishedInspectItemNodeEntity> finishedInspectItemNodeList){
			//保存主信息
			this.save(finishedInspectItem);
		
			/**保存-成品检验明细*/
			for(FinishedInspectItemNodeEntity finishedInspectItemNode:finishedInspectItemNodeList){
				//外键设置
				finishedInspectItemNode.setFinishedCode(finishedInspectItem.getFinishedCode());
				this.save(finishedInspectItemNode);
			}
	}

	
	public void updateMain(FinishedInspectItemEntity finishedInspectItem,
	        List<FinishedInspectItemNodeEntity> finishedInspectItemNodeList) {
		//保存订单主信息
		this.saveOrUpdate(finishedInspectItem);
		
		
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspectItem.getFinishedCode();
		//===================================================================================
		//1.查询出数据库的明细数据-成品检验明细
	    String hql0 = "from FinishedInspectItemNodeEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedInspectItemNodeEntity> finishedInspectItemNodeOldList = this.findHql(hql0,finishedCode0);
		//2.筛选更新明细数据-成品检验明细
		for(FinishedInspectItemNodeEntity oldE:finishedInspectItemNodeOldList){
			boolean isUpdate = false;
				for(FinishedInspectItemNodeEntity sendE:finishedInspectItemNodeList){
					//需要更新的明细数据-成品检验明细
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-成品检验明细
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-成品检验明细
		for(FinishedInspectItemNodeEntity finishedInspectItemNode:finishedInspectItemNodeList){
			if(finishedInspectItemNode.getId()==null){
				//外键设置
				finishedInspectItemNode.setFinishedCode(finishedInspectItem.getFinishedCode());
				this.save(finishedInspectItemNode);
			}
		}
		
	}

	
	public void delMain(FinishedInspectItemEntity finishedInspectItem) {
		//删除主表信息
		this.delete(finishedInspectItem);
		
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspectItem.getFinishedCode();
		//===================================================================================
		//删除-成品检验明细
	    String hql0 = "from FinishedInspectItemNodeEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedInspectItemNodeEntity> finishedInspectItemNodeOldList = this.findHql(hql0,finishedCode0);
		this.deleteAllEntitie(finishedInspectItemNodeOldList);
	}
	
}