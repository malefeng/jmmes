<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#finishedInspectItemList').datagrid({
                idField: 'id',
                title: '成品检测',
                url: 'finishedInspectItemController.do?datagrid&field=id,status,batchNo,sysOrgCode,sysCompanyCode,bpmStatus,finishedCode,finishedName,salesOrderNumber,productionDispatchingNumber,inspectLogSheet,result,count,qualifiedCount,unqualifiedCount,',
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
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true},
                    {field: 'finishedCode', title: '成品编号', sortable: true}, {
                    field: 'finishedName',
                    title: '成品名称',
                    sortable: true
                }, {
                    field: 'productionDispatchingNumber',
                    title: '生产派工单号',
                    sortable: true
                }, {field: 'inspectLogSheet', title: '检验记录表', sortable: true},
                    {
                    field: 'status',
                    title: '检验状态',
                    sortable: true,
                    formatter:function(val){
                        return ${checkState}[val]
                    }
                },
                    {
                    field: 'result',
                    title: '检验结果',
                    sortable: true,
                    formatter:function(val){
                        return ${inspeRes}[val]
                    }
                },
                    {field: 'batchNo', title: '批号', sortable: true},
                    {field: 'count', title: '总数量', sortable: true},
                    {
                    field: 'qualifiedCount',
                    title: '合格数量',
                    sortable: true
                }, {field: 'unqualifiedCount', title: '不合格数量', sortable: true}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('finishedInspectItemController.do?del&id=" + rec.id + "','finishedInspectItemList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#finishedInspectItemList").datagrid("clearChecked");
                    $("#finishedInspectItemList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#finishedInspectItemList');
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
                    gridname = 'finishedInspectItemList';
                }
            });
            $('#finishedInspectItemList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#finishedInspectItemList').datagrid('getPager').pagination({
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

        function reloadfinishedInspectItemList() {
            $('#finishedInspectItemList').datagrid('reload');
        }

        function getfinishedInspectItemListSelected(field) {
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

        function getfinishedInspectItemListSelections(field) {
            var ids = [];
            var rows = $('#finishedInspectItemList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#finishedInspectItemList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#finishedInspectItemList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#finishedInspectItemList').datagrid('getColumnFields');
            }
            var cols = storage.get('finishedInspectItemListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#finishedInspectItemList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('finishedInspectItemListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('finishedInspectItemListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#finishedInspectItemList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('finishedInspectItemListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#finishedInspectItemList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function finishedInspectItemListsearch() {
            try {
                if (!$("#finishedInspectItemListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#finishedInspectItemList').datagrid('options').queryParams;
                $('#finishedInspectItemListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#finishedInspectItemList').datagrid({
                    url: 'finishedInspectItemController.do?datagrid&field=id,status,batchNo,sysOrgCode,sysCompanyCode,bpmStatus,finishedCode,finishedName,salesOrderNumber,productionDispatchingNumber,inspectLogSheet,result,count,qualifiedCount,unqualifiedCount,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#finishedInspectItemList').datagrid({
                url: 'finishedInspectItemController.do?datagrid&field=id,status,batchNo,sysOrgCode,sysCompanyCode,bpmStatus,finishedCode,finishedName,salesOrderNumber,productionDispatchingNumber,inspectLogSheet,result,count,qualifiedCount,unqualifiedCount,',
                queryParams: jsonparams
            });
        }

        function finishedInspectItemListsearchbox(value, name) {
            var queryParams = $('#finishedInspectItemList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#finishedInspectItemList').datagrid('reload');
        }

        $('#finishedInspectItemListsearchbox').searchbox({
            searcher: function (value, name) {
                finishedInspectItemListsearchbox(value, name);
            }, menu: '#finishedInspectItemListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                finishedInspectItemListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#finishedInspectItemList').datagrid('options').queryParams;
            $('#finishedInspectItemListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#finishedInspectItemListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedInspectItemListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedInspectItemList').datagrid({
                url: 'finishedInspectItemController.do?datagrid&field=id,status,batchNo,sysOrgCode,sysCompanyCode,bpmStatus,finishedCode,finishedName,salesOrderNumber,productionDispatchingNumber,inspectLogSheet,result,count,qualifiedCount,unqualifiedCount,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="finishedInspectItemList" toolbar="#finishedInspectItemListtb"></table>
        <div id="finishedInspectItemListtb" style="padding:3px; height: auto">
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
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="finishedInspectItemListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','finishedInspectItemController.do?addorupdate','finishedInspectItemList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','finishedInspectItemController.do?addorupdate','finishedInspectItemList','100%','100%')">编辑</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>