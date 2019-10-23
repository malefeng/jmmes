<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>成品信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="finishedProductController.do?save">
			<input id="id" name="id" type="hidden" value="${finishedProductPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							成品代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedCode" name="finishedCode" ignore="ignore"  value="${finishedProductPage.finishedCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							成品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedName" name="finishedName" ignore="ignore"  value="${finishedProductPage.finishedName}" />
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
						<t:dictSelect id="materialType" field="materialType" typeGroupCode="matType" defaultVal="${finishedProductPage.materialType}" readonly="true"></t:dictSelect>
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
						<input class="inputxt" id="finishedSize" name="finishedSize" ignore="ignore"  value="${finishedProductPage.finishedSize}" />
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
						<input class="inputxt" id="finishedNumber" name="finishedNumber" ignore="ignore"  value="${finishedProductPage.finishedNumber}" />
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
						<t:dictSelect id="finishedUnitCode" field="finishedUnitCode" typeGroupCode="unit" defaultVal="${finishedProductPage.finishedUnitCode}" readonly="true"></t:dictSelect>
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
						<t:webUploader name="ffCheckTemp" pathValues="${finishedProductPage.ffCheckTemp}"  extensions="xlsx" bizType="finished"></t:webUploader>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>