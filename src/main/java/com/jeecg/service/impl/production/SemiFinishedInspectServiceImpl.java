package com.jeecg.service.impl.production;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.production.SemiFinishedInspectServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.production.SemiFinishedInspectEntity;
import com.jeecg.entity.production.SemiFinishedFirstInspectEntity;
import com.jeecg.entity.production.SemiFinishedLastInspectEntity;
@Service("semiFinishedInspectService")
@Transactional
public class SemiFinishedInspectServiceImpl extends CommonServiceImpl implements SemiFinishedInspectServiceI {

	
	public void addMain(SemiFinishedInspectEntity semiFinishedInspect,
	        List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList,List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList){
			//保存主信息
			this.save(semiFinishedInspect);
		
			/**保存-半成品首检*/
			for(SemiFinishedFirstInspectEntity semiFinishedFirstInspect:semiFinishedFirstInspectList){
				//外键设置
				semiFinishedFirstInspect.setSemiFinishedCode(semiFinishedInspect.getSemiFinishedCode());
				this.save(semiFinishedFirstInspect);
			}
			/**保存-半成品末检*/
			for(SemiFinishedLastInspectEntity semiFinishedLastInspect:semiFinishedLastInspectList){
				//外键设置
				semiFinishedLastInspect.setSemiFinishedCode(semiFinishedInspect.getSemiFinishedCode());
				this.save(semiFinishedLastInspect);
			}
	}

	
	public void updateMain(SemiFinishedInspectEntity semiFinishedInspect,
	        List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectList,List<SemiFinishedLastInspectEntity> semiFinishedLastInspectList) {
		//保存订单主信息
		this.saveOrUpdate(semiFinishedInspect);
		
		
		//===================================================================================
		//获取参数
		Object semiFinishedCode0 = semiFinishedInspect.getSemiFinishedCode();
		Object semiFinishedCode1 = semiFinishedInspect.getSemiFinishedCode();
		//===================================================================================
		//1.查询出数据库的明细数据-半成品首检
	    String hql0 = "from SemiFinishedFirstInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
	    List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectOldList = this.findHql(hql0,semiFinishedCode0);
		//2.筛选更新明细数据-半成品首检
		for(SemiFinishedFirstInspectEntity oldE:semiFinishedFirstInspectOldList){
			boolean isUpdate = false;
				for(SemiFinishedFirstInspectEntity sendE:semiFinishedFirstInspectList){
					//需要更新的明细数据-半成品首检
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-半成品首检
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-半成品首检
		for(SemiFinishedFirstInspectEntity semiFinishedFirstInspect:semiFinishedFirstInspectList){
			if(semiFinishedFirstInspect.getId()==null){
				//外键设置
				semiFinishedFirstInspect.setSemiFinishedCode(semiFinishedInspect.getSemiFinishedCode());
				this.save(semiFinishedFirstInspect);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-半成品末检
	    String hql1 = "from SemiFinishedLastInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
	    List<SemiFinishedLastInspectEntity> semiFinishedLastInspectOldList = this.findHql(hql1,semiFinishedCode1);
		//2.筛选更新明细数据-半成品末检
		for(SemiFinishedLastInspectEntity oldE:semiFinishedLastInspectOldList){
			boolean isUpdate = false;
				for(SemiFinishedLastInspectEntity sendE:semiFinishedLastInspectList){
					//需要更新的明细数据-半成品末检
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-半成品末检
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-半成品末检
		for(SemiFinishedLastInspectEntity semiFinishedLastInspect:semiFinishedLastInspectList){
			if(semiFinishedLastInspect.getId()==null){
				//外键设置
				semiFinishedLastInspect.setSemiFinishedCode(semiFinishedInspect.getSemiFinishedCode());
				this.save(semiFinishedLastInspect);
			}
		}
		
	}

	
	public void delMain(SemiFinishedInspectEntity semiFinishedInspect) {
		//删除主表信息
		this.delete(semiFinishedInspect);
		
		//===================================================================================
		//获取参数
		Object semiFinishedCode0 = semiFinishedInspect.getSemiFinishedCode();
		Object semiFinishedCode1 = semiFinishedInspect.getSemiFinishedCode();
		//===================================================================================
		//删除-半成品首检
	    String hql0 = "from SemiFinishedFirstInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
	    List<SemiFinishedFirstInspectEntity> semiFinishedFirstInspectOldList = this.findHql(hql0,semiFinishedCode0);
		this.deleteAllEntitie(semiFinishedFirstInspectOldList);
		//===================================================================================
		//删除-半成品末检
	    String hql1 = "from SemiFinishedLastInspectEntity where 1 = 1 AND semiFinishedCode = ? ";
	    List<SemiFinishedLastInspectEntity> semiFinishedLastInspectOldList = this.findHql(hql1,semiFinishedCode1);
		this.deleteAllEntitie(semiFinishedLastInspectOldList);
	}
	
}