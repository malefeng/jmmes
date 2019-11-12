<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>盘点任务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
		});
	}
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="checkTaskController.do?save">
			<input id="id" name="id" type="hidden" value="${checkTaskPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">所属部门:</label></td>
			<td class="value">
				<input nullmsg="请填写所属部门" errormsg="所属部门格式不对" class="inputxt" id="sysOrgCode" name="sysOrgCode" ignore="ignore"	value="${checkTaskPage.sysOrgCode}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">所属公司:</label></td>
			<td class="value">
				<input nullmsg="请填写所属公司" errormsg="所属公司格式不对" class="inputxt" id="sysCompanyCode" name="sysCompanyCode" ignore="ignore"	value="${checkTaskPage.sysCompanyCode}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">流程状态:</label></td>
			<td class="value">
				<input nullmsg="请填写流程状态" errormsg="流程状态格式不对" class="inputxt" id="bpmStatus" name="bpmStatus" ignore="ignore"	value="${checkTaskPage.bpmStatus}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">盘点批次号:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点批次号" errormsg="盘点批次号格式不对" class="inputxt" id="checkBatch" name="checkBatch" ignore="ignore"	value="${checkTaskPage.checkBatch}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">盘点类型:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点类型" errormsg="盘点类型格式不对" class="inputxt" id="checkType" name="checkType" ignore="ignore"	value="${checkTaskPage.checkType}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">盘点仓库代码:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点仓库代码" errormsg="盘点仓库代码格式不对" class="inputxt" id="repositoryCode" name="repositoryCode" ignore="ignore"	value="${checkTaskPage.repositoryCode}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">盘点仓库名称:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点仓库名称" errormsg="盘点仓库名称格式不对" class="inputxt" id="repositoryName" name="repositoryName" ignore="ignore"	value="${checkTaskPage.repositoryName}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">盘点原料代码:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点原料代码" errormsg="盘点原料代码格式不对" class="inputxt" id="rawMaterialCode" name="rawMaterialCode" ignore="ignore"	value="${checkTaskPage.rawMaterialCode}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">盘点原料名称:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点原料名称" errormsg="盘点原料名称格式不对" class="inputxt" id="rawMaterialName" name="rawMaterialName" ignore="ignore"	value="${checkTaskPage.rawMaterialName}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">盘点成品代码:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点成品代码" errormsg="盘点成品代码格式不对" class="inputxt" id="productCode" name="productCode" ignore="ignore"	value="${checkTaskPage.productCode}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">盘点成品名称:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点成品名称" errormsg="盘点成品名称格式不对" class="inputxt" id="productName" name="productName" ignore="ignore"	value="${checkTaskPage.productName}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">盘点状态:</label></td>
			<td class="value">
				<input nullmsg="请填写盘点状态" errormsg="盘点状态格式不对" class="inputxt" id="checkStatus" name="checkStatus" ignore="ignore"	value="${checkTaskPage.checkStatus}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">计划盘点时间:</label></td>
			<td class="value">
				<input nullmsg="请填写计划盘点时间" errormsg="计划盘点时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="checkTimePlan" name="checkTimePlan" ignore="ignore"	  value="<fmt:formatDate value='${checkTaskPage.checkTimePlan}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">实际盘点时间:</label></td>
			<td class="value">
				<input nullmsg="请填写实际盘点时间" errormsg="实际盘点时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="checkTimeReal" name="checkTimeReal" ignore="ignore"	  value="<fmt:formatDate value='${checkTaskPage.checkTimeReal}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">计划盘点人:</label></td>
			<td class="value">
				<input nullmsg="请填写计划盘点人" errormsg="计划盘点人格式不对" class="inputxt" id="checkPersonPlan" name="checkPersonPlan" ignore="ignore"	value="${checkTaskPage.checkPersonPlan}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">实际盘点人:</label></td>
			<td class="value">
				<input nullmsg="请填写实际盘点人" errormsg="实际盘点人格式不对" class="inputxt" id="checkPersonReal" name="checkPersonReal" ignore="ignore"	value="${checkTaskPage.checkPersonReal}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">预留1:</label></td>
			<td class="value">
				<input nullmsg="请填写预留1" errormsg="预留1格式不对" class="inputxt" id="attr1" name="attr1" ignore="ignore"	value="${checkTaskPage.attr1}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">预留2:</label></td>
			<td class="value">
				<input nullmsg="请填写预留2" errormsg="预留2格式不对" class="inputxt" id="attr2" name="attr2" ignore="ignore"	value="${checkTaskPage.attr2}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">预留3:</label></td>
			<td class="value">
				<input nullmsg="请填写预留3" errormsg="预留3格式不对" class="inputxt" id="attr3" name="attr3" ignore="ignore"	value="${checkTaskPage.attr3}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">预留4:</label></td>
			<td class="value">
				<input nullmsg="请填写预留4" errormsg="预留4格式不对" class="inputxt" id="attr4" name="attr4" ignore="ignore"	value="${checkTaskPage.attr4}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">预留5:</label></td>
			<td class="value">
				<input nullmsg="请填写预留5" errormsg="预留5格式不对" class="inputxt" id="attr5" name="attr5" ignore="ignore"	value="${checkTaskPage.attr5}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="checkTaskController.do?checkNodeList&checkBatch=${checkTaskPage.checkBatch}" icon="icon-search" title="盘点明细表" id="checkNode"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_checkNode_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="checkNodeList[#index#].bpmStatus" maxlength="32" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].sysOrgCode" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].sysCompanyCode" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].checkType" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].mtlCode" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].mtlName" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].mtlSize" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].mtlBatch" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].stockNum" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].checkNum" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].profitLossNum" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].unit" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].repositoryCode" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].storageCode" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].attr1" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].attr2" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].attr3" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].attr4" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="checkNodeList[#index#].attr5" maxlength="120" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 </body>