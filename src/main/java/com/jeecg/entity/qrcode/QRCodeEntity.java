package com.jeecg.entity.qrcode;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 二维码打印信息
 * @author zhangdaihao
 * @date 2019-10-14 23:44:03
 * @version V1.0
 *
 */
@Entity
@Table(name = "t_qr_code", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class QRCodeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**二维码类型*/
	private java.lang.String qrCodeType;
	/**二维码编号*/
	private java.lang.String number;
	/**订单编号*/
	private java.lang.String orderNumber;
	/**批次*/
	private java.lang.String batchNo;
	/**物料代码*/
	private java.lang.String code;
	/**物料类型*/
	private java.lang.String materialType;
	/**物料名称*/
	private java.lang.String materialName;
	/**规格型号*/
	private java.lang.String materialSize;
	/**打印人*/
	private java.lang.String createName;
	/**打印时间*/
	private java.util.Date createDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**有效性*/
	private java.lang.Integer isValid;

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
	 *@return: java.lang.String  二维码类型
	 */
	@Column(name ="QR_CODE_TYPE",nullable=true,length=4)
	public java.lang.String getQrCodeType(){
		return this.qrCodeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码类型
	 */
	public void setQrCodeType(java.lang.String qrCodeType){
		this.qrCodeType = qrCodeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码编号
	 */
	@Column(name ="NUMBER",nullable=true,length=32)
	public java.lang.String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码编号
	 */
	public void setNumber(java.lang.String number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单编号
	 */
	@Column(name ="ORDER_NUMBER",nullable=true,length=32)
	public java.lang.String getOrderNumber(){
		return this.orderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单编号
	 */
	public void setOrderNumber(java.lang.String orderNumber){
		this.orderNumber = orderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批次
	 */
	@Column(name ="BATCH_NO",nullable=true,length=32)
	public java.lang.String getBatchNo(){
		return this.batchNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批次
	 */
	public void setBatchNo(java.lang.String batchNo){
		this.batchNo = batchNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料代码
	 */
	@Column(name ="CODE",nullable=true,length=32)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料代码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料类型
	 */
	@Column(name ="MATERIAL_TYPE",nullable=true,length=32)
	public java.lang.String getMaterialType(){
		return this.materialType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料类型
	 */
	public void setMaterialType(java.lang.String materialType){
		this.materialType = materialType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料名称
	 */
	@Column(name ="MATERIAL_NAME",nullable=true,length=32)
	public java.lang.String getMaterialName(){
		return this.materialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料名称
	 */
	public void setMaterialName(java.lang.String materialName){
		this.materialName = materialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="MATERIAL_SIZE",nullable=true,length=32)
	public java.lang.String getMaterialSize(){
		return this.materialSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setMaterialSize(java.lang.String materialSize){
		this.materialSize = materialSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打印人
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打印人
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  打印时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  打印时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效性
	 */
	@Column(name ="IS_VALID",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsValid(){
		return this.isValid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效性
	 */
	public void setIsValid(java.lang.Integer isValid){
		this.isValid = isValid;
	}

	public QRCodeEntity() {
	}

	public QRCodeEntity( String code, String number,String orderNumber,  String materialSize, String materialName, String qrCodeType, String materialType, String batchNo) {
		this.code = code;
		this.number = number;
		this.orderNumber = orderNumber;
		this.materialSize = materialSize;
		this.materialName = materialName;
		this.qrCodeType = qrCodeType;
		this.materialType = materialType;
		this.batchNo = batchNo;
	}

	public QRCodeEntity clone(){
		return new QRCodeEntity(this.code,this.number,this.orderNumber,this.materialSize,this.materialName,this.qrCodeType,this.materialType,this.batchNo);
	}
}
