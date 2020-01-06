<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购收料单</title>
    <t:base type="jquery,easyui,tools,DatePicker,print,common"></t:base>
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
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1"
             action="purchaseReceiptController.do?save">
    <input id="id" name="id" type="hidden" value="${purchaseReceiptPage.id }">
    <table cellpadding="0" id="main_content" cellspacing="1" class="formtable">
        <tr>
            <td align="right"><label class="Validform_label">收料单编号:</label></td>
            <td class="value">
                <input nullmsg="请填写收料单编号" errormsg="收料单编号格式不对" class="inputxt" id="receiptCode" name="receiptCode"
                       ignore="ignore" value="${purchaseReceiptPage.receiptCode}"/>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">收货组织:</label></td>
            <td class="value">
                <t:dictSelect field="receivingCompanyCode" dictTable="t_s_depart" dictField="id" dictText="departname"
                              defaultVal="${purchaseReceiptPage.receivingCompanyCode}" hasLabel="false"
                              type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">采购组织:</label></td>
            <td class="value">
                <t:dictSelect field="purchasingCompanyCode" dictTable="t_s_depart" dictField="id" dictText="departname"
                              defaultVal="${purchaseReceiptPage.purchasingCompanyCode}" hasLabel="false"
                              type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">单据日期:</label></td>
            <td class="value">
                <input nullmsg="请填写单据日期" errormsg="单据日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       style="width: 150px" id="receiptDate" name="receiptDate" ignore="ignore"
                       value="<fmt:formatDate value='${purchaseReceiptPage.receiptDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">收货部门:</label></td>
            <td class="value">
                <t:dictSelect field="receivingOrgCode" dictTable="t_s_depart" dictField="id" dictText="departname"
                              defaultVal="${purchaseReceiptPage.receivingOrgCode}" hasLabel="false"
                              type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">采购部门:</label></td>
            <td class="value">
                <t:dictSelect field="purchasingOrgCode" dictTable="t_s_depart" dictField="id" dictText="departname"
                              defaultVal="${purchaseReceiptPage.purchasingOrgCode}" hasLabel="false"
                              type="list"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">供应商:</label></td>
            <td class="value">
                <t:dictSelect id="supplierCode" field="supplierCode" dictTable="t_supplier_list"
                              dictField="supplier_code" dictText="supplier_name"
                              defaultVal="${purchaseReceiptPage.supplierCode}" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">收货员:</label></td>
            <td class="value">
                <t:dictSelect id="receivingPersonCode" field="receivingPersonCode"
                              defaultVal="${purchaseReceiptPage.receivingPersonCode}" dictTable="t_s_base_user"
                              dictField="username" dictText="realname" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">采购员:</label></td>
            <td class="value">
                <t:dictSelect id="purchasingPersonCode" field="purchasingPersonCode"
                              defaultVal="${purchaseReceiptPage.purchasingPersonCode}" dictTable="t_s_base_user"
                              dictField="username" dictText="realname" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">审核人:</label></td>
            <td class="value">
                <t:dictSelect id="verifierCode" field="verifierCode" defaultVal="${purchaseReceiptPage.verifierCode}"
                              dictTable="t_s_base_user" dictField="username" dictText="realname"
                              readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">审核日期:</label></td>
            <td class="value">
                <input nullmsg="请填写审核日期" errormsg="审核日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       style="width: 150px" id="verifyDate" name="verifyDate" ignore="ignore"
                       value="<fmt:formatDate value='${purchaseReceiptPage.verifyDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">单据创建日期:</label></td>
            <td class="value">
                <input nullmsg="请填写单据创建日期" errormsg="单据创建日期格式不对" class="Wdate"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" id="createTime"
                       name="createTime" ignore="ignore"
                       value="<fmt:formatDate value='${purchaseReceiptPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">采购订单号:</label></td>
            <td class="value">
                <input nullmsg="请填写采购订单号" errormsg="采购订单号格式不对" class="inputxt" id="orderNumber" name="orderNumber"
                       ignore="ignore" value="${purchaseReceiptPage.orderNumber}"/>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">销售订单号:</label></td>
            <td class="value">
                <input nullmsg="请填写销售订单号" errormsg="销售订单号格式不对" class="inputxt" id="salesOrderNumber"
                       name="salesOrderNumber" ignore="ignore" value="${purchaseReceiptPage.salesOrderNumber}"/>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">读取人:</label></td>
            <td class="value">
                <t:dictSelect id="readPersonCode" field="readPersonCode"
                              defaultVal="${purchaseReceiptPage.readPersonCode}" dictTable="t_s_base_user"
                              dictField="username" dictText="realname" readonly="true"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
            <td align="right"><label class="Validform_label">获取时间:</label></td>
            <td class="value">
                <input nullmsg="请填写获取时间" errormsg="获取时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       style="width: 150px" id="acquireTime" name="acquireTime" ignore="ignore"
                       value="<fmt:formatDate value='${purchaseReceiptPage.acquireTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td data-print="false" colspan="2" style="text-align: center"><button type="button" onclick="print_html()" class="ui_buttons">打印</button></td>
        </tr>
    </table>
    <div style="width: auto;height: 200px;">
            <%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
        <div style="width:690px;height:1px;"></div>
        <t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
            <t:tab href="purchaseReceiptController.do?purchaseReceiptNodeList&id=${purchaseReceiptPage.id}"
                   icon="icon-search" title="采购收料单物料信息" id="purchaseReceiptNode"></t:tab>
        </t:tabs>
    </div>
