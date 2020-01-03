package com.jeecg.entity.production;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 成品生产列表
 * @author zhangdaihao
 * @date 2019-10-11 03:37:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_production_list", schema = "")
@SuppressWarnings("serial")
public class FinishedProductionEntity implements java.io.Serializable {
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
	/**生产人*/
	private java.lang.String productionPersonCode;
	/**生产时间*/
	private java.util.Date productionDate;
	/**生产派工单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String takeMaterilNumber;
	/**是否需要熟成*/
	private java.lang.Integer needRipening;
	/**熟成时长*/
	private java.lang.Double ripeningHours;
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
	 *@return: java.lang.String  生产人
	 */
	@Column(name ="PRODUCTION_PERSON_CODE",nullable=true,length=120)
	public java.lang.String getProductionPersonCode(){
		return this.productionPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产人
	 */
	public void setProductionPersonCode(java.lang.String productionPersonCode){
		this.productionPersonCode = productionPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生产时间
	 */
	@Column(name ="PRODUCTION_DATE",nullable=true)
	public java.util.Date getProductionDate(){
		return this.productionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生产时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setProductionDate(java.util.Date productionDate){
		this.productionDate = productionDate;
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
	 *@return: java.lang.String  是否需要熟成
	 */
	@Column(name ="NEED_RIPENING",nullable=true,length=120)
	public java.lang.Integer getNeedRipening(){
		return this.needRipening;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否需要熟成
	 */
	public void setNeedRipening(java.lang.Integer needRipening){
		this.needRipening = needRipening;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  熟成时长
	 */
	@Column(name ="RIPENING_HOURS",nullable=true,length=120)
	public java.lang.Double getRipeningHours(){
		return this.ripeningHours;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  熟成时长
	 */
	public void setRipeningHours(java.lang.Double ripeningHours){
		this.ripeningHours = ripeningHours;
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
