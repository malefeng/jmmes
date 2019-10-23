<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>仓库信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="repositoryController.do?save">
			<input id="id" name="id" type="hidden" value="${repositoryPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="repositoryCode" name="repositoryCode" ignore="ignore"  value="${repositoryPage.repositoryCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="repositoryName" name="repositoryName" ignore="ignore"  value="${repositoryPage.repositoryName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库容量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="repositoryCapacity" name="repositoryCapacity" ignore="ignore"  value="${repositoryPage.repositoryCapacity}" datatype="d" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库单位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="repositoryUnitCode" field="repositoryUnitCode" typeGroupCode="housUnit" defaultVal="${repositoryPage.repositoryUnitCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="repositoryType" field="repositoryType" typeGroupCode="housType" defaultVal="${repositoryPage.repositoryType}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="repositoryPosition" name="repositoryPosition" ignore="ignore"  value="${repositoryPage.repositoryPosition}" />
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
						<input class="inputxt" id="remark" name="remark" ignore="ignore"  value="${repositoryPage.remark}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>