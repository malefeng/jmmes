<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFinishedProductionNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFinishedProductionNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFinishedProductionNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_finishedProductionNode_table_template tr").clone();
	 	 $("#add_finishedProductionNode_table").append(tr);
	 	 resetTrNum('add_finishedProductionNode_table');
    });  
	$('#delFinishedProductionNodeBtn').bind('click', function(){   
      	$("#add_finishedProductionNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_finishedProductionNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addFinishedProductionNodeBtn" href="#">添加</a> <a id="delFinishedProductionNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="finishedProductionNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">成分编码</td>
				  <td align="left" bgcolor="#EEEEEE">成分代码</td>
				  <td align="left" bgcolor="#EEEEEE">成分名称</td>
				  <td align="left" bgcolor="#EEEEEE">成分类型</td>
				  <td align="left" bgcolor="#EEEEEE">成分批次</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">数量</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
	</tr>
	<tbody id="add_finishedProductionNode_table">	
	<c:if test="${fn:length(finishedProductionNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"  class="select_rawMaterialSherio"><input name="finishedProductionNodeList[0].stuffNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="finishedProductionNodeList[0].code" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="finishedProductionNodeList[0].name" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="finishedProductionNodeList[0].typeCode" typeGroupCode="matType" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="finishedProductionNodeList[0].batchNubmer" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="finishedProductionNodeList[0].size" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="finishedProductionNodeList[0].amount" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="finishedProductionNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(finishedProductionNodeList)  > 0 }">
		<c:forEach items="${finishedProductionNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="finishedProductionNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="finishedProductionNodeList[${stuts.index }].stuffNumber" maxlength="120" value="${poVal.stuffNumber }" type="text" style="width:120px;"></td>
				<c:if test="${poVal.typeCode=='11'}">
					<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].code" defaultVal="${poVal.code }" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
					<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].name" defaultVal="${poVal.name }" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				</c:if>
				<c:if test="${poVal.typeCode=='22'}">
					<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].code" defaultVal="${poVal.code }" dictTable="t_semi_finished_product_list" dictField="semi_finished_code" dictText="semi_finished_name" hasLabel="false" type="list"></t:dictSelect></td>
					<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].name" defaultVal="${poVal.name }" dictTable="t_semi_finished_product_list" dictField="semi_finished_name" dictText="semi_finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				</c:if>
					<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].typeCode" defaultVal="${poVal.typeCode }" typeGroupCode="matType" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="finishedProductionNodeList[${stuts.index }].batchNubmer" maxlength="120" value="${poVal.batchNubmer }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedProductionNodeList[${stuts.index }].size" maxlength="120" value="${poVal.size }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedProductionNodeList[${stuts.index }].amount" maxlength="120" value="${poVal.amount }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedProductionNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>
<div hidden>
	<t:dictSelect field="finishedProductionNodeList[${stuts.index }].code" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect>
	<t:dictSelect field="finishedProductionNodeList[${stuts.index }].name" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect>
	<t:dictSelect field="finishedProductionNodeList[${stuts.index }].code" dictTable="t_semi_finished_product_list" dictField="semi_finished_code" dictText="semi_finished_name" hasLabel="false" type="list"></t:dictSelect>
	<t:dictSelect field="finishedProductionNodeList[${stuts.index }].name" dictTable="t_semi_finished_product_list" dictField="semi_finished_name" dictText="semi_finished_name" hasLabel="false" type="list"></t:dictSelect>
</div>