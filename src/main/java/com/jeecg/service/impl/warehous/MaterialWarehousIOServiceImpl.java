package com.jeecg.service.impl.warehous;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.warehous.MaterialWarehousIOServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.warehous.MaterialWarehousIOEntity;
import com.jeecg.entity.warehous.MaterialWarehousNodeEntity;
@Service("materialWarehousIOService")
@Transactional
public class MaterialWarehousIOServiceImpl extends CommonServiceImpl implements MaterialWarehousIOServiceI {

	
	public void addMain(MaterialWarehousIOEntity materialWarehousIO,
	        List<MaterialWarehousNodeEntity> materialWarehousNodeList){
			//保存主信息
			this.save(materialWarehousIO);
		
			/**保存-原料出库详情列表*/
			for(MaterialWarehousNodeEntity materialWarehousNode:materialWarehousNodeList){
				//外键设置
				materialWarehousNode.setMaterialSerino(materialWarehousIO.getMaterialSerino());
				this.save(materialWarehousNode);
			}
	}

	
	public void updateMain(MaterialWarehousIOEntity materialWarehousIO,
	        List<MaterialWarehousNodeEntity> materialWarehousNodeList) {
		//保存订单主信息
		this.saveOrUpdate(materialWarehousIO);
		
		
		//===================================================================================
		//获取参数
		Object materialSerino0 = materialWarehousIO.getMaterialSerino();
		//===================================================================================
		//1.查询出数据库的明细数据-原料出库详情列表
	    String hql0 = "from MaterialWarehousNodeEntity where 1 = 1 AND materialSerino = ? ";
	    List<MaterialWarehousNodeEntity> materialWarehousNodeOldList = this.findHql(hql0,materialSerino0);
		//2.筛选更新明细数据-原料出库详情列表
		for(MaterialWarehousNodeEntity oldE:materialWarehousNodeOldList){
			boolean isUpdate = false;
				for(MaterialWarehousNodeEntity sendE:materialWarehousNodeList){
					//需要更新的明细数据-原料出库详情列表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-原料出库详情列表
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-原料出库详情列表
		for(MaterialWarehousNodeEntity materialWarehousNode:materialWarehousNodeList){
			if(materialWarehousNode.getId()==null){
				//外键设置
				materialWarehousNode.setMaterialSerino(materialWarehousIO.getMaterialSerino());
				this.save(materialWarehousNode);
			}
		}
		
	}

	
	public void delMain(MaterialWarehousIOEntity materialWarehousIO) {
		//删除主表信息
		this.delete(materialWarehousIO);
		
		//===================================================================================
		//获取参数
		Object materialSerino0 = materialWarehousIO.getMaterialSerino();
		//===================================================================================
		//删除-原料出库详情列表
	    String hql0 = "from MaterialWarehousNodeEntity where 1 = 1 AND materialSerino = ? ";
	    List<MaterialWarehousNodeEntity> materialWarehousNodeOldList = this.findHql(hql0,materialSerino0);
		this.deleteAllEntitie(materialWarehousNodeOldList);
	}
	
}