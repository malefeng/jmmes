package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 熟成出入库列表
 * @author zhangdaihao
 * @date 2019-10-10 23:23:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_ripening_warehous_io_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RipeningWarehousIOEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**产品编号*/
	private java.lang.String productSerino;
	/**产品代码*/
	private java.lang.String productCode;
	/**产品名称*/
	private java.lang.String productName;
	/**熟成产品类型*/
	private java.lang.String ripeningProType;
	/**产品批次*/
	private java.lang.String productBatch;
	/**规格型号*/
	private java.lang.String productSize;
	/**数量*/
	private java.lang.String productNumber;
	/**单位*/
	private java.lang.String unit;
	/**仓库*/
	private java.lang.String warehousePositionCode;
	/**库位*/
	private java.lang.String warehouseSpaceCode;
	/**熟成库存状态*/
	private java.lang.String ripeningStoreType;
	/**入库人*/
	private java.lang.String warehousingPersonCode;
	/**入库时间*/
	private java.util.Date warehousingDate;
	/**出库人*/
	private java.lang.String warehouseOutPersonCode;
	/**出库时间*/
	private java.util.Date warehouseOutDate;
	/**生产订单号 --> 生产派工单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String takeMaterilNumber;
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
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
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
	 *@return: java.lang.String  产品编号
	 */
	@Column(name ="PRODUCT_SERINO",nullable=true,length=120)
	public java.lang.String getProductSerino(){
		return this.productSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品编号
	 */
	public void setProductSerino(java.lang.String productSerino){
		this.productSerino = productSerino;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品代码
	 */
	@Column(name ="PRODUCT_CODE",nullable=true,length=120)
	public java.lang.String getProductCode(){
		return this.productCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品代码
	 */
	public void setProductCode(java.lang.String productCode){
		this.productCode = productCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=120)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  熟成产品类型
	 */
	@Column(name ="RIPENING_PRO_TYPE",nullable=true,length=120)
	public java.lang.String getRipeningProType(){
		return this.ripeningProType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  熟成产品类型
	 */
	public void setRipeningProType(java.lang.String ripeningProType){
		this.ripeningProType = ripeningProType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品批次
	 */
	@Column(name ="PRODUCT_BATCH",nullable=true,length=120)
	public java.lang.String getProductBatch(){
		return this.productBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品批次
	 */
	public void setProductBatch(java.lang.String productBatch){
		this.productBatch = productBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="PRODUCT_SIZE",nullable=true,length=120)
	public java.lang.String getProductSize(){
		return this.productSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setProductSize(java.lang.String productSize){
		this.productSize = productSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */
	@Column(name ="PRODUCT_NUMBER",nullable=true,length=120)
	public java.lang.String getProductNumber(){
		return this.productNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setProductNumber(java.lang.String productNumber){
		this.productNumber = productNumber;
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
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="WAREHOUSE_POSITION_CODE",nullable=true,length=1202)
	public java.lang.String getWarehousePositionCode(){
		return this.warehousePositionCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setWarehousePositionCode(java.lang.String warehousePositionCode){
		this.warehousePositionCode = warehousePositionCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库位
	 */
	@Column(name ="WAREHOUSE_SPACE_CODE",nullable=true,length=120)
	public java.lang.String getWarehouseSpaceCode(){
		return this.warehouseSpaceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库位
	 */
	public void setWarehouseSpaceCode(java.lang.String warehouseSpaceCode){
		this.warehouseSpaceCode = warehouseSpaceCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  熟成库存状态
	 */
	@Column(name ="RIPENING_STORE_TYPE",nullable=true,length=120)
	public java.lang.String getRipeningStoreType(){
		return this.ripeningStoreType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  熟成库存状态
	 */
	public void setRipeningStoreType(java.lang.String ripeningStoreType){
		this.ripeningStoreType = ripeningStoreType;
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
	 *@return: java.lang.String  生产派工单号
	 */
	@Column(name ="PRODUCTION_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getProductionOrderNumber(){
		return this.productionOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产派工单号
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
