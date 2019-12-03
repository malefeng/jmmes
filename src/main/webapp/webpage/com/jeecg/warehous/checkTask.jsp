<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>盘点任务</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //初始化下标
        function resetTrNum(tableId) {
            $tbody = $("#" + tableId + "");
            $tbody.find('>tr').each(function (i) {
                $(':input, select', this).each(function () {
                    var $this = $(this), name = $this.attr('name'), val = $this.val();
                    if (name != null) {
                        if (name.indexOf("#index#") >= 0) {
                            $this.attr("name", name.replace('#index#', i));
                        } else {
                            var s = name.indexOf("[");
                            var e = name.indexOf("]");
                            var new_name = name.substring(s + 1, e);
                            $this.attr("name", name.replace(new_name, i));
                        }
                    }
                });
                $(this).find('div[name=\'xh\']').html(i + 1);
            });
        }
    </script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1"
             action="checkTaskController.do?save">
    <input id="id" name="id" type="hidden" value="${checkTaskPage.id }">
    <table cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right"><label class="Validform_label">盘点批次号:</label></td>
            <td class="value">
                <input nullmsg="请填写盘点批次号" errormsg="盘点批次号格式不对" class="inputxt" id="checkBatch" name="checkBatch"
                       ignore="ignore" value="${checkTaskPage.checkBatch}"/>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">盘点类型:</label></td>
            <td class="value">
                <t:dictSelect field="checkType" defaultVal="${checkTaskPage.checkType}" typeGroupCode="check_type"  hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">盘点仓库代码:</label></td>
            <td class="value">
                <t:dictSelect field="repositoryCode" defaultVal="${checkTaskPage.repositoryCode}" dictTable="t_repository_list" dictField="repository_code" dictText="repository_code" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">盘点仓库名称:</label></td>
            <td class="value">
                <t:dictSelect field="repositoryName" defaultVal="${checkTaskPage.repositoryName}" dictTable="t_repository_list" dictField="repository_name" dictText="repository_name" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">盘点原料代码:</label></td>
            <td class="value">
                <t:dictSelect field="rawMaterialCode" defaultVal="${checkTaskPage.rawMaterialCode}" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">盘点原料名称:</label></td>
            <td class="value">
                <t:dictSelect field="rawMaterialName" defaultVal="${checkTaskPage.rawMaterialName}" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">盘点成品代码:</label></td>
            <td class="value">
                <t:dictSelect field="productCode" defaultVal="${checkTaskPage.productCode}" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">盘点成品名称:</label></td>
            <td class="value">
                <t:dictSelect field="productName" defaultVal="${checkTaskPage.productName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">盘点状态:</label></td>
            <td class="value">
                <t:dictSelect field="checkStatus" defaultVal="${checkTaskPage.checkStatus}" typeGroupCode="checkStaut" hasLabel="false" type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">计划盘点时间:</label></td>
            <td class="value">
                <input nullmsg="请填写计划盘点时间" errormsg="计划盘点时间格式不对" class="Wdate"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" id="checkTimePlan"
                       name="checkTimePlan" ignore="ignore"
                       value="<fmt:formatDate value='${checkTaskPage.checkTimePlan}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">实际盘点时间:</label></td>
            <td class="value">
                <input nullmsg="请填写实际盘点时间" errormsg="实际盘点时间格式不对" class="Wdate"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" id="checkTimeReal"
                       name="checkTimeReal" ignore="ignore"
                       value="<fmt:formatDate value='${checkTaskPage.checkTimeReal}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">计划盘点人:</label></td>
            <td class="value">
                <t:dictSelect field="checkPersonPlan" defaultVal="${checkTaskPage.checkPersonPlan}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">实际盘点人:</label></td>
            <td class="value">
                <t:dictSelect field="checkPersonReal" defaultVal="${checkTaskPage.checkPersonReal}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
    </table>
    <div style="width: auto;height: 200px;">
            <%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
        <div style="width:690px;height:1px;"></div>
        <t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
            <t:tab href="checkTaskController.do?checkNodeList&checkBatch=${checkTaskPage.checkBatch}" icon="icon-search"
                   title="盘点明细表" id="checkNode"></t:tab>
        </t:tabs>
    </div>
</t:formvalid>
<!-- 添加 明细 模版 -->
<table style="display:none">
    <tbody id="add_checkNode_table_template">
    <tr>
        <td align="center">
            <div style="width: 25px;" name="xh"></div>
        </td>
        <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
        <td align="left"><t:dictSelect field="checkNodeList[#index#].checkType" typeGroupCode="check_type"  hasLabel="false" type="list"></t:dictSelect></td>
        <td align="left"><input name="checkNodeList[#index#].mtlCode" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].mtlName" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].mtlSize" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].mtlBatch" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].stockNum" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].checkNum" maxlength="120" type="text" style="width:120px;">
        </td>
        <td align="left"><input name="checkNodeList[#index#].profitLossNum" maxlength="120" type="text"
                                style="width:120px;"></td>
        <td align="left"><t:dictSelect field="checkNodeList[#index#].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
        <td align="left"><t:dictSelect field="checkNodeList[#index#].repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
        <td align="left"><t:dictSelect field="checkNodeList[#index#].storageCode" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect></td>
        </td>
    </tr>
    </tbody>
</table>
</body>