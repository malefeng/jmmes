<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,print"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#purchaseReceiptNodeList').datagrid({
                idField: 'id',
                title: '原料码信息',
                url: 'purchaseReceiptNodeController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialSize,rawMaterialUnit,actualArrivalNumber,actualReceivedNumber,rejectionNumber,repositoryCode,repositorySpace,inventoryStatus,rejectionReason,',
                fit: true,
                rownumbers: true,
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortName:"createDate",
                sortOrder:"desc",
                rownumbers: true,
                singleSelect: true,
                fitColumns: true,
                striped: true,
                showFooter: true,
                frozenColumns: [[]],
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true}, {
                    field: 'rawMaterialCode',
                    title: '物料代码',
                    width: 120,
                    sortable: true
                }, {field: 'rawMaterialName', title: '物料名称', width: 120, sortable: true}, {
                    field: 'rawMaterialSize',
                    title: '规格型号',
                    width: 120,
                    sortable: true
                }, {
                    field: 'rawMaterialUnit',
                    title: '单位',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${unitDic}[value]}
                }, {
                    field: 'actualArrivalNumber',
                    title: '实到数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'actualReceivedNumber',
                    title: '实收数量',
                    width: 120,
                    sortable: true
                }, {field: 'rejectionNumber', title: '拒收数量', width: 120, sortable: true}, {
                    field: 'repositoryCode',
                    title: '仓库',
                    width: 120,
                    sortable: true,
                    formatter: function(value){return ${repostDic}[value]}
                }, {
                    field: 'repositorySpace',
                    title: '仓位',
                    width: 120,
                    sortable: true,
                    formatter: function(value){return ${storageDic}[value]}
                }, {
                    field: 'inventoryStatus',
                    title: '库存状态',
                    width: 120,
                    sortable: true,
                    formatter: function(value){return ${ioStateDic}[value]}
                }, {field: 'rejectionReason', title: '拒收原因', width: 120, sortable: true}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=print('" + rec.id + "','" + index + "')> 打印</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#purchaseReceiptNodeList").datagrid("clearChecked");
                    $("#purchaseReceiptNodeList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#purchaseReceiptNodeList');
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
                    gridname = 'purchaseReceiptNodeList';
                }
            });
            $('#purchaseReceiptNodeList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#purchaseReceiptNodeList').datagrid('getPager').pagination({
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

        function reloadpurchaseReceiptNodeList() {
            $('#purchaseReceiptNodeList').datagrid('reload');
        }

        function getpurchaseReceiptNodeListSelected(field) {
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

        function getpurchaseReceiptNodeListSelections(field) {
            var ids = [];
            var rows = $('#purchaseReceiptNodeList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#purchaseReceiptNodeList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#purchaseReceiptNodeList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#purchaseReceiptNodeList').datagrid('getColumnFields');
            }
            var cols = storage.get('purchaseReceiptNodeListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#purchaseReceiptNodeList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('purchaseReceiptNodeListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('purchaseReceiptNodeListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#purchaseReceiptNodeList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('purchaseReceiptNodeListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#purchaseReceiptNodeList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function purchaseReceiptNodeListsearch() {
            try {
                if (!$("#purchaseReceiptNodeListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#purchaseReceiptNodeList').datagrid('options').queryParams;
                $('#purchaseReceiptNodeListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#purchaseReceiptNodeList').datagrid({
                    url: 'purchaseReceiptNodeController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialSize,rawMaterialUnit,actualArrivalNumber,actualReceivedNumber,rejectionNumber,repositoryCode,repositorySpace,inventoryStatus,rejectionReason,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#purchaseReceiptNodeList').datagrid({
                url: 'purchaseReceiptNodeController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialSize,rawMaterialUnit,actualArrivalNumber,actualReceivedNumber,rejectionNumber,repositoryCode,repositorySpace,inventoryStatus,rejectionReason,',
                queryParams: jsonparams
            });
        }

        function purchaseReceiptNodeListsearchbox(value, name) {
            var queryParams = $('#purchaseReceiptNodeList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#purchaseReceiptNodeList').datagrid('reload');
        }

        $('#purchaseReceiptNodeListsearchbox').searchbox({
            searcher: function (value, name) {
                purchaseReceiptNodeListsearchbox(value, name);
            }, menu: '#purchaseReceiptNodeListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                purchaseReceiptNodeListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#purchaseReceiptNodeList').datagrid('options').queryParams;
            $('#purchaseReceiptNodeListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#purchaseReceiptNodeListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#purchaseReceiptNodeListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#purchaseReceiptNodeList').datagrid({
                url: 'purchaseReceiptNodeController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialSize,rawMaterialUnit,actualArrivalNumber,actualReceivedNumber,rejectionNumber,repositoryCode,repositorySpace,inventoryStatus,rejectionReason,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="purchaseReceiptNodeList" toolbar="#purchaseReceiptNodeListtb"></table>
        <div id="purchaseReceiptNodeListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>物料代码:<input type="text" name="rawMaterialCode"></span>
                <span>物料名称:<input type="text" name="rawMaterialName"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="purchaseReceiptNodeListsearch();" plain="true" icon="icon-search">查询</a>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="printByreceiptCode();" plain="true" icon="icon-search">批量打印</a>
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
        <div style="display: flex;justify-content: space-between;height: 40px; align-items: center; width: 100%;">收料单号：
            <t:dictSelect field="receiptCode" id="receiptCode" dictTable="t_purchase_receipt_list" dictField="receipt_code" dictText="receipt_code" hasLabel="false" type="list"></t:dictSelect>
        </div>
    </div>
    <div style="height: 30px;  display: flex; justify-content: space-between;  align-items: center">
        <button id="bp_submit" style="height: 30px">确认</button>
        <button id="bp_close" style="height: 30px">取消</button>
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
    //打印
    /*function inputParam(id) {

        printId = id;
        $("#win").val("");
        $('#win').window({
            title: '请输入打印信息',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            width: 300,
            height: 130,
            modal: true
        });
    }
    //提交打印
    $("#wind_submit").click(function () {
        var receiptCode = $("#receiptCode").val();
        if (!receiptCode) {
            $.messager.alert('error', '收料单号不能为空!', 'info');
            return false;
        }
        $('#win').window('close');
        print(receiptCode);

    })
    //取消打印
    $("#wind_close").click(function () {
        $('#win').window('close');
    })*/

    function print(id) {
        $.getJSON("purchaseReceiptNodeController.do?getPrintData", {id: id}, function (data) {
            if (!!data) {
                printQRCode(data);
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }
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
        $.getJSON("purchaseReceiptNodeController.do?rePrint", {qrCode: qrCode}, function (data) {
            if (!!data) {
                printQRCode(data);
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }


    function printByreceiptCode(){
        $("#receiptCode").val("");
        $('#win').window({
            title: '请输入打印信息',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            width: 300,
            height: 130,
            modal: true
        });
    }

    //提交批量打印
    $("#bp_submit").click(function(){
        var receiptCode = $("#receiptCode option:selected").val();
        if (!receiptCode) {
            $.messager.alert('error', '收料单号不能为空!', 'info');
            return false;
        }
        doPrintByreceiptCode(receiptCode);
    })

    function doPrintByreceiptCode(receiptCode){
        $.getJSON("purchaseReceiptNodeController.do?getPrintDataByreceiptCode", {receiptCode: receiptCode}, function (data) {
            if (!!data) {
                printQRCode(data);
                $('#win').window('close');
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }
    //取消批量打印
    $("#bp_close").click(function () {
        $('#win').window('close');
    })
</script>