package com.jeecg.entity.production;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 成品检测
 * @author zhangdaihao
 * @date 2019-12-25 23:35:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_inspect_item", schema = "")
@SuppressWarnings("serial")
public class FinishedInspectItemEntity implements java.io.Serializable {
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
	/**成品编号*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**销售订单号-->成品编号*/
	private java.lang.String salesOrderNumber;
	/**生产派工单号*/
	private java.lang.String productionDispatchingNumber;
	/**首末检记录表*/
	private java.lang.String inspectLogSheet;
	/**检验结果*/
	private java.lang.String result;
	/**总数量*/
	private java.lang.Integer count;
	/**合格数量*/
	private java.lang.Integer qualifiedCount;
	/**不合格数量*/
	private java.lang.Integer unqualifiedCount;
	
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
	 *@return: java.lang.String  成品编号
	 */
	@Column(name ="FINISHED_CODE",nullable=true,length=32)
	public java.lang.String getFinishedCode(){
		return this.finishedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品编号
	 */
	public void setFinishedCode(java.lang.String finishedCode){
		this.finishedCode = finishedCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成品名称
	 */
	@Column(name ="FINISHED_NAME",nullable=true,length=32)
	public java.lang.String getFinishedName(){
		return this.finishedName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成品名称
	 */
	public void setFinishedName(java.lang.String finishedName){
		this.finishedName = finishedName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售订单号
	 */
	@Column(name ="SALES_ORDER_NUMBER",nullable=true,length=32)
	public java.lang.String getSalesOrderNumber(){
		return this.salesOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售订单号
	 */
	public void setSalesOrderNumber(java.lang.String salesOrderNumber){
		this.salesOrderNumber = salesOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产派工单号
	 */
	@Column(name ="PRODUCTION_DISPATCHING_NUMBER",nullable=true,length=32)
	public java.lang.String getProductionDispatchingNumber(){
		return this.productionDispatchingNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产派工单号
	 */
	public void setProductionDispatchingNumber(java.lang.String productionDispatchingNumber){
		this.productionDispatchingNumber = productionDispatchingNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  首末检记录表
	 */
	@Column(name ="INSPECT_LOG_SHEET",nullable=true,length=128)
	public java.lang.String getInspectLogSheet(){
		return this.inspectLogSheet;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首末检记录表
	 */
	public void setInspectLogSheet(java.lang.String inspectLogSheet){
		this.inspectLogSheet = inspectLogSheet;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检验结果
	 */
	@Column(name ="RESULT",nullable=true,length=32)
	public java.lang.String getResult(){
		return this.result;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检验结果
	 */
	public void setResult(java.lang.String result){
		this.result = result;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总数量
	 */
	@Column(name ="COUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数量
	 */
	public void setCount(java.lang.Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  合格数量
	 */
	@Column(name ="QUALIFIED_COUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getQualifiedCount(){
		return this.qualifiedCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合格数量
	 */
	public void setQualifiedCount(java.lang.Integer qualifiedCount){
		this.qualifiedCount = qualifiedCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  不合格数量
	 */
	@Column(name ="UNQUALIFIED_COUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUnqualifiedCount(){
		return this.unqualifiedCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  不合格数量
	 */
	public void setUnqualifiedCount(java.lang.Integer unqualifiedCount){
		this.unqualifiedCount = unqualifiedCount;
	}
}
