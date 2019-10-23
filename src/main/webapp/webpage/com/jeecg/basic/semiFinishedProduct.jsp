<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>半成品信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="semiFinishedProductController.do?save">
			<input id="id" name="id" type="hidden" value="${semiFinishedProductPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							半成品代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="semiFinishedCode" name="semiFinishedCode"   value="${semiFinishedProductPage.semiFinishedCode}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							半成品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="semiFinishedName" name="semiFinishedName"   value="${semiFinishedProductPage.semiFinishedName}" datatype="*" />
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
						<t:dictSelect id="materialType" field="materialType" typeGroupCode="matType" defaultVal="${semiFinishedProductPage.materialType}" readonly="true"></t:dictSelect>
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
						<input class="inputxt" id="semiFinishedSize" name="semiFinishedSize" ignore="ignore"  value="${semiFinishedProductPage.semiFinishedSize}" />
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
						<input class="inputxt" id="semiFinishedNumber" name="semiFinishedNumber" ignore="ignore"  value="${semiFinishedProductPage.semiFinishedNumber}" datatype="n" />
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
						<t:dictSelect id="semiFinishedUnitCode" field="semiFinishedUnitCode" typeGroupCode="unit" defaultVal="${semiFinishedProductPage.semiFinishedUnitCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							首末检模板:
						</label>
					</td>
					<td class="value">
						<t:webUploader name="ffCheckTemp" pathValues="${semiFinishedProductPage.ffCheckTemp}"  extensions="xlsx" bizType="semifinished"></t:webUploader>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>