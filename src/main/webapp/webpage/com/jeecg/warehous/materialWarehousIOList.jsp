<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#materialWarehousIOList').datagrid({
                idField: 'id',
                title: '原料出入库列表',
                url: 'materialWarehousIOController.do?datagrid&field=id,materialSerino,materialCode,materialName,materialSize,batchNumber,warehousingNumber,warehousingPersonCode,warehousingDate,warehousePositionCode,warehouseSpaceCode,warehouseOutNumber,virtualRepositoryNumber,unit,virtualRepository,warehouseOutPersonCode,warehouseOutDate,ioType,receivingOrderNumber,purchaseOrderNumber,salesOrderNumber,supplierCode,',
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
                    sortable: true
                }, {field: 'materialCode', title: '原料代码', sortable: true}, {
                    field: 'materialName',
                    title: '原料名称',
                    sortable: true
                }, {field: 'materialSize', title: '规格型号', sortable: true}, {
                    field: 'batchNumber',
                    title: '批次',
                    sortable: true
                }, {field: 'warehousingNumber', title: '入库数量', sortable: true}, {
                    field: 'warehousingPersonCode',
                    title: '入库人',
                    sortable: true,
                    formatter: function (value){return ${userDic}[value]}
                }, {
                    field: 'warehousingDate', title: '入库时间', sortable: true, formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'warehousePositionCode', title: '入库仓库', sortable: true,
                    formatter: function (value){return ${repostDic}[value]}}, {
                    field: 'warehouseSpaceCode',
                    title: '入库库位',
                    sortable: true,
                    formatter: function (value){return ${storageDic}[value]}
                }, {field: 'warehouseOutNumber', title: '当前库存', sortable: true}, {
                    field: 'virtualRepositoryNumber',
                    title: '虚拟仓库数量',
                    sortable: true
                }, {field: 'unit', title: '单位', sortable: true,
                    formatter: function (value){return ${unitDic}[value]}}, {
                    field: 'virtualRepository',
                    title: '虚拟仓库',
                    sortable: true,
                    formatter: function (value){return ${repostDic}[value]}
                }, {field: 'warehouseOutPersonCode', title: '最新出库人', sortable: true,
                    formatter: function (value){return ${userDic}[value]}}, {
                    field: 'warehouseOutDate',
                    title: '最新出库时间',
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'ioType', title: '库存状态', sortable: true,
                    formatter: function (value){
                        return ${ioStateDic}[value]
                    }
                    }, {
                    field: 'receivingOrderNumber',
                    title: '收料单号',
                    sortable: true
                }, {field: 'purchaseOrderNumber', title: '收料单采购订单号', sortable: true}, {
                    field: 'salesOrderNumber',
                    title: '收料单销售订单号',
                    sortable: true
                }, {field: 'supplierCode', title: '供应商', sortable: true,
                    formatter: function (value){return ${suplDic}[value]}}, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('materialWarehousIOController.do?del&id=" + rec.id + "','materialWarehousIOList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#materialWarehousIOList").datagrid("clearChecked");
                    $("#materialWarehousIOList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#materialWarehousIOList');
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
                    gridname = 'materialWarehousIOList';
                }
            });
            $('#materialWarehousIOList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#materialWarehousIOList').datagrid('getPager').pagination({
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

        function reloadmaterialWarehousIOList() {
            $('#materialWarehousIOList').datagrid('reload');
        }

        function getmaterialWarehousIOListSelected(field) {
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

        function getmaterialWarehousIOListSelections(field) {
            var ids = [];
            var rows = $('#materialWarehousIOList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#materialWarehousIOList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#materialWarehousIOList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#materialWarehousIOList').datagrid('getColumnFields');
            }
            var cols = storage.get('materialWarehousIOListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#materialWarehousIOList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('materialWarehousIOListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('materialWarehousIOListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#materialWarehousIOList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('materialWarehousIOListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#materialWarehousIOList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function materialWarehousIOListsearch() {
            try {
                if (!$("#materialWarehousIOListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#materialWarehousIOList').datagrid('options').queryParams;
                $('#materialWarehousIOListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#materialWarehousIOList').datagrid({
                    url: 'materialWarehousIOController.do?datagrid&field=id,materialSerino,materialCode,materialName,materialSize,batchNumber,warehousingNumber,warehousingPersonCode,warehousingDate,warehousePositionCode,warehouseSpaceCode,warehouseOutNumber,virtualRepositoryNumber,unit,virtualRepository,warehouseOutPersonCode,warehouseOutDate,ioType,receivingOrderNumber,purchaseOrderNumber,salesOrderNumber,supplierCode,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#materialWarehousIOList').datagrid({
                url: 'materialWarehousIOController.do?datagrid&field=id,materialSerino,materialCode,materialName,materialSize,batchNumber,warehousingNumber,warehousingPersonCode,warehousingDate,warehousePositionCode,warehouseSpaceCode,warehouseOutNumber,virtualRepositoryNumber,unit,virtualRepository,warehouseOutPersonCode,warehouseOutDate,ioType,receivingOrderNumber,purchaseOrderNumber,salesOrderNumber,supplierCode,',
                queryParams: jsonparams
            });
        }

        function materialWarehousIOListsearchbox(value, name) {
            var queryParams = $('#materialWarehousIOList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#materialWarehousIOList').datagrid('reload');
        }

        $('#materialWarehousIOListsearchbox').searchbox({
            searcher: function (value, name) {
                materialWarehousIOListsearchbox(value, name);
            }, menu: '#materialWarehousIOListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                materialWarehousIOListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#materialWarehousIOList').datagrid('options').queryParams;
            $('#materialWarehousIOListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#materialWarehousIOListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#materialWarehousIOListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#materialWarehousIOList').datagrid({
                url: 'materialWarehousIOController.do?datagrid&field=id,materialSerino,materialCode,materialName,materialSize,batchNumber,warehousingNumber,warehousingPersonCode,warehousingDate,warehousePositionCode,warehouseSpaceCode,warehouseOutNumber,virtualRepositoryNumber,unit,virtualRepository,warehouseOutPersonCode,warehouseOutDate,ioType,receivingOrderNumber,purchaseOrderNumber,salesOrderNumber,supplierCode,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="materialWarehousIOList" toolbar="#materialWarehousIOListtb"></table>
        <div id="materialWarehousIOListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>原料编号：<input type="text" name="materialSerino"></span>
                <span>原料代码:<input type="text" name="materialCode"></span>
                <span>原料名称:<input type="text" name="materialName"></span>
                <span>批次:<input type="text" name="batchNumber"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="materialWarehousIOListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','materialWarehousIOController.do?addorupdate','materialWarehousIOList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','materialWarehousIOController.do?addorupdate','materialWarehousIOList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','materialWarehousIOController.do?addorupdate','materialWarehousIOList','100%','100%')">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>