package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 原料退料列表
 * @author zhangdaihao
 * @date 2019-10-11 05:41:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_material_warehous_return_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MaterialWarehousReturnEntity implements java.io.Serializable {
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
	/**原料编号*/
	private java.lang.String materialSerino;
	/**原料代码*/
	private java.lang.String materialCode;
	/**原料名称*/
	private java.lang.String materialName;
	/**原料批次*/
	private java.lang.String batchNumber;
	/**规格型号*/
	private java.lang.String materialSize;
	/**入库数量*/
	private java.lang.String materialNumber;
	/**单位*/
	private java.lang.String unit;
	/**退料人*/
	private java.lang.String returnedPerson;
	/**退料时间*/
	private java.util.Date returnedDate;
	/**退料原因*/
	private java.lang.String returnedReason;
	/**退料数量*/
	private java.lang.String returnedNumber;
	/**供应商*/
	private java.lang.String supplierCode;
	/**收料单号*/
	private java.lang.String receivingOrderNumber;
	/**收料单采购订单号*/
	private java.lang.String purchaseOrderNumber;
	/**收料单销售订单号*/
	private java.lang.String salesOrderNumber;
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
	 *@return: java.lang.String  原料编号
	 */
	@Column(name ="MATERIAL_SERINO",nullable=true,length=120)
	public java.lang.String getMaterialSerino(){
		return this.materialSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料编号
	 */
	public void setMaterialSerino(java.lang.String materialSerino){
		this.materialSerino = materialSerino;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料代码
	 */
	@Column(name ="MATERIAL_CODE",nullable=true,length=120)
	public java.lang.String getMaterialCode(){
		return this.materialCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料代码
	 */
	public void setMaterialCode(java.lang.String materialCode){
		this.materialCode = materialCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料名称
	 */
	@Column(name ="MATERIAL_NAME",nullable=true,length=120)
	public java.lang.String getMaterialName(){
		return this.materialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料名称
	 */
	public void setMaterialName(java.lang.String materialName){
		this.materialName = materialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料批次
	 */
	@Column(name ="BATCH_NUMBER",nullable=true,length=120)
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料批次
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="MATERIAL_SIZE",nullable=true,length=120)
	public java.lang.String getMaterialSize(){
		return this.materialSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setMaterialSize(java.lang.String materialSize){
		this.materialSize = materialSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库数量
	 */
	@Column(name ="MATERIAL_NUMBER",nullable=true,length=120)
	public java.lang.String getMaterialNumber(){
		return this.materialNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库数量
	 */
	public void setMaterialNumber(java.lang.String materialNumber){
		this.materialNumber = materialNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
	@Column(name ="UNIT",nullable=true,length=120)
	public java.lang.String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退料人
	 */
	@Column(name ="RETURNED_PERSON",nullable=true,length=120)
	public java.lang.String getReturnedPerson(){
		return this.returnedPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退料人
	 */
	public void setReturnedPerson(java.lang.String returnedPerson){
		this.returnedPerson = returnedPerson;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  退料时间
	 */
	@Column(name ="RETURNED_DATE",nullable=true)
	public java.util.Date getReturnedDate(){
		return this.returnedDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  退料时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setReturnedDate(java.util.Date returnedDate){
		this.returnedDate = returnedDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退料原因
	 */
	@Column(name ="RETURNED_REASON",nullable=true,length=120)
	public java.lang.String getReturnedReason(){
		return this.returnedReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退料原因
	 */
	public void setReturnedReason(java.lang.String returnedReason){
		this.returnedReason = returnedReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退料数量
	 */
	@Column(name ="RETURNED_NUMBER",nullable=true,length=120)
	public java.lang.String getReturnedNumber(){
		return this.returnedNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退料数量
	 */
	public void setReturnedNumber(java.lang.String returnedNumber){
		this.returnedNumber = returnedNumber;
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
	 *@return: java.lang.String  收料单号
	 */
	@Column(name ="RECEIVING_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getReceivingOrderNumber(){
		return this.receivingOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收料单号
	 */
	public void setReceivingOrderNumber(java.lang.String receivingOrderNumber){
		this.receivingOrderNumber = receivingOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收料单采购订单号
	 */
	@Column(name ="PURCHASE_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getPurchaseOrderNumber(){
		return this.purchaseOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收料单采购订单号
	 */
	public void setPurchaseOrderNumber(java.lang.String purchaseOrderNumber){
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收料单销售订单号
	 */
	@Column(name ="SALES_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getSalesOrderNumber(){
		return this.salesOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收料单销售订单号
	 */
	public void setSalesOrderNumber(java.lang.String salesOrderNumber){
		this.salesOrderNumber = salesOrderNumber;
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
