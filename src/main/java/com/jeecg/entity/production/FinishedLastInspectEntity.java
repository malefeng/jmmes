package com.jeecg.entity.production;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 成品末检
 * @author zhangdaihao
 * @date 2019-10-11 05:01:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_last_inspect_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FinishedLastInspectEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**首末检外键*/
	private java.lang.String finishedCode;
	/**末检次数*/
	private java.lang.Integer lastInspectTimes;
	/**末检状态*/
	private java.lang.Integer lastInspectState;
	/**末检结果*/
	private java.lang.Integer lastInspectResult;
	/**末检时间*/
	private java.util.Date lastInspectDate;
	/**末检编号*/
	private String lastInspectNumber;
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
	 *@return: java.lang.String  首末检外键
	 */
	@Column(name ="FINISHED_CODE",nullable=true,length=32)
	public java.lang.String getFinishedCode(){
		return this.finishedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首末检外键
	 */
	public void setFinishedCode(java.lang.String finishedCode){
		this.finishedCode = finishedCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  末检次数
	 */
	@Column(name ="LAST_INSPECT_TIMES",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLastInspectTimes(){
		return this.lastInspectTimes;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  末检次数
	 */
	public void setLastInspectTimes(java.lang.Integer lastInspectTimes){
		this.lastInspectTimes = lastInspectTimes;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  末检状态
	 */
	@Column(name ="LAST_INSPECT_STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLastInspectState(){
		return this.lastInspectState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  末检状态
	 */
	public void setLastInspectState(java.lang.Integer lastInspectState){
		this.lastInspectState = lastInspectState;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  末检结果
	 */
	@Column(name ="LAST_INSPECT_RESULT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLastInspectResult(){
		return this.lastInspectResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  末检结果
	 */
	public void setLastInspectResult(java.lang.Integer lastInspectResult){
		this.lastInspectResult = lastInspectResult;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  末检时间
	 */
	@Column(name ="LAST_INSPECT_DATE",nullable=true)
	public java.util.Date getLastInspectDate(){
		return this.lastInspectDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  末检时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setLastInspectDate(java.util.Date lastInspectDate){
		this.lastInspectDate = lastInspectDate;
	}

	@Column(name ="LAST_INSPECT_NUMBER",nullable=true)
	public String getLastInspectNumber() {
		return lastInspectNumber;
	}

	public void setLastInspectNumber(String lastInspectNumber) {
		this.lastInspectNumber = lastInspectNumber;
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
