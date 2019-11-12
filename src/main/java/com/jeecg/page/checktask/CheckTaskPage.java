package com.jeecg.page.checktask;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import com.jeecg.entity.checktask.CheckNodeEntity;

/**   
 * @Title: Entity
 * @Description: 盘点任务
 * @author zhangdaihao
 * @date 2019-11-12 23:12:33
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class CheckTaskPage implements java.io.Serializable {
	/**保存-盘点明细表*/
	private List<CheckNodeEntity> checkNodeList = new ArrayList<CheckNodeEntity>();
	public List<CheckNodeEntity> getCheckNodeList() {
		return checkNodeList;
	}
	public void setCheckNodeList(List<CheckNodeEntity> checkNodeList) {
		this.checkNodeList = checkNodeList;
	}


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
	 *@return: java.lang.String  更新人名称
	 */
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
	 *@return: java.lang.String  所属部门
	 */
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
	public java.util.Date getCheckTimePlan(){
		return this.checkTimePlan;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划盘点时间
	 */
	public void setCheckTimePlan(java.util.Date checkTimePlan){
		this.checkTimePlan = checkTimePlan;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际盘点时间
	 */
	public java.util.Date getCheckTimeReal(){
		return this.checkTimeReal;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际盘点时间
	 */
	public void setCheckTimeReal(java.util.Date checkTimeReal){
		this.checkTimeReal = checkTimeReal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划盘点人
	 */
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
