<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#checkTaskList').datagrid({
                idField: 'id',
                title: '盘点任务',
                url: 'checkTaskController.do?datagrid&field=id,checkBatch,checkType,repositoryCode,repositoryName,rawMaterialCode,rawMaterialName,productCode,productName,checkStatus,checkTimePlan,checkTimeReal,checkPersonPlan,checkPersonReal,',
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
                    field: 'checkBatch',
                    title: '盘点批次号',
                    sortable: true
                }, {field: 'checkType', title: '盘点类型', sortable: true,
                    formatter: function (value){return ${checkType}[value]}}, {
                    field: 'repositoryCode',
                    title: '盘点仓库代码',
                    sortable: true
                }, {field: 'repositoryName', title: '盘点仓库名称', sortable: true}, {
                    field: 'rawMaterialCode',
                    title: '盘点原料代码',
                    sortable: true
                }, {field: 'rawMaterialName', title: '盘点原料名称', sortable: true}, {
                    field: 'productCode',
                    title: '盘点成品代码',
                    sortable: true
                }, {field: 'productName', title: '盘点成品名称', sortable: true}, {
                    field: 'checkStatus',
                    title: '盘点状态',
                    sortable: true,
                    formatter: function (value){return ${checkStaut}[value]}
                }, {
                    field: 'checkTimePlan', title: '计划盘点时间', sortable: true, formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {
                    field: 'checkTimeReal', title: '实际盘点时间', sortable: true, formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'checkPersonPlan', title: '计划盘点人', sortable: true,
                    formatter: function (value){return ${userDic}[value]}}, {
                    field: 'checkPersonReal',
                    title: '实际盘点人',
                    sortable: true,
                    formatter: function (value){return ${userDic}[value]}
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('checkTaskController.do?del&id=" + rec.id + "','checkTaskList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#checkTaskList").datagrid("clearChecked");
                    $("#checkTaskList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#checkTaskList');
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
                    gridname = 'checkTaskList';
                }
            });
            $('#checkTaskList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#checkTaskList').datagrid('getPager').pagination({
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

        function reloadcheckTaskList() {
            $('#checkTaskList').datagrid('reload');
        }

        function getcheckTaskListSelected(field) {
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

        function getcheckTaskListSelections(field) {
            var ids = [];
            var rows = $('#checkTaskList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#checkTaskList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#checkTaskList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#checkTaskList').datagrid('getColumnFields');
            }
            var cols = storage.get('checkTaskListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#checkTaskList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('checkTaskListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('checkTaskListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#checkTaskList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('checkTaskListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#checkTaskList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function checkTaskListsearch() {
            try {
                if (!$("#checkTaskListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#checkTaskList').datagrid('options').queryParams;
                $('#checkTaskListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#checkTaskList').datagrid({
                    url: 'checkTaskController.do?datagrid&field=id,checkBatch,checkType,repositoryCode,repositoryName,rawMaterialCode,rawMaterialName,productCode,productName,checkStatus,checkTimePlan,checkTimeReal,checkPersonPlan,checkPersonReal,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#checkTaskList').datagrid({
                url: 'checkTaskController.do?datagrid&field=id,checkBatch,checkType,repositoryCode,repositoryName,rawMaterialCode,rawMaterialName,productCode,productName,checkStatus,checkTimePlan,checkTimeReal,checkPersonPlan,checkPersonReal,',
                queryParams: jsonparams
            });
        }

        function checkTaskListsearchbox(value, name) {
            var queryParams = $('#checkTaskList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#checkTaskList').datagrid('reload');
        }

        $('#checkTaskListsearchbox').searchbox({
            searcher: function (value, name) {
                checkTaskListsearchbox(value, name);
            }, menu: '#checkTaskListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                checkTaskListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#checkTaskList').datagrid('options').queryParams;
            $('#checkTaskListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#checkTaskListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#checkTaskListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#checkTaskList').datagrid({
                url: 'checkTaskController.do?datagrid&field=id,checkBatch,checkType,repositoryCode,repositoryName,rawMaterialCode,rawMaterialName,productCode,productName,checkStatus,checkTimePlan,checkTimeReal,checkPersonPlan,checkPersonReal,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="checkTaskList" toolbar="#checkTaskListtb"></table>
        <div id="checkTaskListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>盘点批次号：<input type="text" name="checkBatch"></span>
                <span>盘点类型:<t:dictSelect field="checkType" typeGroupCode="check_type"  hasLabel="false" type="list"></t:dictSelect></span>
                <span>盘点仓库代码:<t:dictSelect field="repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_code" hasLabel="false" type="list"></t:dictSelect></span>
                <span>盘点原料代码:<t:dictSelect field="rawMaterialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></span>
                <span>盘点成品代码:<t:dictSelect field="finishedCode" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></span><br>
                <span>盘点状态:<t:dictSelect field="checkStatus" typeGroupCode="checkStaut" hasLabel="false" type="list"></t:dictSelect></span>
                <span>计划盘点时间:
                    <input type="text" name="checkTimePlan_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="checkTimePlan_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>实际盘点时间:
                    <input type="text" name="checkTimeReal_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="checkTimeReal_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="checkTaskListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','checkTaskController.do?addorupdate','checkTaskList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','checkTaskController.do?addorupdate','checkTaskList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','checkTaskController.do?addorupdate','checkTaskList','100%','100%')">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>