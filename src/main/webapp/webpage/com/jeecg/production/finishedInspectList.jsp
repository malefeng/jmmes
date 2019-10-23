<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#finishedInspectList').datagrid({
                idField: 'id',
                title: '成品首末检',
                url: 'finishedInspectController.do?datagrid&field=id,finishedCode,finishedName,salesOrderNumber,productionOrderNumber,inspectLogSheet,',
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
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true}, {
                    field: 'finishedCode',
                    title: '成品编号',
                    sortable: true
                }, {field: 'finishedName', title: '成品名称', sortable: true},
                    {field: 'productionOrderNumber', title: '生产订单号', sortable: true}, {
                    field: 'inspectLogSheet',
                    title: '首末检记录表',
                    sortable: true
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('finishedInspectController.do?del&id=" + rec.id + "','finishedInspectList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#finishedInspectList").datagrid("clearChecked");
                    $("#finishedInspectList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#finishedInspectList');
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
                    gridname = 'finishedInspectList';
                }
            });
            $('#finishedInspectList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#finishedInspectList').datagrid('getPager').pagination({
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

        function reloadfinishedInspectList() {
            $('#finishedInspectList').datagrid('reload');
        }

        function getfinishedInspectListSelected(field) {
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

        function getfinishedInspectListSelections(field) {
            var ids = [];
            var rows = $('#finishedInspectList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#finishedInspectList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#finishedInspectList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#finishedInspectList').datagrid('getColumnFields');
            }
            var cols = storage.get('finishedInspectListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#finishedInspectList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('finishedInspectListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('finishedInspectListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#finishedInspectList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('finishedInspectListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#finishedInspectList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function finishedInspectListsearch() {
            try {
                if (!$("#finishedInspectListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#finishedInspectList').datagrid('options').queryParams;
                $('#finishedInspectListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#finishedInspectList').datagrid({
                    url: 'finishedInspectController.do?datagrid&field=id,finishedCode,finishedName,salesOrderNumber,productionOrderNumber,inspectLogSheet,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#finishedInspectList').datagrid({
                url: 'finishedInspectController.do?datagrid&field=id,finishedCode,finishedName,salesOrderNumber,productionOrderNumber,inspectLogSheet,',
                queryParams: jsonparams
            });
        }

        function finishedInspectListsearchbox(value, name) {
            var queryParams = $('#finishedInspectList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#finishedInspectList').datagrid('reload');
        }

        $('#finishedInspectListsearchbox').searchbox({
            searcher: function (value, name) {
                finishedInspectListsearchbox(value, name);
            }, menu: '#finishedInspectListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                finishedInspectListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#finishedInspectList').datagrid('options').queryParams;
            $('#finishedInspectListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#finishedInspectListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedInspectListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedInspectList').datagrid({
                url: 'finishedInspectController.do?datagrid&field=id,finishedCode,finishedName,salesOrderNumber,productionOrderNumber,inspectLogSheet,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="finishedInspectList" toolbar="#finishedInspectListtb"></table>
        <div id="finishedInspectListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>成品编号:<input type="text" name="finishedCode"></span>
                <span>成品名称:<input type="text" name="finishedName"></span>
                <span>生产订单号:<input type="text" name="productionOrderNumber"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="finishedInspectListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addNew()">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','finishedInspectController.do?addorupdate','finishedInspectList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','finishedInspectController.do?addorupdate','finishedInspectList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    //成品编号长度
    var finishedSerinoLen = 12;
    function addNew(){
        $.messager.prompt("","请输入成品首末检编号",function(data){
            if (data != null) {
                $.ajax({
                    type: 'get',
                    url: "finishedProductionController.do?get&finishedSerino="+data.substr(0,finishedSerinoLen),
                    dataType: 'json',
                    beforeSend: function () {
                        loadMask();
                    },
                    complete: function () {
                        disLoadMask();
                    },
                    success: function (data) {
                        if(!!data){
                            add('录入','finishedInspectController.do?addorupdate&finishedCode='+data.finishedSerino+"&finishedName="+data.finishedName+"&productionOrderNumber="+data.productionOrderNumber,'finishedInspectList',null,null)
                        }else{
                            $.messager.show({
                                msg:'该成品不存在或未投产',
                                showType:'slide',
                                showSpeed:'200',
                                style:{color:'red'}
                            });
                        }
                    }
                });
            }
        });
    }
</script>