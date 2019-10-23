<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>库位信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="storageController.do?save">
			<input id="id" name="id" type="hidden" value="${storagePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							库位代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="storageCode" name="storageCode" ignore="ignore"  value="${storagePage.storageCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							库位名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="storageName" name="storageName" ignore="ignore"  value="${storagePage.storageName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							归属仓库:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="repositoryCode" field="repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" defaultVal="${storagePage.repositoryCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>