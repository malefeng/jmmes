package com.jeecg.entity.production;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 成品首检
 * @author zhangdaihao
 * @date 2019-10-11 05:01:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_finished_first_inspect_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FinishedFirstInspectEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**首末检外键*/
	private java.lang.String finishedCode;
	/**首检次数*/
	private java.lang.Integer firstInspectTimes;
	/**首检状态*/
	private java.lang.Integer firstInspectState;
	/**首检结果*/
	private java.lang.Integer firstInspectResult;
	/**首检时间*/
	private java.util.Date firstInspectDate;
	/**首检编号*/
	private String firstInspectNumber;
	
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
	 *@return: java.lang.Integer  首检次数
	 */
	@Column(name ="FIRST_INSPECT_TIMES",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFirstInspectTimes(){
		return this.firstInspectTimes;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  首检次数
	 */
	public void setFirstInspectTimes(java.lang.Integer firstInspectTimes){
		this.firstInspectTimes = firstInspectTimes;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  首检状态
	 */
	@Column(name ="FIRST_INSPECT_STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFirstInspectState(){
		return this.firstInspectState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  首检状态
	 */
	public void setFirstInspectState(java.lang.Integer firstInspectState){
		this.firstInspectState = firstInspectState;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  首检结果
	 */
	@Column(name ="FIRST_INSPECT_RESULT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFirstInspectResult(){
		return this.firstInspectResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  首检结果
	 */
	public void setFirstInspectResult(java.lang.Integer firstInspectResult){
		this.firstInspectResult = firstInspectResult;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  首检时间
	 */
	@Column(name ="FIRST_INSPECT_DATE",nullable=true)
	public java.util.Date getFirstInspectDate(){
		return this.firstInspectDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  首检时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setFirstInspectDate(java.util.Date firstInspectDate){
		this.firstInspectDate = firstInspectDate;
	}


	@Column(name ="FIRST_INSPECT_NUMBER",nullable=true)
	public String getFirstInspectNumber() {
		return firstInspectNumber;
	}

	public void setFirstInspectNumber(String firstInspectNumber) {
		this.firstInspectNumber = firstInspectNumber;
	}
}
