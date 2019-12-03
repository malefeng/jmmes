package com.jeecg.entity.warehous;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 盘点任务
 * @author zhangdaihao
 * @date 2019-11-13 23:39:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_check_task", schema = "")
@SuppressWarnings("serial")
public class CheckTaskEntity implements java.io.Serializable {
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
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**盘点批次号*/
	private java.lang.String checkBatch;
	/**盘点类型*/
	private java.lang.String checkType;
	/**盘点仓库代码*/
	private java.lang.String repositoryCode;
	/**盘点仓库名称*/
	private java.lang.String repositoryName;
	/**盘点原料代码*/
	private java.lang.String rawMaterialCode;
	/**盘点原料名称*/
	private java.lang.String rawMaterialName;
	/**盘点成品代码*/
	private java.lang.String productCode;
	/**盘点成品名称*/
	private java.lang.String productName;
	/**盘点状态*/
	private java.lang.String checkStatus;
	/**计划盘点时间*/
	private java.util.Date checkTimePlan;
	/**实际盘点时间*/
	private java.util.Date checkTimeReal;
	/**计划盘点人*/
	private java.lang.String checkPersonPlan;
	/**实际盘点人*/
	private java.lang.String checkPersonReal;
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
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点批次号
	 */
	@Column(name ="CHECK_BATCH",nullable=true,length=120)
	public java.lang.String getCheckBatch(){
		return this.checkBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点批次号
	 */
	public void setCheckBatch(java.lang.String checkBatch){
		this.checkBatch = checkBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点类型
	 */
	@Column(name ="CHECK_TYPE",nullable=true,length=120)
	public java.lang.String getCheckType(){
		return this.checkType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点类型
	 */
	public void setCheckType(java.lang.String checkType){
		this.checkType = checkType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点仓库代码
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点仓库代码
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点仓库名称
	 */
	@Column(name ="REPOSITORY_NAME",nullable=true,length=120)
	public java.lang.String getRepositoryName(){
		return this.repositoryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点仓库名称
	 */
	public void setRepositoryName(java.lang.String repositoryName){
		this.repositoryName = repositoryName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点原料代码
	 */
	@Column(name ="RAW_MATERIAL_CODE",nullable=true,length=120)
	public java.lang.String getRawMaterialCode(){
		return this.rawMaterialCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点原料代码
	 */
	public void setRawMaterialCode(java.lang.String rawMaterialCode){
		this.rawMaterialCode = rawMaterialCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点原料名称
	 */
	@Column(name ="RAW_MATERIAL_NAME",nullable=true,length=120)
	public java.lang.String getRawMaterialName(){
		return this.rawMaterialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点原料名称
	 */
	public void setRawMaterialName(java.lang.String rawMaterialName){
		this.rawMaterialName = rawMaterialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点成品代码
	 */
	@Column(name ="PRODUCT_CODE",nullable=true,length=120)
	public java.lang.String getProductCode(){
		return this.productCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点成品代码
	 */
	public void setProductCode(java.lang.String productCode){
		this.productCode = productCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点成品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=120)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点成品名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点状态
	 */
	@Column(name ="CHECK_STATUS",nullable=true,length=120)
	public java.lang.String getCheckStatus(){
		return this.checkStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点状态
	 */
	public void setCheckStatus(java.lang.String checkStatus){
		this.checkStatus = checkStatus;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划盘点时间
	 */
	@Column(name ="CHECK_TIME_PLAN",nullable=true)
	public java.util.Date getCheckTimePlan(){
		return this.checkTimePlan;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划盘点时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setCheckTimePlan(java.util.Date checkTimePlan){
		this.checkTimePlan = checkTimePlan;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际盘点时间
	 */
	@Column(name ="CHECK_TIME_REAL",nullable=true)
	public java.util.Date getCheckTimeReal(){
		return this.checkTimeReal;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际盘点时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setCheckTimeReal(java.util.Date checkTimeReal){
		this.checkTimeReal = checkTimeReal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划盘点人
	 */
	@Column(name ="CHECK_PERSON_PLAN",nullable=true,length=120)
	public java.lang.String getCheckPersonPlan(){
		return this.checkPersonPlan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划盘点人
	 */
	public void setCheckPersonPlan(java.lang.String checkPersonPlan){
		this.checkPersonPlan = checkPersonPlan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际盘点人
	 */
	@Column(name ="CHECK_PERSON_REAL",nullable=true,length=120)
	public java.lang.String getCheckPersonReal(){
		return this.checkPersonReal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际盘点人
	 */
	public void setCheckPersonReal(java.lang.String checkPersonReal){
		this.checkPersonReal = checkPersonReal;
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
