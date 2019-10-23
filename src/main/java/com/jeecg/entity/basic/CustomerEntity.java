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
 * @Description: 客户信息
 * @author zhangdaihao
 * @date 2019-10-04 18:32:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_customer_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CustomerEntity implements java.io.Serializable {
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
	/**客户代码*/
	private java.lang.String customerCode;
	/**客户名称*/
	private java.lang.String customerName;
	/**客户法人*/
	private java.lang.String customerLegalPerson;
	/**客户联系人*/
	private java.lang.String customerLinkman;
	/**客户联系电话*/
	private java.lang.String customerContactNumber;
	/**客户联系地址*/
	private java.lang.String customerContactAddress;
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
	@Column(name ="CREATE_PERSON_CODE",nullable=true,length=36)
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
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_PERSON_CODE",nullable=true,length=36)
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
	 *@return: java.lang.String  客户代码
	 */
	@Column(name ="CUSTOMER_CODE",nullable=false,length=120)
	public java.lang.String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户代码
	 */
	public void setCustomerCode(java.lang.String customerCode){
		this.customerCode = customerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */
	@Column(name ="CUSTOMER_NAME",nullable=false,length=128)
	public java.lang.String getCustomerName(){
		return this.customerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCustomerName(java.lang.String customerName){
		this.customerName = customerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户法人
	 */
	@Column(name ="CUSTOMER_LEGAL_PERSON",nullable=true,length=120)
	public java.lang.String getCustomerLegalPerson(){
		return this.customerLegalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户法人
	 */
	public void setCustomerLegalPerson(java.lang.String customerLegalPerson){
		this.customerLegalPerson = customerLegalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户联系人
	 */
	@Column(name ="CUSTOMER_LINKMAN",nullable=true,length=120)
	public java.lang.String getCustomerLinkman(){
		return this.customerLinkman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户联系人
	 */
	public void setCustomerLinkman(java.lang.String customerLinkman){
		this.customerLinkman = customerLinkman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户联系电话
	 */
	@Column(name ="CUSTOMER_CONTACT_NUMBER",nullable=true,length=120)
	public java.lang.String getCustomerContactNumber(){
		return this.customerContactNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户联系电话
	 */
	public void setCustomerContactNumber(java.lang.String customerContactNumber){
		this.customerContactNumber = customerContactNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户联系地址
	 */
	@Column(name ="CUSTOMER_CONTACT_ADDRESS",nullable=true,length=128)
	public java.lang.String getCustomerContactAddress(){
		return this.customerContactAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户联系地址
	 */
	public void setCustomerContactAddress(java.lang.String customerContactAddress){
		this.customerContactAddress = customerContactAddress;
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
}
