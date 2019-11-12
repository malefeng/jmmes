<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#materialWarehousReturnList').datagrid({
                idField: 'id',
                title: '原料退料列表',
                url: 'materialWarehousReturnController.do?datagrid&field=id,materialSerino,materialCode,materialName,batchNumber,returnedPerson,returnedDate,returnedReason,returnedNumber,',
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
                    field: 'materialSerino',
                    title: '原料编号',
                    width: 120,
                    sortable: true
                }, {field: 'materialCode', title: '原料代码', width: 120, sortable: true}, {
                    field: 'materialName',
                    title: '原料名称',
                    width: 120,
                    sortable: true
                }, {field: 'batchNumber', title: '原料批次', width: 120, sortable: true}, {
                    field: 'returnedPerson',
                    title: '退料人',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${userDic}[value]}
                }, {
                    field: 'returnedDate',
                    title: '退料时间',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'returnedReason', title: '退料原因', width: 120, sortable: true,
                    formatter: function (value){return ${rmReason}[value]}}, {
                    field: 'returnedNumber',
                    title: '退料数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('materialWarehousReturnController.do?del&id=" + rec.id + "','materialWarehousReturnList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#materialWarehousReturnList").datagrid("clearChecked");
                    $("#materialWarehousReturnList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#materialWarehousReturnList');
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
                    gridname = 'materialWarehousReturnList';
                }
            });
            $('#materialWarehousReturnList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#materialWarehousReturnList').datagrid('getPager').pagination({
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

        function reloadmaterialWarehousReturnList() {
            $('#materialWarehousReturnList').datagrid('reload');
        }

        function getmaterialWarehousReturnListSelected(field) {
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

        function getmaterialWarehousReturnListSelections(field) {
            var ids = [];
            var rows = $('#materialWarehousReturnList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#materialWarehousReturnList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#materialWarehousReturnList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#materialWarehousReturnList').datagrid('getColumnFields');
            }
            var cols = storage.get('materialWarehousReturnListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#materialWarehousReturnList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('materialWarehousReturnListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('materialWarehousReturnListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#materialWarehousReturnList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('materialWarehousReturnListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#materialWarehousReturnList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function materialWarehousReturnListsearch() {
            try {
                if (!$("#materialWarehousReturnListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#materialWarehousReturnList').datagrid('options').queryParams;
                $('#materialWarehousReturnListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#materialWarehousReturnList').datagrid({
                    url: 'materialWarehousReturnController.do?datagrid&field=id,materialSerino,materialCode,materialName,batchNumber,returnedPerson,returnedDate,returnedReason,returnedNumber,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#materialWarehousReturnList').datagrid({
                url: 'materialWarehousReturnController.do?datagrid&field=id,materialSerino,materialCode,materialName,batchNumber,returnedPerson,returnedDate,returnedReason,returnedNumber,',
                queryParams: jsonparams
            });
        }

        function materialWarehousReturnListsearchbox(value, name) {
            var queryParams = $('#materialWarehousReturnList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#materialWarehousReturnList').datagrid('reload');
        }

        $('#materialWarehousReturnListsearchbox').searchbox({
            searcher: function (value, name) {
                materialWarehousReturnListsearchbox(value, name);
            }, menu: '#materialWarehousReturnListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                materialWarehousReturnListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#materialWarehousReturnList').datagrid('options').queryParams;
            $('#materialWarehousReturnListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#materialWarehousReturnListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#materialWarehousReturnListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#materialWarehousReturnList').datagrid({
                url: 'materialWarehousReturnController.do?datagrid&field=id,materialSerino,materialCode,materialName,batchNumber,returnedPerson,returnedDate,returnedReason,returnedNumber,',
                pageNumber: 1
            });
        }
        </script>
        <table width="100%" id="materialWarehousReturnList" toolbar="#materialWarehousReturnListtb"></table>
        <div id="materialWarehousReturnListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>原料编号：<input type="text" name="materialSerino"></span>
                <span>原料代码:<t:dictSelect field="materialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></span>
                <span>原料名称:<t:dictSelect field="materialName" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></span>
                <span>原料批次:<input type="text" name="productBatch"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="materialWarehousReturnListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','materialWarehousReturnController.do?addorupdate','materialWarehousReturnList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','materialWarehousReturnController.do?addorupdate','materialWarehousReturnList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','materialWarehousReturnController.do?addorupdate','materialWarehousReturnList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>