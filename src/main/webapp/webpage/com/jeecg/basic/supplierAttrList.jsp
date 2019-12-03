<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#supplierAttrList').datagrid({
                idField: 'id',
                title: '供应商属性',
                url: 'supplierAttrController.do?datagrid&field=id,code,name,',
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
                    field: 'code',
                    title: '代码',
                    width: 120,
                    sortable: true
                }, {field: 'name', title: '名称', width: 120, sortable: true}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('supplierAttrController.do?del&id=" + rec.id + "','supplierAttrList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#supplierAttrList").datagrid("clearChecked");
                    $("#supplierAttrList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#supplierAttrList');
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
                    gridname = 'supplierAttrList';
                }
            });
            $('#supplierAttrList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#supplierAttrList').datagrid('getPager').pagination({
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

        function reloadsupplierAttrList() {
            $('#supplierAttrList').datagrid('reload');
        }

        function getsupplierAttrListSelected(field) {
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

        function getsupplierAttrListSelections(field) {
            var ids = [];
            var rows = $('#supplierAttrList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#supplierAttrList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#supplierAttrList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#supplierAttrList').datagrid('getColumnFields');
            }
            var cols = storage.get('supplierAttrListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#supplierAttrList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('supplierAttrListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('supplierAttrListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#supplierAttrList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('supplierAttrListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#supplierAttrList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function supplierAttrListsearch() {
            try {
                if (!$("#supplierAttrListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#supplierAttrList').datagrid('options').queryParams;
                $('#supplierAttrListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#supplierAttrList').datagrid({
                    url: 'supplierAttrController.do?datagrid&field=id,code,name,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#supplierAttrList').datagrid({
                url: 'supplierAttrController.do?datagrid&field=id,code,name,',
                queryParams: jsonparams
            });
        }

        function supplierAttrListsearchbox(value, name) {
            var queryParams = $('#supplierAttrList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#supplierAttrList').datagrid('reload');
        }

        $('#supplierAttrListsearchbox').searchbox({
            searcher: function (value, name) {
                supplierAttrListsearchbox(value, name);
            }, menu: '#supplierAttrListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                supplierAttrListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#supplierAttrList').datagrid('options').queryParams;
            $('#supplierAttrListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#supplierAttrListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#supplierAttrListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#supplierAttrList').datagrid({
                url: 'supplierAttrController.do?datagrid&field=id,code,name,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="supplierAttrList" toolbar="#supplierAttrListtb"></table>
        <div id="supplierAttrListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>代码:<input type="text" name="code"></span>
                <span>名称:<input type="text" name="name"></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="supplierAttrListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','supplierAttrController.do?addorupdate','supplierAttrList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','supplierAttrController.do?addorupdate','supplierAttrList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','supplierAttrController.do?addorupdate','supplierAttrList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>