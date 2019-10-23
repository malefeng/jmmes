<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#semiFinishedProductList').datagrid({
                idField: 'id',
                title: '半成品信息',
                url: 'semiFinishedProductController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,ffCheckTemp,',
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
                    field: 'semiFinishedCode',
                    title: '半成品代码',
                    width: 120,
                    sortable: true
                }, {field: 'semiFinishedName', title: '半成品名称', width: 120, sortable: true}, {
                    field: 'materialType',
                    title: '物料类型',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${matTypeDic}[value]}
                }, {field: 'semiFinishedSize', title: '规格', width: 120, sortable: true}, {
                    field: 'semiFinishedNumber',
                    title: '数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'semiFinishedUnitCode',
                    title: '单位',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${unitDic}[value]}
                }, {field: 'ffCheckTemp', title: '首末检模板', width: 120, sortable: true}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('semiFinishedProductController.do?del&id=" + rec.id + "','semiFinishedProductList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#semiFinishedProductList").datagrid("clearChecked");
                    $("#semiFinishedProductList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#semiFinishedProductList');
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
                    gridname = 'semiFinishedProductList';
                }
            });
            $('#semiFinishedProductList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#semiFinishedProductList').datagrid('getPager').pagination({
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

        function reloadsemiFinishedProductList() {
            $('#semiFinishedProductList').datagrid('reload');
        }

        function getsemiFinishedProductListSelected(field) {
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

        function getsemiFinishedProductListSelections(field) {
            var ids = [];
            var rows = $('#semiFinishedProductList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#semiFinishedProductList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#semiFinishedProductList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#semiFinishedProductList').datagrid('getColumnFields');
            }
            var cols = storage.get('semiFinishedProductListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#semiFinishedProductList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('semiFinishedProductListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('semiFinishedProductListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#semiFinishedProductList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('semiFinishedProductListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#semiFinishedProductList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function semiFinishedProductListsearch() {
            try {
                if (!$("#semiFinishedProductListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#semiFinishedProductList').datagrid('options').queryParams;
                $('#semiFinishedProductListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#semiFinishedProductList').datagrid({
                    url: 'semiFinishedProductController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,ffCheckTemp,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#semiFinishedProductList').datagrid({
                url: 'semiFinishedProductController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,ffCheckTemp,',
                queryParams: jsonparams
            });
        }

        function semiFinishedProductListsearchbox(value, name) {
            var queryParams = $('#semiFinishedProductList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#semiFinishedProductList').datagrid('reload');
        }

        $('#semiFinishedProductListsearchbox').searchbox({
            searcher: function (value, name) {
                semiFinishedProductListsearchbox(value, name);
            }, menu: '#semiFinishedProductListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                semiFinishedProductListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#semiFinishedProductList').datagrid('options').queryParams;
            $('#semiFinishedProductListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#semiFinishedProductListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductList').datagrid({
                url: 'semiFinishedProductController.do?datagrid&field=id,semiFinishedCode,semiFinishedName,materialType,semiFinishedSize,semiFinishedNumber,semiFinishedUnitCode,ffCheckTemp,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="semiFinishedProductList" toolbar="#semiFinishedProductListtb"></table>
        <div id="semiFinishedProductListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>半成品代码:<input type="text" name="semiFinishedCode"></span>
                <span>半成品名称:<input type="text" name="semiFinishedName"></span>
                <span>物料类型:<t:dictSelect id="materialType" field="materialType" typeGroupCode="matType" readonly="true"></t:dictSelect></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="semiFinishedProductListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','semiFinishedProductController.do?addorupdate','semiFinishedProductList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','semiFinishedProductController.do?addorupdate','semiFinishedProductList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','semiFinishedProductController.do?addorupdate','semiFinishedProductList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('semiFinishedProductList',['semiFinishedCode','semiFinishedName','materialType']);
    })
</script>