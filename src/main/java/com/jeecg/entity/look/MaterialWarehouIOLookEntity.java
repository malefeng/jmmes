package com.jeecg.entity.look;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 原料入库看板
 * @author zhangdaihao
 * @date 2019-09-29 00:25:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_material_warehous_io_look", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MaterialWarehouIOLookEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**收料单号*/
	private java.lang.String receivingOrderNumber;
	/**采购订单号*/
	private java.lang.String purchaseOrderNumber;
	/**销售订单号*/
	private java.lang.String salesOrderNumber;
	/**物料代码*/
	private java.lang.String materialCode;
	/**物料名称*/
	private java.lang.String materialName;
	/**规格型号*/
	private java.lang.String materialSize;
	/**供应商属性*/
	private java.lang.String supplierType;
	/**实收数量*/
	private java.lang.String receiveNumber;
	/**入库数量*/
	private java.lang.String insertNumber;
	/**入库率*/
	private java.lang.Double insertRatio;
	/**入库时间*/
	private java.util.Date insertTime;
	
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
	 *@return: java.lang.String  收料单号
	 */
	@Column(name ="RECEIVING_ORDER_NUMBER",nullable=true,length=32)
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
	 *@return: java.lang.String  采购订单号
	 */
	@Column(name ="PURCHASE_ORDER_NUMBER",nullable=true,length=32)
	public java.lang.String getPurchaseOrderNumber(){
		return this.purchaseOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购订单号
	 */
	public void setPurchaseOrderNumber(java.lang.String purchaseOrderNumber){
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售订单号
	 */
	@Column(name ="SALES_ORDER_NUMBER",nullable=true,length=32)
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
	 *@return: java.lang.String  物料代码
	 */
	@Column(name ="MATERIAL_CODE",nullable=true,length=32)
	public java.lang.String getMaterialCode(){
		return this.materialCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料代码
	 */
	public void setMaterialCode(java.lang.String materialCode){
		this.materialCode = materialCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料名称
	 */
	@Column(name ="MATERIAL_NAME",nullable=true,length=32)
	public java.lang.String getMaterialName(){
		return this.materialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料名称
	 */
	public void setMaterialName(java.lang.String materialName){
		this.materialName = materialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="MATERIAL_SIZE",nullable=true,length=32)
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
	 *@return: java.lang.String  供应商属性
	 */
	@Column(name ="SUPPLIER_TYPE",nullable=true,length=32)
	public java.lang.String getSupplierType(){
		return this.supplierType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商属性
	 */
	public void setSupplierType(java.lang.String supplierType){
		this.supplierType = supplierType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实收数量
	 */
	@Column(name ="RECEIVE_NUMBER",nullable=true,length=32)
	public java.lang.String getReceiveNumber(){
		return this.receiveNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实收数量
	 */
	public void setReceiveNumber(java.lang.String receiveNumber){
		this.receiveNumber = receiveNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库数量
	 */
	@Column(name ="INSERT_NUMBER",nullable=true,length=32)
	public java.lang.String getInsertNumber(){
		return this.insertNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库数量
	 */
	public void setInsertNumber(java.lang.String insertNumber){
		this.insertNumber = insertNumber;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  入库率
	 */
	@Column(name ="INSERT_RATIO",nullable=true,precision=22)
	public java.lang.Double getInsertRatio(){
		return this.insertRatio;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  入库率
	 */
	public void setInsertRatio(java.lang.Double insertRatio){
		this.insertRatio = insertRatio;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@Column(name ="INSERT_TIME",nullable=true)
	public java.util.Date getInsertTime(){
		return this.insertTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库时间
	 */
	public void setInsertTime(java.util.Date insertTime){
		this.insertTime = insertTime;
	}
}
