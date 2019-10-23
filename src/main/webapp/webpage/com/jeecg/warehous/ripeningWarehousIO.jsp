<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>熟成出入库列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ripeningWarehousIOController.do?save">
			<input id="id" name="id" type="hidden" value="${ripeningWarehousIOPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productSerino" name="productSerino" ignore="ignore"  value="${ripeningWarehousIOPage.productSerino}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							产品代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productCode" name="productCode" ignore="ignore"  value="${ripeningWarehousIOPage.productCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productName" name="productName" ignore="ignore"  value="${ripeningWarehousIOPage.productName}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							熟成产品类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="ripeningProType" defaultVal="${ripeningWarehousIOPage.ripeningProType}" typeGroupCode="rip_p_type" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品批次:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productBatch" name="productBatch" ignore="ignore"  value="${ripeningWarehousIOPage.productBatch}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							规格型号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productSize" name="productSize" ignore="ignore"  value="${ripeningWarehousIOPage.productSize}" />
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
						<input class="inputxt" id="productNumber" name="productNumber" ignore="ignore"  value="${ripeningWarehousIOPage.productNumber}" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="unit" defaultVal="${ripeningWarehousIOPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
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
						<t:dictSelect field="warehousePositionCode" defaultVal="${ripeningWarehousIOPage.warehousePositionCode}" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							库位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehouseSpaceCode" defaultVal="${ripeningWarehousIOPage.warehouseSpaceCode}" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							熟成库存状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="ripeningStoreType" defaultVal="${ripeningWarehousIOPage.ripeningStoreType}" typeGroupCode="rip_s_type" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							入库人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehousingPersonCode" defaultVal="${ripeningWarehousIOPage.warehousingPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入库时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="warehousingDate" name="warehousingDate" ignore="ignore"    value="<fmt:formatDate value='${ripeningWarehousIOPage.warehousingDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							出库人:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="warehouseOutPersonCode" defaultVal="${ripeningWarehousIOPage.warehouseOutPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出库时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="warehouseOutDate" name="warehouseOutDate" ignore="ignore"    value="<fmt:formatDate value='${ripeningWarehousIOPage.warehouseOutDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							生产派工单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productionOrderNumber" name="productionOrderNumber" ignore="ignore"  value="${ripeningWarehousIOPage.productionOrderNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产领料单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="takeMaterilNumber" name="takeMaterilNumber" ignore="ignore"  value="${ripeningWarehousIOPage.takeMaterilNumber}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>