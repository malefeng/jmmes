package com.jeecg.entity.invoices;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 采购收料单
 * @author zhangdaihao
 * @date 2019-10-09 22:26:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_purchase_receipt_list", schema = "")
@SuppressWarnings("serial")
public class PurchaseReceiptEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**收料单编号*/
	private java.lang.String receiptCode;
	/**收货组织*/
	private java.lang.String receivingCompanyCode;
	/**采购组织*/
	private java.lang.String purchasingCompanyCode;
	/**单据日期*/
	private java.util.Date receiptDate;
	/**收货部门*/
	private java.lang.String receivingOrgCode;
	/**采购部门*/
	private java.lang.String purchasingOrgCode;
	/**供应商*/
	private java.lang.String supplierCode;
	/**收货员*/
	private java.lang.String receivingPersonCode;
	/**采购员*/
	private java.lang.String purchasingPersonCode;
	/**审核人*/
	private java.lang.String verifierCode;
	/**审核日期*/
	private java.util.Date verifyDate;
	/**单据创建人*/
	private java.lang.String createName;
	/**单据创建日期*/
	private java.util.Date createTime;
	/**采购订单号*/
	private java.lang.String orderNumber;
	/**销售订单号*/
	private java.lang.String salesOrderNumber;
	/**读取人*/
	private java.lang.String readPersonCode;
	/**获取时间*/
	private java.util.Date acquireTime;
	/**预留1*/
	private java.lang.String attr1;
	/**预留2*/
	private java.lang.String attr2;
	/**预留3*/
	private java.lang.String attr3;
	/**预留4*/
	private java.lang.String attr4;
	/**预留5*/
	private java.lang.String attr5;
	//子项二维码拼接内容
	private String qrCodeStrs;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=true,length=32)
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
	@Column(name ="CREATE_DATE",nullable=true)
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
	 *@return: java.lang.String  收料单编号
	 */
	@Column(name ="RECEIPT_CODE",nullable=true,length=120)
	public java.lang.String getReceiptCode(){
		return this.receiptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收料单编号
	 */
	public void setReceiptCode(java.lang.String receiptCode){
		this.receiptCode = receiptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货组织
	 */
	@Column(name ="RECEIVING_COMPANY_CODE",nullable=true,length=120)
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
	 *@return: java.lang.String  采购组织
	 */
	@Column(name ="PURCHASING_COMPANY_CODE",nullable=true,length=120)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  单据日期
	 */
	@Column(name ="RECEIPT_DATE",nullable=true)
	public java.util.Date getReceiptDate(){
		return this.receiptDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  单据日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setReceiptDate(java.util.Date receiptDate){
		this.receiptDate = receiptDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货部门
	 */
	@Column(name ="RECEIVING_ORG_CODE",nullable=true,length=120)
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
	 *@return: java.lang.String  采购部门
	 */
	@Column(name ="PURCHASING_ORG_CODE",nullable=true,length=120)
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
	 *@return: java.lang.String  供应商
	 */
	@Column(name ="SUPPLIER_CODE",nullable=true,length=120)
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
	 *@return: java.lang.String  收货员
	 */
	@Column(name ="RECEIVING_PERSON_CODE",nullable=true,length=120)
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
	 *@return: java.lang.String  采购员
	 */
	@Column(name ="PURCHASING_PERSON_CODE",nullable=true,length=120)
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
	@Column(name ="VERIFIER_CODE",nullable=true,length=120)
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
	@Column(name ="VERIFY_DATE",nullable=true)
	public java.util.Date getVerifyDate(){
		return this.verifyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setVerifyDate(java.util.Date verifyDate){
		this.verifyDate = verifyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据创建人
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=120)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据创建人
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  单据创建日期
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  单据创建日期
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购订单号
	 */
	@Column(name ="ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getOrderNumber(){
		return this.orderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购订单号
	 */
	public void setOrderNumber(java.lang.String orderNumber){
		this.orderNumber = orderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售订单号
	 */
	@Column(name ="SALES_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getSalesOrderNumber(){
		return this.salesOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售订单号
	 */
	public void setSalesOrderNumber(java.lang.String salesOrderNumber){
		this.salesOrderNumber = salesOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  读取人
	 */
	@Column(name ="READ_PERSON_CODE",nullable=true,length=120)
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
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  获取时间
	 */
	@Column(name ="ACQUIRE_TIME",nullable=true)
	public java.util.Date getAcquireTime(){
		return this.acquireTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  获取时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setAcquireTime(java.util.Date acquireTime){
		this.acquireTime = acquireTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */
	@Column(name ="ATTR1",nullable=true,length=120)
	public java.lang.String getAttr1(){
		return this.attr1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留1
	 */
	public void setAttr1(java.lang.String attr1){
		this.attr1 = attr1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */
	@Column(name ="ATTR2",nullable=true,length=120)
	public java.lang.String getAttr2(){
		return this.attr2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setAttr2(java.lang.String attr2){
		this.attr2 = attr2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */
	@Column(name ="ATTR3",nullable=true,length=120)
	public java.lang.String getAttr3(){
		return this.attr3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setAttr3(java.lang.String attr3){
		this.attr3 = attr3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */
	@Column(name ="ATTR4",nullable=true,length=120)
	public java.lang.String getAttr4(){
		return this.attr4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setAttr4(java.lang.String attr4){
		this.attr4 = attr4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留5
	 */
	@Column(name ="ATTR5",nullable=true,length=120)
	public java.lang.String getAttr5(){
		return this.attr5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setAttr5(java.lang.String attr5){
		this.attr5 = attr5;
	}

	@Transient
	public String getQrCodeStrs() {
		return qrCodeStrs;
	}

	public void setQrCodeStrs(String qrCodeStrs) {
		this.qrCodeStrs = qrCodeStrs;
	}
}
