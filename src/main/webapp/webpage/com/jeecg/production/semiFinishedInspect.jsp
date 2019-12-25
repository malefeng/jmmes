<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>半成品首末检</title>
  <t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
			$(this).find('input[class=\'inspectTimes\']').val(i+1);
			$(this).find('input[class=\'inspectDate\']').val(new Date().format("yyyy-MM-dd"));
		});
	}
 </script>
	 <style>
		 ul.top_title,.top_title li{
			 list-style: none;
			 margin:0;
			 padding:0;
			 box-sizing: border-box ;
		 }
		 ul.top_title{
			 width:100%;
			 height: 120px;
			 padding: 0 10px;
			 position: relative;
			 display: flex;
			 flex-wrap: wrap;
			 align-items: center;
		 }
		 .top_title li{
			 width: 25%;
		 }
		 .top_title li a{
			 text-decoration: none;
			 cursor: default;
		 }
		 .top_title li a:hover, .top_title li a:visited, .top_title li a:link, .top_title li a:active {
			 color:#000;
		 }
		 #download, #upload{
			 display: inline-block;
			 width: 60px;
			 height: 20px;
			 line-height: 20px;
			 border: 1px solid #eee;
			 border-radius: 3px;
			 margin-right: 5px;
			 text-align: center;
			 cursor: pointer;
		 }
		 #download{
			 border-color:#3b09e2;
			 color:#3b09e2
		 }
		 #upload{
			 border-color:#e2092a;
			 color:#e2092a
		 }
	 </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="semiFinishedInspectController.do?save">
			<input id="id" name="id" type="hidden" value="${semiFinishedInspectMainPage.id }">
		  <ul class="top_title">
			  <li>
				  <a href="javascrip:;" name="">半成品编号:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.semiFinishedCode}</a>
				  <input type="hidden" name="semiFinishedCode" value="${semiFinishedInspectMainPage.semiFinishedCode}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">半成品名称:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.semiFinishedName}</a>
				  <input type="hidden" name="semiFinishedName" value="${semiFinishedInspectMainPage.semiFinishedName}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">生产派工单号:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.productionOrderNumber}</a>
				  <input type="hidden" name="productionOrderNumber" value="${semiFinishedInspectMainPage.productionOrderNumber}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">总数量:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.count}</a>
				  <input type="hidden" name="count" value="${semiFinishedInspectMainPage.count}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">合格数量:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.qualifiedCount}</a>
				  <input type="hidden" name="qualifiedCount" value="${semiFinishedInspectMainPage.qualifiedCount}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">不合格数量:</a>
				  <a href="javascrip:;">${semiFinishedInspectMainPage.unQualifiedCount}</a>
				  <input type="hidden" name="unQualifiedCount" value="${semiFinishedInspectMainPage.unQualifiedCount}">
			  </li>
			  <li>
				  <a href="javascrip:;" name="">首末检记录表:</a>
				  <t:webUploader name="inspectLogSheet" displayTxt="true" pathValues="${semiFinishedInspectMainPage.inspectLogSheet}"  extensions="xlsx" bizType="semifinished"></t:webUploader>
			  </li>
			  <li>
			  </li>
		  </ul>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="semiFinishedInspectController.do?semiFinishedFirstInspectList&semiFinishedCode=${semiFinishedInspectMainPage.semiFinishedCode}" icon="icon-search" title="半成品首检" id="semiFinishedFirstInspect"></t:tab>
				 <t:tab href="semiFinishedInspectController.do?semiFinishedLastInspectList&semiFinishedCode=${semiFinishedInspectMainPage.semiFinishedCode}" icon="icon-search" title="半成品末检" id="semiFinishedLastInspect"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_semiFinishedFirstInspect_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="semiFinishedFirstInspectList[#index#].firstInspectNumber" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input class="inspectTimes" name="semiFinishedFirstInspectList[#index#].firstInspectTimes" readonly maxlength="" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="semiFinishedFirstInspectList[#index#].firstInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><input class="inspectDate" name="semiFinishedFirstInspectList[#index#].firstInspectDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="semiFinishedFirstInspectList[#index#].firstInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
			</tr>
		 </tbody>
		<tbody id="add_semiFinishedLastInspect_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="semiFinishedLastInspectList[#index#].lastInspectNumber" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input class="inspectTimes" name="semiFinishedLastInspectList[#index#].lastInspectTimes" readonly maxlength="" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="semiFinishedLastInspectList[#index#].lastInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><input class="inspectDate" name="semiFinishedLastInspectList[#index#].lastInspectDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="semiFinishedLastInspectList[#index#].lastInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
			</tr>
		 </tbody>
		</table>
 </body>