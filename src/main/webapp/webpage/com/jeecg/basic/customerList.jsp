<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#customerList').datagrid({
                idField: 'id',
                title: '客户信息',
                url: 'customerController.do?datagrid&field=id,customerCode,customerName,customerLegalPerson,customerLinkman,customerContactNumber,customerContactAddress,remark,',
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
                    field: 'customerCode',
                    title: '客户代码',
                    width: 120,
                    sortable: true
                }, {field: 'customerName', title: '客户名称', width: 120, sortable: true}, {
                    field: 'customerLegalPerson',
                    title: '客户法人',
                    width: 120,
                    sortable: true
                }, {
                    field: 'customerLinkman',
                    title: '客户联系人',
                    width: 120,
                    sortable: true
                }, {
                    field: 'customerContactNumber',
                    title: '客户联系电话',
                    width: 120,
                    sortable: true
                }, {field: 'customerContactAddress', title: '客户联系地址', width: 120, sortable: true}, {
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
                        href += "<a href='#'   class='ace_button'  onclick=delObj('customerController.do?del&id=" + rec.id + "','customerList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#customerList").datagrid("clearChecked");
                    $("#customerList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#customerList');
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
                    gridname = 'customerList';
                }
            });
            $('#customerList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#customerList').datagrid('getPager').pagination({
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

        function reloadcustomerList() {
            $('#customerList').datagrid('reload');
        }

        function getcustomerListSelected(field) {
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

        function getcustomerListSelections(field) {
            var ids = [];
            var rows = $('#customerList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#customerList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#customerList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#customerList').datagrid('getColumnFields');
            }
            var cols = storage.get('customerListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#customerList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('customerListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('customerListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#customerList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('customerListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#customerList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function customerListsearch() {
            try {
                if (!$("#customerListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#customerList').datagrid('options').queryParams;
                $('#customerListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#customerList').datagrid({
                    url: 'customerController.do?datagrid&field=id,customerCode,customerName,customerLegalPerson,customerLinkman,customerContactNumber,customerContactAddress,remark,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#customerList').datagrid({
                url: 'customerController.do?datagrid&field=id,customerCode,customerName,customerLegalPerson,customerLinkman,customerContactNumber,customerContactAddress,remark,',
                queryParams: jsonparams
            });
        }

        function customerListsearchbox(value, name) {
            var queryParams = $('#customerList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#customerList').datagrid('reload');
        }

        $('#customerListsearchbox').searchbox({
            searcher: function (value, name) {
                customerListsearchbox(value, name);
            }, menu: '#customerListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                customerListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#customerList').datagrid('options').queryParams;
            $('#customerListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#customerListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#customerListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#customerList').datagrid({
                url: 'customerController.do?datagrid&field=id,customerCode,customerName,customerLegalPerson,customerLinkman,customerContactNumber,customerContactAddress,remark,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="customerList" toolbar="#customerListtb"></table>
        <div id="customerListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>客户代码:<input type="text" id="customerCode"></span>
                <span>客户名称:<input type="text" id="customerName"></span>
                <span>客户法人:<input type="text" id="customerLegalPerson"></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="customerListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','customerController.do?addorupdate','customerList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','customerController.do?addorupdate','customerList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','customerController.do?addorupdate','customerList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('customerList',['customerCode','customerName','customerLegalPerson']);
    })
</script>