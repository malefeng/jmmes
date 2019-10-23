<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#supplierList').datagrid({
                idField: 'id',
                title: '供应商信息',
                url: 'supplierController.do?datagrid&field=id,supplierCode,supplierName,supplierAttr,supplierLegalPerson,supplierLinkman,supplierContactNumber,supplierContactAddress,remark,',
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
                    field: 'supplierCode',
                    title: '供应商代码',
                    width: 120,
                    sortable: true
                }, {field: 'supplierName', title: '供应商名称', width: 120, sortable: true}, {
                    field: 'supplierAttr',
                    title: '供应商属性',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${supplAttr}[value]}
                }, {
                    field: 'supplierLegalPerson',
                    title: '供应商法人',
                    width: 120,
                    sortable: true
                }, {
                    field: 'supplierLinkman',
                    title: '供应商联系人',
                    width: 120,
                    sortable: true
                }, {
                    field: 'supplierContactNumber',
                    title: '供应商联系电话',
                    width: 120,
                    sortable: true
                }, {field: 'supplierContactAddress', title: '供应商联系地址', width: 120, sortable: true}, {
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
                        href += "<a href='#'   class='ace_button'  onclick=delObj('supplierController.do?del&id=" + rec.id + "','supplierList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#supplierList").datagrid("clearChecked");
                    $("#supplierList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#supplierList');
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
                    gridname = 'supplierList';
                }
            });
            $('#supplierList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#supplierList').datagrid('getPager').pagination({
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

        function reloadsupplierList() {
            $('#supplierList').datagrid('reload');
        }

        function getsupplierListSelected(field) {
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

        function getsupplierListSelections(field) {
            var ids = [];
            var rows = $('#supplierList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#supplierList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#supplierList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#supplierList').datagrid('getColumnFields');
            }
            var cols = storage.get('supplierListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#supplierList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('supplierListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('supplierListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#supplierList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('supplierListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#supplierList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function supplierListsearch() {
            try {
                if (!$("#supplierListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#supplierList').datagrid('options').queryParams;
                $('#supplierListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#supplierList').datagrid({
                    url: 'supplierController.do?datagrid&field=id,supplierCode,supplierName,supplierAttr,supplierLegalPerson,supplierLinkman,supplierContactNumber,supplierContactAddress,remark,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#supplierList').datagrid({
                url: 'supplierController.do?datagrid&field=id,supplierCode,supplierName,supplierAttr,supplierLegalPerson,supplierLinkman,supplierContactNumber,supplierContactAddress,remark,',
                queryParams: jsonparams
            });
        }

        function supplierListsearchbox(value, name) {
            var queryParams = $('#supplierList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#supplierList').datagrid('reload');
        }

        $('#supplierListsearchbox').searchbox({
            searcher: function (value, name) {
                supplierListsearchbox(value, name);
            }, menu: '#supplierListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                supplierListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#supplierList').datagrid('options').queryParams;
            $('#supplierListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#supplierListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#supplierListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#supplierList').datagrid({
                url: 'supplierController.do?datagrid&field=id,supplierCode,supplierName,supplierAttr,supplierLegalPerson,supplierLinkman,supplierContactNumber,supplierContactAddress,remark,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="supplierList" toolbar="#supplierListtb"></table>
        <div id="supplierListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>供应商代码:<input type="text" name="supplierCode"></span>
                <span>供应商名称:<input type="text" name="supplierName"></span>
                <span>供应商属性:<input type="text" name="supplierAttr"></span>
                <span>供应商法人:<input type="text" name="supplierLegalPerson"></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="supplierListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','supplierController.do?addorupdate','supplierList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','supplierController.do?addorupdate','supplierList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','supplierController.do?addorupdate','supplierList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('supplierList',['supplierCode','supplierName','supplierAttr','supplierLegalPerson']);
    })
</script>