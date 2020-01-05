<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#semiFinishedInspectList').datagrid({
                idField: 'id',
                title: '半成品首末检',
                url: 'semiFinishedInspectController.do?datagrid&field=result,batchNo,count,qualifiedCount,unQualifiedCount,id,semiFinishedCode,semiFinishedName,productionOrderNumber,inspectLogSheet,',
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
                    field: 'semiFinishedCode',
                    title: '半成品编号',
                    sortable: true
                }, {field: 'semiFinishedName', title: '半成品名称', sortable: true}, {
                    field: 'productionOrderNumber',
                    title: '生产订单号',
                    sortable: true
                },
                    {field: 'result', title: '检验结果', sortable: true},
                    {field: 'batchNo', title: '批号', sortable: true},
                    {field: 'count', title: '数量', sortable: true},
                    {field: 'qualifiedCount', title: '合格数量', sortable: true},
                    {field: 'unQualifiedCount', title: '不合格数量', sortable: true},
                    {field: 'inspectLogSheet', title: '首末检记录表', sortable: true,
                    formatter:function(value){
                        return value&&value.length>20?value.substring(0,19)+"...":value;
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
                        href += "<a href='#'   class='ace_button'  onclick=delObj('semiFinishedInspectController.do?del&id=" + rec.id + "','semiFinishedInspectList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#semiFinishedInspectList").datagrid("clearChecked");
                    $("#semiFinishedInspectList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#semiFinishedInspectList');
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
                    gridname = 'semiFinishedInspectList';
                }
            });
            $('#semiFinishedInspectList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#semiFinishedInspectList').datagrid('getPager').pagination({
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

        function reloadsemiFinishedInspectList() {
            $('#semiFinishedInspectList').datagrid('reload');
        }

        function getsemiFinishedInspectListSelected(field) {
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

        function getsemiFinishedInspectListSelections(field) {
            var ids = [];
            var rows = $('#semiFinishedInspectList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#semiFinishedInspectList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#semiFinishedInspectList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#semiFinishedInspectList').datagrid('getColumnFields');
            }
            var cols = storage.get('semiFinishedInspectListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#semiFinishedInspectList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('semiFinishedInspectListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('semiFinishedInspectListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#semiFinishedInspectList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('semiFinishedInspectListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#semiFinishedInspectList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function semiFinishedInspectListsearch() {
            try {
                if (!$("#semiFinishedInspectListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#semiFinishedInspectList').datagrid('options').queryParams;
                $('#semiFinishedInspectListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#semiFinishedInspectList').datagrid({
                    url: 'semiFinishedInspectController.do?datagrid&field=result,batchNo,count,qualifiedCount,unQualifiedCount,id,semiFinishedCode,semiFinishedName,productionOrderNumber,inspectLogSheet,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#semiFinishedInspectList').datagrid({
                url: 'semiFinishedInspectController.do?datagrid&field=result,batchNo,count,qualifiedCount,unQualifiedCount,id,semiFinishedCode,semiFinishedName,productionOrderNumber,inspectLogSheet,',
                queryParams: jsonparams
            });
        }

        function semiFinishedInspectListsearchbox(value, name) {
            var queryParams = $('#semiFinishedInspectList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#semiFinishedInspectList').datagrid('reload');
        }

        $('#semiFinishedInspectListsearchbox').searchbox({
            searcher: function (value, name) {
                semiFinishedInspectListsearchbox(value, name);
            }, menu: '#semiFinishedInspectListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                semiFinishedInspectListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#semiFinishedInspectList').datagrid('options').queryParams;
            $('#semiFinishedInspectListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#semiFinishedInspectListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedInspectListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#semiFinishedInspectList').datagrid({
                url: 'semiFinishedInspectController.do?datagrid&field=result,batchNo,count,qualifiedCount,unQualifiedCount,id,semiFinishedCode,semiFinishedName,productionOrderNumber,inspectLogSheet,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="semiFinishedInspectList" toolbar="#semiFinishedInspectListtb"></table>
        <div id="semiFinishedInspectListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>半成品编号:<input type="text" name="semiFinishedCode"></span>
                <span>半成品名称:<input type="text" name="semiFinishedName"></span>
                <span>生产订单号:<input type="text" name="productionOrderNumber"></span>
                <span>创建时间:
                    <input type="text" name="createDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="createDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="semiFinishedInspectListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick=addNew()>录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','semiFinishedInspectController.do?addorupdate','semiFinishedInspectList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','semiFinishedInspectController.do?addorupdate','semiFinishedInspectList','100%','100%')">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    //半成品编号长度
    var semiFinishedSerinoLen = 12;
    function addNew() {
        $.messager.prompt("", "请输入半成品首末检编号", function (data) {
            if (data != null) {
                $.ajax({
                    type: 'get',
                    url: "semiFinishedProductionController.do?get&semiFinishedSerino=" + data.substr(0,semiFinishedSerinoLen),
                    dataType: 'json',
                    beforeSend: function () {
                        loadMask();
                    },
                    complete: function () {
                        disLoadMask();
                    },
                    success: function (data) {
                        if (!!data&&!data.msg) {
                            add('录入', 'semiFinishedInspectController.do?addorupdate&semiFinishedCode=' + data.semiFinishedSerino + "&semiFinishedName=" + data.semiFinishedName + "&productionOrderNumber=" + data.productionOrderNumber, 'semiFinishedInspectMainList', '100%', '100%')
                        }/*else if(!!data&&!!data.msg){
                            $.messager.show({
                                msg: data.msg,
                                showType: 'slide',
                                showSpeed: '200',
                                style: {color: 'red'}
                            });
                        }*/ else {
                            $.messager.show({
                                msg: '该半成品不存在或未投产',
                                showType: 'slide',
                                showSpeed: '200',
                                style: {color: 'red'}
                            });
                        }
                    }
                });
            }
        });
    }
</script>