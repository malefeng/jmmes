<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备维护</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="equipmentMaintenanceController.do?save">
			<input id="id" name="id" type="hidden" value="${equipmentMaintenancePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							维护批次号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="maintenanceBatch" name="maintenanceBatch" ignore="ignore"  value="${equipmentMaintenancePage.maintenanceBatch}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							维护类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="maintenanceType" defaultVal="${equipmentMaintenancePage.maintenanceType}" typeGroupCode="maintType"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维护方式:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="maintenanceWay" defaultVal="${equipmentMaintenancePage.maintenanceWay}" typeGroupCode="maintWay"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							设备编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentNumber" name="equipmentNumber" ignore="ignore"  value="${equipmentMaintenancePage.equipmentNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentName" name="equipmentName" ignore="ignore"  value="${equipmentMaintenancePage.equipmentName}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							设备型号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentSize" name="equipmentSize" ignore="ignore"  value="${equipmentMaintenancePage.equipmentSize}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="equipmentSeat" name="equipmentSeat" ignore="ignore"  value="${equipmentMaintenancePage.equipmentSeat}" />
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
									  defaultVal="${equipmentMaintenancePage.equipmentSupplier}" readonly="true"></t:dictSelect>
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
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="purchaseDate" name="purchaseDate" ignore="ignore"    value="<fmt:formatDate value='${equipmentMaintenancePage.purchaseDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							采购人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="purchasePerson" defaultVal="${equipmentMaintenancePage.purchasePerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维护要求:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="maintenanceRequest" name="maintenanceRequest" ignore="ignore"  value="${equipmentMaintenancePage.maintenanceRequest}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							维护状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="maintenanceState" defaultVal="${equipmentMaintenancePage.maintenanceState}" typeGroupCode="maintState"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维护结果:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="maintenanceResult" defaultVal="${equipmentMaintenancePage.maintenanceResult}" typeGroupCode="maintRes"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							维护结果描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="remark" name="remark" ignore="ignore"  value="${equipmentMaintenancePage.remark}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计划维护时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="planMaintenanceData" name="planMaintenanceData" ignore="ignore"    value="<fmt:formatDate value='${equipmentMaintenancePage.planMaintenanceData}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							计划维护人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="planMaintenancePerson" defaultVal="${equipmentMaintenancePage.planMaintenancePerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实际维护时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="actualMaintenanceData" name="actualMaintenanceData" ignore="ignore"    value="<fmt:formatDate value='${equipmentMaintenancePage.actualMaintenanceData}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							实际维护人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="actualMaintenancePerson" defaultVal="${equipmentMaintenancePage.actualMaintenancePerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>