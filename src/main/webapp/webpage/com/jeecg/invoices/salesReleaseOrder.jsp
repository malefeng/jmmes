<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>销售出库单</title>
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
                 action="salesReleaseOrderController.do?save">
        <input id="id" name="id" type="hidden" value="${salesReleaseOrderPage.id }">
        <table cellpadding="0" id="main_content" cellspacing="1" class="formtable">
            <tr>
                <td align="right"><label class="Validform_label">销售出库单号:</label></td>
                <td class="value">
                    <input nullmsg="请填写销售出库单号" errormsg="销售出库单号格式不对" class="inputxt" id="receiptCode" name="receiptCode"
                           ignore="ignore" value="${salesReleaseOrderPage.receiptCode}"/>
                    <span class="Validform_checktip"></span>
                </td>
                <td align="right"><label class="Validform_label">发货通知单号:</label></td>
                <td class="value">
                    <input nullmsg="请填写发货通知单号" errormsg="发货通知单号格式不对" class="inputxt" id="sendNoticeNumber"
                           name="sendNoticeNumber" ignore="ignore" value="${salesReleaseOrderPage.sendNoticeNumber}"/>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">日期:</label></td>
                <td class="value">
                    <input nullmsg="请填写日期" errormsg="日期格式不对" class="Wdate"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" id="receiptDate"
                           name="receiptDate" ignore="ignore"
                           value="<fmt:formatDate value='${salesReleaseOrderPage.receiptDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                    <span class="Validform_checktip"></span>
                </td>
                <td align="right"><label class="Validform_label">结算币别:</label></td>
                <td class="value">
                    <t:dictSelect field="currency" typeGroupCode="currency"
                                  defaultVal="${salesReleaseOrderPage.currency}" readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">客户:</label></td>
                <td class="value">
                    <t:dictSelect field="customerCode" dictTable="t_customer_list" dictField="customer_code"
                                  dictText="customer_name" defaultVal="${salesReleaseOrderPage.customerCode}"
                                  readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
                <td align="right"><label class="Validform_label">交货地点:</label></td>
                <td class="value">
                    <input nullmsg="请填写交货地点" errormsg="交货地点格式不对" class="inputxt" id="deliveryPoints"
                           name="deliveryPoints" ignore="ignore" value="${salesReleaseOrderPage.deliveryPoints}"/>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">承运商:</label></td>
                <td class="value">
                        <t:dictSelect field="commonCarrierCode" dictTable="t_common_carrier_list" dictField="trans_code"
                                      dictText="trans_name" defaultVal="${salesReleaseOrderPage.commonCarrierCode}"
                                      readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                <td align="right"><label class="Validform_label">运输单号:</label></td>
                <td class="value">
                    <input nullmsg="请填写运输单号" errormsg="运输单号格式不对" class="inputxt" id="waybillNumber" name="waybillNumber"
                           ignore="ignore" value="${salesReleaseOrderPage.waybillNumber}"/>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">仓管员:</label></td>
                <td class="value">
                    <t:dictSelect field="repositoryManager" defaultVal="${salesReleaseOrderPage.repositoryManager}"
                                  dictTable="t_s_base_user" dictField="username" dictText="realname"
                                  readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
                <td align="right"><label class="Validform_label">单据状态:</label></td>
                <td class="value">
                    <t:dictSelect field="receiptState" typeGroupCode="orderState"
                                  defaultVal="${salesReleaseOrderPage.receiptState}" readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">销售员:</label></td>
                <td class="value">
                    <t:dictSelect field="salesmanCode" defaultVal="${salesReleaseOrderPage.salesmanCode}"
                                  dictTable="t_s_base_user" dictField="username" dictText="realname"
                                  readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
                <td align="right"><label class="Validform_label">读取人:</label></td>
                <td class="value">
                    <t:dictSelect field="readPersonCode" defaultVal="${salesReleaseOrderPage.readPersonCode}"
                                  dictTable="t_s_base_user" dictField="username" dictText="realname"
                                  readonly="true"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><label class="Validform_label">获取时间:</label></td>
                <td class="value">
                    <input nullmsg="请填写获取时间" errormsg="获取时间格式不对" class="Wdate"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" id="acquireTime"
                           name="acquireTime" ignore="ignore"
                           value="<fmt:formatDate value='${salesReleaseOrderPage.acquireTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"/>
                    <span class="Validform_checktip"></span>
                </td>
                <td data-print="false" colspan="2" style="text-align: center">
                    <button type="button" onclick="print_html()" class="ui_buttons">打印</button>
                    <button type="button" onclick="dosing()" class="ui_buttons">配货</button>
                </td>
            </tr>
        </table>
        <div style="width: auto;height: 200px;">
                <%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
            <div style="width:690px;height:1px;"></div>
            <t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
                <t:tab href="salesReleaseOrderController.do?salesReleaseNodeList&id=${salesReleaseOrderPage.id}"
                       icon="icon-search" title="销售出库配料物料详情" id="salesReleaseNode"></t:tab>
                <t:tab href="salesReleaseOrderController.do?salesReleaseOrgNodeList&id=${salesReleaseOrderPage.id}"
                       icon="icon-search" title="销售出库原始物料详情" id="salesReleaseOrgNode"></t:tab>
            </t:tabs>
        </div>
    </t:formvalid>
    <!-- 添加 明细 模版 -->
    <table style="display:none">
        <tbody id="add_salesReleaseNode_table_template">
        <tr>
            <td align="center">
                <div style="width: 25px;" name="xh"></div>
            </td>
            <td data-print="false" align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].finishedSerino" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left" class="select_finishedCode"><t:dictSelect field="salesReleaseNodeList[#index#].finishedCode"
                                           dictTable="t_finished_product_list" dictField="finished_code"
                                           dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
            <td align="left" class="select_finishedName"><t:dictSelect field="salesReleaseNodeList[#index#].finishedName"
                                           dictTable="t_finished_product_list" dictField="finished_name"
                                           dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].finishedSize" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].batchNumber" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].unitPrice" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].unitPriceWithTax" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><t:dictSelect field="salesReleaseNodeList[#index#].repositoryCode"
                                           dictTable="t_repository_list" dictField="repository_code"
                                           dictText="repository_name" readonly="true"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseNodeList[#index#].productionOrderNumber" maxlength="120"
                                    type="text" style="width:120px;"></td>
        </tr>
        </tbody>
        <tbody id="add_salesReleaseOrgNode_table_template">
        <tr>
            <td align="center">
                <div style="width: 25px;" name="xh"></div>
            </td>
            <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
            <td align="left"><t:dictSelect field="salesReleaseNodeList[#index#].finishedCode"
                                           dictTable="t_finished_product_list" dictField="finished_code"
                                           dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
            <td align="left"><t:dictSelect field="salesReleaseNodeList[#index#].finishedName"
                                           dictTable="t_finished_product_list" dictField="finished_name"
                                           dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].finishedSize" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[#index#].supplierAttr"
                                           typeGroupCode="supplAttr" readonly="true"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].wideInWidth" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].inventoryUnit" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].shouldSendNumber" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].actualSendNumber" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].valuationUnit" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].valuationNumber" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].unitPrice" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].unitPriceWithTax" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].isFree" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].batchNumber" maxlength="120" type="text"
                                    style="width:120px;"></td>
            <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[#index#].repositoryCode"
                                           dictTable="t_repository_list" dictField="repository_code"
                                           dictText="repository_name" readonly="true"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].productionOrderNumber" maxlength="120"
                                    type="text" style="width:120px;"></td>
            <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[#index#].repositoryStatus"
                                           typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
            <td align="left"><input name="salesReleaseOrgNodeList[#index#].remark" maxlength="120" type="text"
                                    style="width:120px;"></td>
        </tr>
        </tbody>
    </table>
    <script src="plug-in/common/js/dosing.js"></script>
<script>
    var properArrs1 = ['finishedSerino','finishedCode','finishedName','finishedSize','batchNumber','unitPrice','unitPriceWithTax','repositoryCode','productionOrderNumber'];
    var properArrs2 = ['finishedSerino','finishedCode','finishedName','finishedSize','finishedBatch','','','warehousPosition','productionOrderNumber'];
    function print_html() {
        LODOP = getLodop();
        LODOP.PRINT_INIT();
        LODOP.SET_PRINT_STYLE("FontSize",18);
        LODOP.SET_PRINT_STYLE("Bold",1);
        LODOP.SET_PRINT_STYLE("Alignment",2);
        LODOP.ADD_PRINT_BARCODE(29, 700, 50,50, "QRCode", '${salesReleaseOrderPage.receiptCode},CKD');
        LODOP.SET_PRINT_STYLE("HOrient",2);
        LODOP.ADD_PRINT_TEXT(29,0,'RightMargin:0',38,"销售出库单");
        LODOP.ADD_PRINT_HTM(90,'5%',"90%",200,generateTab(getTabData("main_content")));
        LODOP.ADD_PRINT_HTM(300,'5%',"90%",400,generateTab(getTabData("salesReleaseNode_table")));
        LODOP.PRINTA();
    }
    function dosing(){
        var remainData = CountRemaining("salesReleaseNode_table",3,null,"salesReleaseOrgNode_table",2,9);
        if (JSON.stringify(remainData) != JSON.stringify({})){
            $.getJSON("salesReleaseOrderController.do?dosing",{remainData:JSON.stringify(remainData)},function(data){
                if(!!data&&data.length>0){
                    $.each(data,function(i,e){
                        var tr =  $("#add_salesReleaseNode_table_template tr").clone();
                        generateTr(tr,e,"salesReleaseNodeList",properArrs1,properArrs2);
                        $("#add_salesReleaseNode_table").append(tr);
                        resetTrNum('add_salesReleaseNode_table');
                    })
                }
            })
        }else{
            $.messager.alert('error','无需配货!','info');
        }
    }
</script>
</body>