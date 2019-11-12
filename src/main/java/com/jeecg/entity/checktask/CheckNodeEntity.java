package com.jeecg.entity.checktask;

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
 * @Description: 盘点明细表
 * @author zhangdaihao
 * @date 2019-11-12 23:12:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_check_node_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CheckNodeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**盘点批次号*/
	private java.lang.String checkBatch;
	/**盘点类型*/
	private java.lang.String checkType;
	/**物料代码*/
	private java.lang.String mtlCode;
	/**物料名称*/
	private java.lang.String mtlName;
	/**规格型号*/
	private java.lang.String mtlSize;
	/**批次*/
	private java.lang.String mtlBatch;
	/**库存数量*/
	private java.lang.String stockNum;
	/**盘点数量*/
	private java.lang.String checkNum;
	/**盈亏数量*/
	private java.lang.String profitLossNum;
	/**单位*/
	private java.lang.String unit;
	/**仓库*/
	private java.lang.String repositoryCode;
	/**库位*/
	private java.lang.String storageCode;
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
	 *@return: java.lang.String  物料代码
	 */
	@Column(name ="MTL_CODE",nullable=true,length=120)
	public java.lang.String getMtlCode(){
		return this.mtlCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料代码
	 */
	public void setMtlCode(java.lang.String mtlCode){
		this.mtlCode = mtlCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料名称
	 */
	@Column(name ="MTL_NAME",nullable=true,length=120)
	public java.lang.String getMtlName(){
		return this.mtlName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料名称
	 */
	public void setMtlName(java.lang.String mtlName){
		this.mtlName = mtlName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="MTL_SIZE",nullable=true,length=120)
	public java.lang.String getMtlSize(){
		return this.mtlSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setMtlSize(java.lang.String mtlSize){
		this.mtlSize = mtlSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批次
	 */
	@Column(name ="MTL_BATCH",nullable=true,length=120)
	public java.lang.String getMtlBatch(){
		return this.mtlBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批次
	 */
	public void setMtlBatch(java.lang.String mtlBatch){
		this.mtlBatch = mtlBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存数量
	 */
	@Column(name ="STOCK_NUM",nullable=true,length=120)
	public java.lang.String getStockNum(){
		return this.stockNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存数量
	 */
	public void setStockNum(java.lang.String stockNum){
		this.stockNum = stockNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点数量
	 */
	@Column(name ="CHECK_NUM",nullable=true,length=120)
	public java.lang.String getCheckNum(){
		return this.checkNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点数量
	 */
	public void setCheckNum(java.lang.String checkNum){
		this.checkNum = checkNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盈亏数量
	 */
	@Column(name ="PROFIT_LOSS_NUM",nullable=true,length=120)
	public java.lang.String getProfitLossNum(){
		return this.profitLossNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盈亏数量
	 */
	public void setProfitLossNum(java.lang.String profitLossNum){
		this.profitLossNum = profitLossNum;
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
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="REPOSITORY_CODE",nullable=true,length=120)
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库位
	 */
	@Column(name ="STORAGE_CODE",nullable=true,length=120)
	public java.lang.String getStorageCode(){
		return this.storageCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库位
	 */
	public void setStorageCode(java.lang.String storageCode){
		this.storageCode = storageCode;
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
