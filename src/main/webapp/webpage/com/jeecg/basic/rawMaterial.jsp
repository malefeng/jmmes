<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>原料信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="rawMaterialController.do?save">
			<input id="id" name="id" type="hidden" value="${rawMaterialPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							原料代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rawMaterialCode" name="rawMaterialCode" ignore="ignore"  value="${rawMaterialPage.rawMaterialCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							原料名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rawMaterialName" name="rawMaterialName" ignore="ignore"  value="${rawMaterialPage.rawMaterialName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物料类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="rawMaterialType" field="rawMaterialType" typeGroupCode="matType" defaultVal="${rawMaterialPage.rawMaterialType}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rawMaterialSize" name="rawMaterialSize" ignore="ignore"  value="${rawMaterialPage.rawMaterialSize}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rawMaterialNumber" name="rawMaterialNumber" ignore="ignore"  value="${rawMaterialPage.rawMaterialNumber}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="rawMaterialUnit" field="rawMaterialUnit" typeGroupCode="unit" defaultVal="${rawMaterialPage.rawMaterialUnit}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>