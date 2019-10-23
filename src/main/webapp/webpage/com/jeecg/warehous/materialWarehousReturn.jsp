<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>原料退料列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="materialWarehousReturnController.do?save">
			<input id="id" name="id" type="hidden" value="${materialWarehousReturnPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							原料编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="materialSerino" name="materialSerino" ignore="ignore"  value="${materialWarehousReturnPage.materialSerino}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							原料代码:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="materialCode" defaultVal="${materialWarehousReturnPage.materialCode}" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect>
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
						<t:dictSelect field="materialName" defaultVal="${materialWarehousReturnPage.materialName}" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							原料批次:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="batchNumber" name="batchNumber" ignore="ignore"  value="${materialWarehousReturnPage.batchNumber}" />
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
						<input class="inputxt" id="materialSize" name="materialSize" ignore="ignore"  value="${materialWarehousReturnPage.materialSize}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入库数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="materialNumber" name="materialNumber" ignore="ignore"  value="${materialWarehousReturnPage.materialNumber}" />
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
						<t:dictSelect field="unit" defaultVal="${materialWarehousReturnPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退料人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="returnedPerson" defaultVal="${materialWarehousReturnPage.returnedPerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退料时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="returnedDate" name="returnedDate" ignore="ignore"    value="<fmt:formatDate value='${materialWarehousReturnPage.returnedDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退料原因:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="returnedReason" name="returnedReason" ignore="ignore"  value="${materialWarehousReturnPage.returnedReason}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退料数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="returnedNumber" name="returnedNumber" ignore="ignore"  value="${materialWarehousReturnPage.returnedNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							供应商:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="supplierCode" dictTable="t_supplier_list" dictField="supplier_code" dictText="supplier_name" defaultVal="${materialWarehousReturnPage.supplierCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收料单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="receivingOrderNumber" name="receivingOrderNumber" ignore="ignore"  value="${materialWarehousReturnPage.receivingOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收料单采购订单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="purchaseOrderNumber" name="purchaseOrderNumber" ignore="ignore"  value="${materialWarehousReturnPage.purchaseOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收料单销售订单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="salesOrderNumber" name="salesOrderNumber" ignore="ignore"  value="${materialWarehousReturnPage.salesOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>