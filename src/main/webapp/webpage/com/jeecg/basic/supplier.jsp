<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>供应商信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="supplierController.do?save">
			<input id="id" name="id" type="hidden" value="${supplierPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierCode" name="supplierCode"   value="${supplierPage.supplierCode}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierName" name="supplierName"   value="${supplierPage.supplierName}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商属性:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierAttr" name="supplierAttr" ignore="ignore"  value="${supplierPage.supplierAttr}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商法人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierLegalPerson" name="supplierLegalPerson" ignore="ignore"  value="${supplierPage.supplierLegalPerson}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierLinkman" name="supplierLinkman" ignore="ignore"  value="${supplierPage.supplierLinkman}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商联系电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierContactNumber" name="supplierContactNumber" ignore="ignore"  value="${supplierPage.supplierContactNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商联系地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierContactAddress" name="supplierContactAddress" ignore="ignore"  value="${supplierPage.supplierContactAddress}" />
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
						<input class="inputxt" id="remark" name="remark" ignore="ignore"  value="${supplierPage.remark}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>