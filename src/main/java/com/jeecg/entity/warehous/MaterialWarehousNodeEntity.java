package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 原料出库详情列表
 * @author zhangdaihao
 * @date 2019-10-11 05:19:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_material_warehous_node_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MaterialWarehousNodeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**原料编号*/
	private java.lang.String materialSerino;
	/**原料代码*/
	private java.lang.String code;
	/**原料名称*/
	private java.lang.String materialName;
	/**规格型号*/
	private java.lang.String materialSize;
	/**原料批次*/
	private java.lang.String batchNumber;
	/**出库数量*/
	private java.lang.String warehouseOutNumber;
	/**本次入虚拟仓库数量*/
	private java.lang.String virtualRepositoryNumber;
	/**虚拟仓库*/
	private java.lang.String virtualRepository;
	/**单位*/
	private java.lang.String unit;
	/**出库人*/
	private java.lang.String warehouseOutPersonCode;
	/**出库时间*/
	private java.util.Date warehouseOutDate;
	/**出库状态*/
	private java.lang.String warehouseOutState;
	/**生产订单号 --> 生产派工单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String prodReqOrderNumber;
	/**生产用料清单号 -- >生产订单号*/
	private java.lang.String productionMaterialNumber;
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
	 *@return: java.lang.String  创建
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建
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
	@Column(name ="CODE",nullable=true,length=120)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料代码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
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
	 *@return: java.lang.String  出库数量
	 */
	@Column(name ="WAREHOUSE_OUT_NUMBER",nullable=true,length=120)
	public java.lang.String getWarehouseOutNumber(){
		return this.warehouseOutNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库数量
	 */
	public void setWarehouseOutNumber(java.lang.String warehouseOutNumber){
		this.warehouseOutNumber = warehouseOutNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本次入虚拟仓库数量
	 */
	@Column(name ="VIRTUAL_REPOSITORY_NUMBER",nullable=true,length=120)
	public java.lang.String getVirtualRepositoryNumber(){
		return this.virtualRepositoryNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本次入虚拟仓库数量
	 */
	public void setVirtualRepositoryNumber(java.lang.String virtualRepositoryNumber){
		this.virtualRepositoryNumber = virtualRepositoryNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  虚拟仓库
	 */
	@Column(name ="VIRTUAL_REPOSITORY",nullable=true,length=120)
	public java.lang.String getVirtualRepository(){
		return this.virtualRepository;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  虚拟仓库
	 */
	public void setVirtualRepository(java.lang.String virtualRepository){
		this.virtualRepository = virtualRepository;
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
	 *@return: java.lang.String  出库状态
	 */
	@Column(name ="WAREHOUSE_OUT_STATE",nullable=true,length=120)
	public java.lang.String getWarehouseOutState(){
		return this.warehouseOutState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库状态
	 */
	public void setWarehouseOutState(java.lang.String warehouseOutState){
		this.warehouseOutState = warehouseOutState;
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
	@Column(name ="PROD_REQ_ORDER_NUMBER",nullable=true,length=120)
	public java.lang.String getProdReqOrderNumber(){
		return this.prodReqOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产领料单号
	 */
	public void setProdReqOrderNumber(java.lang.String prodReqOrderNumber){
		this.prodReqOrderNumber = prodReqOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产用料清单号
	 */
	@Column(name ="PRODUCTION_MATERIAL_NUMBER",nullable=true,length=120)
	public java.lang.String getProductionMaterialNumber(){
		return this.productionMaterialNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产用料清单号
	 */
	public void setProductionMaterialNumber(java.lang.String productionMaterialNumber){
		this.productionMaterialNumber = productionMaterialNumber;
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
