package com.jeecg.page.productionrequisition;

import java.util.List;
import java.util.ArrayList;

import com.jeecg.entity.invoices.ProductionRequisitionNodeEntity;

/**   
 * @Title: Entity
 * @Description: 生产领料单
 * @author zhangdaihao
 * @date 2019-10-04 02:10:19
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ProductionRequisitionPage implements java.io.Serializable {
	/**保存-生产领料单物料详情*/
	private List<ProductionRequisitionNodeEntity> productionRequisitionNodeList = new ArrayList<ProductionRequisitionNodeEntity>();
	public List<ProductionRequisitionNodeEntity> getProductionRequisitionNodeList() {
		return productionRequisitionNodeList;
	}
	public void setProductionRequisitionNodeList(List<ProductionRequisitionNodeEntity> productionRequisitionNodeList) {
		this.productionRequisitionNodeList = productionRequisitionNodeList;
	}


	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**读取人*/
	private java.lang.String readPersonCode;
	/**获取时间*/
	private java.util.Date acquireTime;
	/**领料日期*/
	private java.util.Date requisitionDate;
	/**领料车间*/
	private java.lang.String requisitionWorkshopCode;
	/**单据编号*/
	private java.lang.String receiptCode;
	/**有效性*/
	private java.lang.Integer isValid;
	/**领料仓库*/
	private java.lang.String repositoryCode;
	/**仓库管理员*/
	private java.lang.String repositoryManagerCode;
	/**领料人*/
	private java.lang.String acquirePersonCode;
	/**制单人*/
	private java.lang.String creatorCode;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
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
	 *@return: java.lang.String  读取人
	 */
	public java.lang.String getReadPersonCode(){
		return this.readPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  读取人
	 */
	public void setReadPersonCode(java.lang.String readPersonCode){
		this.readPersonCode = readPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  获取时间
	 */
	public java.util.Date getAcquireTime(){
		return this.acquireTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  获取时间
	 */
	public void setAcquireTime(java.util.Date acquireTime){
		this.acquireTime = acquireTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  领料日期
	 */
	public java.util.Date getRequisitionDate(){
		return this.requisitionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  领料日期
	 */
	public void setRequisitionDate(java.util.Date requisitionDate){
		this.requisitionDate = requisitionDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料车间
	 */
	public java.lang.String getRequisitionWorkshopCode(){
		return this.requisitionWorkshopCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料车间
	 */
	public void setRequisitionWorkshopCode(java.lang.String requisitionWorkshopCode){
		this.requisitionWorkshopCode = requisitionWorkshopCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据编号
	 */
	public java.lang.String getReceiptCode(){
		return this.receiptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据编号
	 */
	public void setReceiptCode(java.lang.String receiptCode){
		this.receiptCode = receiptCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效性
	 */
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料仓库
	 */
	public java.lang.String getRepositoryCode(){
		return this.repositoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料仓库
	 */
	public void setRepositoryCode(java.lang.String repositoryCode){
		this.repositoryCode = repositoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库管理员
	 */
	public java.lang.String getRepositoryManagerCode(){
		return this.repositoryManagerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库管理员
	 */
	public void setRepositoryManagerCode(java.lang.String repositoryManagerCode){
		this.repositoryManagerCode = repositoryManagerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领料人
	 */
	public java.lang.String getAcquirePersonCode(){
		return this.acquirePersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领料人
	 */
	public void setAcquirePersonCode(java.lang.String acquirePersonCode){
		this.acquirePersonCode = acquirePersonCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  制单人
	 */
	public java.lang.String getCreatorCode(){
		return this.creatorCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  制单人
	 */
	public void setCreatorCode(java.lang.String creatorCode){
		this.creatorCode = creatorCode;
	}
}