</t:formvalid>
<!-- 添加 明细 模版 -->
<table style="display:none">
    <tbody id="add_purchaseReceiptNode_table_template">
    <tr>
        <td align="center">
            <div style="width: 25px;" name="xh"></div>
        </td>
        <td data-print="false" align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
        <td align="left" class="select_rawMaterialCode"><t:dictSelect field="purchaseReceiptNodeList[#index#].rawMaterialCode"
                                       dictTable="t_raw_material_list" dictField="raw_material_code"
                                       dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
        <td align="left" class="select_rawMaterialName"><t:dictSelect field="purchaseReceiptNodeList[#index#].rawMaterialName"
                                       dictTable="t_raw_material_list" dictField="raw_material_name"
                                       dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].rawMaterialSize" maxlength="120" type="text"
                                style="width:120px;"></td>
        <td align="left"><t:dictSelect field="purchaseReceiptNodeList[#index#].rawMaterialUnit" typeGroupCode="unit"
                                       readonly="true"></t:dictSelect></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].attr1" maxlength="" type="text"
                                style="width:120px;"></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].actualArrivalNumber" maxlength="" type="text"
                                style="width:120px;"></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].actualReceivedNumber" maxlength="" type="text"
                                style="width:120px;"></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].rejectionNumber" maxlength="" type="text"
                                style="width:120px;"></td>
        <td align="left"><t:dictSelect field="purchaseReceiptNodeList[#index#].repositoryCode"
                                       dictTable="t_repository_list" dictField="repository_code"
                                       dictText="repository_name" readonly="true"></t:dictSelect></td>
        <td align="left"><t:dictSelect field="purchaseReceiptNodeList[#index#].repositorySpace"
                                       dictTable="t_storage_list" dictField="storage_code" dictText="storage_name"
                                       readonly="true"></t:dictSelect></td>
        <td align="left"><t:dictSelect field="purchaseReceiptNodeList[#index#].inventoryStatus" typeGroupCode="io_type"
                                       readonly="true"></t:dictSelect></td>
        <td align="left"><input name="purchaseReceiptNodeList[#index#].rejectionReason" maxlength="256" type="text"
                                style="width:120px;"></td>
    </tr>
    </tbody>
</table>
<script>
    function print_html() {
        LODOP = getLodop();
        LODOP.PRINT_INIT();
        LODOP.SET_PRINT_STYLE("FontSize",18);
        LODOP.SET_PRINT_STYLE("Bold",1);
        LODOP.SET_PRINT_STYLE("Alignment",2);
        LODOP.ADD_PRINT_BARCODE(29, 700, 50,50, "QRCode", '${purchaseReceiptPage.receiptCode},SLD');
        LODOP.SET_PRINT_STYLE("HOrient",2);
        LODOP.ADD_PRINT_TEXT(29,0,'RightMargin:0',38,"采购收料单");
        LODOP.ADD_PRINT_HTM(90,'5%',"90%",200,generateTab(getTabData("main_content")));
        LODOP.ADD_PRINT_HTM(300,'5%',"90%",400,generateTab(getTabData("purchaseReceiptNode_table")));
        LODOP.PREVIEW();
    }
</script>
</body>