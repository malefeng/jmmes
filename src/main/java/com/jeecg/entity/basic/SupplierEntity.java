package com.jeecg.entity.basic;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 供应商信息
 * @author zhangdaihao
 * @date 2019-10-04 18:32:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_supplier_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SupplierEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createPersonCode;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人*/
	private java.lang.String updatePersonCode;
	/**更新日期*/
	private java.util.Date updateDate;
	/**有效性*/
	private java.lang.Integer isValidity;
	/**供应商代码*/
	private java.lang.String supplierCode;
	/**供应商名称*/
	private java.lang.String supplierName;
	/**供应商法人*/
	private java.lang.String supplierLegalPerson;
	/**供应商联系人*/
	private java.lang.String supplierLinkman;
	/**供应商联系电话*/
	private java.lang.String supplierContactNumber;
	/**供应商联系地址*/
	private java.lang.String supplierContactAddress;
	/**备注*/
	private java.lang.String remark;
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
	/**供应商属性*/
	private java.lang.String supplierAttr;
	
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
	@Column(name ="CREATE_PERSON_CODE",nullable=true,length=32)
	public java.lang.String getCreatePersonCode(){
		return this.createPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreatePersonCode(java.lang.String createPersonCode){
		this.createPersonCode = createPersonCode;
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
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_PERSON_CODE",nullable=true,length=50)
	public java.lang.String getUpdatePersonCode(){
		return this.updatePersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdatePersonCode(java.lang.String updatePersonCode){
		this.updatePersonCode = updatePersonCode;
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
	 *@return: java.lang.String  供应商代码
	 */
	@Column(name ="SUPPLIER_CODE",nullable=false,length=120)
	public java.lang.String getSupplierCode(){
		return this.supplierCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商代码
	 */
	public void setSupplierCode(java.lang.String supplierCode){
		this.supplierCode = supplierCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */
	@Column(name ="SUPPLIER_NAME",nullable=false,length=120)
	public java.lang.String getSupplierName(){
		return this.supplierName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setSupplierName(java.lang.String supplierName){
		this.supplierName = supplierName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商法人
	 */
	@Column(name ="SUPPLIER_LEGAL_PERSON",nullable=true,length=120)
	public java.lang.String getSupplierLegalPerson(){
		return this.supplierLegalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商法人
	 */
	public void setSupplierLegalPerson(java.lang.String supplierLegalPerson){
		this.supplierLegalPerson = supplierLegalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商联系人
	 */
	@Column(name ="SUPPLIER_LINKMAN",nullable=true,length=120)
	public java.lang.String getSupplierLinkman(){
		return this.supplierLinkman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商联系人
	 */
	public void setSupplierLinkman(java.lang.String supplierLinkman){
		this.supplierLinkman = supplierLinkman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商联系电话
	 */
	@Column(name ="SUPPLIER_CONTACT_NUMBER",nullable=true,length=120)
	public java.lang.String getSupplierContactNumber(){
		return this.supplierContactNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商联系电话
	 */
	public void setSupplierContactNumber(java.lang.String supplierContactNumber){
		this.supplierContactNumber = supplierContactNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商联系地址
	 */
	@Column(name ="SUPPLIER_CONTACT_ADDRESS",nullable=true,length=128)
	public java.lang.String getSupplierContactAddress(){
		return this.supplierContactAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商联系地址
	 */
	public void setSupplierContactAddress(java.lang.String supplierContactAddress){
		this.supplierContactAddress = supplierContactAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=128)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
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
	 *@return: java.lang.String  供应商属性
	 */
	@Column(name ="SUPPLIER_ATTR",nullable=true,length=120)
	public java.lang.String getSupplierAttr(){
		return this.supplierAttr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商属性
	 */
	public void setSupplierAttr(java.lang.String supplierAttr){
		this.supplierAttr = supplierAttr;
	}
}
