<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="equipmentController.do?save">
			<input id="id" name="id" type="hidden" value="${equipmentPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备型号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentNumber" name="equipmentNumber" ignore="ignore"  value="${equipmentPage.equipmentNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							设备名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentName" name="equipmentName" ignore="ignore"  value="${equipmentPage.equipmentName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备型号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentSize" name="equipmentSize" ignore="ignore"  value="${equipmentPage.equipmentSize}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							设备供应商:
						</label>
					</td>
					<td class="value">
						<t:dictSelect id="equipmentSupplier" field="equipmentSupplier" dictTable="t_supplier_list"
									  dictField="supplier_code" dictText="supplier_name"
									  defaultVal="${equipmentPage.equipmentSupplier}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierDetail" name="supplierDetail" ignore="ignore"  value="${equipmentPage.supplierDetail}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							设备位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="supplierSeat" name="supplierSeat" ignore="ignore"  value="${equipmentPage.supplierSeat}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="purchaseDate" name="purchaseDate" ignore="ignore"    value="<fmt:formatDate value='${equipmentPage.purchaseDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							采购人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="purchasePerson" defaultVal="${equipmentPage.purchasePerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>