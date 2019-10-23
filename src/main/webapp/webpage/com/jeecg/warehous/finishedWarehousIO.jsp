<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>成品出入库</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="finishedWarehousIOController.do?save">
			<input id="id" name="id" type="hidden" value="${finishedWarehousIOPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							成品编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedSerino" name="finishedSerino" ignore="ignore"  value="${finishedWarehousIOPage.finishedSerino}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							成品代码:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="finishedCode" defaultVal="${finishedWarehousIOPage.finishedCode}" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect>
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
						<t:dictSelect field="finishedName" defaultVal="${finishedWarehousIOPage.finishedName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							成品批次:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedBatch" name="finishedBatch" ignore="ignore"  value="${finishedWarehousIOPage.finishedBatch}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格型号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedSize" name="finishedSize" ignore="ignore"  value="${finishedWarehousIOPage.finishedSize}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedNumber" name="finishedNumber" ignore="ignore"  value="${finishedWarehousIOPage.finishedNumber}" />
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
						<t:dictSelect field="unit" defaultVal="${finishedWarehousIOPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							库存状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="ioType" defaultVal="${finishedWarehousIOPage.ioType}" typeGroupCode="io_type" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入库人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehousingPersonCode" defaultVal="${finishedWarehousIOPage.warehousingPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							入库时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="warehousingDate" name="warehousingDate" ignore="ignore"    value="<fmt:formatDate value='${finishedWarehousIOPage.warehousingDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehousPosition" defaultVal="${finishedWarehousIOPage.warehousPosition}" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							库位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehousSpace" defaultVal="${finishedWarehousIOPage.warehousSpace}" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出库人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehouseOutPersonCode" defaultVal="${finishedWarehousIOPage.warehouseOutPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							出库时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="warehouseOutDate" name="warehouseOutDate" ignore="ignore"    value="<fmt:formatDate value='${finishedWarehousIOPage.warehouseOutDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="customerCode" dictTable="t_customer_list" dictField="customer_code" dictText="customer_name" defaultVal="${finishedWarehousIOPage.customerCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							交货日期:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="deliveryDate" name="deliveryDate" ignore="ignore"    value="<fmt:formatDate value='${finishedWarehousIOPage.deliveryDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							交货地点:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deliveryPoint" name="deliveryPoint" ignore="ignore"  value="${finishedWarehousIOPage.deliveryPoint}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							货币类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="currencyCode" typeGroupCode="currency" defaultVal="${finishedWarehousIOPage.currencyCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							运输单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="trackingNumber" name="trackingNumber" ignore="ignore"  value="${finishedWarehousIOPage.trackingNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							销售人员:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="salesman" defaultVal="${finishedWarehousIOPage.salesman}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发货通知单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deliveryAdviceOrderNumber" name="deliveryAdviceOrderNumber" ignore="ignore"  value="${finishedWarehousIOPage.deliveryAdviceOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							销售出库单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="salesDeliveryOrderNumber" name="salesDeliveryOrderNumber" ignore="ignore"  value="${finishedWarehousIOPage.salesDeliveryOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产订单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productionOrderNumber" name="productionOrderNumber" ignore="ignore"  value="${finishedWarehousIOPage.productionOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							生产领料单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="takeMaterilNumber" name="takeMaterilNumber" ignore="ignore"  value="${finishedWarehousIOPage.takeMaterilNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产派工号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productionDispatchingNumber" name="productionDispatchingNumber" ignore="ignore"  value="${finishedWarehousIOPage.productionDispatchingNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>