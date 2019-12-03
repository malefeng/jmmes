package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 调库调位
 * @author zhangdaihao
 * @date 2019-11-24 14:49:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_warehous_io_change_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class WarehousIOChangeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**物资类型*/
	private java.lang.String materialsType;
	/**调配人*/
	private java.lang.String changeMan;
	/**调配时间*/
	private java.util.Date changeDate;
	/**物资编号*/
	private java.lang.String materialsSerino;
	/**物资数量*/
	private java.lang.Integer materialsNumber;
	/**移入仓库*/
	private java.lang.String repositoryCode;
	/**移入库位*/
	private java.lang.String storageCode;
	/**备注*/
	private java.lang.String remark;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
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
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
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
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资类型
	 */
	@Column(name ="MATERIALS_TYPE",nullable=true,length=2)
	public java.lang.String getMaterialsType(){
		return this.materialsType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资类型
	 */
	public void setMaterialsType(java.lang.String materialsType){
		this.materialsType = materialsType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  调配人
	 */
	@Column(name ="CHANGE_MAN",nullable=true,length=32)
	public java.lang.String getChangeMan(){
		return this.changeMan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  调配人
	 */
	public void setChangeMan(java.lang.String changeMan){
		this.changeMan = changeMan;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  调配时间
	 */
	@Column(name ="CHANGE_DATE",nullable=true)
	public java.util.Date getChangeDate(){
		return this.changeDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  调配时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setChangeDate(java.util.Date changeDate){
		this.changeDate = changeDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资编号
	 */
	@Column(name ="MATERIALS_SERINO",nullable=true,length=32)
	public java.lang.String getMaterialsSerino(){
		return this.materialsSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资编号
	 */
	public void setMaterialsSerino(java.lang.String materialsSerino){
		this.materialsSerino = materialsSerino;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  物资数量
	 */
	@Column(name ="MATERIALS_NUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMaterialsNumber(){
		return this.materialsNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  物资数量
	 */
	public void setMaterialsNumber(java.lang.Integer materialsNumber){
		this.materialsNumber = materialsNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  移入仓库
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=32)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  移入仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  移入库位
	 */
	@Column(name ="STORAGE_CODE",nullable=true,length=32)
	public java.lang.String getStorageCode(){
		return this.storageCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  移入库位
	 */
	public void setStorageCode(java.lang.String storageCode){
		this.storageCode = storageCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */
	@Column(name ="REMARK",nullable=true,length=32)
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
	 *@return: java.lang.String  预留2
	 */
	@Column(name ="ATTR2",nullable=true,length=32)
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
	@Column(name ="ATTR3",nullable=true,length=32)
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
	@Column(name ="ATTR4",nullable=true,length=32)
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
	@Column(name ="ATTR5",nullable=true,length=32)
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
