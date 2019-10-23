package com.jeecg.entity.basic;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 承运商信息
 * @author zhangdaihao
 * @date 2019-10-04 18:32:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_common_carrier_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CommonCarrierEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**承运商名称*/
	private java.lang.String transName;
	/**承运商代码*/
	private java.lang.String transCode;
	/**承运商联系人*/
	private java.lang.String transLinkman;
	/**承运商联系电话*/
	private java.lang.String transContactNumben;
	/**承运商联系地址*/
	private java.lang.String transContactAddress;
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
	/**承运商法人*/
	private java.lang.String transLegalPerson;
	
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
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
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
	 *@return: java.lang.String  承运商名称
	 */
	@Column(name ="TRANS_NAME",nullable=true,length=120)
	public java.lang.String getTransName(){
		return this.transName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商名称
	 */
	public void setTransName(java.lang.String transName){
		this.transName = transName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商代码
	 */
	@Column(name ="TRANS_CODE",nullable=true,length=120)
	public java.lang.String getTransCode(){
		return this.transCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商代码
	 */
	public void setTransCode(java.lang.String transCode){
		this.transCode = transCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商联系人
	 */
	@Column(name ="TRANS_LINKMAN",nullable=true,length=120)
	public java.lang.String getTransLinkman(){
		return this.transLinkman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商联系人
	 */
	public void setTransLinkman(java.lang.String transLinkman){
		this.transLinkman = transLinkman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商联系电话
	 */
	@Column(name ="TRANS_CONTACT_NUMBEN",nullable=true,length=120)
	public java.lang.String getTransContactNumben(){
		return this.transContactNumben;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商联系电话
	 */
	public void setTransContactNumben(java.lang.String transContactNumben){
		this.transContactNumben = transContactNumben;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商联系地址
	 */
	@Column(name ="TRANS_CONTACT_ADDRESS",nullable=true,length=120)
	public java.lang.String getTransContactAddress(){
		return this.transContactAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商联系地址
	 */
	public void setTransContactAddress(java.lang.String transContactAddress){
		this.transContactAddress = transContactAddress;
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
	 *@return: java.lang.String  承运商法人
	 */
	@Column(name ="TRANS_LEGAL_PERSON",nullable=true,length=120)
	public java.lang.String getTransLegalPerson(){
		return this.transLegalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商法人
	 */
	public void setTransLegalPerson(java.lang.String transLegalPerson){
		this.transLegalPerson = transLegalPerson;
	}
}
