<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,common"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">


		<script type="text/javascript">$(function () {
			storage = $.localStorage;
			if (!storage) storage = $.cookieStorage;
			$('#storageList').datagrid({
				idField: 'id',
				title: '库位信息',
				url: 'storageController.do?datagrid&field=id,storageCode,storageName,repositoryCode,',
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
					field: 'storageCode',
					title: '库位代码',
					width: 120,
					sortable: true
				}, {field: 'storageName', title: '库位名称', width: 120, sortable: true}, {
					field: 'repositoryCode',
					title: '归属仓库',
					width: 120,
					sortable: true,
					formatter: function (value){return ${repostDic}[value]}
				}, {
					field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
						if (!rec.id) {
							return '';
						}
						var href = '';
						href += "<a href='#'   class='ace_button'  onclick=delObj('storageController.do?del&id=" + rec.id + "','storageList')>  <i class=' fa fa-trash-o'></i> ";
						href += "删除</a>&nbsp;";
						return href;
					}
				}]],
				onLoadSuccess: function (data) {
					$("#storageList").datagrid("clearChecked");
					$("#storageList").datagrid("clearSelections");
					if (!false) {
						if (data.total && data.rows.length == 0) {
							var grid = $('#storageList');
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
					gridname = 'storageList';
				}
			});
			$('#storageList').datagrid('getPager').pagination({
				beforePageText: '',
				afterPageText: '/{pages}',
				displayMsg: '{from}-{to}共 {total}条',
				showPageList: true,
				showRefresh: true
			});
			$('#storageList').datagrid('getPager').pagination({
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

		function reloadstorageList() {
			$('#storageList').datagrid('reload');
		}

		function getstorageListSelected(field) {
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

		function getstorageListSelections(field) {
			var ids = [];
			var rows = $('#storageList').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i][field]);
			}
			ids.join(',');
			return ids
		};

		function getSelectRows() {
			return $('#storageList').datagrid('getChecked');
		}

		function saveHeader() {
			var columnsFields = null;
			var easyextends = false;
			try {
				columnsFields = $('#storageList').datagrid('getColumns');
				easyextends = true;
			} catch (e) {
				columnsFields = $('#storageList').datagrid('getColumnFields');
			}
			var cols = storage.get('storageListhiddenColumns');
			var init = true;
			if (cols) {
				init = false;
			}
			var hiddencolumns = [];
			for (var i = 0; i < columnsFields.length; i++) {
				if (easyextends) {
					hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
				} else {
					var columsDetail = $('#storageList').datagrid("getColumnOption", columnsFields[i]);
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
			storage.set('storageListhiddenColumns', JSON.stringify(hiddencolumns));
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
			var cols = storage.get('storageListhiddenColumns');
			if (!cols) return;
			for (var i = 0; i < cols.length; i++) {
				try {
					if (cols.visible != false) $('#storageList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
				} catch (e) {
				}
			}
		}

		function resetheader() {
			var cols = storage.get('storageListhiddenColumns');
			if (!cols) return;
			for (var i = 0; i < cols.length; i++) {
				try {
					$('#storageList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
				} catch (e) {
				}
			}
		}

		function storageListsearch() {
			try {
				if (!$("#storageListForm").Validform({tiptype: 3}).check()) {
					return false;
				}
			} catch (e) {
			}
			if (true) {
				var queryParams = $('#storageList').datagrid('options').queryParams;
				$('#storageListtb').find('*').each(function () {
					queryParams[$(this).attr('name')] = $(this).val();
				});
				$('#storageList').datagrid({
					url: 'storageController.do?datagrid&field=id,storageCode,storageName,repositoryCode,',
					pageNumber: 1
				});
			}
		}

		function dosearch(params) {
			var jsonparams = $.parseJSON(params);
			$('#storageList').datagrid({
				url: 'storageController.do?datagrid&field=id,storageCode,storageName,repositoryCode,',
				queryParams: jsonparams
			});
		}

		function storageListsearchbox(value, name) {
			var queryParams = $('#storageList').datagrid('options').queryParams;
			queryParams[name] = value;
			queryParams.searchfield = name;
			$('#storageList').datagrid('reload');
		}

		$('#storageListsearchbox').searchbox({
			searcher: function (value, name) {
				storageListsearchbox(value, name);
			}, menu: '#storageListmm', prompt: '请输入查询关键字'
		});

		function EnterPress(e) {
			var e = e || window.event;
			if (e.keyCode == 13) {
				storageListsearch();
			}
		}

		function searchReset(name) {
			$("#" + name + "tb").find(":input").val("");
			var queryParams = $('#storageList').datagrid('options').queryParams;
			$('#storageListtb').find('*').each(function () {
				queryParams[$(this).attr('name')] = $(this).val();
			});
			$('#storageListtb').find("input[type='checkbox']").each(function () {
				$(this).attr('checked', false);
			});
			$('#storageListtb').find("input[type='radio']").each(function () {
				$(this).attr('checked', false);
			});
			$('#storageList').datagrid({
				url: 'storageController.do?datagrid&field=id,storageCode,storageName,repositoryCode,',
				pageNumber: 1
			});
		}</script>

		<table width="100%" id="storageList" toolbar="#storageListtb"></table>
		<div id="storageListtb" style="padding:3px; height: auto">
			<input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
			<div name="searchColums">
				<span>库位代码:<input type="text" name="storageCode"></span>
				<span>库位名称:<input type="text" name="storageName"></span>
				<span>归属仓库:<t:dictSelect id="repositoryCode" field="repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></span>
				<a href="#" class="easyui-linkbutton" style="float: right" onclick="storageListsearch();" plain="true" icon="icon-search">查询</a>
			</div>
			<div style="border-bottom-width:0;" class="datagrid-toolbar">
                <span>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('录入','storageController.do?addorupdate','storageList',null,null)">录入</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','storageController.do?addorupdate','storageList',null,null)">编辑</a>
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','storageController.do?addorupdate','storageList',null,null)">查看</a>
                </span>
				<div style="clear:both"></div>
			</div>
		</div>
	</div>
</div>
<script>
	$("#search").click(function(){
		generateSearchParam('storageList',['storageCode','storageName','repositoryCode']);
	})
</script>