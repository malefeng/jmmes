<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>生产看板</title>
	<script src="plug-in/showTime/showTime.js"></script>
	<script src="plug-in/common/js/common.js"></script>
  <t:base type="jquery,bootstrap"></t:base>
	 <style>
		 body {
			 background: #000;
			 color: #fff;
		 }
		 .content{
			 width: 100%;
			 box-sizing: border-box;
			 padding: 10px;
			 height: 100%;
		 }
		 .height-100 {
			 /*height: 100%;*/
		 }

		 .table-bordered td {
			 width: 25%;
			 height: 28px;
			 line-height: 28px;
		 }
		 .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
			 padding: 0px 8px;
		  	line-height: 28px;
			 vertical-align: top;
			 border-top: 1px solid #ddd;
		 }
		 .td_text_center{
			 text-align: center;
		 }
		 .td_text_right{
			 text-align: right;
		 }
		 .flex {
			 display: flex;
			 flex-direction: column;
		 }

		 .table-bordered {
			 border: 1px solid #4472C4;
		 }

		 .table-bordered>tbody>tr>td,
		 .table-bordered>tbody>tr>th,
		 .table-bordered>tfoot>tr>td,
		 .table-bordered>tfoot>tr>th,
		 .table-bordered>thead>tr>td,
		 .table-bordered>thead>tr>th {
			 border: 1px solid #4472C4;
		 }
		 .sum-progress{
			 flex: 1;
			 border: 1px solid #4472C4;
			 width: 100%;
			 /*height: 340px;*/
			 padding: 10px;
			 display: flex;
			 flex-direction: column;
			 justify-content: center;
			 align-items: center;
		 }
		 .progress-item {
			 flex:1;
			 width: 100%;
			 align-self: center;
			 margin-top: 8px;
		 }
		 .progress-label {
			 width: 10%;
			 margin-right:10px;
			 float: left
		 }
		 .title-wrapper{
			 width: 100%;
			 /*margin: 5px auto;*/
		 }
		 .title{
			 text-align: center;
			 width:100%;
			 font-size: 28px;
		 }
		 .sub-title{
			 font-size: 18px;
			 box-sizing: border-box;
			 padding: 0 20px;
			 display:flex;
			 width:100%;
			 justify-content: space-between;

		 }
		 .table{
			 margin-bottom: 10px;
		 }
		 .table-wrapper{
			 display: flex;
			 align-items:stretch;
		 }
		 .width-48{
			 width:48%;
		 }
		 .margin-lr{
			 margin:0 10px 10px 20px;
		 }
		 .margin-b{
			 margin-bottom: 20px
		 }
		.content{
			dislay: contents
		}
		 .td_title{
			 font-weight: bold;
			 text-align: center;
			 font-size: 16px;
		 }
	 </style>
 </head>
 <body style="overflow-y: auto" scroll="no">
 <div class="content">
	 <div class="title-wrapper" ondblclick="showOrNone()" >
		 <div class="title">生产看板</div>
		 <div class="sub-title">
			 <div>MES管理系统</div>
			 <div>今日生产</div>
		 </div>
	 </div>
	 <div class="table-wrapper height-100 background">
		 <div class="table-responsive width-48 height-100 margin-lr">
			 <table id="tab_1" class="table table-bordered">
				 <tbody>
				 <tr>
					 <td class="td_title" colspan="4">第1生产车间</td>
				 </tr>
				 <tr>
					 <td class="td_text_right">成品名称</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">计划生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">规格型号</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">当前生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">计划完成日期</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">生产单位</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">生产派工单号</td>
					 <td class="td_text_center" colspan="3"></td>
				 </tr>
				 </tbody>
			 </table>
			 <table id="tab_2" class="table table-bordered">
				 <tbody>
				 <tr>
					 <td class="td_title" colspan="4">第2生产车间</td>
				 </tr>
				 <tr>
					 <td class="td_text_right">成品名称</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">计划生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">规格型号</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">当前生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">计划完成日期</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">生产单位</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">生产派工单号</td>
					 <td class="td_text_center"  colspan="3"></td>
				 </tr>
				 </tbody>
			 </table>
			 <table id="tab_3" class="table table-bordered">
				 <tbody>
				 <tr>
					 <td class="td_title" colspan="4">第3生产车间</td>
				 </tr>
				 <tr>
					 <td class="td_text_right">成品名称</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">计划生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">规格型号</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">当前生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">计划完成日期</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">生产单位</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">生产派工单号</td>
					 <td  class="td_text_center" colspan="3"></td>
				 </tr>
				 </tbody>
			 </table>
		 </div>
		 <div class="table-responsive width-48  height-100 flex  margin-b">
			 <table id="tab_4" class="table table-bordered">
				 <tbody>
				 <tr>
					 <td class="td_title" colspan="4">第4生产车间</td>
				 </tr>
				 <tr>
					 <td class="td_text_right">成品名称</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">计划生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">规格型号</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">当前生产数量</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">计划完成日期</td>
					 <td class="td_text_center"></td>
					 <td class="td_text_right">生产单位</td>
					 <td class="td_text_center"></td>
				 </tr>
				 <tr>
					 <td class="td_text_right">生产派工单号</td>
					 <td  class="td_text_center" colspan="3"></td>
				 </tr>
				 </tbody>
			 </table>
			 <div class="sum-progress">
				 <div class="progress-item">
					 <div class="progress-label">车间1</div>
					 <div class="progress">
						 <div id="rate_1" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
							  aria-valuemax="100" style="width: 0;text-align: right">
							 70%
							 <span class="sr-only">60% Complete</span>
						 </div>
					 </div>
				 </div>
				 <div class="progress-item">
					 <div class="progress-label">车间2</div>
					 <div class="progress">
						 <div id="rate_2" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
							  aria-valuemax="100" style="width: 0;text-align: right">
							 <span class="sr-only">60% Complete</span>
						 </div>
					 </div>
				 </div>
				 <div class="progress-item">
					 <div class="progress-label">车间3</div>
					 <div class="progress">
						 <div id="rate_3" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
							  aria-valuemax="100" style="width: 0;text-align: right">
							 <span class="sr-only">60% Complete</span>
						 </div>
					 </div>
				 </div>
				 <div class="progress-item">
					 <div class="progress-label">车间4</div>
					 <div class="progress">
						 <div id="rate_4" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
							  aria-valuemax="100" style="width: 0;text-align: right">
							 <span class="sr-only">60% Complete</span>
						 </div>
					 </div>
				 </div>
				 <div class="date ">
					 <div style="font-size: 20px">
						 <span>当前时间</span>
						 <span id="timeDiv">0000-00-00 00:00:00</span>
					 </div>
				 </div>
			 </div>
		 </div>
	 </div>
 </div>
 <script>
	 var unit = ${unitDic};
	 $(function(){
		 //显示时间
		 showTime("timeDiv");
		 //初次加载
		 show();
		 //刷新数据
		 flash();
	 })
	 //定时5秒刷新页面数据
	 var timeId;
	 function flash(){
		 clearInterval(timeId)
		 timeId = setInterval(function () {
			 show();
		 }, 5000)
	 }

	 function show(){
		 loadData(1);
		 loadData(2);
		 loadData(3);
		 loadData(4);
	 }

	 function loadData(workshap){
	 	var trs = $("#tab_"+workshap+" tr");
	 	$.getJSON("productionParehousIOLookController.do?listData&workshop="+workshap,function(data){
	 		if(!!data){
				$(trs[1]).children('td').eq(1).text(data['productName']);
				$(trs[1]).children('td').eq(3).text(data['planProdctNumber']);
				$(trs[2]).children('td').eq(1).text(data['size']);
				$(trs[2]).children('td').eq(3).text(data['productedNumber']||0);
				$(trs[3]).children('td').eq(1).text(data['planFinishDate']);
				$(trs[3]).children('td').eq(3).text(unit[data['unit']]);
				$(trs[4]).children('td').eq(1).text(data['productionDispatchingNumber']);
				var rate = Math.round(Number(data['productedNumber']||0)*100 / Number(data['planProdctNumber']));
				$("#rate_"+workshap).css("width",(rate>=100?100:rate)+"%")
				$("#rate_"+workshap).text(rate+"%")
			}
		})
	 }
 </script>
 </body>