<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,print"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#equipmentList').datagrid({
                idField: 'id',
                title: '设备列表',
                url: 'equipmentController.do?datagrid&field=id,equipmentNumber,equipmentName,equipmentSize,equipmentSupplier,supplierDetail,supplierSeat,purchaseDate,purchasePerson,',
                fit: true,
                rownumbers: true,
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortOrder: 'desc',
                rownumbers: true,
                singleSelect: true,
                fitColumns: true,
                striped: true,
                showFooter: true,
                frozenColumns: [[]],
                columns: [[
                    {field: 'id', title: '编号', hidden: true, sortable: true},
                    {field: 'equipmentNumber', title: '设备编号', width: 120, sortable: true},
                    {field: 'equipmentName', title: '设备名称', width: 120, sortable: true},
                    {field: 'equipmentSize', title: '设备型号', width: 120, sortable: true},
                    {field: 'equipmentSupplier', title: '设备供应商', width: 120, sortable: true, formatter:function(value){return ${suplDic}[value]}},
                    {field: 'supplierDetail', title: '设备描述', width: 120, sortable: true},
                    {field: 'supplierSeat', title: '设备位置', width: 120, sortable: true},
                    {field: 'purchaseDate', title: '采购时间', width: 120, sortable: true, formatter: function (value, rec, index) {return new Date().format('yyyy-MM-dd hh:mm:ss', value);}},
                    {field: 'purchasePerson', title: '采购人', width: 120, sortable: true, formatter:function(value){return ${userDic}[value]}},
                    {field: 'opt', title: '操作', width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=print('" + rec.id + "')> 打印</a>&nbsp;";
                        return href;
                    }
                }
                ]],
                onLoadSuccess: function (data) {
                    $("#equipmentList").datagrid("clearChecked");
                    $("#equipmentList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#equipmentList');
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
                    gridname = 'equipmentList';
                }
            });
            $('#equipmentList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#equipmentList').datagrid('getPager').pagination({
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

        function reloadequipmentList() {
            $('#equipmentList').datagrid('reload');
        }

        function getequipmentListSelected(field) {
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

        function getequipmentListSelections(field) {
            var ids = [];
            var rows = $('#equipmentList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#equipmentList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#equipmentList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#equipmentList').datagrid('getColumnFields');
            }
            var cols = storage.get('equipmentListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#equipmentList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('equipmentListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('equipmentListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#equipmentList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('equipmentListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#equipmentList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function equipmentListsearch() {
            try {
                if (!$("#equipmentListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#equipmentList').datagrid('options').queryParams;
                $('#equipmentListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#equipmentList').datagrid({
                    url: 'equipmentController.do?datagrid&field=id,equipmentNumber,equipmentName,equipmentSize,equipmentSupplier,supplierDetail,supplierSeat,purchaseDate,purchasePerson,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#equipmentList').datagrid({
                url: 'equipmentController.do?datagrid&field=id,equipmentNumber,equipmentName,equipmentSize,equipmentSupplier,supplierDetail,supplierSeat,purchaseDate,purchasePerson,',
                queryParams: jsonparams
            });
        }

        function equipmentListsearchbox(value, name) {
            var queryParams = $('#equipmentList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#equipmentList').datagrid('reload');
        }

        $('#equipmentListsearchbox').searchbox({
            searcher: function (value, name) {
                equipmentListsearchbox(value, name);
            }, menu: '#equipmentListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                equipmentListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#equipmentList').datagrid('options').queryParams;
            $('#equipmentListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#equipmentListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#equipmentListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#equipmentList').datagrid({
                url: 'equipmentController.do?datagrid&field=id,equipmentNumber,equipmentName,equipmentSize,equipmentSupplier,supplierDetail,supplierSeat,purchaseDate,purchasePerson,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="equipmentList" toolbar="#equipmentListtb"></table>
        <div id="equipmentListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>设备编号:<input type="text" name="equipmentNumber"></span>
                <span>设备名称:<input type="text" name="equipmentName"></span>
                <span>采购时间:
                    <input type="text" name="purchaseDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="purchaseDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>采购人:<t:dictSelect field="purchasePerson" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="equipmentListsearch();" plain="true" icon="icon-search">查询</a>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="rePrint();" plain="true" icon="icon-search">重打</a>
            </div>
        </div>
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
    function print(id) {
        $.getJSON("equipmentController.do?getPrintData", {id: id}, function (data) {
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
        $.getJSON("equipmentController.do?rePrint", {qrCode: qrCode}, function (data) {
            if (!!data) {
                printQRCode(data);
                $('#rp').window('close');
            } else {
                $.messager.alert('error', '无有效数据!', 'info');
            }
        })
    }
</script>