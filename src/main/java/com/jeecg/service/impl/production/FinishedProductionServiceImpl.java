package com.jeecg.service.impl.production;

import com.jeecg.entity.production.FinishedInspectItemEntity;
import com.jeecg.page.production.FinishedInspectItemPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.production.FinishedProductionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.production.FinishedProductionEntity;
import com.jeecg.entity.production.FinishedProductionNodeEntity;
@Service("finishedProductionService")
@Transactional
public class FinishedProductionServiceImpl extends CommonServiceImpl implements FinishedProductionServiceI {

	
	public void addMain(FinishedProductionEntity finishedProduction,
		List<FinishedProductionNodeEntity> finishedProductionNodeList){
		//保存主信息
		this.save(finishedProduction);

		/**保存-成品生产物料详情*/
		for(FinishedProductionNodeEntity finishedProductionNode:finishedProductionNodeList){
			//外键设置
			finishedProductionNode.setFinishedSerino(finishedProduction.getFinishedSerino());
			this.save(finishedProductionNode);
		}

		/**
		 * 如果不需要熟成，则生成成品检验数据
		 */
		if(!"1".equals(finishedProduction.getNeedRipening())){
			FinishedInspectItemEntity finishedInspectItemEntity = new FinishedInspectItemEntity();
			finishedInspectItemEntity.setFinishedCode(finishedProduction.getFinishedCode());
			finishedInspectItemEntity.setFinishedName(finishedProduction.getFinishedName());
			finishedInspectItemEntity.setProductionDispatchingNumber(finishedProduction.getProductionOrderNumber());
			finishedInspectItemEntity.setSalesOrderNumber(finishedProduction.getFinishedSerino());
			finishedInspectItemEntity.setStatus("1");
			super.save(finishedInspectItemEntity);
		}
	}

	
	public void updateMain(FinishedProductionEntity finishedProduction,
	        List<FinishedProductionNodeEntity> finishedProductionNodeList) {
		//保存订单主信息
		this.saveOrUpdate(finishedProduction);
		
		
		//===================================================================================
		//获取参数
		Object finishedSerino0 = finishedProduction.getFinishedSerino();
		//===================================================================================
		//1.查询出数据库的明细数据-成品生产物料详情
	    String hql0 = "from FinishedProductionNodeEntity where 1 = 1 AND finishedSerino = ? ";
	    List<FinishedProductionNodeEntity> finishedProductionNodeOldList = this.findHql(hql0,finishedSerino0);
		//2.筛选更新明细数据-成品生产物料详情
		for(FinishedProductionNodeEntity oldE:finishedProductionNodeOldList){
			boolean isUpdate = false;
				for(FinishedProductionNodeEntity sendE:finishedProductionNodeList){
					//需要更新的明细数据-成品生产物料详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-成品生产物料详情
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-成品生产物料详情
		for(FinishedProductionNodeEntity finishedProductionNode:finishedProductionNodeList){
			if(finishedProductionNode.getId()==null){
				//外键设置
				finishedProductionNode.setFinishedSerino(finishedProduction.getFinishedSerino());
				this.save(finishedProductionNode);
			}
		}
		
	}

	
	public void delMain(FinishedProductionEntity finishedProduction) {
		//删除主表信息
		this.delete(finishedProduction);
		
		//===================================================================================
		//获取参数
		Object finishedSerino0 = finishedProduction.getFinishedSerino();
		//===================================================================================
		//删除-成品生产物料详情
	    String hql0 = "from FinishedProductionNodeEntity where 1 = 1 AND finishedSerino = ? ";
	    List<FinishedProductionNodeEntity> finishedProductionNodeOldList = this.findHql(hql0,finishedSerino0);
		this.deleteAllEntitie(finishedProductionNodeOldList);
	}
	
}