<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>承运商信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commonCarrierController.do?save">
			<input id="id" name="id" type="hidden" value="${commonCarrierPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transCode" name="transCode" ignore="ignore"  value="${commonCarrierPage.transCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transName" name="transName" ignore="ignore"  value="${commonCarrierPage.transName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商法人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transLegalPerson" name="transLegalPerson" ignore="ignore"  value="${commonCarrierPage.transLegalPerson}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transLinkman" name="transLinkman" ignore="ignore"  value="${commonCarrierPage.transLinkman}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商联系电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transContactNumben" name="transContactNumben" ignore="ignore"  value="${commonCarrierPage.transContactNumben}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							承运商联系地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transContactAddress" name="transContactAddress" ignore="ignore"  value="${commonCarrierPage.transContactAddress}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>