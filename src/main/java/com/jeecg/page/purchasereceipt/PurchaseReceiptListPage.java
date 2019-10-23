package com.jeecg.page.purchasereceipt;

import java.util.List;
import java.util.ArrayList;

import com.jeecg.entity.invoices.PurchaseReceiptNodeEntity;

/**   
 * @Title: Entity
 * @Description: 采购收料单
 * @author zhangdaihao
 * @date 2019-10-04 02:10:51
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class PurchaseReceiptListPage implements java.io.Serializable {
	/**保存-采购收料单物料信息*/
	private List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList = new ArrayList<PurchaseReceiptNodeEntity>();
	public List<PurchaseReceiptNodeEntity> getPurchaseReceiptNodeList() {
		return purchaseReceiptNodeList;
	}
	public void setPurchaseReceiptNodeList(List<PurchaseReceiptNodeEntity> purchaseReceiptNodeList) {
		this.purchaseReceiptNodeList = purchaseReceiptNodeList;
	}


	/**主键*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**单据编号*/
	private java.lang.String receiptCode;
	/**单据日期*/
	private java.util.Date receiptDate;
	/**供应商*/
	private java.lang.String supplierCode;
	/**收货组织*/
	private java.lang.String receivingCompanyCode;
	/**收货部门*/
	private java.lang.String receivingOrgCode;
	/**收货员*/
	private java.lang.String receivingPersonCode;
	/**采购组织*/
	private java.lang.String purchasingCompanyCode;
	/**采购部门*/
	private java.lang.String purchasingOrgCode;
	/**采购员*/
	private java.lang.String purchasingPersonCode;
	/**审核人*/
	private java.lang.String verifierCode;
	/**审核日期*/
	private java.util.Date verifyDate;
	/**获取时间*/
	private java.util.Date acquireTime;
	/**读取人*/
	private java.lang.String readPersonCode;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据编号
	 */
	public java.lang.String getReceiptCode(){
		return this.receiptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据编号
	 */
	public void setReceiptCode(java.lang.String receiptCode){
		this.receiptCode = receiptCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  单据日期
	 */
	public java.util.Date getReceiptDate(){
		return this.receiptDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  单据日期
	 */
	public void setReceiptDate(java.util.Date receiptDate){
		this.receiptDate = receiptDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */
	public java.lang.String getSupplierCode(){
		return this.supplierCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setSupplierCode(java.lang.String supplierCode){
		this.supplierCode = supplierCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货组织
	 */
	public java.lang.String getReceivingCompanyCode(){
		return this.receivingCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货组织
	 */
	public void setReceivingCompanyCode(java.lang.String receivingCompanyCode){
		this.receivingCompanyCode = receivingCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货部门
	 */
	public java.lang.String getReceivingOrgCode(){
		return this.receivingOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货部门
	 */
	public void setReceivingOrgCode(java.lang.String receivingOrgCode){
		this.receivingOrgCode = receivingOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货员
	 */
	public java.lang.String getReceivingPersonCode(){
		return this.receivingPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货员
	 */
	public void setReceivingPersonCode(java.lang.String receivingPersonCode){
		this.receivingPersonCode = receivingPersonCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购组织
	 */
	public java.lang.String getPurchasingCompanyCode(){
		return this.purchasingCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购组织
	 */
	public void setPurchasingCompanyCode(java.lang.String purchasingCompanyCode){
		this.purchasingCompanyCode = purchasingCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购部门
	 */
	public java.lang.String getPurchasingOrgCode(){
		return this.purchasingOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购部门
	 */
	public void setPurchasingOrgCode(java.lang.String purchasingOrgCode){
		this.purchasingOrgCode = purchasingOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购员
	 */
	public java.lang.String getPurchasingPersonCode(){
		return this.purchasingPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购员
	 */
	public void setPurchasingPersonCode(java.lang.String purchasingPersonCode){
		this.purchasingPersonCode = purchasingPersonCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */
	public java.lang.String getVerifierCode(){
		return this.verifierCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setVerifierCode(java.lang.String verifierCode){
		this.verifierCode = verifierCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核日期
	 */
	public java.util.Date getVerifyDate(){
		return this.verifyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核日期
	 */
	public void setVerifyDate(java.util.Date verifyDate){
		this.verifyDate = verifyDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  获取时间
	 */
	public java.util.Date getAcquireTime(){
		return this.acquireTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  获取时间
	 */
	public void setAcquireTime(java.util.Date acquireTime){
		this.acquireTime = acquireTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  读取人
	 */
	public java.lang.String getReadPersonCode(){
		return this.readPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  读取人
	 */
	public void setReadPersonCode(java.lang.String readPersonCode){
		this.readPersonCode = readPersonCode;
	}
}
