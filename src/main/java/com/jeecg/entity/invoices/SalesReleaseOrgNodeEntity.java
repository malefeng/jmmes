package com.jeecg.entity.invoices;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 销售出库原始物料详情
 * @author zhangdaihao
 * @date 2019-10-10 00:22:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_sales_release_org_node", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SalesReleaseOrgNodeEntity implements java.io.Serializable {
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
	/**成品代码*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**规格型号*/
	private java.lang.String finishedSize;
	/**供应商属性*/
	private java.lang.String supplierAttr;
	/**宽幅*/
	private java.lang.String wideInWidth;
	/**库存单位*/
	private java.lang.String inventoryUnit;
	/**应发数量*/
	private java.lang.String shouldSendNumber;
	/**实发数量*/
	private java.lang.String actualSendNumber;
	/**计价单位*/
	private java.lang.String valuationUnit;
	/**计价数量*/
	private java.lang.String valuationNumber;
	/**单价*/
	private java.lang.String unitPrice;
	/**含税单价*/
	private java.lang.String unitPriceWithTax;
	/**是否赠品*/
	private java.lang.String isFree;
	/**批号*/
	private java.lang.String batchNumber;
	/**仓库*/
	private java.lang.String repositoryCode;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**库存状态*/
	private java.lang.String repositoryStatus;
	/**备注*/
	private java.lang.String remark;
	/**销售出库单主键*/
	private java.lang.String receiptId;
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
	 *@return: java.lang.String  成品代码
	 */
	@Column(name ="FINISHED_CODE",nullable=true,length=120)
	public java.lang.String getFinishedCode(){
		return this.finishedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品代码
	 */
	public void setFinishedCode(java.lang.String finishedCode){
		this.finishedCode = finishedCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成品名称
	 */
	@Column(name ="FINISHED_NAME",nullable=true,length=120)
	public java.lang.String getFinishedName(){
		return this.finishedName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品名称
	 */
	public void setFinishedName(java.lang.String finishedName){
		this.finishedName = finishedName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="FINISHED_SIZE",nullable=true,length=120)
	public java.lang.String getFinishedSize(){
		return this.finishedSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setFinishedSize(java.lang.String finishedSize){
		this.finishedSize = finishedSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商属性
	 */
	@Column(name ="SUPPLIER_ATTR",nullable=true,length=120)
	public java.lang.String getSupplierAttr(){
		return this.supplierAttr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商属性
	 */
	public void setSupplierAttr(java.lang.String supplierAttr){
		this.supplierAttr = supplierAttr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  宽幅
	 */
	@Column(name ="WIDE_IN_WIDTH",nullable=true,length=120)
	public java.lang.String getWideInWidth(){
		return this.wideInWidth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  宽幅
	 */
	public void setWideInWidth(java.lang.String wideInWidth){
		this.wideInWidth = wideInWidth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存单位
	 */
	@Column(name ="INVENTORY_UNIT",nullable=true,length=120)
	public java.lang.String getInventoryUnit(){
		return this.inventoryUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存单位
	 */
	public void setInventoryUnit(java.lang.String inventoryUnit){
		this.inventoryUnit = inventoryUnit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应发数量
	 */
	@Column(name ="SHOULD_SEND_NUMBER",nullable=true,length=120)
	public java.lang.String getShouldSendNumber(){
		return this.shouldSendNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应发数量
	 */
	public void setShouldSendNumber(java.lang.String shouldSendNumber){
		this.shouldSendNumber = shouldSendNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实发数量
	 */
	@Column(name ="ACTUAL_SEND_NUMBER",nullable=true,length=120)
	public java.lang.String getActualSendNumber(){
		return this.actualSendNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实发数量
	 */
	public void setActualSendNumber(java.lang.String actualSendNumber){
		this.actualSendNumber = actualSendNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计价单位
	 */
	@Column(name ="VALUATION_UNIT",nullable=true,length=120)
	public java.lang.String getValuationUnit(){
		return this.valuationUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计价单位
	 */
	public void setValuationUnit(java.lang.String valuationUnit){
		this.valuationUnit = valuationUnit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计价数量
	 */
	@Column(name ="VALUATION_NUMBER",nullable=true,length=120)
	public java.lang.String getValuationNumber(){
		return this.valuationNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计价数量
	 */
	public void setValuationNumber(java.lang.String valuationNumber){
		this.valuationNumber = valuationNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */
	@Column(name ="UNIT_PRICE",nullable=true,length=120)
	public java.lang.String getUnitPrice(){
		return this.unitPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setUnitPrice(java.lang.String unitPrice){
		this.unitPrice = unitPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  含税单价
	 */
	@Column(name ="UNIT_PRICE_WITH_TAX",nullable=true,length=120)
	public java.lang.String getUnitPriceWithTax(){
		return this.unitPriceWithTax;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  含税单价
	 */
	public void setUnitPriceWithTax(java.lang.String unitPriceWithTax){
		this.unitPriceWithTax = unitPriceWithTax;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否赠品
	 */
	@Column(name ="IS_FREE",nullable=true,length=120)
	public java.lang.String getIsFree(){
		return this.isFree;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否赠品
	 */
	public void setIsFree(java.lang.String isFree){
		this.isFree = isFree;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批号
	 */
	@Column(name ="BATCH_NUMBER",nullable=true,length=120)
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批号
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产订单号
	 */
	@Column(name ="PRODUCTION_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getProductionOrderNumber(){
		return this.productionOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产订单号
	 */
	public void setProductionOrderNumber(java.lang.String productionOrderNumber){
		this.productionOrderNumber = productionOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存状态
	 */
	@Column(name ="REPOSITORY_STATUS",nullable=true,length=120)
	public java.lang.String getRepositoryStatus(){
		return this.repositoryStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存状态
	 */
	public void setRepositoryStatus(java.lang.String repositoryStatus){
		this.repositoryStatus = repositoryStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=120)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售出库单主键
	 */
	@Column(name ="RECEIPT_ID",nullable=true,length=120)
	public java.lang.String getReceiptId(){
		return this.receiptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售出库单主键
	 */
	public void setReceiptId(java.lang.String receiptId){
		this.receiptId = receiptId;
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
