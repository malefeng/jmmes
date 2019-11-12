<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>生产领料单</title>
  <t:base type="jquery,easyui,tools,DatePicker,print,common"></t:base>
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}

	function removeEmptTr(tableId){
		$tbody = $("#"+tableId+"");
		$tr = $tbody.find(">tr");
		$tr.each(function(i){
			var flag = true;
			$(':input:not(input[type="checkbox"]), select', this).each(function(){
				var $this = $(this), val = $this.val(),$seletedOption = $this.find("option:selected");
				if(!!val||($seletedOption.length>0&&!!$seletedOption.eq(0).val())){
					flag = false;
					return false;
				}
			});
			if(flag){
				$tr.eq(i).remove();
			}
		});
		resetTrNum(tableId);
	}
 </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="productionRequisitionController.do?save">
			<input id="id" name="id" type="hidden" value="${productionRequisitionPage.id }">
			<table cellpadding="0" cellspacing="1" id="main_content" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">领料单编号:</label></td>
			<td class="value">
				<input nullmsg="请填写领料单编号" errormsg="领料单编号格式不对" class="inputxt check_notnull" id="receiptCode" name="receiptCode" ignore="ignore"	value="${productionRequisitionPage.receiptCode}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">领料日期:</label></td>
			<td class="value">
				<input nullmsg="请填写领料日期" errormsg="领料日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="requisitionDate" name="requisitionDate" ignore="ignore"	  value="<fmt:formatDate value='${productionRequisitionPage.requisitionDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">领料车间:</label></td>
			<td class="value">
				<t:dictSelect field="requisitionWorkshopCode"
							  defaultVal="${productionRequisitionPage.requisitionWorkshopCode}"
							  typeGroupCode="workshop"
							  readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">领料仓库:</label></td>
			<td class="value">
				<t:dictSelect field="repositoryCode" defaultVal="${productionRequisitionPage.repositoryCode}"
							  dictTable="t_repository_list" dictField="repository_code" dictText="repository_name"
							  readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">仓库管理员:</label></td>
				<td class="value">
					<t:dictSelect field="repositoryManagerCode"
								  defaultVal="${productionRequisitionPage.repositoryManagerCode}"
								  dictTable="t_s_base_user" dictField="username" dictText="realname"
								  readonly="true"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
				<td align="right"><label class="Validform_label">领料人:</label></td>
				<td class="value">
					<t:dictSelect field="acquirePersonCode" defaultVal="${productionRequisitionPage.acquirePersonCode}"
								  dictTable="t_s_base_user" dictField="username" dictText="realname"
								  readonly="true"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">生产订单号:</label></td>
			<td class="value">
				<input class="inputxt" id="productionOrderNumber" name="productionOrderNumber" ignore="ignore"	value="${productionRequisitionPage.productionOrderNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">生产派工单号:</label></td>
			<td class="value">
				<input class="inputxt" id="productionDispatchingNumber" name="productionDispatchingNumber" ignore="ignore"	value="${productionRequisitionPage.productionDispatchingNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">成品代码:</label></td>
			<td class="value">
				<t:dictSelect id="tab_select_finishedCode" field="finishedCode" defaultVal="${productionRequisitionPage.finishedCode}" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">成品名称:</label></td>
			<td class="value">
				<t:dictSelect id="tab_select_finishedName" field="finishedName" defaultVal="${productionRequisitionPage.finishedName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">计划生产数量:</label></td>
			<td class="value">
				<input nullmsg="请填写计划生产数量" errormsg="计划生产数量格式不对" class="inputxt" id="plannedProductionQuantity" name="plannedProductionQuantity" ignore="ignore"	value="${productionRequisitionPage.plannedProductionQuantity}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">计划开工日期:</label></td>
			<td class="value">
				<input nullmsg="请填写计划开工日期" errormsg="计划开工日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="plannedStartDate" name="plannedStartDate" ignore="ignore"	  value="<fmt:formatDate value='${productionRequisitionPage.plannedStartDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">计划完工日期:</label></td>
			<td class="value">
				<input nullmsg="请填写计划完工日期" errormsg="计划完工日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="plannedCompletionDate" name="plannedCompletionDate" ignore="ignore"	  value="<fmt:formatDate value='${productionRequisitionPage.plannedCompletionDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
				<td align="right"><label class="Validform_label">读取人:</label></td>
				<td class="value">
					<t:dictSelect field="readPersonCode" defaultVal="${productionRequisitionPage.readPersonCode}"
								  dictTable="t_s_base_user" dictField="username" dictText="realname"
								  readonly="true"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
				<tr>
					<td align="right"><label class="Validform_label">获取时间:</label></td>
					<td class="value">
						<input nullmsg="请填写获取时间" errormsg="获取时间格式不对" class="Wdate"
							   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" id="acquireTime"
							   name="acquireTime" ignore="ignore"
							   value="<fmt:formatDate value='${productionRequisitionPage.acquireTime}' type="date" pattern="yyyy-MM-dd"/>"/>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right"><label class="Validform_label">制单人:</label></td>
					<td class="value">
						<t:dictSelect field="creatorCode" defaultVal="${productionRequisitionPage.creatorCode}"
									  dictTable="t_s_base_user" dictField="username" dictText="realname"
									  readonly="true"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
			</tr>
				<tr>
					<td data-print="false" colspan="2" style="text-align: center">
						<button type="button" onclick="print_html()" class="ui_buttons">打印</button>
						<button type="button" onclick="dosing()" class="ui_buttons">配料</button>
					</td>
				</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="productionRequisitionController.do?productionRequisitionNodeList&id=${productionRequisitionPage.id}" icon="icon-search" title="生产领料单配料物料详情" id="productionRequisitionNode"></t:tab>
				 <t:tab href="productionRequisitionController.do?productionRequisitionOrgNodeList&id=${productionRequisitionPage.id}" icon="icon-search" title="生产领料单原始物料详情" id="productionRequisitionOrgNode"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_productionRequisitionNode_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td data-print="false" align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="productionRequisitionNodeList[#index#].rawMaterialSerino" maxlength="120" type="text" style="width:120px;"></td>
				<td align="left" class="select_rawMaterialCode"><t:dictSelect field="productionRequisitionNodeList[#index#].rawMaterialCode"
											   dictTable="t_raw_material_list" dictField="raw_material_code"
											   dictText="raw_material_code" hasLabel="false"
											   type="list"></t:dictSelect></td>
				<td align="left" class="select_rawMaterialName"><t:dictSelect field="productionRequisitionNodeList[#index#].rawMaterialName"
											   dictTable="t_raw_material_list" dictField="raw_material_name"
											   dictText="raw_material_name" hasLabel="false"
											   type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionNodeList[#index#].unit" typeGroupCode="unit"
											   readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="productionRequisitionNodeList[#index#].rawMaterialNum" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="productionRequisitionNodeList[#index#].batchNumber" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="productionRequisitionNodeList[#index#].warehousingDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_productionRequisitionOrgNode_table_template">
			<tr>
				<td align="center">
					<div style="width: 25px;" name="xh"></div>
				</td>
				<td data-print="false" align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].rawMaterialCode"
											   dictTable="t_raw_material_list" dictField="raw_material_code"
											   dictText="raw_material_code" hasLabel="false"
											   type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].rawMaterialName"
											   dictTable="t_raw_material_list" dictField="raw_material_name"
											   dictText="raw_material_name" hasLabel="false"
											   type="list"></t:dictSelect></td>
				<td align="left"><input name="productionRequisitionOrgNodeList[#index#].rawMaterialSize" maxlength="120"
										type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].unit" typeGroupCode="unit"
											   readonly="true"></t:dictSelect></td>
				<td align="left"><input name="productionRequisitionOrgNodeList[#index#].rawMaterialNum" maxlength="120"
										type="text" style="width:120px;"></td>
				<td align="left"><input name="productionRequisitionOrgNodeList[#index#].batchNumber" maxlength="120"
										type="text" style="width:120px;"></td>
				<td align="left"><input name="productionRequisitionOrgNodeList[#index#].productionOrderNumber"
										maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].finishedName"
											   dictTable="t_finished_product_list" dictField="finished_name"
											   dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
			</tr>
		 </tbody>
		</table>
  <script src="plug-in/common/js/dosing.js"></script>
  <script src="plug-in/common/js/autoComplete.js"></script>
  <script>
	  var properArrs1 = ['rawMaterialSerino','rawMaterialCode','rawMaterialName','unit','rawMaterialNum','batchNumber'/*,'finishedCode','finishedName','productionOrderNumber'*/,'warehousingDate'];
      function print_html(){
          LODOP=getLodop();
          LODOP.PRINT_INIT();
          LODOP.SET_PRINT_STYLE("FontSize",18);
          LODOP.SET_PRINT_STYLE("Bold",1);
          LODOP.SET_PRINT_STYLE("Alignment",2);
          LODOP.ADD_PRINT_BARCODE(29, 700, 50,50, "QRCode", '${productionRequisitionPage.receiptCode},LLD');
		  LODOP.SET_PRINT_STYLE("HOrient",2);
          LODOP.ADD_PRINT_TEXT(29,0,'RightMargin:0',38,"生产领料单");
          LODOP.ADD_PRINT_HTM(90,'5%',"90%",200,generateTab(getTabData("main_content")));
          LODOP.ADD_PRINT_HTM(300,'5%',"90%",400,generateTab(getTabData("productionRequisitionNode_table")));
          LODOP.PREVIEW();
      }

      function dosing(){
      	var requisitionWorkshopCode = $("select[name=requisitionWorkshopCode]").find("option:selected").val();
      	if(!requisitionWorkshopCode){
			$.messager.alert('error', '请先选择领料车间!', 'info');
			$("select[name=requisitionWorkshopCode]").click();
			return;
		}
		 var remainData = CountRemaining("productionRequisitionNode_table",3,6,"productionRequisitionOrgNode_table",2,6);
      	if(JSON.stringify(remainData) == JSON.stringify({})){
			  $.messager.alert('error','无需配货!','info');
			  return;
		}
      	$.getJSON("productionRequisitionController.do?dosing",{remainData:JSON.stringify(remainData),requisitionWorkshopCode:requisitionWorkshopCode},function(data){
			if(!!data&&data.length>0){
				//去除空行
				removeEmptTr('add_productionRequisitionNode_table');
				$.each(data,function(i,e){
					var tr =  $("#add_productionRequisitionNode_table_template tr").clone();
					generateTr(tr,e,"productionRequisitionNodeList",properArrs1,properArrs1);
					$("#add_productionRequisitionNode_table").append(tr);
					resetTrNum('add_productionRequisitionNode_table');
				})
			}else{
				$.messager.alert('error', '无可用数据!', 'info');
			}
		})
	  }
  </script>
 </body>
<script>

</script>
</html>