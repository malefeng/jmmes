<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#commonCarrierList').datagrid({
                idField: 'id',
                title: '承运商信息',
                url: 'commonCarrierController.do?datagrid&field=id,transCode,transName,transLegalPerson,transLinkman,transContactNumben,transContactAddress,',
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
                    field: 'transCode',
                    title: '承运商代码',
                    width: 120,
                    sortable: true
                }, {field: 'transName', title: '承运商名称', width: 120, sortable: true}, {
                    field: 'transLegalPerson',
                    title: '承运商法人',
                    width: 120,
                    sortable: true
                }, {field: 'transLinkman', title: '承运商联系人', width: 120, sortable: true}, {
                    field: 'transContactNumben',
                    title: '承运商联系电话',
                    width: 120,
                    sortable: true
                }, {field: 'transContactAddress', title: '承运商联系地址', width: 120, sortable: true}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('commonCarrierController.do?del&id=" + rec.id + "','commonCarrierList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#commonCarrierList").datagrid("clearChecked");
                    $("#commonCarrierList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#commonCarrierList');
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
                    gridname = 'commonCarrierList';
                }
            });
            $('#commonCarrierList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#commonCarrierList').datagrid('getPager').pagination({
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

        function reloadcommonCarrierList() {
            $('#commonCarrierList').datagrid('reload');
        }

        function getcommonCarrierListSelected(field) {
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

        function getcommonCarrierListSelections(field) {
            var ids = [];
            var rows = $('#commonCarrierList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#commonCarrierList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#commonCarrierList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#commonCarrierList').datagrid('getColumnFields');
            }
            var cols = storage.get('commonCarrierListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#commonCarrierList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('commonCarrierListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('commonCarrierListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#commonCarrierList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('commonCarrierListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#commonCarrierList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function commonCarrierListsearch() {
            try {
                if (!$("#commonCarrierListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#commonCarrierList').datagrid('options').queryParams;
                $('#commonCarrierListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#commonCarrierList').datagrid({
                    url: 'commonCarrierController.do?datagrid&field=id,transCode,transName,transLegalPerson,transLinkman,transContactNumben,transContactAddress,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#commonCarrierList').datagrid({
                url: 'commonCarrierController.do?datagrid&field=id,transCode,transName,transLegalPerson,transLinkman,transContactNumben,transContactAddress,',
                queryParams: jsonparams
            });
        }

        function commonCarrierListsearchbox(value, name) {
            var queryParams = $('#commonCarrierList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#commonCarrierList').datagrid('reload');
        }

        $('#commonCarrierListsearchbox').searchbox({
            searcher: function (value, name) {
                commonCarrierListsearchbox(value, name);
            }, menu: '#commonCarrierListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                commonCarrierListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#commonCarrierList').datagrid('options').queryParams;
            $('#commonCarrierListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#commonCarrierListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#commonCarrierListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#commonCarrierList').datagrid({
                url: 'commonCarrierController.do?datagrid&field=id,transCode,transName,transLegalPerson,transLinkman,transContactNumben,transContactAddress,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="commonCarrierList" toolbar="#commonCarrierListtb"></table>
        <div id="commonCarrierListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>承运商代码:<input type="text" name="transCode"></span>
                <span>承运商名称:<input type="text" name="transName"></span>
                <span>承运商法人:<input type="text" name="transLegalPerson"></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="commonCarrierListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','commonCarrierController.do?addorupdate','commonCarrierList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','commonCarrierController.do?addorupdate','commonCarrierList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','commonCarrierController.do?addorupdate','commonCarrierList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('commonCarrierList',['transCode','transName','transLegalPerson']);
    })
</script>