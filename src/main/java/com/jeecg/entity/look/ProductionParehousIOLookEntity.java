package com.jeecg.entity.look;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 生产看板
 * @author zhangdaihao
 * @date 2019-10-02 01:31:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_production_warehous_io_look", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ProductionParehousIOLookEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**成品名称*/
	private java.lang.String productName;
	/**计划生产数量*/
	private java.lang.Integer planProdctNumber;
	/**规格型号*/
	private java.lang.String size;
	/**当前生产数量*/
	private java.lang.Integer productedNumber;
	/**计划完成日期*/
	private java.util.Date planFinishDate;
	/**生产单位*/
	private java.lang.String unit;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**生产派工单号	*/
	private java.lang.String productionDispatchingNumber;
	/**生产车间名称*/
	private java.lang.String workshop;
	/**预入仓库*/
	private java.lang.String planWarehouse;
	
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
	 *@return: java.lang.String  成品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=64)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计划生产数量
	 */
	@Column(name ="PLAN_PRODCT_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPlanProdctNumber(){
		return this.planProdctNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计划生产数量
	 */
	public void setPlanProdctNumber(java.lang.Integer planProdctNumber){
		this.planProdctNumber = planProdctNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="SIZE",nullable=true,length=32)
	public java.lang.String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSize(java.lang.String size){
		this.size = size;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  当前生产数量
	 */
	@Column(name ="PRODUCTED_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getProductedNumber(){
		return this.productedNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  当前生产数量
	 */
	public void setProductedNumber(java.lang.Integer productedNumber){
		this.productedNumber = productedNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划完成日期
	 */
	@Column(name ="PLAN_FINISH_DATE",nullable=true)
	public java.util.Date getPlanFinishDate(){
		return this.planFinishDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划完成日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setPlanFinishDate(java.util.Date planFinishDate){
		this.planFinishDate = planFinishDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产单位
	 */
	@Column(name ="UNIT",nullable=true,length=32)
	public java.lang.String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产单位
	 */
	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产订单号
	 */
	@Column(name ="PRODUCTION_ORDER_NUMBER",nullable=true,length=32)
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
	 * 生产派工单号
	 * @return
	 */
	@Column(name ="PRODUCTION_DISPATCHING_NUMBER",nullable=true,length=32)
	public String getProductionDispatchingNumber() {
		return productionDispatchingNumber;
	}

	public void setProductionDispatchingNumber(String productionDispatchingNumber) {
		this.productionDispatchingNumber = productionDispatchingNumber;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产车间名称
	 */
	@Column(name ="WORKSHOP",nullable=true,length=32)
	public java.lang.String getWorkshop(){
		return this.workshop;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产车间名称
	 */
	public void setWorkshop(java.lang.String workshop){
		this.workshop = workshop;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预入仓库
	 */
	@Column(name ="PLAN_WAREHOUSE",nullable=true,length=32)
	public java.lang.String getPlanWarehouse(){
		return this.planWarehouse;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预入仓库
	 */
	public void setPlanWarehouse(java.lang.String planWarehouse){
		this.planWarehouse = planWarehouse;
	}
}
