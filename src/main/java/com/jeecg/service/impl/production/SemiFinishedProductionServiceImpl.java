package com.jeecg.service.impl.production;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.production.SemiFinishedProductionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.production.SemiFinishedProductionEntity;
import com.jeecg.entity.production.SemiFinishedProductionNodeEntity;
@Service("semiFinishedProductionService")
@Transactional
public class SemiFinishedProductionServiceImpl extends CommonServiceImpl implements SemiFinishedProductionServiceI {

	
	public void addMain(SemiFinishedProductionEntity semiFinishedProduction,
	        List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList){
			//保存主信息
			this.save(semiFinishedProduction);
		
			/**保存-半成品生产物料详情*/
			for(SemiFinishedProductionNodeEntity semiFinishedProductionNode:semiFinishedProductionNodeList){
				//外键设置
				semiFinishedProductionNode.setSemiFinishedSerino(semiFinishedProduction.getSemiFinishedSerino());
				this.save(semiFinishedProductionNode);
			}
	}

	
	public void updateMain(SemiFinishedProductionEntity semiFinishedProduction,
	        List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList) {
		//保存订单主信息
		this.saveOrUpdate(semiFinishedProduction);
		
		
		//===================================================================================
		//获取参数
		Object semiFinishedSerino0 = semiFinishedProduction.getSemiFinishedSerino();
		//===================================================================================
		//1.查询出数据库的明细数据-半成品生产物料详情
	    String hql0 = "from SemiFinishedProductionNodeEntity where 1 = 1 AND semiFinishedSerino = ? ";
	    List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeOldList = this.findHql(hql0,semiFinishedSerino0);
		//2.筛选更新明细数据-半成品生产物料详情
		for(SemiFinishedProductionNodeEntity oldE:semiFinishedProductionNodeOldList){
			boolean isUpdate = false;
				for(SemiFinishedProductionNodeEntity sendE:semiFinishedProductionNodeList){
					//需要更新的明细数据-半成品生产物料详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-半成品生产物料详情
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-半成品生产物料详情
		for(SemiFinishedProductionNodeEntity semiFinishedProductionNode:semiFinishedProductionNodeList){
			if(semiFinishedProductionNode.getId()==null){
				//外键设置
				semiFinishedProductionNode.setSemiFinishedSerino(semiFinishedProduction.getSemiFinishedSerino());
				this.save(semiFinishedProductionNode);
			}
		}
		
	}

	
	public void delMain(SemiFinishedProductionEntity semiFinishedProduction) {
		//删除主表信息
		this.delete(semiFinishedProduction);
		
		//===================================================================================
		//获取参数
		Object semiFinishedSerino0 = semiFinishedProduction.getSemiFinishedSerino();
		//===================================================================================
		//删除-半成品生产物料详情
	    String hql0 = "from SemiFinishedProductionNodeEntity where 1 = 1 AND semiFinishedSerino = ? ";
	    List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeOldList = this.findHql(hql0,semiFinishedSerino0);
		this.deleteAllEntitie(semiFinishedProductionNodeOldList);
	}
	
}