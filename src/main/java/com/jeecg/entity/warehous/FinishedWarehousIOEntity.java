package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 成品出入库
 * @author zhangdaihao
 * @date 2019-10-11 06:30:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_warehous_io_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FinishedWarehousIOEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
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
	/**库存状态*/
	private java.lang.String ioType;
	/**入库人*/
	private java.lang.String warehousingPersonCode;
	/**入库时间*/
	private java.util.Date warehousingDate;
	/**仓库*/
	private java.lang.String warehousPosition;
	/**库位*/
	private java.lang.String warehousSpace;
	/**出库人*/
	private java.lang.String warehouseOutPersonCode;
	/**出库时间*/
	private java.util.Date warehouseOutDate;
	/**客户*/
	private java.lang.String customerCode;
	/**交货日期*/
	private java.util.Date deliveryDate;
	/**交货地点*/
	private java.lang.String deliveryPoint;
	/**货币类型*/
	private java.lang.String currencyCode;
	/**运输单号*/
	private java.lang.String trackingNumber;
	/**销售人员*/
	private java.lang.String salesman;
	/**发货通知单号*/
	private java.lang.String deliveryAdviceOrderNumber;
	/**销售出库单号*/
	private java.lang.String salesDeliveryOrderNumber;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String takeMaterilNumber;
	/**生产派工单号*/
	private java.lang.String productionDispatchingNumber;
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
	 *@return: java.lang.String  库存状态
	 */
	@Column(name ="IO_TYPE",nullable=true,length=120)
	public java.lang.String getIoType(){
		return this.ioType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存状态
	 */
	public void setIoType(java.lang.String ioType){
		this.ioType = ioType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库人
	 */
	@Column(name ="WAREHOUSING_PERSON_CODE",nullable=true,length=120)
	public java.lang.String getWarehousingPersonCode(){
		return this.warehousingPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库人
	 */
	public void setWarehousingPersonCode(java.lang.String warehousingPersonCode){
		this.warehousingPersonCode = warehousingPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库时间
	 */
	@Column(name ="WAREHOUSING_DATE",nullable=true)
	public java.util.Date getWarehousingDate(){
		return this.warehousingDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setWarehousingDate(java.util.Date warehousingDate){
		this.warehousingDate = warehousingDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="WAREHOUS_POSITION",nullable=true,length=120)
	public java.lang.String getWarehousPosition(){
		return this.warehousPosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setWarehousPosition(java.lang.String warehousPosition){
		this.warehousPosition = warehousPosition;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库位
	 */
	@Column(name ="WAREHOUS_SPACE",nullable=true,length=120)
	public java.lang.String getWarehousSpace(){
		return this.warehousSpace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库位
	 */
	public void setWarehousSpace(java.lang.String warehousSpace){
		this.warehousSpace = warehousSpace;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出库人
	 */
	@Column(name ="WAREHOUSE_OUT_PERSON_CODE",nullable=true,length=120)
	public java.lang.String getWarehouseOutPersonCode(){
		return this.warehouseOutPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库人
	 */
	public void setWarehouseOutPersonCode(java.lang.String warehouseOutPersonCode){
		this.warehouseOutPersonCode = warehouseOutPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出库时间
	 */
	@Column(name ="WAREHOUSE_OUT_DATE",nullable=true)
	public java.util.Date getWarehouseOutDate(){
		return this.warehouseOutDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出库时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setWarehouseOutDate(java.util.Date warehouseOutDate){
		this.warehouseOutDate = warehouseOutDate;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交货日期
	 */
	@Column(name ="DELIVERY_DATE",nullable=true)
	public java.util.Date getDeliveryDate(){
		return this.deliveryDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交货日期
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setDeliveryDate(java.util.Date deliveryDate){
		this.deliveryDate = deliveryDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交货地点
	 */
	@Column(name ="DELIVERY_POINT",nullable=true,length=120)
	public java.lang.String getDeliveryPoint(){
		return this.deliveryPoint;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交货地点
	 */
	public void setDeliveryPoint(java.lang.String deliveryPoint){
		this.deliveryPoint = deliveryPoint;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货币类型
	 */
	@Column(name ="CURRENCY_CODE",nullable=true,length=120)
	public java.lang.String getCurrencyCode(){
		return this.currencyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货币类型
	 */
	public void setCurrencyCode(java.lang.String currencyCode){
		this.currencyCode = currencyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输单号
	 */
	@Column(name ="TRACKING_NUMBER",nullable=true,length=120)
	public java.lang.String getTrackingNumber(){
		return this.trackingNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输单号
	 */
	public void setTrackingNumber(java.lang.String trackingNumber){
		this.trackingNumber = trackingNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售人员
	 */
	@Column(name ="SALESMAN",nullable=true,length=120)
	public java.lang.String getSalesman(){
		return this.salesman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售人员
	 */
	public void setSalesman(java.lang.String salesman){
		this.salesman = salesman;
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
	 *@return: java.lang.String  生产派工单号
	 */
	@Column(name ="PRODUCTION_DISPATCHING_NUMBER",nullable=true,length=120)
	public String getProductionDispatchingNumber() {
		return productionDispatchingNumber;
	}

	public void setProductionDispatchingNumber(String productionDispatchingNumber) {
		this.productionDispatchingNumber = productionDispatchingNumber;
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
