<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>成品首末检</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="finishedInspectController.do?save">
			<input id="id" name="id" type="hidden" value="${finishedInspectMainPage.id }">
	  <ul class="top_title">
		  <li>
			  <a href="javascrip:;" name="">成品编号:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.finishedCode}</a>
			  <input type="hidden" name="finishedCode" value="${finishedInspectMainPage.finishedCode}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">成品名称:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.finishedName}</a>
			  <input type="hidden" name="finishedName" value="${finishedInspectMainPage.finishedName}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">生产派工单号:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.productionOrderNumber}</a>
			  <input type="hidden" name="productionOrderNumber" value="${finishedInspectMainPage.productionOrderNumber}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">总数量:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.count}</a>
			  <input type="hidden" name="count" value="${finishedInspectMainPage.count}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">合格数量:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.qualifiedCount}</a>
			  <input type="hidden" name="qualifiedCount" value="${finishedInspectMainPage.qualifiedCount}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">不合格数量:</a>
			  <a href="javascrip:;">${finishedInspectMainPage.unQualifiedCount}</a>
			  <input type="hidden" name="unQualifiedCount" value="${finishedInspectMainPage.unQualifiedCount}">
		  </li>
		  <li>
			  <a href="javascrip:;" name="">首末检记录表:</a>
			  <t:webUploader name="inspectLogSheet" displayTxt="true" pathValues="${finishedInspectMainPage.inspectLogSheet}"  extensions="xlsx" bizType="semifinished"></t:webUploader>
		  </li>
		  <li>
		  </li>

	  </ul>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="finishedInspectController.do?finishedFirstInspectList&finishedCode=${finishedInspectMainPage.finishedCode}" icon="icon-search" title="成品首检" id="finishedFirstInspect"></t:tab>
				 <t:tab href="finishedInspectController.do?finishedLastInspectList&finishedCode=${finishedInspectMainPage.finishedCode}" icon="icon-search" title="成品末检" id="finishedLastInspect"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_finishedFirstInspect_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="finishedFirstInspectList[#index#].firstInspectNumber" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input class="inspectTimes" name="finishedFirstInspectList[#index#].firstInspectTimes" maxlength="" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedFirstInspectList[#index#].firstInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedFirstInspectList[#index#].firstInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				<td align="left"><input class="inspectDate" name="finishedFirstInspectList[#index#].firstInspectDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
			</tr>
		 </tbody>
		<tbody id="add_finishedLastInspect_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="finishedLastInspectList[#index#].lastInspectNumber" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input class="inspectTimes" name="finishedLastInspectList[#index#].lastInspectTimes" maxlength="" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[#index#].lastInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[#index#].lastInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				<td align="left"><input class="inspectDate" name="finishedLastInspectList[#index#].lastInspectDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
			</tr>
		 </tbody>
		</table>
 </body>