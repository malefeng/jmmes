package com.jeecg.entity.invoices;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 生产领料单配料物料详情
 * @author zhangdaihao
 * @date 2019-10-10 22:17:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_production_requisition_node", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ProductionRequisitionNodeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**原料编号*/
	private java.lang.String rawMaterialSerino;
	/**原料代码*/
	private java.lang.String rawMaterialCode;
	/**原料名称*/
	private java.lang.String rawMaterialName;
	/**单位*/
	private java.lang.String unit;
	/**数量*/
	private java.lang.String rawMaterialNum;
	/**批号*/
	private java.lang.String batchNumber;
	/**成品代码*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**原料入库时间*/
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date warehousingDate;
	/**生产领料单主键*/
	private java.lang.String inspectId;
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
	 *@return: java.lang.String  原料编号
	 */
	@Column(name ="RAW_MATERIAL_SERINO",nullable=true,length=120)
	public java.lang.String getRawMaterialSerino(){
		return this.rawMaterialSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原料编号
	 */
	public void setRawMaterialSerino(java.lang.String rawMaterialSerino){
		this.rawMaterialSerino = rawMaterialSerino;
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
	 *@return: java.lang.String  数量
	 */
	@Column(name ="RAW_MATERIAL_NUM",nullable=true,length=120)
	public java.lang.String getRawMaterialNum(){
		return this.rawMaterialNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setRawMaterialNum(java.lang.String rawMaterialNum){
		this.rawMaterialNum = rawMaterialNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批号
	 */
	@Column(name ="BATCH_NUMBER",nullable=true,length=120)
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批号
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  原料入库时间
	 */
	@Column(name ="WAREHOUSING_DATE",nullable=true)
	public java.util.Date getWarehousingDate(){
		return this.warehousingDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  原料入库时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setWarehousingDate(java.util.Date warehousingDate){
		this.warehousingDate = warehousingDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产领料单主键
	 */
	@Column(name ="INSPECT_ID",nullable=true,length=120)
	public java.lang.String getInspectId(){
		return this.inspectId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产领料单主键
	 */
	public void setInspectId(java.lang.String inspectId){
		this.inspectId = inspectId;
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
