<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>成品退货列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="finishedWarehousReturnController.do?save">
			<input id="id" name="id" type="hidden" value="${finishedWarehousReturnPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							成品编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedSerino" name="finishedSerino" ignore="ignore"  value="${finishedWarehousReturnPage.finishedSerino}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							成品代码:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="finishedCode" defaultVal="${finishedWarehousReturnPage.finishedCode}" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect>
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
						<t:dictSelect field="finishedName" defaultVal="${finishedWarehousReturnPage.finishedName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							成品批次:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedBatch" name="finishedBatch" ignore="ignore"  value="${finishedWarehousReturnPage.finishedBatch}" />
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
						<input class="inputxt" id="finishedSize" name="finishedSize" ignore="ignore"  value="${finishedWarehousReturnPage.finishedSize}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="finishedNumber" name="finishedNumber" ignore="ignore"  value="${finishedWarehousReturnPage.finishedNumber}" />
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
						<t:dictSelect field="unit" defaultVal="${finishedWarehousReturnPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							退货仓库:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="returnWhouse" defaultVal="${finishedWarehousReturnPage.returnWhouse}"  dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退货库位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehouseSpaceCode" defaultVal="${finishedWarehousReturnPage.warehouseSpaceCode}"  dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							客户:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="customerCode" dictTable="t_customer_list" dictField="customer_code" dictText="customer_name" defaultVal="${finishedWarehousReturnPage.customerCode}" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退货人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="returnedPerson" defaultVal="${productionRequisitionPage.returnedPerson}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							退货时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="returnedDate" name="returnedDate" ignore="ignore"    value="<fmt:formatDate value='${finishedWarehousReturnPage.returnedDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
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
						<input class="inputxt" id="returnedNumber" name="returnedNumber" ignore="ignore"  value="${finishedWarehousReturnPage.returnedNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							退货原因:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="returnedReason" defaultVal="${finishedWarehousReturnPage.returnedReason}" typeGroupCode="r_p_reason" readonly="true"></t:dictSelect>
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
						<input class="inputxt" id="deliveryAdviceOrderNumber" name="deliveryAdviceOrderNumber" ignore="ignore"  value="${finishedWarehousReturnPage.deliveryAdviceOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							销售出库单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="salesDeliveryOrderNumber" name="salesDeliveryOrderNumber" ignore="ignore"  value="${finishedWarehousReturnPage.salesDeliveryOrderNumber}" />
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
						<input class="inputxt" id="productionOrderNumber" name="productionOrderNumber" ignore="ignore"  value="${finishedWarehousReturnPage.productionOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							生产领料单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="takeMaterilNumber" name="takeMaterilNumber" ignore="ignore"  value="${finishedWarehousReturnPage.takeMaterilNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>