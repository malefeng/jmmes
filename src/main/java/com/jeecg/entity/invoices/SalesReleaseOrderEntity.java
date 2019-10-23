package com.jeecg.entity.invoices;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 销售出库单
 * @author zhangdaihao
 * @date 2019-10-10 00:22:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_sales_release_order", schema = "")
@SuppressWarnings("serial")
public class SalesReleaseOrderEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**销售出库单号*/
	private java.lang.String receiptCode;
	/**发货通知单号*/
	private java.lang.String sendNoticeNumber;
	/**日期*/
	private java.util.Date receiptDate;
	/**结算币别*/
	private java.lang.String currency;
	/**客户*/
	private java.lang.String customerCode;
	/**交货地点*/
	private java.lang.String deliveryPoints;
	/**承运商*/
	private java.lang.String commonCarrierCode;
	/**运输单号*/
	private java.lang.String waybillNumber;
	/**仓管员*/
	private java.lang.String repositoryManager;
	/**单据状态*/
	private java.lang.String receiptState;
	/**销售员*/
	private java.lang.String salesmanCode;
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
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
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售出库单号
	 */
	@Column(name ="RECEIPT_CODE",nullable=true,length=120)
	public java.lang.String getReceiptCode(){
		return this.receiptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售出库单号
	 */
	public void setReceiptCode(java.lang.String receiptCode){
		this.receiptCode = receiptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货通知单号
	 */
	@Column(name ="SEND_NOTICE_NUMBER",nullable=true,length=120)
	public java.lang.String getSendNoticeNumber(){
		return this.sendNoticeNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货通知单号
	 */
	public void setSendNoticeNumber(java.lang.String sendNoticeNumber){
		this.sendNoticeNumber = sendNoticeNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="RECEIPT_DATE",nullable=true)
	public java.util.Date getReceiptDate(){
		return this.receiptDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setReceiptDate(java.util.Date receiptDate){
		this.receiptDate = receiptDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结算币别
	 */
	@Column(name ="CURRENCY",nullable=true,length=120)
	public java.lang.String getCurrency(){
		return this.currency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结算币别
	 */
	public void setCurrency(java.lang.String currency){
		this.currency = currency;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户
	 */
	@Column(name ="CUSTOMER_CODE",nullable=true,length=120)
	public java.lang.String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户
	 */
	public void setCustomerCode(java.lang.String customerCode){
		this.customerCode = customerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交货地点
	 */
	@Column(name ="DELIVERY_POINTS",nullable=true,length=120)
	public java.lang.String getDeliveryPoints(){
		return this.deliveryPoints;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交货地点
	 */
	public void setDeliveryPoints(java.lang.String deliveryPoints){
		this.deliveryPoints = deliveryPoints;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商
	 */
	@Column(name ="COMMON_CARRIER_CODE",nullable=true,length=120)
	public java.lang.String getCommonCarrierCode(){
		return this.commonCarrierCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商
	 */
	public void setCommonCarrierCode(java.lang.String commonCarrierCode){
		this.commonCarrierCode = commonCarrierCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输单号
	 */
	@Column(name ="WAYBILL_NUMBER",nullable=true,length=120)
	public java.lang.String getWaybillNumber(){
		return this.waybillNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输单号
	 */
	public void setWaybillNumber(java.lang.String waybillNumber){
		this.waybillNumber = waybillNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓管员
	 */
	@Column(name ="REPOSITORY_MANAGER",nullable=true,length=120)
	public java.lang.String getRepositoryManager(){
		return this.repositoryManager;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓管员
	 */
	public void setRepositoryManager(java.lang.String repositoryManager){
		this.repositoryManager = repositoryManager;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据状态
	 */
	@Column(name ="RECEIPT_STATE",nullable=true,length=120)
	public java.lang.String getReceiptState(){
		return this.receiptState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据状态
	 */
	public void setReceiptState(java.lang.String receiptState){
		this.receiptState = receiptState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售员
	 */
	@Column(name ="SALESMAN_CODE",nullable=true,length=120)
	public java.lang.String getSalesmanCode(){
		return this.salesmanCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售员
	 */
	public void setSalesmanCode(java.lang.String salesmanCode){
		this.salesmanCode = salesmanCode;
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

}
