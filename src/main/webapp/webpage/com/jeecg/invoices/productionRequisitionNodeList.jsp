<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addProductionRequisitionNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delProductionRequisitionNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addProductionRequisitionNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_productionRequisitionNode_table_template tr").clone();
	 	 $("#add_productionRequisitionNode_table").append(tr);
	 	 resetTrNum('add_productionRequisitionNode_table');
    });  
	$('#delProductionRequisitionNodeBtn').bind('click', function(){   
      	$("#add_productionRequisitionNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_productionRequisitionNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addProductionRequisitionNodeBtn" href="#">添加</a> <a id="delProductionRequisitionNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="productionRequisitionNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" data-print="false" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">原料编号</td>
				  <td align="left" bgcolor="#EEEEEE">原料代码</td>
				  <td align="left" bgcolor="#EEEEEE">原料名称</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">数量</td>
				  <td align="left" bgcolor="#EEEEEE">批号</td>
				  <%--<td align="left" bgcolor="#EEEEEE">成品代码</td>
				  <td align="left" bgcolor="#EEEEEE">成品名称</td>
				  <td align="left" bgcolor="#EEEEEE">生产订单号</td>
				  <td align="left" bgcolor="#EEEEEE">生产派工号</td>--%>
				  <td align="left" bgcolor="#EEEEEE">原料入库时间</td>
	</tr>
	<tbody id="add_productionRequisitionNode_table">	
	<c:if test="${fn:length(productionRequisitionNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="productionRequisitionNodeList[0].rawMaterialSerino" maxlength="120" type="text" style="width:120px;" ></td>
				<td align="left" class="select_rawMaterialCode"><t:dictSelect field="productionRequisitionNodeList[0].rawMaterialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left" class="select_rawMaterialName"><t:dictSelect field="productionRequisitionNodeList[0].rawMaterialName" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="productionRequisitionNodeList[0].rawMaterialNum" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="productionRequisitionNodeList[0].batchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <%--<td align="left" class="select_finishedCode"><t:dictSelect field="productionRequisitionNodeList[0].finishedCode" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left" class="select_finishedName"><t:dictSelect field="productionRequisitionNodeList[0].finishedName" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="productionRequisitionNodeList[0].productionOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="productionRequisitionNodeList[0].productionDispatchingNumber" maxlength="120" type="text" style="width:120px;" ></td>--%>
				  <td align="left"><input name="productionRequisitionNodeList[0].warehousingDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(productionRequisitionNodeList)  > 0 }">
		<c:forEach items="${productionRequisitionNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="productionRequisitionNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].rawMaterialSerino" maxlength="120" value="${poVal.rawMaterialSerino }" type="text" style="width:120px;"></td>
				<td align="left" class="select_rawMaterialCode"><t:dictSelect field="productionRequisitionNodeList[${stuts.index }].rawMaterialCode" defaultVal="${poVal.rawMaterialCode }" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left" class="select_rawMaterialName"><t:dictSelect field="productionRequisitionNodeList[${stuts.index }].rawMaterialName" defaultVal="${poVal.rawMaterialName }" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].rawMaterialNum" maxlength="120" value="${poVal.rawMaterialNum }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].batchNumber" maxlength="120" value="${poVal.batchNumber }" type="text" style="width:120px;"></td>
				<%--<td align="left" class="select_finishedCode"><t:dictSelect field="productionRequisitionNodeList[${stuts.index }].finishedCode" defaultVal="${poVal.finishedCode }" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left" class="select_finishedName"><t:dictSelect field="productionRequisitionNodeList[${stuts.index }].finishedName" defaultVal="${poVal.finishedName }" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].productionOrderNumber" maxlength="120" value="${poVal.productionOrderNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].productionDispatchingNumber" maxlength="120" value="${poVal.productionDispatchingNumber }" type="text" style="width:120px;"></td>--%>
				   <td align="left"><input name="productionRequisitionNodeList[${stuts.index }].warehousingDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" value="${poVal.warehousingDate }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>
<script>
	$(document).ready(function(){
		$(document).on('change', 'td.select_rawMaterialCode',function(){
			var rawMaterialCode = $(this).children("select").find("option:selected").val();
			var tds = $(this).find("~ td");
			if(!!rawMaterialCode){
				$.getJSON("rawMaterialController.do?getByParam",{rawMaterialCode:rawMaterialCode},function(data){
					if(!!data&&data.length>0){
						tds.eq(0).children()[0].value = data[0]['rawMaterialName'];
						tds.eq(1).children()[0].value = data[0]['rawMaterialUnit'];
					}
				})
			}else{
				tds.eq(0).children()[0].value = '';
				tds.eq(1).children()[0].value = '';
			}

		})
		$(document).on('change',"td.select_rawMaterialName",function(){
			var tds = $(this).find("~ td");
			var rawMaterialName = $(this).children("select").find("option:selected").val();
			var rawMaterialCodeTag = $(this).prev("td");
			if(!!rawMaterialName){
				$.getJSON("rawMaterialController.do?getByParam",{rawMaterialName:rawMaterialName},function(data){
					if(!!data&&data.length>0){
						rawMaterialCodeTag.children()[0].value = data[0]['rawMaterialCode'];
						tds.eq(0).children()[0].value = data[0]['rawMaterialUnit'];
					}
				})
			}else{
				rawMaterialCodeTag.children()[0].value = '';
				tds.eq(0).children()[0].value = '';
			}
		})
		$(document).on('change', 'td.select_finishedCode',function(){
			var finishedCode = $(this).children("select").find("option:selected").val();
			var tds = $(this).find("~ td");
			if (!!finishedCode){
				$.getJSON("finishedProductController.do?getByParam",{finishedCode:finishedCode},function(data){
					if(!!data&&data.length>0){
						tds.eq(0).children()[0].value = data[0]['finishedName'];
					}
				})
			} else{
				tds.eq(0).children()[0].value = '';
			}

		})
		$(document).on('change',"td.select_finishedName",function(){
			var tds = $(this).find("~ td");
			var finishedName = $(this).children("select").find("option:selected").val();
			var finishedCode = $(this).prev("td");
			if(!!finishedName){
				$.getJSON("finishedProductController.do?getByParam",{finishedName:finishedName},function(data){
					if(!!data&&data.length>0){
						finishedCode.children()[0].value = data[0]['finishedCode'];
					}
				})
			}else{
				finishedCode.children()[0].value = '';
			}
		})
	})
</script>