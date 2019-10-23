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
 * @Description: 仓库信息
 * @author zhangdaihao
 * @date 2019-10-04 18:31:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_repository_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RepositoryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**有效性*/
	private java.lang.Integer isValidity;
	/**仓库代码*/
	private java.lang.String repositoryCode;
	/**仓库名称*/
	private java.lang.String repositoryName;
	/**仓库容量*/
	private java.lang.Double repositoryCapacity;
	/**仓库单位*/
	private java.lang.String repositoryUnitCode;
	/**仓库类型*/
	private java.lang.String repositoryType;
	/**仓库位置*/
	private java.lang.String repositoryPosition;
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
	 *@return: java.lang.String  仓库代码
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库代码
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库名称
	 */
	@Column(name ="REPOSITORY_NAME",nullable=true,length=120)
	public java.lang.String getRepositoryName(){
		return this.repositoryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库名称
	 */
	public void setRepositoryName(java.lang.String repositoryName){
		this.repositoryName = repositoryName;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  仓库容量
	 */
	@Column(name ="REPOSITORY_CAPACITY",nullable=true,precision=120,scale=3)
	public java.lang.Double getRepositoryCapacity(){
		return this.repositoryCapacity;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  仓库容量
	 */
	public void setRepositoryCapacity(java.lang.Double repositoryCapacity){
		this.repositoryCapacity = repositoryCapacity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库单位
	 */
	@Column(name ="REPOSITORY_UNIT_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryUnitCode(){
		return this.repositoryUnitCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库单位
	 */
	public void setRepositoryUnitCode(java.lang.String repositoryUnitCode){
		this.repositoryUnitCode = repositoryUnitCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库类型
	 */
	@Column(name ="REPOSITORY_TYPE",nullable=true,length=120)
	public java.lang.String getRepositoryType(){
		return this.repositoryType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库类型
	 */
	public void setRepositoryType(java.lang.String repositoryType){
		this.repositoryType = repositoryType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库位置
	 */
	@Column(name ="REPOSITORY_POSITION",nullable=true,length=120)
	public java.lang.String getRepositoryPosition(){
		return this.repositoryPosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库位置
	 */
	public void setRepositoryPosition(java.lang.String repositoryPosition){
		this.repositoryPosition = repositoryPosition;
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
