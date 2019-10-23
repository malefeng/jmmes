package com.jeecg.service.impl.invoices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.invoices.PurchaseReceiptServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.invoices.PurchaseReceiptEntity;
import com.jeecg.entity.invoices.PurchaseReceiptNodeEntity;
@Service("purchaseReceiptService")
@Transactional
public class PurchaseReceiptServiceImpl extends CommonServiceImpl implements PurchaseReceiptServiceI {

	
	public void addMain(PurchaseReceiptEntity purchaseReceipt,
	        List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList){
			//保存主信息
			this.save(purchaseReceipt);
		
			/**保存-采购收料单物料信息*/
			for(PurchaseReceiptNodeEntity purchaseReceiptNode:purchaseReceiptNodeList){
				//外键设置
				purchaseReceiptNode.setInspectId(purchaseReceipt.getId());
				this.save(purchaseReceiptNode);
			}
	}

	
	public void updateMain(PurchaseReceiptEntity purchaseReceipt,
	        List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList) {
		//保存订单主信息
		this.saveOrUpdate(purchaseReceipt);
		
		
		//===================================================================================
		//获取参数
		Object id0 = purchaseReceipt.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-采购收料单物料信息
	    String hql0 = "from PurchaseReceiptNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<PurchaseReceiptNodeEntity> purchaseReceiptNodeOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-采购收料单物料信息
		for(PurchaseReceiptNodeEntity oldE:purchaseReceiptNodeOldList){
			boolean isUpdate = false;
				for(PurchaseReceiptNodeEntity sendE:purchaseReceiptNodeList){
					//需要更新的明细数据-采购收料单物料信息
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-采购收料单物料信息
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-采购收料单物料信息
		for(PurchaseReceiptNodeEntity purchaseReceiptNode:purchaseReceiptNodeList){
			if(purchaseReceiptNode.getId()==null){
				//外键设置
				purchaseReceiptNode.setInspectId(purchaseReceipt.getId());
				this.save(purchaseReceiptNode);
			}
		}
		
	}

	
	public void delMain(PurchaseReceiptEntity purchaseReceipt) {
		//删除主表信息
		this.delete(purchaseReceipt);
		
		//===================================================================================
		//获取参数
		Object id0 = purchaseReceipt.getId();
		//===================================================================================
		//删除-采购收料单物料信息
	    String hql0 = "from PurchaseReceiptNodeEntity where 1 = 1 AND inspectId = ? ";
	    List<PurchaseReceiptNodeEntity> purchaseReceiptNodeOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(purchaseReceiptNodeOldList);
	}
	
}