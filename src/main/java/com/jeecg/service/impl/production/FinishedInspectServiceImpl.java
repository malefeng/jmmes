package com.jeecg.service.impl.production;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.production.FinishedInspectServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.production.FinishedInspectEntity;
import com.jeecg.entity.production.FinishedFirstInspectEntity;
import com.jeecg.entity.production.FinishedLastInspectEntity;
@Service("finishedInspectService")
@Transactional
public class FinishedInspectServiceImpl extends CommonServiceImpl implements FinishedInspectServiceI {

	
	public void addMain(FinishedInspectEntity finishedInspect,
	        List<FinishedFirstInspectEntity> finishedFirstInspectList,List<FinishedLastInspectEntity> finishedLastInspectList){
			//保存主信息
			this.save(finishedInspect);
		
			/**保存-成品首检*/
			for(FinishedFirstInspectEntity finishedFirstInspect:finishedFirstInspectList){
				//外键设置
				finishedFirstInspect.setFinishedCode(finishedInspect.getFinishedCode());
				this.save(finishedFirstInspect);
			}
			/**保存-成品末检*/
			for(FinishedLastInspectEntity finishedLastInspect:finishedLastInspectList){
				//外键设置
				finishedLastInspect.setFinishedCode(finishedInspect.getFinishedCode());
				this.save(finishedLastInspect);
			}
	}

	
	public void updateMain(FinishedInspectEntity finishedInspect,
	        List<FinishedFirstInspectEntity> finishedFirstInspectList,List<FinishedLastInspectEntity> finishedLastInspectList) {
		//保存订单主信息
		this.saveOrUpdate(finishedInspect);
		
		
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspect.getFinishedCode();
		Object finishedCode1 = finishedInspect.getFinishedCode();
		//===================================================================================
		//1.查询出数据库的明细数据-成品首检
	    String hql0 = "from FinishedFirstInspectEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedFirstInspectEntity> finishedFirstInspectOldList = this.findHql(hql0,finishedCode0);
		//2.筛选更新明细数据-成品首检
		for(FinishedFirstInspectEntity oldE:finishedFirstInspectOldList){
			boolean isUpdate = false;
				for(FinishedFirstInspectEntity sendE:finishedFirstInspectList){
					//需要更新的明细数据-成品首检
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-成品首检
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-成品首检
		for(FinishedFirstInspectEntity finishedFirstInspect:finishedFirstInspectList){
			if(finishedFirstInspect.getId()==null){
				//外键设置
				finishedFirstInspect.setFinishedCode(finishedInspect.getFinishedCode());
				this.save(finishedFirstInspect);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-成品末检
	    String hql1 = "from FinishedLastInspectEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedLastInspectEntity> finishedLastInspectOldList = this.findHql(hql1,finishedCode1);
		//2.筛选更新明细数据-成品末检
		for(FinishedLastInspectEntity oldE:finishedLastInspectOldList){
			boolean isUpdate = false;
				for(FinishedLastInspectEntity sendE:finishedLastInspectList){
					//需要更新的明细数据-成品末检
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-成品末检
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-成品末检
		for(FinishedLastInspectEntity finishedLastInspect:finishedLastInspectList){
			if(finishedLastInspect.getId()==null){
				//外键设置
				finishedLastInspect.setFinishedCode(finishedInspect.getFinishedCode());
				this.save(finishedLastInspect);
			}
		}
		
	}

	
	public void delMain(FinishedInspectEntity finishedInspect) {
		//删除主表信息
		this.delete(finishedInspect);
		
		//===================================================================================
		//获取参数
		Object finishedCode0 = finishedInspect.getFinishedCode();
		Object finishedCode1 = finishedInspect.getFinishedCode();
		//===================================================================================
		//删除-成品首检
	    String hql0 = "from FinishedFirstInspectEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedFirstInspectEntity> finishedFirstInspectOldList = this.findHql(hql0,finishedCode0);
		this.deleteAllEntitie(finishedFirstInspectOldList);
		//===================================================================================
		//删除-成品末检
	    String hql1 = "from FinishedLastInspectEntity where 1 = 1 AND finishedCode = ? ";
	    List<FinishedLastInspectEntity> finishedLastInspectOldList = this.findHql(hql1,finishedCode1);
		this.deleteAllEntitie(finishedLastInspectOldList);
	}
	
}