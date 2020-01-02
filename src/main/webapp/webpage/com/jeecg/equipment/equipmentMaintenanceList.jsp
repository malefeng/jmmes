<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#equipmentMaintenanceList').datagrid({
                idField: 'id',
                title: '设备维护',
                url: 'equipmentMaintenanceController.do?datagrid&field=id,maintenanceBatch,maintenanceType,maintenanceWay,equipmentNumber,equipmentName,equipmentSize,equipmentSeat,equipmentSupplier,purchaseDate,purchasePerson,maintenanceRequest,maintenanceState,maintenanceResult,remark,planMaintenanceData,planMaintenancePerson,actualMaintenanceData,actualMaintenancePerson,',
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
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true}
                    , {field: 'maintenanceBatch', title: '维护批次号', width: 120, sortable: true}, {
                        field: 'maintenanceType',
                        title: '维护类型',
                        width: 120,
                        sortable: true,
                        formatter: function (value) {
                            return ${maintType}[value]
                        }
                    }, {
                        field: 'maintenanceWay', title: '维护方式', width: 120, sortable: true,
                        formatter: function (value) {
                            return ${maintWay}[value]
                        }
                    }, {
                        field: 'equipmentNumber',
                        title: '设备编号',
                        width: 120,
                        sortable: true
                    }, {field: 'equipmentName', title: '设备名称', width: 120, sortable: true}, {
                        field: 'equipmentSize',
                        title: '设备型号',
                        width: 120,
                        sortable: true
                    }, {field: 'equipmentSeat', title: '设备位置', width: 120, sortable: true},   {
                        field: 'maintenanceRequest',
                        title: '维护要求',
                        width: 120,
                        sortable: true
                    }, {
                        field: 'maintenanceState', title: '维护状态', width: 120, sortable: true,
                        formatter: function (value) {
                            return ${maintState}[value]
                        }
                    }, {
                        field: 'maintenanceResult',
                        title: '维护结果',
                        width: 120,
                        sortable: true,
                        formatter: function (value) {
                            return ${maintRes}[value]
                        }
                    }, {
                        field: 'planMaintenanceData',
                        title: '计划维护时间',
                        width: 120,
                        sortable: true,
                        formatter: function (value, rec, index) {
                            return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                        }
                    }, {
                        field: 'planMaintenancePerson',
                        title: '计划维护人',
                        width: 120,
                        sortable: true,
                        formatter: function (value) {
                            return ${userDic}[value]
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
                            href += "<a href='#'   class='ace_button'  onclick=delObj('equipmentMaintenanceController.do?del&id=" + rec.id + "','equipmentMaintenanceList')>  <i class=' fa fa-trash-o'></i> ";
                            href += "删除</a>&nbsp;";
                            return href;
                        }
                    }]],
                onLoadSuccess: function (data) {
                    $("#equipmentMaintenanceList").datagrid("clearChecked");
                    $("#equipmentMaintenanceList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#equipmentMaintenanceList');
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
                    gridname = 'equipmentMaintenanceList';
                }
            });
            $('#equipmentMaintenanceList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#equipmentMaintenanceList').datagrid('getPager').pagination({
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

        function reloadequipmentMaintenanceList() {
            $('#equipmentMaintenanceList').datagrid('reload');
        }

        function getequipmentMaintenanceListSelected(field) {
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

        function getequipmentMaintenanceListSelections(field) {
            var ids = [];
            var rows = $('#equipmentMaintenanceList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#equipmentMaintenanceList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#equipmentMaintenanceList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#equipmentMaintenanceList').datagrid('getColumnFields');
            }
            var cols = storage.get('equipmentMaintenanceListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#equipmentMaintenanceList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('equipmentMaintenanceListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('equipmentMaintenanceListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#equipmentMaintenanceList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('equipmentMaintenanceListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#equipmentMaintenanceList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function equipmentMaintenanceListsearch() {
            try {
                if (!$("#equipmentMaintenanceListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#equipmentMaintenanceList').datagrid('options').queryParams;
                $('#equipmentMaintenanceListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#equipmentMaintenanceList').datagrid({
                    url: 'equipmentMaintenanceController.do?datagrid&field=id,maintenanceBatch,maintenanceType,maintenanceWay,equipmentNumber,equipmentName,equipmentSize,equipmentSeat,equipmentSupplier,purchaseDate,purchasePerson,maintenanceRequest,maintenanceState,maintenanceResult,remark,planMaintenanceData,planMaintenancePerson,actualMaintenanceData,actualMaintenancePerson,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#equipmentMaintenanceList').datagrid({
                url: 'equipmentMaintenanceController.do?datagrid&field=id,maintenanceBatch,maintenanceType,maintenanceWay,equipmentNumber,equipmentName,equipmentSize,equipmentSeat,equipmentSupplier,purchaseDate,purchasePerson,maintenanceRequest,maintenanceState,maintenanceResult,remark,planMaintenanceData,planMaintenancePerson,actualMaintenanceData,actualMaintenancePerson,',
                queryParams: jsonparams
            });
        }

        function equipmentMaintenanceListsearchbox(value, name) {
            var queryParams = $('#equipmentMaintenanceList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#equipmentMaintenanceList').datagrid('reload');
        }

        $('#equipmentMaintenanceListsearchbox').searchbox({
            searcher: function (value, name) {
                equipmentMaintenanceListsearchbox(value, name);
            }, menu: '#equipmentMaintenanceListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                equipmentMaintenanceListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#equipmentMaintenanceList').datagrid('options').queryParams;
            $('#equipmentMaintenanceListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#equipmentMaintenanceListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#equipmentMaintenanceListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#equipmentMaintenanceList').datagrid({
                url: 'equipmentMaintenanceController.do?datagrid&field=id,maintenanceBatch,maintenanceType,maintenanceWay,equipmentNumber,equipmentName,equipmentSize,equipmentSeat,equipmentSupplier,purchaseDate,purchasePerson,maintenanceRequest,maintenanceState,maintenanceResult,remark,planMaintenanceData,planMaintenancePerson,actualMaintenanceData,actualMaintenancePerson,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="equipmentMaintenanceList" toolbar="#equipmentMaintenanceListtb"></table>
        <div id="equipmentMaintenanceListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>设备维护批次号:<input type="text" name="maintenanceBatch"></span>
                <span>设备编号:<input type="text" name="equipmentNumber"></span>
                <span>设备名称:<input type="text" name="equipmentName"></span>
                <span>维护类型:<t:dictSelect field="maintenanceType" typeGroupCode="maintType" readonly="true"></t:dictSelect></span>
                <span>计划维护时间:
                    <input type="text" name="planMaintenanceData_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="planMaintenanceData_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>实际维护时间:
                    <input type="text" name="actualMaintenanceData_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="actualMaintenanceData_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>维护方式:<t:dictSelect field="maintenanceWay" typeGroupCode="maintWay" readonly="true"></t:dictSelect></span>
                <span>维护状态:<t:dictSelect field="maintenanceState" typeGroupCode="maintState" readonly="true"></t:dictSelect></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="equipmentMaintenanceListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','equipmentMaintenanceController.do?addorupdate','equipmentMaintenanceList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','equipmentMaintenanceController.do?addorupdate','equipmentMaintenanceList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','equipmentMaintenanceController.do?addorupdate','equipmentMaintenanceList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>