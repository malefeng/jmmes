<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#finishedProductionList').datagrid({
                idField: 'id',
                title: '成品生产列表',
                url: 'finishedProductionController.do?datagrid&field=id,finishedSerino,finishedCode,finishedName,finishedBatch,finishedSize,finishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,attr1,attr2,attr3,attr4,attr5,',
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
                    field: 'finishedSerino',
                    title: '成品编号',
                    sortable: true
                }, {field: 'finishedCode', title: '成品代码', sortable: true}, {
                    field: 'finishedName',
                    title: '成品名称',
                    sortable: true
                }, {field: 'finishedBatch', title: '成品批次', sortable: true}, {
                    field: 'finishedSize',
                    title: '规格型号',
                    sortable: true
                }, {field: 'finishedNumber', title: '数量', sortable: true}, {
                    field: 'unit',
                    title: '单位',
                    sortable: true,
                    formatter: function (value){return ${unitDic}[value]}
                }, {field: 'productionPersonCode', title: '生产人', sortable: true,
                    formatter: function (value){return ${userDic}[value]}}, {
                    field: 'productionDate',
                    title: '生产时间',
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'productionOrderNumber', title: '生产派工单号', sortable: true}, {
                    field: 'takeMaterilNumber',
                    title: '生产领料单号',
                    sortable: true
                }, {
                    field: 'opt',
                    title: '操作',
                    width: 100,
                    formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('finishedProductionController.do?del&id=" + rec.id + "','finishedProductionList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        href += "<a href='#'   class='ace_button'  onclick=add('录入','finishedInspectController.do?addorupdate&finishedCode=" + rec.finishedSerino + "&finishedName="+rec.finishedName+"&productionOrderNumber="+rec.productionOrderNumber+"','finishedProductionList','100%','100%')></i> ";
                        href += "首末检</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#finishedProductionList").datagrid("clearChecked");
                    $("#finishedProductionList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#finishedProductionList');
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
                    gridname = 'finishedProductionList';
                }
            });
            $('#finishedProductionList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#finishedProductionList').datagrid('getPager').pagination({
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

        function reloadfinishedProductionList() {
            $('#finishedProductionList').datagrid('reload');
        }

        function getfinishedProductionListSelected(field) {
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

        function getfinishedProductionListSelections(field) {
            var ids = [];
            var rows = $('#finishedProductionList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#finishedProductionList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#finishedProductionList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#finishedProductionList').datagrid('getColumnFields');
            }
            var cols = storage.get('finishedProductionListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#finishedProductionList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('finishedProductionListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('finishedProductionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#finishedProductionList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('finishedProductionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#finishedProductionList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function finishedProductionListsearch() {
            try {
                if (!$("#finishedProductionListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#finishedProductionList').datagrid('options').queryParams;
                $('#finishedProductionListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#finishedProductionList').datagrid({
                    url: 'finishedProductionController.do?datagrid&field=id,finishedSerino,finishedCode,finishedName,finishedBatch,finishedSize,finishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,attr1,attr2,attr3,attr4,attr5,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#finishedProductionList').datagrid({
                url: 'finishedProductionController.do?datagrid&field=id,finishedSerino,finishedCode,finishedName,finishedBatch,finishedSize,finishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,attr1,attr2,attr3,attr4,attr5,',
                queryParams: jsonparams
            });
        }

        function finishedProductionListsearchbox(value, name) {
            var queryParams = $('#finishedProductionList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#finishedProductionList').datagrid('reload');
        }

        $('#finishedProductionListsearchbox').searchbox({
            searcher: function (value, name) {
                finishedProductionListsearchbox(value, name);
            }, menu: '#finishedProductionListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                finishedProductionListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#finishedProductionList').datagrid('options').queryParams;
            $('#finishedProductionListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#finishedProductionListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedProductionListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#finishedProductionList').datagrid({
                url: 'finishedProductionController.do?datagrid&field=id,finishedSerino,finishedCode,finishedName,finishedBatch,finishedSize,finishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,attr1,attr2,attr3,attr4,attr5,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="finishedProductionList" toolbar="#finishedProductionListtb"></table>
        <div id="finishedProductionListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>成品编号：<input type="text" name="finishedSerino"></span>
                <span>成品代码:<t:dictSelect field="finishedCode" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></span>
                <span>成品名称:<t:dictSelect field="finishedName" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></span>
                <span>成品批次:<input type="text" name="finishedBatch"></span>
                <span>生产时间:
                    <input type="text" name="productionDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="productionDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="finishedProductionListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','finishedProductionController.do?addorupdate','finishedProductionList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','finishedProductionController.do?addorupdate','finishedProductionList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','finishedProductionController.do?addorupdate','finishedProductionList','100%','100%')">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>