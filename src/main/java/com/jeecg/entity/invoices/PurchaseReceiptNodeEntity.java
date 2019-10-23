package com.jeecg.entity.invoices;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 采购收料单物料信息
 * @author zhangdaihao
 * @date 2019-10-09 22:26:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_purchase_receipt_node", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PurchaseReceiptNodeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**原料代码*/
	private java.lang.String rawMaterialCode;
	/**原料名称*/
	private java.lang.String rawMaterialName;
	/**规格型号*/
	private java.lang.String rawMaterialSize;
	/**单位*/
	private java.lang.Integer rawMaterialUnit;
	/**实到数量*/
	private java.lang.Double actualArrivalNumber;
	/**实收数量*/
	private java.lang.Double actualReceivedNumber;
	/**拒收数量*/
	private java.lang.Double rejectionNumber;
	/**仓库*/
	private java.lang.String repositoryCode;
	/**库位*/
	private java.lang.String repositorySpace;
	/**库存状态*/
	private java.lang.String inventoryStatus;
	/**拒收原因*/
	private java.lang.String rejectionReason;
	/**采购收料单主键*/
	private java.lang.String inspectId;
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
	@Column(name ="CREATE_BY",nullable=true,length=32)
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
	 *@return: java.lang.String  原料代码
	 */
	@Column(name ="RAW_MATERIAL_CODE",nullable=true,length=120)
	public java.lang.String getRawMaterialCode(){
		return this.rawMaterialCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料代码
	 */
	public void setRawMaterialCode(java.lang.String rawMaterialCode){
		this.rawMaterialCode = rawMaterialCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原料名称
	 */
	@Column(name ="RAW_MATERIAL_NAME",nullable=true,length=120)
	public java.lang.String getRawMaterialName(){
		return this.rawMaterialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料名称
	 */
	public void setRawMaterialName(java.lang.String rawMaterialName){
		this.rawMaterialName = rawMaterialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="RAW_MATERIAL_SIZE",nullable=true,length=120)
	public java.lang.String getRawMaterialSize(){
		return this.rawMaterialSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setRawMaterialSize(java.lang.String rawMaterialSize){
		this.rawMaterialSize = rawMaterialSize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  单位
	 */
	@Column(name ="RAW_MATERIAL_UNIT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRawMaterialUnit(){
		return this.rawMaterialUnit;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  单位
	 */
	public void setRawMaterialUnit(java.lang.Integer rawMaterialUnit){
		this.rawMaterialUnit = rawMaterialUnit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  实到数量
	 */
	@Column(name ="ACTUAL_ARRIVAL_NUMBER",nullable=true,precision=22)
	public java.lang.Double getActualArrivalNumber(){
		return this.actualArrivalNumber;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  实到数量
	 */
	public void setActualArrivalNumber(java.lang.Double actualArrivalNumber){
		this.actualArrivalNumber = actualArrivalNumber;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  实收数量
	 */
	@Column(name ="ACTUAL_RECEIVED_NUMBER",nullable=true,precision=22)
	public java.lang.Double getActualReceivedNumber(){
		return this.actualReceivedNumber;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  实收数量
	 */
	public void setActualReceivedNumber(java.lang.Double actualReceivedNumber){
		this.actualReceivedNumber = actualReceivedNumber;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  拒收数量
	 */
	@Column(name ="REJECTION_NUMBER",nullable=true,precision=22)
	public java.lang.Double getRejectionNumber(){
		return this.rejectionNumber;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  拒收数量
	 */
	public void setRejectionNumber(java.lang.Double rejectionNumber){
		this.rejectionNumber = rejectionNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库位
	 */
	@Column(name ="REPOSITORY_SPACE",nullable=true,length=120)
	public java.lang.String getRepositorySpace(){
		return this.repositorySpace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库位
	 */
	public void setRepositorySpace(java.lang.String repositorySpace){
		this.repositorySpace = repositorySpace;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存状态
	 */
	@Column(name ="INVENTORY_STATUS",nullable=true,length=120)
	public java.lang.String getInventoryStatus(){
		return this.inventoryStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存状态
	 */
	public void setInventoryStatus(java.lang.String inventoryStatus){
		this.inventoryStatus = inventoryStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拒收原因
	 */
	@Column(name ="REJECTION_REASON",nullable=true,length=256)
	public java.lang.String getRejectionReason(){
		return this.rejectionReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拒收原因
	 */
	public void setRejectionReason(java.lang.String rejectionReason){
		this.rejectionReason = rejectionReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购收料单主键
	 */
	@Column(name ="INSPECT_ID",nullable=true,length=120)
	public java.lang.String getInspectId(){
		return this.inspectId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购收料单主键
	 */
	public void setInspectId(java.lang.String inspectId){
		this.inspectId = inspectId;
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
