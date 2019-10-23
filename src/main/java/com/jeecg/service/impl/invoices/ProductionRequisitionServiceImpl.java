package com.jeecg.service.impl.invoices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.invoices.ProductionRequisitionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.invoices.ProductionRequisitionEntity;
import com.jeecg.entity.invoices.ProductionRequisitionNodeEntity;
import com.jeecg.entity.invoices.ProductionRequisitionOrgNodeEntity;
@Service("productionRequisitionService")
@Transactional
public class ProductionRequisitionServiceImpl extends CommonServiceImpl implements ProductionRequisitionServiceI {

	
	public void addMain(ProductionRequisitionEntity productionRequisition,
	        List<ProductionRequisitionNodeEntity> productionRequisitionNodeList,List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList){
			//保存主信息
			this.save(productionRequisition);
		
			/**保存-生产领料单配料物料详情*/
			for(ProductionRequisitionNodeEntity productionRequisitionNode:productionRequisitionNodeList){
				//外键设置
				productionRequisitionNode.setInspectId(productionRequisition.getId());
				this.save(productionRequisitionNode);
			}
			/**保存-销售出库原始物料详情*/
			for(ProductionRequisitionOrgNodeEntity productionRequisitionOrgNode:productionRequisitionOrgNodeList){
				//外键设置
				productionRequisitionOrgNode.setInspectId(productionRequisition.getId());
				this.save(productionRequisitionOrgNode);
			}
	}

	
	public void updateMain(ProductionRequisitionEntity productionRequisition,
	        List<ProductionRequisitionNodeEntity> productionRequisitionNodeList,List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeList) {
		//保存订单主信息
		this.saveOrUpdate(productionRequisition);
		
		
		//===================================================================================
		//获取参数
		Object id0 = productionRequisition.getId();
		Object id1 = productionRequisition.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-生产领料单配料物料详情
	    String hql0 = "from ProductionRequisitionNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<ProductionRequisitionNodeEntity> productionRequisitionNodeOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-生产领料单配料物料详情
		for(ProductionRequisitionNodeEntity oldE:productionRequisitionNodeOldList){
			boolean isUpdate = false;
				for(ProductionRequisitionNodeEntity sendE:productionRequisitionNodeList){
					//需要更新的明细数据-生产领料单配料物料详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-生产领料单配料物料详情
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-生产领料单配料物料详情
		for(ProductionRequisitionNodeEntity productionRequisitionNode:productionRequisitionNodeList){
			if(productionRequisitionNode.getId()==null){
				//外键设置
				productionRequisitionNode.setInspectId(productionRequisition.getId());
				this.save(productionRequisitionNode);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-销售出库原始物料详情
	    String hql1 = "from ProductionRequisitionOrgNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-销售出库原始物料详情
		for(ProductionRequisitionOrgNodeEntity oldE:productionRequisitionOrgNodeOldList){
			boolean isUpdate = false;
				for(ProductionRequisitionOrgNodeEntity sendE:productionRequisitionOrgNodeList){
					//需要更新的明细数据-销售出库原始物料详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-销售出库原始物料详情
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-销售出库原始物料详情
		for(ProductionRequisitionOrgNodeEntity productionRequisitionOrgNode:productionRequisitionOrgNodeList){
			if(productionRequisitionOrgNode.getId()==null){
				//外键设置
				productionRequisitionOrgNode.setInspectId(productionRequisition.getId());
				this.save(productionRequisitionOrgNode);
			}
		}
		
	}

	
	public void delMain(ProductionRequisitionEntity productionRequisition) {
		//删除主表信息
		this.delete(productionRequisition);
		
		//===================================================================================
		//获取参数
		Object id0 = productionRequisition.getId();
		Object id1 = productionRequisition.getId();
		//===================================================================================
		//删除-生产领料单配料物料详情
	    String hql0 = "from ProductionRequisitionNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<ProductionRequisitionNodeEntity> productionRequisitionNodeOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(productionRequisitionNodeOldList);
		//===================================================================================
		//删除-销售出库原始物料详情
	    String hql1 = "from ProductionRequisitionOrgNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<ProductionRequisitionOrgNodeEntity> productionRequisitionOrgNodeOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(productionRequisitionOrgNodeOldList);
	}
	
}