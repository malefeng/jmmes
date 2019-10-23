<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>二维码打印信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qRCodeController.do?save">
			<input id="id" name="id" type="hidden" value="${qRCodePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属部门:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sysOrgCode" name="sysOrgCode" ignore="ignore"  value="${qRCodePage.sysOrgCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属公司:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sysCompanyCode" name="sysCompanyCode" ignore="ignore"  value="${qRCodePage.sysCompanyCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="code" name="code" ignore="ignore"  value="${qRCodePage.code}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="number" name="number" ignore="ignore"  value="${qRCodePage.number}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							有效性:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isValid" name="isValid" ignore="ignore"  value="${qRCodePage.isValid}" datatype="n" />
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
						<input class="inputxt" id="materialSize" name="materialSize" ignore="ignore"  value="${qRCodePage.materialSize}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物料名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="materialName" name="materialName" ignore="ignore"  value="${qRCodePage.materialName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							二维码类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="qrCodeType" name="qrCodeType" ignore="ignore"  value="${qRCodePage.qrCodeType}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物料类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="materialType" name="materialType" ignore="ignore"  value="${qRCodePage.materialType}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							批次:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="batchNo" name="batchNo" ignore="ignore"  value="${qRCodePage.batchNo}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>