package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 成品退货列表
 * @author zhangdaihao
 * @date 2019-10-11 05:43:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_warehous_return_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FinishedWarehousReturnEntity implements java.io.Serializable {
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
	/**成品编号*/
	private java.lang.String finishedSerino;
	/**成品代码*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**成品批次*/
	private java.lang.String finishedBatch;
	/**规格型号*/
	private java.lang.String finishedSize;
	/**数量*/
	private java.lang.String finishedNumber;
	/**单位*/
	private java.lang.String unit;
	/**客户*/
	private java.lang.String customerCode;
	/**退货人*/
	private java.lang.String returnedPerson;
	/**退货时间*/
	private java.util.Date returnedDate;
	/**退料数量*/
	private java.lang.String returnedNumber;
	/**退货原因*/
	private java.lang.String returnedReason;
	/**发货通知单号*/
	private java.lang.String deliveryAdviceOrderNumber;
	/**销售出库单号*/
	private java.lang.String salesDeliveryOrderNumber;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String takeMaterilNumber;
	/**退货仓库*/
	private java.lang.String returnWhouse;
	/**退货库位*/
	private java.lang.String warehouseSpaceCode;
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
	 *@return: java.lang.String  成品编号
	 */
	@Column(name ="FINISHED_SERINO",nullable=true,length=120)
	public java.lang.String getFinishedSerino(){
		return this.finishedSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品编号
	 */
	public void setFinishedSerino(java.lang.String finishedSerino){
		this.finishedSerino = finishedSerino;
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
	 *@return: java.lang.String  成品批次
	 */
	@Column(name ="FINISHED_BATCH",nullable=true,length=120)
	public java.lang.String getFinishedBatch(){
		return this.finishedBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品批次
	 */
	public void setFinishedBatch(java.lang.String finishedBatch){
		this.finishedBatch = finishedBatch;
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
	 *@return: java.lang.String  数量
	 */
	@Column(name ="FINISHED_NUMBER",nullable=true,length=120)
	public java.lang.String getFinishedNumber(){
		return this.finishedNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setFinishedNumber(java.lang.String finishedNumber){
		this.finishedNumber = finishedNumber;
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
	 *@return: java.lang.String  退货人
	 */
	@Column(name ="RETURNED_PERSON",nullable=true,length=120)
	public java.lang.String getReturnedPerson(){
		return this.returnedPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货人
	 */
	public void setReturnedPerson(java.lang.String returnedPerson){
		this.returnedPerson = returnedPerson;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  退货时间
	 */
	@Column(name ="RETURNED_DATE",nullable=true)
	public java.util.Date getReturnedDate(){
		return this.returnedDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  退货时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setReturnedDate(java.util.Date returnedDate){
		this.returnedDate = returnedDate;
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
	 *@return: java.lang.String  退货原因
	 */
	@Column(name ="RETURNED_REASON",nullable=true,length=120)
	public java.lang.String getReturnedReason(){
		return this.returnedReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货原因
	 */
	public void setReturnedReason(java.lang.String returnedReason){
		this.returnedReason = returnedReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货通知单号
	 */
	@Column(name ="DELIVERY_ADVICE_ORDER_NUMBER",nullable=true,length=120)
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
	 *@return: java.lang.String  销售出库单号
	 */
	@Column(name ="SALES_DELIVERY_ORDER_NUMBER",nullable=true,length=120)
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
	 *@return: java.lang.String  生产领料单号
	 */
	@Column(name ="TAKE_MATERIL_NUMBER",nullable=true,length=120)
	public java.lang.String getTakeMaterilNumber(){
		return this.takeMaterilNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产领料单号
	 */
	public void setTakeMaterilNumber(java.lang.String takeMaterilNumber){
		this.takeMaterilNumber = takeMaterilNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货仓库
	 */
	@Column(name ="RETURN_WHOUSE",nullable=true,length=120)
	public java.lang.String getReturnWhouse(){
		return this.returnWhouse;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货仓库
	 */
	public void setReturnWhouse(java.lang.String returnWhouse){
		this.returnWhouse = returnWhouse;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货库位
	 */
	@Column(name ="WAREHOUSE_SPACECODE",nullable=true,length=120)
	public java.lang.String getWarehouseSpaceCode(){
		return this.warehouseSpaceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货库位
	 */
	public void setWarehouseSpaceCode(java.lang.String warehouseSpaceCode){
		this.warehouseSpaceCode = warehouseSpaceCode;
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
