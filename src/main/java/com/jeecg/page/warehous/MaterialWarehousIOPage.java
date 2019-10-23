package com.jeecg.page.warehous;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import com.jeecg.entity.warehous.MaterialWarehousNodeEntity;

/**   
 * @Title: Entity
 * @Description: 原料出入库列表
 * @author zhangdaihao
 * @date 2019-10-11 05:19:33
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class MaterialWarehousIOPage implements java.io.Serializable {
	/**保存-原料出库详情列表*/
	private List<MaterialWarehousNodeEntity> materialWarehousNodeList = new ArrayList<MaterialWarehousNodeEntity>();
	public List<MaterialWarehousNodeEntity> getMaterialWarehousNodeList() {
		return materialWarehousNodeList;
	}
	public void setMaterialWarehousNodeList(List<MaterialWarehousNodeEntity> materialWarehousNodeList) {
		this.materialWarehousNodeList = materialWarehousNodeList;
	}


	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**原料编号*/
	private java.lang.String materialSerino;
	/**原料代码*/
	private java.lang.String materialCode;
	/**原料名称*/
	private java.lang.String materialName;
	/**规格型号*/
	private java.lang.String materialSize;
	/**批次*/
	private java.lang.String batchNumber;
	/**入库数量*/
	private java.lang.String warehousingNumber;
	/**入库人*/
	private java.lang.String warehousingPersonCode;
	/**入库时间*/
	private java.util.Date warehousingDate;
	/**入库仓库*/
	private java.lang.String warehousePositionCode;
	/**入库库位*/
	private java.lang.String warehouseSpaceCode;
	/**已出库数量*/
	private java.lang.String warehouseOutNumber;
	/**虚拟仓库数量*/
	private java.lang.String virtualRepositoryNumber;
	/**单位*/
	private java.lang.String unit;
	/**虚拟仓库*/
	private java.lang.String virtualRepository;
	/**最新出库人*/
	private java.lang.String warehouseOutPersonCode;
	/**最新出库时间*/
	private java.util.Date warehouseOutDate;
	/**库存状态*/
	private java.lang.String ioType;
	/**收料单号*/
	private java.lang.String receivingOrderNumber;
	/**收料单采购订单号*/
	private java.lang.String purchaseOrderNumber;
	/**收料单销售订单号*/
	private java.lang.String salesOrderNumber;
	/**供应商*/
	private java.lang.String supplierCode;
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
	 *@return: java.lang.String  批次
	 */
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批次
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库数量
	 */
	public java.lang.String getWarehousingNumber(){
		return this.warehousingNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库数量
	 */
	public void setWarehousingNumber(java.lang.String warehousingNumber){
		this.warehousingNumber = warehousingNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库人
	 */
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
	public java.util.Date getWarehousingDate(){
		return this.warehousingDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库时间
	 */
	public void setWarehousingDate(java.util.Date warehousingDate){
		this.warehousingDate = warehousingDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库仓库
	 */
	public java.lang.String getWarehousePositionCode(){
		return this.warehousePositionCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库仓库
	 */
	public void setWarehousePositionCode(java.lang.String warehousePositionCode){
		this.warehousePositionCode = warehousePositionCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库库位
	 */
	public java.lang.String getWarehouseSpaceCode(){
		return this.warehouseSpaceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库库位
	 */
	public void setWarehouseSpaceCode(java.lang.String warehouseSpaceCode){
		this.warehouseSpaceCode = warehouseSpaceCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已出库数量
	 */
	public java.lang.String getWarehouseOutNumber(){
		return this.warehouseOutNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已出库数量
	 */
	public void setWarehouseOutNumber(java.lang.String warehouseOutNumber){
		this.warehouseOutNumber = warehouseOutNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  虚拟仓库数量
	 */
	public java.lang.String getVirtualRepositoryNumber(){
		return this.virtualRepositoryNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  虚拟仓库数量
	 */
	public void setVirtualRepositoryNumber(java.lang.String virtualRepositoryNumber){
		this.virtualRepositoryNumber = virtualRepositoryNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
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
	 *@return: java.lang.String  虚拟仓库
	 */
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
	 *@return: java.lang.String  最新出库人
	 */
	public java.lang.String getWarehouseOutPersonCode(){
		return this.warehouseOutPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最新出库人
	 */
	public void setWarehouseOutPersonCode(java.lang.String warehouseOutPersonCode){
		this.warehouseOutPersonCode = warehouseOutPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最新出库时间
	 */
	public java.util.Date getWarehouseOutDate(){
		return this.warehouseOutDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最新出库时间
	 */
	public void setWarehouseOutDate(java.util.Date warehouseOutDate){
		this.warehouseOutDate = warehouseOutDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存状态
	 */
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
	 *@return: java.lang.String  收料单号
	 */
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
	 *@return: java.lang.String  供应商
	 */
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
	 *@return: java.lang.String  预留1
	 */
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
