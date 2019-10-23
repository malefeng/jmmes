<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#repositoryList').datagrid({
                idField: 'id',
                title: '仓库信息',
                url: 'repositoryController.do?datagrid&field=id,repositoryCode,repositoryName,repositoryCapacity,repositoryUnitCode,repositoryType,repositoryPosition,remark,',
                fit: true,
                rownumbers: true,
                queryParams: {},
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
                    field: 'repositoryCode',
                    title: '仓库代码',
                    width: 120,
                    sortable: true
                }, {field: 'repositoryName', title: '仓库名称', width: 120, sortable: true}, {
                    field: 'repositoryCapacity',
                    title: '仓库容量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'repositoryUnitCode',
                    title: '仓库单位',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${housUnitDic}[value]}
                }, {
                    field: 'repositoryType',
                    title: '仓库类型',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${housTypeDic}[value]}
                }, {field: 'repositoryPosition', title: '仓库位置', width: 120, sortable: true}, {
                    field: 'remark',
                    title: '备注',
                    width: 120,
                    sortable: true
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('repositoryController.do?del&id=" + rec.id + "','repositoryList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#repositoryList").datagrid("clearChecked");
                    $("#repositoryList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#repositoryList');
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
                    gridname = 'repositoryList';
                }
            });
            $('#repositoryList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#repositoryList').datagrid('getPager').pagination({
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

        function reloadrepositoryList() {
            $('#repositoryList').datagrid('reload');
        }

        function getrepositoryListSelected(field) {
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

        function getrepositoryListSelections(field) {
            var ids = [];
            var rows = $('#repositoryList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#repositoryList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#repositoryList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#repositoryList').datagrid('getColumnFields');
            }
            var cols = storage.get('repositoryListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#repositoryList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('repositoryListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('repositoryListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#repositoryList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('repositoryListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#repositoryList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function repositoryListsearch() {
            try {
                if (!$("#repositoryListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#repositoryList').datagrid('options').queryParams;
                $('#repositoryListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#repositoryList').datagrid({
                    url: 'repositoryController.do?datagrid&field=id,repositoryCode,repositoryName,repositoryCapacity,repositoryUnitCode,repositoryType,repositoryPosition,remark,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#repositoryList').datagrid({
                url: 'repositoryController.do?datagrid&field=id,repositoryCode,repositoryName,repositoryCapacity,repositoryUnitCode,repositoryType,repositoryPosition,remark,',
                queryParams: jsonparams
            });
        }

        function repositoryListsearchbox(value, name) {
            var queryParams = $('#repositoryList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#repositoryList').datagrid('reload');
        }

        $('#repositoryListsearchbox').searchbox({
            searcher: function (value, name) {
                repositoryListsearchbox(value, name);
            }, menu: '#repositoryListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                repositoryListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#repositoryList').datagrid('options').queryParams;
            $('#repositoryListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#repositoryListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#repositoryListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#repositoryList').datagrid({
                url: 'repositoryController.do?datagrid&field=id,repositoryCode,repositoryName,repositoryCapacity,repositoryUnitCode,repositoryType,repositoryPosition,remark,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="repositoryList" toolbar="#repositoryListtb"></table>
        <div id="repositoryListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>仓库代码:<input type="text" name="repositoryCode"></span>
                <span>仓库名称:<input type="text" name="repositoryName"></span>
                <span>仓库类型:<t:dictSelect id="repositoryType" field="repositoryType" typeGroupCode="housType" readonly="true"></t:dictSelect></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="repositoryListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','repositoryController.do?addorupdate','repositoryList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','repositoryController.do?addorupdate','repositoryList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','repositoryController.do?addorupdate','repositoryList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('repositoryList',['repositoryCode','repositoryName','repositoryType']);
    })
</script>