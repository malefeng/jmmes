package com.jeecg.service.impl.invoices;

import com.jeecg.entity.invoices.SalesReleaseNodeEntity;
import com.jeecg.entity.invoices.SalesReleaseOrderEntity;
import com.jeecg.entity.invoices.SalesReleaseOrgNodeEntity;
import com.jeecg.service.invoices.SalesReleaseOrderServiceI;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("salesReleaseOrderService")
@Transactional
public class SalesReleaseOrderServiceImpl extends CommonServiceImpl implements SalesReleaseOrderServiceI {

	
	public void addMain(SalesReleaseOrderEntity salesReleaseOrder,
	        List<SalesReleaseNodeEntity> salesReleaseNodeList,List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList){
			//保存主信息
			this.save(salesReleaseOrder);

			/**保存-销售出库配料物料详情*/
			for(SalesReleaseNodeEntity salesReleaseNode:salesReleaseNodeList){
				//外键设置
				salesReleaseNode.setReceiptId(salesReleaseOrder.getId());
				this.save(salesReleaseNode);
			}
			/**保存-销售出库原始物料详情*/
			for(SalesReleaseOrgNodeEntity salesReleaseOrgNode:salesReleaseOrgNodeList){
				//外键设置
				salesReleaseOrgNode.setReceiptId(salesReleaseOrder.getId());
				this.save(salesReleaseOrgNode);
			}
	}

	
	public void updateMain(SalesReleaseOrderEntity salesReleaseOrder,
	        List<SalesReleaseNodeEntity> salesReleaseNodeList,List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeList) {
		//保存订单主信息
		this.saveOrUpdate(salesReleaseOrder);
		
		
		//===================================================================================
		//获取参数
		Object id0 = salesReleaseOrder.getId();
		Object id1 = salesReleaseOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-销售出库配料物料详情
	    String hql0 = "from SalesReleaseNodeEntity where 1 = 1 AND receiptId = ? ";
	    List<SalesReleaseNodeEntity> salesReleaseNodeOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-销售出库配料物料详情
		for(SalesReleaseNodeEntity oldE:salesReleaseNodeOldList){
			boolean isUpdate = false;
				for(SalesReleaseNodeEntity sendE:salesReleaseNodeList){
					//需要更新的明细数据-销售出库配料物料详情
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-销售出库配料物料详情
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-销售出库配料物料详情
		for(SalesReleaseNodeEntity salesReleaseNode:salesReleaseNodeList){
			if(salesReleaseNode.getId()==null){
				//外键设置
				salesReleaseNode.setReceiptId(salesReleaseOrder.getId());
				this.save(salesReleaseNode);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-销售出库原始物料详情
	    String hql1 = "from SalesReleaseOrgNodeEntity where 1 = 1 AND receiptId = ? ";
	    List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-销售出库原始物料详情
		for(SalesReleaseOrgNodeEntity oldE:salesReleaseOrgNodeOldList){
			boolean isUpdate = false;
				for(SalesReleaseOrgNodeEntity sendE:salesReleaseOrgNodeList){
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
		for(SalesReleaseOrgNodeEntity salesReleaseOrgNode:salesReleaseOrgNodeList){
			if(salesReleaseOrgNode.getId()==null){
				//外键设置
				salesReleaseOrgNode.setReceiptId(salesReleaseOrder.getId());
				this.save(salesReleaseOrgNode);
			}
		}
		
	}

	
	public void delMain(SalesReleaseOrderEntity salesReleaseOrder) {
		//删除主表信息
		this.delete(salesReleaseOrder);
		
		//===================================================================================
		//获取参数
		Object id0 = salesReleaseOrder.getId();
		Object id1 = salesReleaseOrder.getId();
		//===================================================================================
		//删除-销售出库配料物料详情
	    String hql0 = "from SalesReleaseNodeEntity where 1 = 1 AND receiptId = ? ";
	    List<SalesReleaseNodeEntity> salesReleaseNodeOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(salesReleaseNodeOldList);
		//===================================================================================
		//删除-销售出库原始物料详情
	    String hql1 = "from SalesReleaseOrgNodeEntity where 1 = 1 AND receiptId = ? ";
	    List<SalesReleaseOrgNodeEntity> salesReleaseOrgNodeOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(salesReleaseOrgNodeOldList);
	}
	
}