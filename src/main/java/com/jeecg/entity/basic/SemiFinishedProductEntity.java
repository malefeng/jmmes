package com.jeecg.entity.basic;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 半成品信息
 * @author zhangdaihao
 * @date 2019-10-04 18:29:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_semi_finished_product_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SemiFinishedProductEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**半成品代码*/
	private java.lang.String semiFinishedCode;
	/**半成品名称*/
	private java.lang.String semiFinishedName;
	/**物料类型*/
	private java.lang.String materialType;
	/**规格*/
	private java.lang.String semiFinishedSize;
	/**数量*/
	private java.lang.Integer semiFinishedNumber;
	/**单位*/
	private java.lang.String semiFinishedUnitCode;
	/**预留5*/
	private java.lang.String attr5;
	/**预留2*/
	private java.lang.String attr2;
	/**预留1*/
	private java.lang.String attr1;
	/**预留4*/
	private java.lang.String attr4;
	/**预留3*/
	private java.lang.String attr3;
	/**首末检模板*/
	private java.lang.String ffCheckTemp;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
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
	 *@return: java.lang.String  半成品代码
	 */
	@Column(name ="SEMI_FINISHED_CODE",nullable=false,length=120)
	public java.lang.String getSemiFinishedCode(){
		return this.semiFinishedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品代码
	 */
	public void setSemiFinishedCode(java.lang.String semiFinishedCode){
		this.semiFinishedCode = semiFinishedCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  半成品名称
	 */
	@Column(name ="SEMI_FINISHED_NAME",nullable=false,length=120)
	public java.lang.String getSemiFinishedName(){
		return this.semiFinishedName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品名称
	 */
	public void setSemiFinishedName(java.lang.String semiFinishedName){
		this.semiFinishedName = semiFinishedName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料类型
	 */
	@Column(name ="MATERIAL_TYPE",nullable=true,length=120)
	public java.lang.String getMaterialType(){
		return this.materialType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料类型
	 */
	public void setMaterialType(java.lang.String materialType){
		this.materialType = materialType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格
	 */
	@Column(name ="SEMI_FINISHED_SIZE",nullable=true,length=120)
	public java.lang.String getSemiFinishedSize(){
		return this.semiFinishedSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格
	 */
	public void setSemiFinishedSize(java.lang.String semiFinishedSize){
		this.semiFinishedSize = semiFinishedSize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	@Column(name ="SEMI_FINISHED_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSemiFinishedNumber(){
		return this.semiFinishedNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setSemiFinishedNumber(java.lang.Integer semiFinishedNumber){
		this.semiFinishedNumber = semiFinishedNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
	@Column(name ="SEMI_FINISHED_UNIT_CODE",nullable=true,length=120)
	public java.lang.String getSemiFinishedUnitCode(){
		return this.semiFinishedUnitCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setSemiFinishedUnitCode(java.lang.String semiFinishedUnitCode){
		this.semiFinishedUnitCode = semiFinishedUnitCode;
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
	 *@return: java.lang.String  首末检模板
	 */
	@Column(name ="FF_CHECK_TEMP",nullable=true,length=500)
	public java.lang.String getFfCheckTemp(){
		return this.ffCheckTemp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首末检模板
	 */
	public void setFfCheckTemp(java.lang.String ffCheckTemp){
		this.ffCheckTemp = ffCheckTemp;
	}
}
