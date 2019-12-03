<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <script type="text/javascript">
			$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#purchaseReceiptList').datagrid({
                idField: 'id',
                title: '采购收料单',
                url: 'purchaseReceiptController.do?datagrid&field=id,receiptCode,receivingCompanyCode,purchasingCompanyCode,receiptDate,receivingOrgCode,purchasingOrgCode,supplierCode,receivingPersonCode,purchasingPersonCode,verifierCode,verifyDate,createTime,orderNumber,salesOrderNumber,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5',
                fit: true,
                rownumbers: true,
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortName:'receiptDate',
                sortOrder: 'desc',
                singleSelect: true,
                fitColumns: true,
                striped: true,
                showFooter: true,
                frozenColumns: [[]],
                columns: [[
                    {
                        field: 'id',
                    title: '编号',
                    hidden: true,
                    sortable: true,
                    formatter: function(value){return ${depDic}[value]}
                }, {
                    field: 'receiptCode',
                    title: '收料单编号',
                    sortable: true
                }, {field: 'receivingCompanyCode',
                        title: '收货组织',
                        sortable: true,
                        formatter: function(value){return ${depDic}[value]}
                    }, {
                    field: 'purchasingCompanyCode',
                    title: '采购组织',
                    sortable: true,
                    formatter: function(value){return ${depDic}[value]}
                }, {
                    field: 'receiptDate', title: '单据日期', sortable: true
                }, {field: 'receivingOrgCode',
                    title: '收货部门',
                    sortable: true,
                    formatter: function(value){return ${depDic}[value]}
                }, {
                    field: 'purchasingOrgCode',
                    title: '采购部门',
                    sortable: true,
                    formatter: function(value){return ${depDic}[value]}
                }, {field: 'supplierCode',
                        title: '供应商',
                        sortable: true,
                        formatter: function(value){return ${suplDic}[value]}
                    }, {
                    field: 'receivingPersonCode',
                    title: '收货员',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {field: 'purchasingPersonCode',
                    title: '采购员',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {
                    field: 'verifierCode',
                    title: '审核人',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }, {
                    field: 'verifyDate', title: '审核日期', sortable: true
                }, {
                    field: 'createTime', title: '单据创建日期', sortable: true
                }, {field: 'orderNumber', title: '采购订单号', sortable: true}, {
                    field: 'salesOrderNumber',
                    title: '销售订单号',
                    sortable: true
                }, {field: 'readPersonCode',
                    title: '读取人',
                    sortable: true,
                    formatter: function(value){return ${userDic}[value]}
                }
                , {
                    field: 'acquireTime',
                    title: '获取时间',
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
                        href += "<a href='#'   class='ace_button'  onclick=delObj('purchaseReceiptController.do?del&id=" + rec.id + "','purchaseReceiptList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#purchaseReceiptList").datagrid("clearChecked");
                    $("#purchaseReceiptList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#purchaseReceiptList');
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
                    gridname = 'purchaseReceiptList';
                }
            });
            $('#purchaseReceiptList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#purchaseReceiptList').datagrid('getPager').pagination({
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

        function reloadpurchaseReceiptList() {
            $('#purchaseReceiptList').datagrid('reload');
        }

        function getpurchaseReceiptListSelected(field) {
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

        function getpurchaseReceiptListSelections(field) {
            var ids = [];
            var rows = $('#purchaseReceiptList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#purchaseReceiptList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#purchaseReceiptList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#purchaseReceiptList').datagrid('getColumnFields');
            }
            var cols = storage.get('purchaseReceiptListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#purchaseReceiptList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('purchaseReceiptListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('purchaseReceiptListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#purchaseReceiptList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('purchaseReceiptListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#purchaseReceiptList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function purchaseReceiptListsearch() {
            try {
                if (!$("#purchaseReceiptListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#purchaseReceiptList').datagrid('options').queryParams;
                $('#purchaseReceiptListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#purchaseReceiptList').datagrid({
                    url: 'purchaseReceiptController.do?datagrid&field=id,receiptCode,receivingCompanyCode,purchasingCompanyCode,receiptDate,receivingOrgCode,purchasingOrgCode,supplierCode,receivingPersonCode,purchasingPersonCode,verifierCode,verifyDate,createTime,orderNumber,salesOrderNumber,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#purchaseReceiptList').datagrid({
                url: 'purchaseReceiptController.do?datagrid&field=id,receiptCode,receivingCompanyCode,purchasingCompanyCode,receiptDate,receivingOrgCode,purchasingOrgCode,supplierCode,receivingPersonCode,purchasingPersonCode,verifierCode,verifyDate,createTime,orderNumber,salesOrderNumber,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                queryParams: jsonparams
            });
        }

        function purchaseReceiptListsearchbox(value, name) {
            var queryParams = $('#purchaseReceiptList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#purchaseReceiptList').datagrid('reload');
        }

        $('#purchaseReceiptListsearchbox').searchbox({
            searcher: function (value, name) {
                purchaseReceiptListsearchbox(value, name);
            }, menu: '#purchaseReceiptListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                purchaseReceiptListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#purchaseReceiptList').datagrid('options').queryParams;
            $('#purchaseReceiptListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#purchaseReceiptListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#purchaseReceiptListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#purchaseReceiptList').datagrid({
                url: 'purchaseReceiptController.do?datagrid&field=id,receiptCode,receivingCompanyCode,purchasingCompanyCode,receiptDate,receivingOrgCode,purchasingOrgCode,supplierCode,receivingPersonCode,purchasingPersonCode,verifierCode,verifyDate,createTime,orderNumber,salesOrderNumber,readPersonCode,acquireTime,attr1,attr2,attr3,attr4,attr5,',
                pageNumber: 1
            });
        }
		</script>
        <table width="100%" id="purchaseReceiptList" toolbar="#purchaseReceiptListtb"></table>
        <div id="purchaseReceiptListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
				<span>收料单编号:<input type="text" name="receiptCode"></span>
				<span>供应商:<t:dictSelect id="supplierCode" field="supplierCode" dictTable="t_supplier_list" dictField="supplier_code" dictText="supplier_name" defaultVal="${storagePage.repositoryCode}" readonly="true"></t:dictSelect></span>
				<span>单据创建日期:
                    <input type="text" name="receiptDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="receiptDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
                <span>创建日期:
                    <input type="text" name="receiptDate_begin"  style="width: 120px"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                    <span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
                    <input type="text" name="receiptDate_end"  style="width: 120px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                </span>
				<a href="#" class="easyui-linkbutton" style="float: right" onclick="purchaseReceiptListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','purchaseReceiptController.do?addorupdate','purchaseReceiptList','100%','100%')">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','purchaseReceiptController.do?addorupdate','purchaseReceiptList','100%','100%')">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="detail('查看','purchaseReceiptController.do?addorupdate','purchaseReceiptList','100%','100%')">查看</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="copy()">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    function copy(){
        $.messager.prompt("","请输入收料单编号",function(data){
            if (data != null) {
                $.ajax({
                    type: 'get',
                    url: "purchaseReceiptController.do?getErpData&number="+data,
                    dataType: 'json',
                    beforeSend: function () {
                        loadMask();
                    },
                    complete: function () {
                        disLoadMask();
                    },
                    success: function (id) {
                        if(!!id){
                            update('编辑','purchaseReceiptController.do?addorupdate&id='+id,'purchaseReceiptList','100%','100%');
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