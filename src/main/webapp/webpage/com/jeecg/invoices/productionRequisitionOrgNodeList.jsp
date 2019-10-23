<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addProductionRequisitionOrgNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delProductionRequisitionOrgNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addProductionRequisitionOrgNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_productionRequisitionOrgNode_table_template tr").clone();
	 	 $("#add_productionRequisitionOrgNode_table").append(tr);
	 	 resetTrNum('add_productionRequisitionOrgNode_table');
    });  
	$('#delProductionRequisitionOrgNodeBtn').bind('click', function(){   
      	$("#add_productionRequisitionOrgNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_productionRequisitionOrgNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addProductionRequisitionOrgNodeBtn" href="#">添加</a> <a id="delProductionRequisitionOrgNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="productionRequisitionOrgNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">原料代码</td>
				  <td align="left" bgcolor="#EEEEEE">原料名称</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">数量</td>
				  <td align="left" bgcolor="#EEEEEE">批号</td>
				  <td align="left" bgcolor="#EEEEEE">生产订单号</td>
				  <td align="left" bgcolor="#EEEEEE">成品名称</td>
	</tr>
	<tbody id="add_productionRequisitionOrgNode_table">	
	<c:if test="${fn:length(productionRequisitionOrgNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].rawMaterialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].rawMaterialName" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="productionRequisitionOrgNodeList[0].rawMaterialSize" maxlength="120" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="productionRequisitionOrgNodeList[0].rawMaterialNum" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="productionRequisitionOrgNodeList[0].batchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="productionRequisitionOrgNodeList[0].productionOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[0].finishedName" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect>
   			</tr>
	</c:if>
	<c:if test="${fn:length(productionRequisitionOrgNodeList)  > 0 }">
		<c:forEach items="${productionRequisitionOrgNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="productionRequisitionOrgNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[${stuts.index }].rawMaterialCode" defaultVal="${poVal.rawMaterialCode }" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[${stuts.index }].rawMaterialName" defaultVal="${poVal.rawMaterialName }" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="productionRequisitionOrgNodeList[${stuts.index }].rawMaterialSize" maxlength="120" value="${poVal.rawMaterialSize }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="productionRequisitionOrgNodeList[${stuts.index }].rawMaterialNum" maxlength="120" value="${poVal.rawMaterialNum }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="productionRequisitionOrgNodeList[${stuts.index }].batchNumber" maxlength="120" value="${poVal.batchNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="productionRequisitionOrgNodeList[${stuts.index }].productionOrderNumber" maxlength="120" value="${poVal.productionOrderNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><t:dictSelect field="productionRequisitionOrgNodeList[${stuts.index }].finishedName" defaultVal="${poVal.finishedName}" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>