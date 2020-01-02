package com.jeecg.entity.equipment;

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
 * @Description: 设备维护
 * @author zhangdaihao
 * @date 2020-01-02 16:11:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "T_EQUIPMENT_MAINTENANCE", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class EquipmentMaintenanceEntity implements java.io.Serializable {
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
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**维护批次号*/
	private java.lang.String maintenanceBatch;
	/**维护类型*/
	private java.lang.String maintenanceType;
	/**维护方式*/
	private java.lang.String maintenanceWay;
	/**设备编号*/
	private java.lang.String equipmentNumber;
	/**设备名称*/
	private java.lang.String equipmentName;
	/**设备型号*/
	private java.lang.String equipmentSize;
	/**设备位置*/
	private java.lang.String equipmentSeat;
	/**设备供应商*/
	private java.lang.String equipmentSupplier;
	/**采购时间*/
	private java.util.Date purchaseDate;
	/**采购人*/
	private java.lang.String purchasePerson;
	/**维护要求*/
	private java.lang.String maintenanceRequest;
	/**维护状态*/
	private java.lang.String maintenanceState;
	/**维护结果*/
	private java.lang.String maintenanceResult;
	/**维护结果描述*/
	private java.lang.String remark;
	/**计划维护时间*/
	private java.util.Date planMaintenanceData;
	/**计划维护人*/
	private java.lang.String planMaintenancePerson;
	/**实际维护时间*/
	private java.util.Date actualMaintenanceData;
	/**实际维护人*/
	private java.lang.String actualMaintenancePerson;
	
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
	 *@return: java.lang.String  维护批次号
	 */
	@Column(name ="MAINTENANCE_BATCH",nullable=true,length=32)
	public java.lang.String getMaintenanceBatch(){
		return this.maintenanceBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护批次号
	 */
	public void setMaintenanceBatch(java.lang.String maintenanceBatch){
		this.maintenanceBatch = maintenanceBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护类型
	 */
	@Column(name ="MAINTENANCE_TYPE",nullable=true,length=32)
	public java.lang.String getMaintenanceType(){
		return this.maintenanceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护类型
	 */
	public void setMaintenanceType(java.lang.String maintenanceType){
		this.maintenanceType = maintenanceType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护方式
	 */
	@Column(name ="MAINTENANCE_WAY",nullable=true,length=32)
	public java.lang.String getMaintenanceWay(){
		return this.maintenanceWay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护方式
	 */
	public void setMaintenanceWay(java.lang.String maintenanceWay){
		this.maintenanceWay = maintenanceWay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备编号
	 */
	@Column(name ="EQUIPMENT_NUMBER",nullable=true,length=32)
	public java.lang.String getEquipmentNumber(){
		return this.equipmentNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备编号
	 */
	public void setEquipmentNumber(java.lang.String equipmentNumber){
		this.equipmentNumber = equipmentNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备名称
	 */
	@Column(name ="EQUIPMENT_NAME",nullable=true,length=32)
	public java.lang.String getEquipmentName(){
		return this.equipmentName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名称
	 */
	public void setEquipmentName(java.lang.String equipmentName){
		this.equipmentName = equipmentName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备型号
	 */
	@Column(name ="EQUIPMENT_SIZE",nullable=true,length=32)
	public java.lang.String getEquipmentSize(){
		return this.equipmentSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备型号
	 */
	public void setEquipmentSize(java.lang.String equipmentSize){
		this.equipmentSize = equipmentSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备位置
	 */
	@Column(name ="EQUIPMENT_SEAT",nullable=true,length=32)
	public java.lang.String getEquipmentSeat(){
		return this.equipmentSeat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备位置
	 */
	public void setEquipmentSeat(java.lang.String equipmentSeat){
		this.equipmentSeat = equipmentSeat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备供应商
	 */
	@Column(name ="EQUIPMENT_SUPPLIER",nullable=true,length=32)
	public java.lang.String getEquipmentSupplier(){
		return this.equipmentSupplier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备供应商
	 */
	public void setEquipmentSupplier(java.lang.String equipmentSupplier){
		this.equipmentSupplier = equipmentSupplier;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  采购时间
	 */
	@Column(name ="PURCHASE_DATE",nullable=true)
	public java.util.Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  采购时间
	 */
	public void setPurchaseDate(java.util.Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购人
	 */
	@Column(name ="PURCHASE_PERSON",nullable=true,length=32)
	public java.lang.String getPurchasePerson(){
		return this.purchasePerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购人
	 */
	public void setPurchasePerson(java.lang.String purchasePerson){
		this.purchasePerson = purchasePerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护要求
	 */
	@Column(name ="MAINTENANCE_REQUEST",nullable=true,length=32)
	public java.lang.String getMaintenanceRequest(){
		return this.maintenanceRequest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护要求
	 */
	public void setMaintenanceRequest(java.lang.String maintenanceRequest){
		this.maintenanceRequest = maintenanceRequest;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护状态
	 */
	@Column(name ="MAINTENANCE_STATE",nullable=true,length=32)
	public java.lang.String getMaintenanceState(){
		return this.maintenanceState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护状态
	 */
	public void setMaintenanceState(java.lang.String maintenanceState){
		this.maintenanceState = maintenanceState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护结果
	 */
	@Column(name ="MAINTENANCE_RESULT",nullable=true,length=32)
	public java.lang.String getMaintenanceResult(){
		return this.maintenanceResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护结果
	 */
	public void setMaintenanceResult(java.lang.String maintenanceResult){
		this.maintenanceResult = maintenanceResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维护结果描述
	 */
	@Column(name ="REMARK",nullable=true,length=256)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维护结果描述
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划维护时间
	 */
	@Column(name ="PLAN_MAINTENANCE_DATA",nullable=true)
	public java.util.Date getPlanMaintenanceData(){
		return this.planMaintenanceData;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划维护时间
	 */
	public void setPlanMaintenanceData(java.util.Date planMaintenanceData){
		this.planMaintenanceData = planMaintenanceData;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划维护人
	 */
	@Column(name ="PLAN_MAINTENANCE_PERSON",nullable=true,length=32)
	public java.lang.String getPlanMaintenancePerson(){
		return this.planMaintenancePerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划维护人
	 */
	public void setPlanMaintenancePerson(java.lang.String planMaintenancePerson){
		this.planMaintenancePerson = planMaintenancePerson;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际维护时间
	 */
	@Column(name ="ACTUAL_MAINTENANCE_DATA",nullable=true)
	public java.util.Date getActualMaintenanceData(){
		return this.actualMaintenanceData;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际维护时间
	 */
	public void setActualMaintenanceData(java.util.Date actualMaintenanceData){
		this.actualMaintenanceData = actualMaintenanceData;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际维护人
	 */
	@Column(name ="ACTUAL_MAINTENANCE_PERSON",nullable=true,length=32)
	public java.lang.String getActualMaintenancePerson(){
		return this.actualMaintenancePerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际维护人
	 */
	public void setActualMaintenancePerson(java.lang.String actualMaintenancePerson){
		this.actualMaintenancePerson = actualMaintenancePerson;
	}
}
