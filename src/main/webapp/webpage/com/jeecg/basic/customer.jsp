<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="customerController.do?save">
			<input id="id" name="id" type="hidden" value="${customerPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerCode" name="customerCode"   value="${customerPage.customerCode}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerName" name="customerName"   value="${customerPage.customerName}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户法人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerLegalPerson" name="customerLegalPerson" ignore="ignore"  value="${customerPage.customerLegalPerson}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerLinkman" name="customerLinkman" ignore="ignore"  value="${customerPage.customerLinkman}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户联系电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerContactNumber" name="customerContactNumber" ignore="ignore"  value="${customerPage.customerContactNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户联系地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="customerContactAddress" name="customerContactAddress" ignore="ignore"  value="${customerPage.customerContactAddress}" />
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
						<input class="inputxt" id="remark" name="remark" ignore="ignore"  value="${customerPage.remark}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>