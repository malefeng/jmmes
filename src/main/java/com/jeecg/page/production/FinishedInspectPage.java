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

import com.jeecg.entity.production.FinishedFirstInspectEntity;
import com.jeecg.entity.production.FinishedLastInspectEntity;

/**   
 * @Title: Entity
 * @Description: 成品首末检
 * @author zhangdaihao
 * @date 2019-10-11 05:01:18
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class FinishedInspectPage implements java.io.Serializable {
	/**保存-成品首检*/
	private List<FinishedFirstInspectEntity> finishedFirstInspectList = new ArrayList<FinishedFirstInspectEntity>();
	public List<FinishedFirstInspectEntity> getFinishedFirstInspectList() {
		return finishedFirstInspectList;
	}
	public void setFinishedFirstInspectList(List<FinishedFirstInspectEntity> finishedFirstInspectList) {
		this.finishedFirstInspectList = finishedFirstInspectList;
	}
	/**保存-成品末检*/
	private List<FinishedLastInspectEntity> finishedLastInspectList = new ArrayList<FinishedLastInspectEntity>();
	public List<FinishedLastInspectEntity> getFinishedLastInspectList() {
		return finishedLastInspectList;
	}
	public void setFinishedLastInspectList(List<FinishedLastInspectEntity> finishedLastInspectList) {
		this.finishedLastInspectList = finishedLastInspectList;
	}


	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**成品编号*/
	private java.lang.String finishedCode;
	/**成品名称*/
	private java.lang.String finishedName;
	/**销售订单号*/
	private java.lang.String salesOrderNumber;
	/**生产订单号*/
	private java.lang.String productionOrderNumber;
	/**首末检记录表*/
	private java.lang.String inspectLogSheet;
	
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
	 *@return: java.lang.String  成品编号
	 */
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
	 *@return: java.lang.String  首末检记录表
	 */
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
}
