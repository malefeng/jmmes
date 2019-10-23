<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qRCodeList" title="二维码打印信息" actionUrl="qRCodeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="所属部门" field="sysOrgCode"   width="120"></t:dgCol>
   <t:dgCol title="所属公司" field="sysCompanyCode"   width="120"></t:dgCol>
   <t:dgCol title="代码" field="code"   width="120"></t:dgCol>
   <t:dgCol title="编号" field="number"   width="120"></t:dgCol>
   <t:dgCol title="有效性" field="isValid"   width="120"></t:dgCol>
   <t:dgCol title="规格型号" field="materialSize"   width="120"></t:dgCol>
   <t:dgCol title="物料名称" field="materialName"   width="120"></t:dgCol>
   <t:dgCol title="二维码类型" field="qrCodeType"   width="120"></t:dgCol>
   <t:dgCol title="物料类型" field="materialType"   width="120"></t:dgCol>
   <t:dgCol title="批次" field="batchNo"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qRCodeController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qRCodeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qRCodeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qRCodeController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>