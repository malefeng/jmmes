package com.jeecg.entity.basic;

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
 * @Description: 原料信息
 * @author zhangdaihao
 * @date 2019-10-04 18:30:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_raw_material_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RawMaterialEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**有效性*/
	private java.lang.Integer isValidity;
	/**原料代码*/
	private java.lang.String rawMaterialCode;
	/**物料类型*/
	private java.lang.String rawMaterialType;
	/**原料名称*/
	private java.lang.String rawMaterialName;
	/**规格*/
	private java.lang.String rawMaterialSize;
	/**数量*/
	private java.lang.Integer rawMaterialNumber;
	/**单位*/
	private java.lang.String rawMaterialUnit;
	/**预留1*/
	private java.lang.String attr1;
	/**预留5*/
	private java.lang.String att5;
	/**预留2*/
	private java.lang.String att2;
	/**预留4*/
	private java.lang.String att4;
	/**预留3*/
	private java.lang.String att3;
	
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
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=true,length=36)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效性
	 */
	@Column(name ="IS_VALIDITY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsValidity(){
		return this.isValidity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效性
	 */
	public void setIsValidity(java.lang.Integer isValidity){
		this.isValidity = isValidity;
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
	 *@return: java.lang.String  物料类型
	 */
	@Column(name ="RAW_MATERIAL_TYPE",nullable=true,length=120)
	public java.lang.String getRawMaterialType(){
		return this.rawMaterialType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料类型
	 */
	public void setRawMaterialType(java.lang.String rawMaterialType){
		this.rawMaterialType = rawMaterialType;
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
	 *@return: java.lang.String  规格
	 */
	@Column(name ="RAW_MATERIAL_SIZE",nullable=true,length=120)
	public java.lang.String getRawMaterialSize(){
		return this.rawMaterialSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格
	 */
	public void setRawMaterialSize(java.lang.String rawMaterialSize){
		this.rawMaterialSize = rawMaterialSize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	@Column(name ="RAW_MATERIAL_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRawMaterialNumber(){
		return this.rawMaterialNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setRawMaterialNumber(java.lang.Integer rawMaterialNumber){
		this.rawMaterialNumber = rawMaterialNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
	@Column(name ="RAW_MATERIAL_UNIT",nullable=true,length=120)
	public java.lang.String getRawMaterialUnit(){
		return this.rawMaterialUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setRawMaterialUnit(java.lang.String rawMaterialUnit){
		this.rawMaterialUnit = rawMaterialUnit;
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
	 *@return: java.lang.String  预留5
	 */
	@Column(name ="ATT5",nullable=true,length=120)
	public java.lang.String getAtt5(){
		return this.att5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setAtt5(java.lang.String att5){
		this.att5 = att5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */
	@Column(name ="ATT2",nullable=true,length=120)
	public java.lang.String getAtt2(){
		return this.att2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setAtt2(java.lang.String att2){
		this.att2 = att2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */
	@Column(name ="ATT4",nullable=true,length=120)
	public java.lang.String getAtt4(){
		return this.att4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setAtt4(java.lang.String att4){
		this.att4 = att4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */
	@Column(name ="ATT3",nullable=true,length=120)
	public java.lang.String getAtt3(){
		return this.att3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setAtt3(java.lang.String att3){
		this.att3 = att3;
	}
}
