package com.jeecg.entity.invoices;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 生产领料单
 * @author zhangdaihao
 * @date 2019-10-10 22:17:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_production_requisition_list", schema = "")
@SuppressWarnings("serial")
public class ProductionRequisitionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**领料单编号*/
	private java.lang.String receiptCode;
	/**领料日期*/
	private java.util.Date requisitionDate;
	/**领料车间*/
	private java.lang.String requisitionWorkshopCode;
	/**领料仓库*/
	private java.lang.String repositoryCode;
	/**仓库管理员*/
	private java.lang.String repositoryManagerCode;
	/**领料人*/
	private java.lang.String acquirePersonCode;
	/**制单人*/
	private java.lang.String creatorCode;
	/**生产派工单号*/
	private java.lang.String productionDispatchingNumber;
	/**成品代码*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**计划生产数量*/
	private java.lang.String plannedProductionQuantity;
	/**计划开工日期*/
	private java.util.Date plannedStartDate;
	/**计划完工日期*/
	private java.util.Date plannedCompletionDate;
	/**读取人*/
	private java.lang.String readPersonCode;
	/**获取时间*/
	private java.util.Date acquireTime;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
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
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
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
	 *@return: java.lang.String  领料单编号
	 */
	@Column(name ="RECEIPT_CODE",nullable=true,length=120)
	public java.lang.String getReceiptCode(){
		return this.receiptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料单编号
	 */
	public void setReceiptCode(java.lang.String receiptCode){
		this.receiptCode = receiptCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  领料日期
	 */
	@Column(name ="REQUISITION_DATE",nullable=true)
	public java.util.Date getRequisitionDate(){
		return this.requisitionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  领料日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setRequisitionDate(java.util.Date requisitionDate){
		this.requisitionDate = requisitionDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料车间
	 */
	@Column(name ="REQUISITION_WORKSHOP_CODE",nullable=true,length=120)
	public java.lang.String getRequisitionWorkshopCode(){
		return this.requisitionWorkshopCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料车间
	 */
	public void setRequisitionWorkshopCode(java.lang.String requisitionWorkshopCode){
		this.requisitionWorkshopCode = requisitionWorkshopCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料仓库
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库管理员
	 */
	@Column(name ="REPOSITORY_MANAGER_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryManagerCode(){
		return this.repositoryManagerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库管理员
	 */
	public void setRepositoryManagerCode(java.lang.String repositoryManagerCode){
		this.repositoryManagerCode = repositoryManagerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料人
	 */
	@Column(name ="ACQUIRE_PERSON_CODE",nullable=true,length=120)
	public java.lang.String getAcquirePersonCode(){
		return this.acquirePersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料人
	 */
	public void setAcquirePersonCode(java.lang.String acquirePersonCode){
		this.acquirePersonCode = acquirePersonCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  制单人
	 */
	@Column(name ="CREATOR_CODE",nullable=true,length=120)
	public java.lang.String getCreatorCode(){
		return this.creatorCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  制单人
	 */
	public void setCreatorCode(java.lang.String creatorCode){
		this.creatorCode = creatorCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产派工单号
	 */
	@Column(name ="PRODUCTION_DISPATCHING_NUMBER",nullable=true,length=120)
	public java.lang.String getProductionDispatchingNumber(){
		return this.productionDispatchingNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产派工单号
	 */
	public void setProductionDispatchingNumber(java.lang.String productionDispatchingNumber){
		this.productionDispatchingNumber = productionDispatchingNumber;
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
	 *@return: java.lang.String  计划生产数量
	 */
	@Column(name ="PLANNED_PRODUCTION_QUANTITY",nullable=true,length=120)
	public java.lang.String getPlannedProductionQuantity(){
		return this.plannedProductionQuantity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划生产数量
	 */
	public void setPlannedProductionQuantity(java.lang.String plannedProductionQuantity){
		this.plannedProductionQuantity = plannedProductionQuantity;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划开工日期
	 */
	@Column(name ="PLANNED_START_DATE",nullable=true)
	public java.util.Date getPlannedStartDate(){
		return this.plannedStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划开工日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setPlannedStartDate(java.util.Date plannedStartDate){
		this.plannedStartDate = plannedStartDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划完工日期
	 */
	@Column(name ="PLANNED_COMPLETION_DATE",nullable=true)
	public java.util.Date getPlannedCompletionDate(){
		return this.plannedCompletionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划完工日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setPlannedCompletionDate(java.util.Date plannedCompletionDate){
		this.plannedCompletionDate = plannedCompletionDate;
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
	 *方法: 设置java.lang.String
	 *@return: java.lang.String  生产订单号
	 */
	@Column(name ="PRODUCTION_ORDER_NUMBER",nullable=true,length=120)
	public String getProductionOrderNumber() {
		return productionOrderNumber;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产订单号
	 */
	public void setProductionOrderNumber(String productionOrderNumber) {
		this.productionOrderNumber = productionOrderNumber;
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
