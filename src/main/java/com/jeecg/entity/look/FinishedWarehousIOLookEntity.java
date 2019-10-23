package com.jeecg.entity.look;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: t_finished_warehous_io_look
 * @author zhangdaihao
 * @date 2019-09-29 00:22:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_warehous_io_look", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FinishedWarehousIOLookEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**销售出库单号*/
	private java.lang.String salesDeliveryOrderNumber;
	/**发货通知单号*/
	private java.lang.String deliveryAdviceOrderNumber;
	/**销售订单号*/
	private java.lang.String salesOrderNumber;
	/**物料代码*/
	private java.lang.String materialCode;
	/**物料名称*/
	private java.lang.String materialName;
	/**规格型号*/
	private java.lang.String materialSize;
	/**客户*/
	private java.lang.String customerCode;
	/**应发数量*/
	private java.lang.Integer shouldSendNumber;
	/**实发数量*/
	private java.lang.Integer actualSendNumber;
	/**发货率*/
	private java.lang.Double sendRatio;
	/**发货完成时间*/
	private java.util.Date sendFinishTime;
	
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
	 *@return: java.lang.String  销售出库单号
	 */
	@Column(name ="SALES_DELIVERY_ORDER_NUMBER",nullable=true,length=32)
	public java.lang.String getSalesDeliveryOrderNumber(){
		return this.salesDeliveryOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售出库单号
	 */
	public void setSalesDeliveryOrderNumber(java.lang.String salesDeliveryOrderNumber){
		this.salesDeliveryOrderNumber = salesDeliveryOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货通知单号
	 */
	@Column(name ="DELIVERY_ADVICE_ORDER_NUMBER",nullable=true,length=32)
	public java.lang.String getDeliveryAdviceOrderNumber(){
		return this.deliveryAdviceOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货通知单号
	 */
	public void setDeliveryAdviceOrderNumber(java.lang.String deliveryAdviceOrderNumber){
		this.deliveryAdviceOrderNumber = deliveryAdviceOrderNumber;
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
	 *@return: java.lang.String  客户
	 */
	@Column(name ="CUSTOMER_CODE",nullable=true,length=32)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  应发数量
	 */
	@Column(name ="SHOULD_SEND_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getShouldSendNumber(){
		return this.shouldSendNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  应发数量
	 */
	public void setShouldSendNumber(java.lang.Integer shouldSendNumber){
		this.shouldSendNumber = shouldSendNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  实发数量
	 */
	@Column(name ="ACTUAL_SEND_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getActualSendNumber(){
		return this.actualSendNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  实发数量
	 */
	public void setActualSendNumber(java.lang.Integer actualSendNumber){
		this.actualSendNumber = actualSendNumber;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  发货率
	 */
	@Column(name ="SEND_RATIO",nullable=true,precision=22)
	public java.lang.Double getSendRatio(){
		return this.sendRatio;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  发货率
	 */
	public void setSendRatio(java.lang.Double sendRatio){
		this.sendRatio = sendRatio;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发货完成时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@Column(name ="SEND_FINISH_TIME",nullable=true)
	public java.util.Date getSendFinishTime(){
		return this.sendFinishTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发货完成时间
	 */
	public void setSendFinishTime(java.util.Date sendFinishTime){
		this.sendFinishTime = sendFinishTime;
	}
}
