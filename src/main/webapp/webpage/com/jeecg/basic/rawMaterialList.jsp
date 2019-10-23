<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage) storage = $.cookieStorage;
            $('#rawMaterialList').datagrid({
                idField: 'id',
                title: '原料信息',
                url: 'rawMaterialController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialType,rawMaterialSize,rawMaterialNumber,rawMaterialUnit,',
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
                    field: 'rawMaterialCode',
                    title: '原料代码',
                    width: 120,
                    sortable: true
                }, {field: 'rawMaterialName', title: '原料名称', width: 120, sortable: true}, {
                    field: 'rawMaterialType',
                    title: '物料类型',
                    width: 120,
                    sortable: true,
                    formatter: function (value){return ${matTypeDic}[value]}
                }, {field: 'rawMaterialSize', title: '规格', width: 120, sortable: true}, {
                    field: 'rawMaterialNumber',
                    title: '数量',
                    width: 120,
                    sortable: true
                }, {
                    field: 'rawMaterialUnit',
                    title: '单位',
                    width: 120,
                    sortable: true,
                    formatter:function (value){return ${unitDic}[value]}
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "<a href='#'   class='ace_button'  onclick=delObj('rawMaterialController.do?del&id=" + rec.id + "','rawMaterialList')>  <i class=' fa fa-trash-o'></i> ";
                        href += "删除</a>&nbsp;";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#rawMaterialList").datagrid("clearChecked");
                    $("#rawMaterialList").datagrid("clearSelections");
                    if (!false) {
                        if (data.total && data.rows.length == 0) {
                            var grid = $('#rawMaterialList');
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
                    gridname = 'rawMaterialList';
                }
            });
            $('#rawMaterialList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#rawMaterialList').datagrid('getPager').pagination({
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

        function reloadrawMaterialList() {
            $('#rawMaterialList').datagrid('reload');
        }

        function getrawMaterialListSelected(field) {
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

        function getrawMaterialListSelections(field) {
            var ids = [];
            var rows = $('#rawMaterialList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        };

        function getSelectRows() {
            return $('#rawMaterialList').datagrid('getChecked');
        }

        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#rawMaterialList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#rawMaterialList').datagrid('getColumnFields');
            }
            var cols = storage.get('rawMaterialListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#rawMaterialList').datagrid("getColumnOption", columnsFields[i]);
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
            storage.set('rawMaterialListhiddenColumns', JSON.stringify(hiddencolumns));
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
            var cols = storage.get('rawMaterialListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false) $('#rawMaterialList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function resetheader() {
            var cols = storage.get('rawMaterialListhiddenColumns');
            if (!cols) return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#rawMaterialList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }

        function rawMaterialListsearch() {
            try {
                if (!$("#rawMaterialListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#rawMaterialList').datagrid('options').queryParams;
                $('#rawMaterialListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#rawMaterialList').datagrid({
                    url: 'rawMaterialController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialType,rawMaterialSize,rawMaterialNumber,rawMaterialUnit,',
                    pageNumber: 1
                });
            }
        }

        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#rawMaterialList').datagrid({
                url: 'rawMaterialController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialType,rawMaterialSize,rawMaterialNumber,rawMaterialUnit,',
                queryParams: jsonparams
            });
        }

        function rawMaterialListsearchbox(value, name) {
            var queryParams = $('#rawMaterialList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#rawMaterialList').datagrid('reload');
        }

        $('#rawMaterialListsearchbox').searchbox({
            searcher: function (value, name) {
                rawMaterialListsearchbox(value, name);
            }, menu: '#rawMaterialListmm', prompt: '请输入查询关键字'
        });

        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                rawMaterialListsearch();
            }
        }

        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#rawMaterialList').datagrid('options').queryParams;
            $('#rawMaterialListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#rawMaterialListtb').find("input[type='checkbox']").each(function () {
                $(this).attr('checked', false);
            });
            $('#rawMaterialListtb').find("input[type='radio']").each(function () {
                $(this).attr('checked', false);
            });
            $('#rawMaterialList').datagrid({
                url: 'rawMaterialController.do?datagrid&field=id,rawMaterialCode,rawMaterialName,rawMaterialType,rawMaterialSize,rawMaterialNumber,rawMaterialUnit,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="rawMaterialList" toolbar="#rawMaterialListtb"></table>
        <div id="rawMaterialListtb" style="padding:3px; height: auto">
            <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
            <div name="searchColums">
                <span>原料代码:<input type="text" name="rawMaterialCode"></span>
                <span>原料名称:<input type="text" name="rawMaterialName"></span>
                <a href="#" class="easyui-linkbutton" id="search" plain="true" icon="icon-search">查询</a>
                <a href="#" class="easyui-linkbutton" style="float: right" onclick="rawMaterialListsearch();" plain="true" icon="icon-search">查询</a>
            </div>
            <div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span style="float:left;">
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','rawMaterialController.do?addorupdate','rawMaterialList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','rawMaterialController.do?addorupdate','rawMaterialList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','rawMaterialController.do?addorupdate','rawMaterialList',null,null)">查看</a>
                </span>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#search").click(function(){
        generateSearchParam('rawMaterialList',['rawMaterialCode','rawMaterialName']);
    })
</script>