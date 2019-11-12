<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="checkTaskList" fitColumns="true" title="盘点任务" actionUrl="checkTaskController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="所属部门" field="sysOrgCode" ></t:dgCol>
   <t:dgCol title="所属公司" field="sysCompanyCode" ></t:dgCol>
   <t:dgCol title="流程状态" field="bpmStatus" ></t:dgCol>
   <t:dgCol title="盘点批次号" field="checkBatch" ></t:dgCol>
   <t:dgCol title="盘点类型" field="checkType" ></t:dgCol>
   <t:dgCol title="盘点仓库代码" field="repositoryCode" ></t:dgCol>
   <t:dgCol title="盘点仓库名称" field="repositoryName" ></t:dgCol>
   <t:dgCol title="盘点原料代码" field="rawMaterialCode" ></t:dgCol>
   <t:dgCol title="盘点原料名称" field="rawMaterialName" ></t:dgCol>
   <t:dgCol title="盘点成品代码" field="productCode" ></t:dgCol>
   <t:dgCol title="盘点成品名称" field="productName" ></t:dgCol>
   <t:dgCol title="盘点状态" field="checkStatus" ></t:dgCol>
   <t:dgCol title="计划盘点时间" field="checkTimePlan" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="实际盘点时间" field="checkTimeReal" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="计划盘点人" field="checkPersonPlan" ></t:dgCol>
   <t:dgCol title="实际盘点人" field="checkPersonReal" ></t:dgCol>
   <t:dgCol title="预留1" field="attr1" ></t:dgCol>
   <t:dgCol title="预留2" field="attr2" ></t:dgCol>
   <t:dgCol title="预留3" field="attr3" ></t:dgCol>
   <t:dgCol title="预留4" field="attr4" ></t:dgCol>
   <t:dgCol title="预留5" field="attr5" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="checkTaskController.do?del&id={id}" urlclass="ace_button" urlfont="fa-trash-o"/>
 	  <t:dgToolBar title="录入" icon="icon-add" url="checkTaskController.do?addorupdate" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="checkTaskController.do?addorupdate" funname="update" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>