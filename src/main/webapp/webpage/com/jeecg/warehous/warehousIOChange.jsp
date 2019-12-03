<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>调库调位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="warehousIOChangeController.do?save">
			<input id="id" name="id" type="hidden" value="${warehousIOChangePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							物资类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="materialsType" defaultVal="${warehousIOChangePage.materialsType}" typeGroupCode="mater_type"  hasLabel="false" type="list"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							调配人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="changeMan" defaultVal="${warehousIOChangePage.changeMan}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							调配时间:
						</label>
					</td>
					<td class="value">
						<input nullmsg="请填写计划盘点时间" errormsg="计划盘点时间格式不对" class="Wdate"
							   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" id="changeDate"
							   name="changeDate" ignore="ignore"
							   value="<fmt:formatDate value='${warehousIOChangePage.changeDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物资编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="materialsSerino" name="materialsSerino" ignore="ignore"  value="${warehousIOChangePage.materialsSerino}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							移入仓库:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="repositoryCode" defaultVal="${warehousIOChangePage.repositoryCode}" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							移入库位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="storageCode" defaultVal="${warehousIOChangePage.storageCode}" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<textarea id="remark" name="remark" ignore="ignore"  value="${warehousIOChangePage.remark}"></textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>