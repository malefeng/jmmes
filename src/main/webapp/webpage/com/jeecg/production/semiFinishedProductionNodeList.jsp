<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addSemiFinishedProductionNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delSemiFinishedProductionNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addSemiFinishedProductionNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_semiFinishedProductionNode_table_template tr").clone();
	 	 $("#add_semiFinishedProductionNode_table").append(tr);
	 	 resetTrNum('add_semiFinishedProductionNode_table');
    });  
	$('#delSemiFinishedProductionNodeBtn').bind('click', function(){   
      	$("#add_semiFinishedProductionNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_semiFinishedProductionNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addSemiFinishedProductionNodeBtn" href="#">添加</a> <a id="delSemiFinishedProductionNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="semiFinishedProductionNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">原料编号</td>
				  <td align="left" bgcolor="#EEEEEE">原料代码</td>
				  <td align="left" bgcolor="#EEEEEE">原料名称</td>
				  <td align="left" bgcolor="#EEEEEE">原料批次</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">数量</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
	</tr>
	<tbody id="add_semiFinishedProductionNode_table">	
	<c:if test="${fn:length(semiFinishedProductionNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="semiFinishedProductionNodeList[0].rawMaterialNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="semiFinishedProductionNodeList[0].rawMaterialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="semiFinishedProductionNodeList[0].rawMaterialName" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="semiFinishedProductionNodeList[0].rawMaterialBatchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="semiFinishedProductionNodeList[0].rawMaterialSize" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="semiFinishedProductionNodeList[0].rawMaterialAmount" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="semiFinishedProductionNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(semiFinishedProductionNodeList)  > 0 }">
		<c:forEach items="${semiFinishedProductionNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="semiFinishedProductionNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="semiFinishedProductionNodeList[${stuts.index }].rawMaterialNumber" maxlength="120" value="${poVal.rawMaterialNumber }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="semiFinishedProductionNodeList[${stuts.index }].rawMaterialCode" defaultVal="${poVal.rawMaterialCode }" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="semiFinishedProductionNodeList[${stuts.index }].rawMaterialName" defaultVal="${poVal.rawMaterialName }" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="semiFinishedProductionNodeList[${stuts.index }].rawMaterialBatchNumber" maxlength="120" value="${poVal.rawMaterialBatchNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="semiFinishedProductionNodeList[${stuts.index }].rawMaterialSize" maxlength="120" value="${poVal.rawMaterialSize }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="semiFinishedProductionNodeList[${stuts.index }].rawMaterialAmount" maxlength="120" value="${poVal.rawMaterialAmount }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="semiFinishedProductionNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>