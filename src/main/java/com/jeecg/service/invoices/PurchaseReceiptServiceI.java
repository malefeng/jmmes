package com.jeecg.service.invoices;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.invoices.PurchaseReceiptEntity;
import com.jeecg.entity.invoices.PurchaseReceiptNodeEntity;

public interface PurchaseReceiptServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(PurchaseReceiptEntity purchaseReceipt,
	        List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(PurchaseReceiptEntity purchaseReceipt,
	        List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList);
	public void delMain (PurchaseReceiptEntity purchaseReceipt);
}
