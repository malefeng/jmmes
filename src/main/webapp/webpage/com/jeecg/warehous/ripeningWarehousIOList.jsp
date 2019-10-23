<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#ripeningWarehousIOList').datagrid({
                idField: 'id',
                title: '熟成出入库列表',
                url: 'ripeningWarehousIOController.do?datagrid&field=id,productSerino,productCode,productName,ripeningProType,productBatch,productSize,productNumber,unit,warehousePositionCode,warehouseSpaceCode,ripeningStoreType,warehousingPersonCode,warehousingDate,warehouseOutPersonCode,warehouseOutDate,productionOrderNumber,takeMaterilNumber,',
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
                    field: 'productSerino',
                    title: '产品编号',
                    width: 120,
                    sortable: true
                }, {field: 'ripeningProType', title: '熟成产品类型', width: 120, sortable: true,
                    formatter: function (value){return ${finshTypeDic}[value]}}, {
                    field: 'productBatch',
                    title: '产品批次',
                    width: 120,
                    sortable: true
                }, {field: 'productSize', title: '规格型号', width: 120, sortable: true}, {
                    field: 'productNumber',
                    title: '数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'ripeningStoreType',
                    title: '熟成库存状态',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${finishioStateDic}[value]}
                }, {
                    field: 'warehousingPersonCode',
                    title: '入库人',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${userDic}[value]}
                }, {
                    field: 'warehousingDate',
                    title: '入库时间',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {
                    field: 'warehouseOutPersonCode',
                    title: '出库人',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${userDic}[value]}
                }, {
                    field: 'warehouseOutDate',
                    title: '出库时间',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('ripeningWarehousIOController.do?del&id=" + rec.id + "','ripeningWarehousIOList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#ripeningWarehousIOList").datagrid("clearChecked");
                    $("#ripeningWarehousIOList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#ripeningWarehousIOList');
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
                    gridname = 'ripeningWarehousIOList';
                }
            });
            $('#ripeningWarehousIOList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#ripeningWarehousIOList').datagrid('getPager').pagination({
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

        function reloadripeningWarehousIOList() {
            $('#ripeningWarehousIOList').datagrid('reload');
        }

        function getripeningWarehousIOListSelected(field) {
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

        function getripeningWarehousIOListSelections(field) {
            var ids = [];
            var rows = $('#ripeningWarehousIOList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#ripeningWarehousIOList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#ripeningWarehousIOList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#ripeningWarehousIOList').datagrid('getColumnFields');
            }
            var cols = storage.get('ripeningWarehousIOListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#ripeningWarehousIOList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('ripeningWarehousIOListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('ripeningWarehousIOListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#ripeningWarehousIOList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('ripeningWarehousIOListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#ripeningWarehousIOList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function ripeningWarehousIOListsearch() {
            try {
                if (!$("#ripeningWarehousIOListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#ripeningWarehousIOList').datagrid('options').queryParams;
                $('#ripeningWarehousIOListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#ripeningWarehousIOList').datagrid({
                    url: 'ripeningWarehousIOController.do?datagrid&field=id,productSerino,productCode,productName,ripeningProType,productBatch,productSize,productNumber,unit,warehousePositionCode,warehouseSpaceCode,ripeningStoreType,warehousingPersonCode,warehousingDate,warehouseOutPersonCode,warehouseOutDate,productionOrderNumber,takeMaterilNumber,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#ripeningWarehousIOList').datagrid({
                url: 'ripeningWarehousIOController.do?datagrid&field=id,productSerino,productCode,productName,ripeningProType,productBatch,productSize,productNumber,unit,warehousePositionCode,warehouseSpaceCode,ripeningStoreType,warehousingPersonCode,warehousingDate,warehouseOutPersonCode,warehouseOutDate,productionOrderNumber,takeMaterilNumber,',
                queryParams: jsonparams
            });
        }

        function ripeningWarehousIOListsearchbox(value, name) {
            var queryParams = $('#ripeningWarehousIOList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#ripeningWarehousIOList').datagrid('reload');
        }

        $('#ripeningWarehousIOListsearchbox').searchbox({
            searcher: function (value, name) {
                ripeningWarehousIOListsearchbox(value, name);
            }, menu: '#ripeningWarehousIOListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                ripeningWarehousIOListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#ripeningWarehousIOList').datagrid('options').queryParams;
            $('#ripeningWarehousIOListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#ripeningWarehousIOListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#ripeningWarehousIOListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#ripeningWarehousIOList').datagrid({
                url: 'ripeningWarehousIOController.do?datagrid&field=id,productSerino,productCode,productName,ripeningProType,productBatch,productSize,productNumber,unit,warehousePositionCode,warehouseSpaceCode,ripeningStoreType,warehousingPersonCode,warehousingDate,warehouseOutPersonCode,warehouseOutDate,productionOrderNumber,takeMaterilNumber,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="ripeningWarehousIOList" toolbar="#ripeningWarehousIOListtb"></table>
        <div id="ripeningWarehousIOListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>产品编号：<input type="text" name="productSerino"></span>
                <span>产品代码:<input type="text" name="productCode"></span>
                <span>产品名称:<input type="text" name="productName"></span>
                <span>产品批次:<input type="text" name="productBatch"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="ripeningWarehousIOListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','ripeningWarehousIOController.do?addorupdate','ripeningWarehousIOList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','ripeningWarehousIOController.do?addorupdate','ripeningWarehousIOList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','ripeningWarehousIOController.do?addorupdate','ripeningWarehousIOList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>