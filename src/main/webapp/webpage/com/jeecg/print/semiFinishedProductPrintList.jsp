<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,print"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#semiFinishedProductPrintList').datagrid({
                idField: 'id',
                title: '半成品码',
                url: 'semiFinishedProductPrintController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,',
                fit: true,
                rownumbers: true,
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortName:"createDate",
                sortOrder:"desc",
                singleSelect: true,
                fitColumns: true,
                striped: true,
                showFooter: true,
                frozenColumns: [[]],
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true}, {
                    field: 'semiFinishedCode',
                    title: '代码',
                    width: 120,
                    sortable: true
                }, {field: 'semiFinishedName', title: '名称', width: 120, sortable: true}, {
                    field: 'materialType',
                    title: '物料类型',
                    width: 120,
                    sortable: true,
                    formatter: function(value){return ${matTypeDic}[value]}
                }, {field: 'semiFinishedSize', title: '规格', width: 120, sortable: true}, {
                    field: 'semiFinishedNumber',
                    title: '数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'semiFinishedUnitCode',
                    title: '单位',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${unitDic}[value]}
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=inputParam('" + rec.id + "','" + rec.semiFinishedCode + "')> ";
                        href += "打印</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#semiFinishedProductPrintList").datagrid("clearChecked");
                    $("#semiFinishedProductPrintList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#semiFinishedProductPrintList');
                            var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
                            grid.datagrid({pageNumber: (curr - 1)});
                        }
                    }
                    try {
                        loadAjaxDict(data);
                    } catch (e) {
                    }
                },
                onClickRow: function (rowIndex, rowData) {
                    rowid = rowData.id;
                    gridname = 'semiFinishedProductPrintList';
                }
            });
            $('#semiFinishedProductPrintList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#semiFinishedProductPrintList').datagrid('getPager').pagination({
                onBeforeRefresh: function (pageNumber, pageSize) {
                    $(this).pagination('loading');
                    $(this).pagination('loaded');
                }
            });
            try {
                restoreheader();
            } catch (ex) {
            }
        });

        function reloadTable() {
            try {
                $('#' + gridname).datagrid('reload');
                $('#' + gridname).treegrid('reload');
            } catch (ex) {
            }
        }

        function reloadsemiFinishedProductPrintList() {
            $('#semiFinishedProductPrintList').datagrid('reload');
        }

        function getsemiFinishedProductPrintListSelected(field) {
            return getSelected(field);
        }

        function getSelected(field) {
            var row = $('#' + gridname).datagrid('getSelected');
            if (row != null) {
                value = row[field];
            } else {
                value = '';
            }
            return value;
        }

        function getsemiFinishedProductPrintListSelections(field) {
            var ids = [];
            var rows = $('#semiFinishedProductPrintList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#semiFinishedProductPrintList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#semiFinishedProductPrintList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#semiFinishedProductPrintList').datagrid('getColumnFields');
            }
            var cols = storage.get('semiFinishedProductPrintListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#semiFinishedProductPrintList').datagrid("getColumnOption", columnsFields[i]);
                    if (init) {
                        hiddencolumns.push({
                            field: columsDetail.field,
                            hidden: columsDetail.hidden,
                            visible: (columsDetail.hidden == true ? false : true)
                        });
                    } else {
                        for (var j = 0; j < cols.length; j++) {
                            if (cols[j].field == columsDetail.field) {
                                hiddencolumns.push({
                                    field: columsDetail.field,
                                    hidden: columsDetail.hidden,
                                    visible: cols[j].visible
                                });
                            }
                        }
                    }
                }
            }
            storage.set('semiFinishedProductPrintListhiddenColumns', JSON.stringify(hiddencolumns));
        }

        function isShowBut() {
            var isShowSearchId = $('#isShowSearchId').val();
            if (isShowSearchId == "true") {
                $("#searchColums").hide();
                $('#isShowSearchId').val("false");
                $('#columsShow').remove("src");
                $('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_expand.png");
            } else {
                $("#searchColums").show();
                $('#isShowSearchId').val("true");
                $('#columsShow').remove("src");
                $('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_collapse.png");
            }
        }

        function restoreheader() {
            var cols = storage.get('semiFinishedProductPrintListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#semiFinishedProductPrintList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('semiFinishedProductPrintListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#semiFinishedProductPrintList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function semiFinishedProductPrintListsearch() {
            try {
                if (!$("#semiFinishedProductPrintListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#semiFinishedProductPrintList').datagrid('options').queryParams;
                $('#semiFinishedProductPrintListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#semiFinishedProductPrintList').datagrid({
                    url: 'semiFinishedProductPrintController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#semiFinishedProductPrintList').datagrid({
                url: 'semiFinishedProductPrintController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,',
                queryParams: jsonparams
            });
        }

        function semiFinishedProductPrintListsearchbox(value, name) {
            var queryParams = $('#semiFinishedProductPrintList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#semiFinishedProductPrintList').datagrid('reload');
        }

        $('#semiFinishedProductPrintListsearchbox').searchbox({
            searcher: function (value, name) {
                semiFinishedProductPrintListsearchbox(value, name);
            }, menu: '#semiFinishedProductPrintListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                semiFinishedProductPrintListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#semiFinishedProductPrintList').datagrid('options').queryParams;
            $('#semiFinishedProductPrintListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#semiFinishedProductPrintListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductPrintListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductPrintList').datagrid({
                url: 'semiFinishedProductPrintController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="semiFinishedProductPrintList" toolbar="#semiFinishedProductPrintListtb"></table>
        <div id="semiFinishedProductPrintListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>半成品代码:<input type="text" name="semiFinishedCode"></span>
                <span>半成品名称:<input type="text" name="semiFinishedName"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="semiFinishedProductPrintListsearch();" plain="true" icon="icon-search">查询</a>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="rePrint();" plain="true" icon="icon-search">重打</a>
            </div>
            <div style="height:0px;">
                <span style="float:left;"></span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<div id="win" style="padding: 0 10px;">
    <div style=" display: flex; flex-direction: column; justify-content: space-between; align-items: center">
        <div style="display: flex;justify-content: space-between;height: 40px; align-items: center; width: 100%;">批 次 号：<input
                type="text" id="batchNo"></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            半成品次数：<input type="text" id="times"></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            首检次数：<input type="text" id="firstTimes"></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            末检次数：<input type="text" id="lastTimes"></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            生产派工单号：<select id="productionDispatchingNumber"></select></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            领料单号：<input type="text" id="takeMaterilNumber" readonly/></div>
        <div style="display: flex;justify-content: space-between; align-items: center;height: 40px; width: 100%;">
            生产订单号：<input type="text" id="productionOrderNumber" readonly/></div>
    </div>
    <div style="height: 30px;  display: flex; justify-content: space-between;  align-items: center">
        <button id="wind_submit" style="height: 30px">确认</button>
        <button id="wind_close" style="height: 30px">取消</button>
    </div>
</div>
<div id="rp" style="padding: 0 10px;">
    <div style=" display: flex; flex-direction: column; justify-content: space-between; align-items: center">
        <div style="display: flex;justify-content: space-between;height: 40px; align-items: center; width: 100%;">二维码编号：<input
                type="text" id="qrCode"></div>
    </div>
    <div style="height: 30px;  display: flex; justify-content: space-between;  align-items: center">
        <button id="rp_submit" style="height: 30px">确认</button>
        <button id="rp_close" style="height: 30px">取消</button>
    </div>
</div>
<script>
    var printId;

    function inputParam(id) {
        printId = id;
        $("#batchNo").val("");
        $("#times").val(1);
        $("#firstTimes").val(3);
        $("#lastTimes").val(3);
        loadTakeMaterilNumberMsg();
        $('#win').window({
            title: '请输入打印信息',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            width: 300,
            height: 370,
            modal: true
        });
    }

    $("#wind_submit").click(function () {
        var batchNo = $("#batchNo").val();
        if (!batchNo) {
            $.messager.alert('error', '批次号不能为空!', 'info');
            return false;
        }
        $('#win').window('close');
        print(printId, batchNo)
    })
    $("#wind_close").click(function () {
        $('#win').window('close');
    })

    function print(id, batchNo) {
        var times = $("#times").val();
        var firstTimes = $("#firstTimes").val();
        var lastTimes = $("#lastTimes").val();
        var productionDispatchingNumber = $("#productionDispatchingNumber option:selected").val();
        var productionOrderNumber = $("#productionOrderNumber").val();
        var takeMaterilNumber = $("#takeMaterilNumber").val();
        $.getJSON("semiFinishedProductPrintController.do?getPrintData", {
            id: id,
            batchNo: batchNo,
            times: times,
            firstTimes: firstTimes,
            lastTimes: lastTimes,
            takeMaterilNumber: takeMaterilNumber,
            productionDispatchingNumber: productionDispatchingNumber,
            productionOrderNumber: productionOrderNumber
        }, function (data) {
            if (!!data) {
                printQRCode(data);
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }

    function loadTakeMaterilNumberMsg() {
        $.getJSON("productionRequisitionController.do?getUnproductedListByParam",{printType:'semiFinishedPrint'}, function (data) {
            $("#productionDispatchingNumber").html("<option>请选择<\/option>");
            $("#takeMaterilNumber").html("");
            $("#productionOrderNumber").html("");
            if (!!data) {
                for (var i = 0; i < data.length; i++) {
                    $("#productionDispatchingNumber").append("<option value='" + data[i]["productionDispatchingNumber"] + "' data-takematerilnumber='"+data[i]["receiptCode"]+"' data-productionOrderNumber='" + data[i]["productionOrderNumber"] + "'>" + data[i]["productionDispatchingNumber"] + "</option>")
                }
            } else {
                $.messager.alert('error', '无对应领料单信息!', 'info');
            }
        })
    }

    $("#productionDispatchingNumber").change(function (e) {
        var productionOrderNumber = $(this).find("option:selected").data("productionordernumber");
        var takeMaterilNumber = $(this).find("option:selected").data("takematerilnumber");
        $("#productionOrderNumber").val(productionOrderNumber)
        $("#takeMaterilNumber").val(takeMaterilNumber)
    })

    //重打
    function rePrint(){
        $('#rp').window({
            title: '请输入二维码编号',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            width: 300,
            height: 130,
            modal: true
        });
    }
    //提交重打
    $("#rp_submit").click(function(){
        var qrCode = $("#qrCode").val();
        if (!qrCode) {
            $.messager.alert('error', '二维码不能为空!', 'info');
            return false;
        }
        doRePrint(qrCode);
    })
    //取消重打
    $("#rp_close").click(function () {
        $('#rp').window('close');
    })
    function doRePrint(qrCode) {
        $.getJSON("semiFinishedProductPrintController.do?rePrint", {qrCode: qrCode}, function (data) {
            if (!!data) {
                printQRCode(data);
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }
</script>