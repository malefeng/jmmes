<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addMaterialWarehousNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delMaterialWarehousNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addMaterialWarehousNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_materialWarehousNode_table_template tr").clone();
	 	 $("#add_materialWarehousNode_table").append(tr);
	 	 resetTrNum('add_materialWarehousNode_table');
    });  
	$('#delMaterialWarehousNodeBtn').bind('click', function(){   
      	$("#add_materialWarehousNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_materialWarehousNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addMaterialWarehousNodeBtn" href="#">添加</a> <a id="delMaterialWarehousNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="materialWarehousNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">原料批次</td>
				  <td align="left" bgcolor="#EEEEEE">出库数量</td>
				  <td align="left" bgcolor="#EEEEEE">本次入虚拟仓库数量</td>
				  <td align="left" bgcolor="#EEEEEE">虚拟仓库</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">出库人</td>
				  <td align="left" bgcolor="#EEEEEE">出库时间</td>
				  <td align="left" bgcolor="#EEEEEE">出库状态</td>
				  <td align="left" bgcolor="#EEEEEE">生产派工单号</td>
				  <td align="left" bgcolor="#EEEEEE">生产订单号</td>
				  <td align="left" bgcolor="#EEEEEE">生产领料单号</td>
	</tr>
	<tbody id="add_materialWarehousNode_table">	
	<c:if test="${fn:length(materialWarehousNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="materialWarehousNodeList[0].batchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="materialWarehousNodeList[0].warehouseOutNumber" class="out_number" value="0" data-old="0" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="materialWarehousNodeList[0].virtualRepositoryNumber" readonly maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="materialWarehousNodeList[0].virtualRepository" typeGroupCode="workshop" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="materialWarehousNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="materialWarehousNodeList[0].warehouseOutPersonCode" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="materialWarehousNodeList[0].warehouseOutDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="materialWarehousNodeList[0].warehouseOutState" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="materialWarehousNodeList[0].productionOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="materialWarehousNodeList[0].productionMaterialNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="materialWarehousNodeList[0].prodReqOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(materialWarehousNodeList)  > 0 }">
		<c:forEach items="${materialWarehousNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="materialWarehousNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].batchNumber" maxlength="120" value="${poVal.batchNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].warehouseOutNumber" class="out_number" maxlength="120" value="${poVal.warehouseOutNumber+0 }" data-old="0" type="text" style="width:120px;"></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].virtualRepositoryNumber" readonly maxlength="120" value="${poVal.virtualRepositoryNumber }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="materialWarehousNodeList[${stuts.index }].virtualRepository" defaultVal="${poVal.virtualRepository }" typeGroupCode="workshop" readonly="true"></t:dictSelect></td>
					<td align="left"><t:dictSelect field="materialWarehousNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
					<td align="left"><t:dictSelect field="materialWarehousNodeList[${stuts.index }].warehouseOutPersonCode" defaultVal="${poVal.warehouseOutPersonCode }" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].warehouseOutDate" maxlength="" value="${poVal.warehouseOutDate }" type="text" style="width:120px;"></td>
					<td align="left"><t:dictSelect field="materialWarehousNodeList[${stuts.index }].warehouseOutState" defaultVal="${poVal.warehouseOutState }" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].productionOrderNumber" maxlength="120" value="${poVal.productionOrderNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].productionMaterialNumber" maxlength="120" value="${poVal.prodReqOrderNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="materialWarehousNodeList[${stuts.index }].prodReqOrderNumber" maxlength="120" value="${poVal.productionMaterialNumber }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>