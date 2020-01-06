<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>成品检测</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //初始化下标
        function resetTrNum(tableId) {w
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
    <style>
        ul.top_title,.top_title li{
            list-style: none;
            margin:0;
            padding:0;
            box-sizing: border-box ;
        }
        ul.top_title{
            width:100%;
            height: 120px;
            padding: 0 10px;
            position: relative;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        .top_title li{
            width: 25%;
        }
        .top_title li a{
            text-decoration: none;
            cursor: default;
        }
        .top_title li a:hover, .top_title li a:visited, .top_title li a:link, .top_title li a:active {
            color:#000;
        }
        #download, #upload{
            display: inline-block;
            width: 60px;
            height: 20px;
            line-height: 20px;
            border: 1px solid #eee;
            border-radius: 3px;
            margin-right: 5px;
            text-align: center;
            cursor: pointer;
        }
        #download{
            border-color:#3b09e2;
            color:#3b09e2
        }
        #upload{
            border-color:#e2092a;
            color:#e2092a
        }
    </style>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1"
             action="finishedInspectItemController.do?save">
    <input id="id" name="id" type="hidden" value="${finishedInspectItemPage.id }">
    <ul class="top_title">
        <li>
            <a href="javascrip:;" name="">成品编号:</a>
            <a href="javascrip:;">${finishedInspectItemPage.finishedCode}</a>
            <input type="hidden" name="finishedCode" value="${finishedInspectItemPage.finishedCode}">
        </li>
        <li>
            <a href="javascrip:;" name="">成品名称:</a>
            <a href="javascrip:;">${finishedInspectItemPage.finishedName}</a>
            <input type="hidden" name="finishedName" value="${finishedInspectItemPage.finishedName}">
        </li>
        <li>
            <a href="javascrip:;" name="">生产派工单号:</a>
            <a href="javascrip:;">${finishedInspectItemPage.productionDispatchingNumber}</a>
            <input type="hidden" name="productionDispatchingNumber"
                   value="${finishedInspectItemPage.productionDispatchingNumber}">
        </li>
        <li>
            <a href="javascrip:;" name="">总数量:</a>
            <a href="javascrip:;">${finishedInspectItemPage.count}</a>
            <input type="hidden" name="count" value="${finishedInspectItemPage.count}">
        </li>
        <li>
            <a href="javascrip:;" name="">合格数量:</a>
            <a href="javascrip:;">${finishedInspectItemPage.qualifiedCount}</a>
            <input type="hidden" name="qualifiedCount" value="${finishedInspectItemPage.qualifiedCount}">
        </li>
        <li>
            <a href="javascrip:;" name="">不合格数量:</a>
            <a href="javascrip:;">${finishedInspectItemPage.unqualifiedCount}</a>
            <input type="hidden" name="unQualifiedCount" value="${finishedInspectItemPage.unqualifiedCount}">
        </li>
        <li>
            <a href="javascrip:;" name="">首末检记录表:</a>
            <t:webUploader name="inspectLogSheet" displayTxt="true"
                           pathValues="${finishedInspectItemPage.inspectLogSheet}" extensions="xlsx"
                           bizType="semifinished"></t:webUploader>
        </li>
        <li>
        </li>
    </ul>
    <div style="width: auto;height: 200px;">
            <%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
        <div style="width:690px;height:1px;"></div>
        <t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
            <t:tab href="finishedInspectItemController.do?finishedInspectItemNodeList&finishedCode=${finishedInspectItemPage.finishedCode}"
                   icon="icon-search" title="成品检验明细" id="finishedInspectItemNode"></t:tab>
        </t:tabs>
    </div>
</t:formvalid>
<!-- 添加 明细 模版 -->
<table style="display:none">
    <tbody id="add_finishedInspectItemNode_table_template">
    <tr>
        <td align="center">
            <div style="width: 25px;" name="xh"></div>
        </td>
        <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].inspectTimes" maxlength="32" type="text"
                                style="width:120px;"></td>
        <td align="left"><t:dictSelect field="finishedInspectItemNodeList[#index#].inspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
        <td align="left"><t:dictSelect field="finishedInspectItemNodeList[#index#].inspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].inspectDate" maxlength="" type="text"
                                style="width:120px;"></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].inspectNumber" maxlength="32" type="text" style="width:120px;"></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].count" maxlength="32" type="text" style="width:120px;"></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].qualifiedCount" maxlength="32" type="text" style="width:120px;"></td>
        <td align="left"><input name="finishedInspectItemNodeList[#index#].unqualifiedCount" maxlength="32" type="text" style="width:120px;"></td>
    </tr>
    </tbody>
</table>
</body>