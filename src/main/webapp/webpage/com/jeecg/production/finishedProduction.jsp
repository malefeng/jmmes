<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>成品生产列表</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="finishedProductionController.do?save">
			<input id="id" name="id" type="hidden" value="${finishedProductionPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">成品编号:</label></td>
			<td class="value">
				<input nullmsg="请填写成品编号" errormsg="成品编号格式不对" class="inputxt" id="finishedSerino" name="finishedSerino" ignore="ignore"	value="${finishedProductionPage.finishedSerino}" />
				<span class="Validform_checktip"></span>
			</td>
				<td align="right"><label class="Validform_label">成品代码:</label></td>
				<td class="value">
					<t:dictSelect field="finishedCode" defaultVal="${finishedProductionPage.finishedCode}" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
				<tr>
					<td align="right"><label class="Validform_label">成品名称:</label></td>
					<td class="value">
						<t:dictSelect field="finishedName" defaultVal="${finishedProductionPage.finishedName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
			<td align="right"><label class="Validform_label">成品批次:</label></td>
			<td class="value">
				<input nullmsg="请填写成品批次" errormsg="成品批次格式不对" class="inputxt" id="finishedBatch" name="finishedBatch" ignore="ignore"	value="${finishedProductionPage.finishedBatch}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">规格型号:</label></td>
			<td class="value">
				<input nullmsg="请填写规格型号" errormsg="规格型号格式不对" class="inputxt" id="finishedSize" name="finishedSize" ignore="ignore"	value="${finishedProductionPage.finishedSize}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">数量:</label></td>
			<td class="value">
				<input nullmsg="请填写数量" errormsg="数量格式不对" class="inputxt" id="finishedNumber" name="finishedNumber" ignore="ignore"	value="${finishedProductionPage.finishedNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">单位:</label></td>
				<td class="value">
					<t:dictSelect field="unit" defaultVal="${finishedProductionPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
				<td align="right"><label class="Validform_label">生产人:</label></td>
				<td class="value">
					<t:dictSelect field="productionPersonCode" defaultVal="${finishedProductionPage.productionPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">生产时间:</label></td>
			<td class="value">
				<input nullmsg="请填写生产时间" errormsg="生产时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="productionDate" name="productionDate" ignore="ignore"	  value="<fmt:formatDate value='${finishedProductionPage.productionDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">生产派工单号:</label></td>
			<td class="value">
				<input nullmsg="请填写生产订单号" errormsg="生产派工单号格式不对" class="inputxt" id="productionOrderNumber" name="productionOrderNumber" ignore="ignore"	value="${finishedProductionPage.productionOrderNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">生产领料单号:</label></td>
			<td class="value">
				<input nullmsg="请填写生产领料单号" errormsg="生产领料单号格式不对" class="inputxt" id="takeMaterilNumber" name="takeMaterilNumber" ignore="ignore"	value="${finishedProductionPage.takeMaterilNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">是否需要熟成:</label></td>
			<td class="value">
				<select id="needRipening" name="needRipening" value="${finishedProductionPage.needRipening}">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
			</tr>
				<tr id="ripeningHours_line">
					<td align="right"><label class="Validform_label">熟成时长:</label></td>
					<td class="value">
						<input nullmsg="请填写生产领料单号" errormsg="生产领料单号格式不对" class="inputxt" id="ripeningHours" name="ripeningHours" ignore="ignore" value="${finishedProductionPage.ripeningHours}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="finishedProductionController.do?finishedProductionNodeList&finishedSerino=${finishedProductionPage.finishedSerino}" icon="icon-search" title="成品生产物料详情" id="finishedProductionNode"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_finishedProductionNode_table_template">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh"></div></td>
				<td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				<td align="left"><input name="finishedProductionNodeList[#index#].stuffNumber" maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedProductionNodeList[#index#].code" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedProductionNodeList[#index#].name" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedProductionNodeList[#index#].typeCode" typeGroupCode="matType" readonly="true"></t:dictSelect></td>
				<td align="left"><input name="finishedProductionNodeList[#index#].batchNubmer" maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><input name="finishedProductionNodeList[#index#].size" maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><input name="finishedProductionNodeList[#index#].amount" maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedProductionNodeList[#index#].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
			</tr>
		 </tbody>
		</table>
 </body>
<script>
	$("#needRipening").change(function(){
		var val = $("#needRipening option:selected").val();
		if(val=="0"){
			$("#ripeningHours").val("");
			$("#ripeningHours_line").hide();
		}else{
			$("#ripeningHours").removeAttr("disable");
			$("#ripeningHours_line").show();
		}
	})
</script>