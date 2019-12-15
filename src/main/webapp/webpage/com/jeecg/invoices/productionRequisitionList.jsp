<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">
            $(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#productionRequisitionList').datagrid({
                idField: 'id',
                title: '生产领料单',
                url: 'productionRequisitionController.do?datagrid&field=id,receiptCode,requisitionDate,requisitionWorkshopCode,repositoryCode,repositoryManagerCode,acquirePersonCode,creatorCode,productionDispatchingNumber,productionOrderNumber,finishedCode,finishedName,plannedProductionQuantity,plannedStartDate,plannedCompletionDate,readPersonCode,acquireTime,productionOrderNumber,attr2,attr3,attr4,attr5,',
                fit: true,
                rownumbers: true,
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortName:'requisitionDate',
                sortOrder: 'desc',
                singleSelect: true,
                fitColumns: true,
                striped: true,
                showFooter: true,
                frozenColumns: [[]],
                columns: [[{field: 'id', title: '编号', hidden: true, sortable: true}, {
                    field: 'receiptCode',
                    title: '领料单编号',
                    sortable: true
                }, {
                    field: 'requisitionDate', title: '领料日期', sortable: true, formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'requisitionWorkshopCode',
                    title: '领料车间',
                    sortable: true,
                    formatter:function(value){return ${workshopDic}[value]}
                }, {
                    field: 'repositoryCode',
                    title: '领料仓库',
                    sortable: true,
                    formatter: function(value){return ${repostDic}[value]}
                }, {field: 'repositoryManagerCode',
                    title: '仓库管理员',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {
                    field: 'acquirePersonCode',
                    title: '领料人',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {field: 'creatorCode', title: '制单人',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {
                    field: 'productionDispatchingNumber',
                    title: '派工单号',
                    sortable: true
                }, {
                    field: 'productionOrderNumber',
                    title: '生产订单号',
                    sortable: true
                }, {field: 'finishedCode', title: '成品代码', sortable: true}, {
                    field: 'finishedName',
                    title: '成品名称',
                    sortable: true
                }, {field: 'plannedProductionQuantity', title: '计划生产数量', sortable: true}, {
                    field: 'plannedStartDate',
                    title: '计划开工日期',
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
                    }
                }, {
                    field: 'plannedCompletionDate',
                    title: '计划完工日期',
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
                    }
                }, {field: 'readPersonCode', title: '读取人', sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {
                    field: 'acquireTime',
                    title: '获取时间',
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
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
                        href += "<a href='#'   class='ace_button'  onclick=delObj('productionRequisitionController.do?del&id=" + rec.id + "','productionRequisitionList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#productionRequisitionList").datagrid("clearChecked");
                    $("#productionRequisitionList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#productionRequisitionList');
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
                    gridname = 'productionRequisitionList';
                }
            });
            $('#productionRequisitionList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#productionRequisitionList').datagrid('getPager').pagination({
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

        function reloadproductionRequisitionList() {
            $('#productionRequisitionList').datagrid('reload');
        }

        function getproductionRequisitionListSelected(field) {
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

        function getproductionRequisitionListSelections(field) {
            var ids = [];
            var rows = $('#productionRequisitionList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#productionRequisitionList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#productionRequisitionList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#productionRequisitionList').datagrid('getColumnFields');
            }
            var cols = storage.get('productionRequisitionListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#productionRequisitionList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('productionRequisitionListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('productionRequisitionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#productionRequisitionList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('productionRequisitionListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#productionRequisitionList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function productionRequisitionListsearch() {
            try {
                if (!$("#productionRequisitionListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#productionRequisitionList').datagrid('options').queryParams;
                $('#productionRequisitionListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#productionRequisitionList').datagrid({
                    url: 'productionRequisitionController.do?datagrid&field=id,receiptCode,requisitionDate,requisitionWorkshopCode,repositoryCode,repositoryManagerCode,acquirePersonCode,creatorCode,productionDispatchingNumber,productionOrderNumber,finishedCode,finishedName,plannedProductionQuantity,plannedStartDate,plannedCompletionDate,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#productionRequisitionList').datagrid({
                url: 'productionRequisitionController.do?datagrid&field=id,receiptCode,requisitionDate,requisitionWorkshopCode,repositoryCode,repositoryManagerCode,acquirePersonCode,creatorCode,productionDispatchingNumber,finishedCode,finishedName,plannedProductionQuantity,plannedStartDate,plannedCompletionDate,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                queryParams: jsonparams
            });
        }

        function productionRequisitionListsearchbox(value, name) {
            var queryParams = $('#productionRequisitionList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#productionRequisitionList').datagrid('reload');
        }

        $('#productionRequisitionListsearchbox').searchbox({
            searcher: function (value, name) {
                productionRequisitionListsearchbox(value, name);
            }, menu: '#productionRequisitionListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                productionRequisitionListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#productionRequisitionList').datagrid('options').queryParams;
            $('#productionRequisitionListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#productionRequisitionListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#productionRequisitionListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#productionRequisitionList').datagrid({
                url: 'productionRequisitionController.do?datagrid&field=id,receiptCode,requisitionDate,requisitionWorkshopCode,repositoryCode,repositoryManagerCode,acquirePersonCode,creatorCode,productionDispatchingNumber,finishedCode,finishedName,plannedProductionQuantity,plannedStartDate,plannedCompletionDate,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="productionRequisitionList" toolbar="#productionRequisitionListtb"></table>
        <div id="productionRequisitionListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>领料单编号:<input type="text" name="receiptCode"></span>
                <span>领料日期:
                    <input type="text" name="requisitionDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="requisitionDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>领料车间:<t:dictSelect field="supplierCode" typeGroupCode="workshop" readonly="true"></t:dictSelect></span>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="productionRequisitionListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','productionRequisitionController.do?addorupdate','productionRequisitionList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','productionRequisitionController.do?addorupdate','productionRequisitionList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','productionRequisitionController.do?addorupdate','productionRequisitionList','100%','100%')">查看</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="copy()">同步数据</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>

<script>
    function copy(){
        $.messager.prompt("","请输入领料单编号",function(data){
            if (data != null) {
                $.ajax({
                    type: 'get',
                    url: "productionRequisitionController.do?getErpData&number="+data,
                    dataType: 'json',
                    beforeSend: function () {
                        loadMask();
                    },
                    complete: function () {
                        disLoadMask();
                    },
                    success: function (id) {
                        if(!!id){
                            add('编辑','productionRequisitionController.do?addorupdate&id='+id,'productionRequisitionList','100%','100%');
                        }else{
                            $.messager.show({
                                msg:'未查询到有效数据',
                                showType:'slide',
                                showSpeed:'200',
                                style:{color:'red'}
                            });
                        }
                    }
                });
            }
        });
    }
</script>