<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#semiFinishedProductionList').datagrid({
                idField: 'id',
                title: '半成品生产列表',
                url: 'semiFinishedProductionController.do?datagrid&field=id,semiFinishedSerino,semiFinishedCode,semiFinishedName,batchNumber,semiFinishedSize,semiFinishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,',
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
                    field: 'semiFinishedSerino',
                    title: '半成品编号',
                    sortable: true
                }, {field: 'semiFinishedCode', title: '半成品代码', sortable: true}, {
                    field: 'semiFinishedName',
                    title: '半成品名称',
                    sortable: true
                }, {field: 'batchNumber', title: '半成品批次', sortable: true}, {
                    field: 'semiFinishedSize',
                    title: '规格型号',
                    sortable: true
                }, {field: 'semiFinishedNumber', title: '数量', sortable: true}, {
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
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('semiFinishedProductionController.do?del&id=" + rec.id + "','semiFinishedProductionList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        href += "<a href='#'   class='ace_button'  onclick=add('录入','semiFinishedInspectController.do?addorupdate&semiFinishedCode="+rec.semiFinishedSerino+"&semiFinishedName="+rec.semiFinishedName+"&productionOrderNumber="+rec.productionOrderNumber+"','semiFinishedInspectMainList','100%','100%')>";
                        href += "首末检</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#semiFinishedProductionList").datagrid("clearChecked");
                    $("#semiFinishedProductionList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#semiFinishedProductionList');
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
                    gridname = 'semiFinishedProductionList';
                }
            });
            $('#semiFinishedProductionList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#semiFinishedProductionList').datagrid('getPager').pagination({
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

        function reloadsemiFinishedProductionList() {
            $('#semiFinishedProductionList').datagrid('reload');
        }

        function getsemiFinishedProductionListSelected(field) {
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

        function getsemiFinishedProductionListSelections(field) {
            var ids = [];
            var rows = $('#semiFinishedProductionList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#semiFinishedProductionList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#semiFinishedProductionList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#semiFinishedProductionList').datagrid('getColumnFields');
            }
            var cols = storage.get('semiFinishedProductionListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#semiFinishedProductionList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('semiFinishedProductionListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('semiFinishedProductionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#semiFinishedProductionList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('semiFinishedProductionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#semiFinishedProductionList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function semiFinishedProductionListsearch() {
            try {
                if (!$("#semiFinishedProductionListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#semiFinishedProductionList').datagrid('options').queryParams;
                $('#semiFinishedProductionListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#semiFinishedProductionList').datagrid({
                    url: 'semiFinishedProductionController.do?datagrid&field=id,semiFinishedSerino,semiFinishedCode,semiFinishedName,batchNumber,semiFinishedSize,semiFinishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#semiFinishedProductionList').datagrid({
                url: 'semiFinishedProductionController.do?datagrid&field=id,semiFinishedSerino,semiFinishedCode,semiFinishedName,batchNumber,semiFinishedSize,semiFinishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,',
                queryParams: jsonparams
            });
        }

        function semiFinishedProductionListsearchbox(value, name) {
            var queryParams = $('#semiFinishedProductionList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#semiFinishedProductionList').datagrid('reload');
        }

        $('#semiFinishedProductionListsearchbox').searchbox({
            searcher: function (value, name) {
                semiFinishedProductionListsearchbox(value, name);
            }, menu: '#semiFinishedProductionListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                semiFinishedProductionListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#semiFinishedProductionList').datagrid('options').queryParams;
            $('#semiFinishedProductionListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#semiFinishedProductionListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductionListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedProductionList').datagrid({
                url: 'semiFinishedProductionController.do?datagrid&field=id,semiFinishedSerino,semiFinishedCode,semiFinishedName,batchNumber,semiFinishedSize,semiFinishedNumber,unit,productionPersonCode,productionDate,productionOrderNumber,takeMaterilNumber,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="semiFinishedProductionList" toolbar="#semiFinishedProductionListtb"></table>
        <div id="semiFinishedProductionListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>半成品编号：<input type="text" name="semiFinishedSerino"></span>
                <span>半成品代码:<input type="text" name="semiFinishedCode"></span>
                <span>半成品名称:<input type="text" name="semiFinishedName"></span>
                <span>半成品批次:<input type="text" name="batchNumber"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="semiFinishedProductionListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','semiFinishedProductionController.do?addorupdate','semiFinishedProductionList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','semiFinishedProductionController.do?addorupdate','semiFinishedProductionList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','semiFinishedProductionController.do?addorupdate','semiFinishedProductionList','100%','100%')">查看</a>

                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>