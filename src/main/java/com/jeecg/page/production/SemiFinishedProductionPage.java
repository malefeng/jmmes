package com.jeecg.page.production;

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

import com.jeecg.entity.production.SemiFinishedProductionNodeEntity;

/**   
 * @Title: Entity
 * @Description: 半成品生产列表
 * @author zhangdaihao
 * @date 2019-10-11 05:26:31
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class SemiFinishedProductionPage implements java.io.Serializable {
	/**保存-半成品生产物料详情*/
	private List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList = new ArrayList<SemiFinishedProductionNodeEntity>();
	public List<SemiFinishedProductionNodeEntity> getSemiFinishedProductionNodeList() {
		return semiFinishedProductionNodeList;
	}
	public void setSemiFinishedProductionNodeList(List<SemiFinishedProductionNodeEntity> semiFinishedProductionNodeList) {
		this.semiFinishedProductionNodeList = semiFinishedProductionNodeList;
	}


	/**id*/
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**半成品编号*/
	private java.lang.String semiFinishedSerino;
	/**半成品代码*/
	private java.lang.String semiFinishedCode;
	/**半成品名称*/
	private java.lang.String semiFinishedName;
	/**半成品批次*/
	private java.lang.String batchNumber;
	/**规格型号*/
	private java.lang.String semiFinishedSize;
	/**数量*/
	private java.lang.String semiFinishedNumber;
	/**单位*/
	private java.lang.String unit;
	/**生产人*/
	private java.lang.String productionPersonCode;
	/**生产时间*/
	private java.util.Date productionDate;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**生产领料单号*/
	private java.lang.String takeMaterilNumber;
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
	 *@return: java.lang.String  创建人
	 */
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
	 *@return: java.lang.String  半成品编号
	 */
	public java.lang.String getSemiFinishedSerino(){
		return this.semiFinishedSerino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品编号
	 */
	public void setSemiFinishedSerino(java.lang.String semiFinishedSerino){
		this.semiFinishedSerino = semiFinishedSerino;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  半成品代码
	 */
	public java.lang.String getSemiFinishedCode(){
		return this.semiFinishedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品代码
	 */
	public void setSemiFinishedCode(java.lang.String semiFinishedCode){
		this.semiFinishedCode = semiFinishedCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  半成品名称
	 */
	public java.lang.String getSemiFinishedName(){
		return this.semiFinishedName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品名称
	 */
	public void setSemiFinishedName(java.lang.String semiFinishedName){
		this.semiFinishedName = semiFinishedName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  半成品批次
	 */
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  半成品批次
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	public java.lang.String getSemiFinishedSize(){
		return this.semiFinishedSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSemiFinishedSize(java.lang.String semiFinishedSize){
		this.semiFinishedSize = semiFinishedSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */
	public java.lang.String getSemiFinishedNumber(){
		return this.semiFinishedNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setSemiFinishedNumber(java.lang.String semiFinishedNumber){
		this.semiFinishedNumber = semiFinishedNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
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
	 *@return: java.lang.String  生产人
	 */
	public java.lang.String getProductionPersonCode(){
		return this.productionPersonCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产人
	 */
	public void setProductionPersonCode(java.lang.String productionPersonCode){
		this.productionPersonCode = productionPersonCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生产时间
	 */
	public java.util.Date getProductionDate(){
		return this.productionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生产时间
	 */
	public void setProductionDate(java.util.Date productionDate){
		this.productionDate = productionDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产订单号
	 */
	public java.lang.String getProductionOrderNumber(){
		return this.productionOrderNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产订单号
	 */
	public void setProductionOrderNumber(java.lang.String productionOrderNumber){
		this.productionOrderNumber = productionOrderNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产领料单号
	 */
	public java.lang.String getTakeMaterilNumber(){
		return this.takeMaterilNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产领料单号
	 */
	public void setTakeMaterilNumber(java.lang.String takeMaterilNumber){
		this.takeMaterilNumber = takeMaterilNumber;
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
