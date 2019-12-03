<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addCheckNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delCheckNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addCheckNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_checkNode_table_template tr").clone();
	 	 $("#add_checkNode_table").append(tr);
	 	 resetTrNum('add_checkNode_table');
    });  
	$('#delCheckNodeBtn').bind('click', function(){   
      	$("#add_checkNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_checkNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addCheckNodeBtn" href="#">添加</a> <a id="delCheckNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="checkNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">盘点类型</td>
				  <td align="left" bgcolor="#EEEEEE">物料代码</td>
				  <td align="left" bgcolor="#EEEEEE">物料名称</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">批次</td>
				  <td align="left" bgcolor="#EEEEEE">库存数量</td>
				  <td align="left" bgcolor="#EEEEEE">盘点数量</td>
				  <td align="left" bgcolor="#EEEEEE">盈亏数量</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">仓库</td>
				  <td align="left" bgcolor="#EEEEEE">库位</td>
	</tr>
	<tbody id="add_checkNode_table">	
	<c:if test="${fn:length(checkNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><t:dictSelect field="checkNodeList[0].checkType" typeGroupCode="check_type"  hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="checkNodeList[0].mtlCode" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].mtlName" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].mtlSize" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].mtlBatch" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].stockNum" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].checkNum" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="checkNodeList[0].profitLossNum" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="checkNodeList[0].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="checkNodeList[0].repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="checkNodeList[0].storageCode" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(checkNodeList)  > 0 }">
		<c:forEach items="${checkNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="checkNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><t:dictSelect field="checkNodeList[${stuts.index }].checkType" defaultVal="${poVal.checkType }" typeGroupCode="check_type"  hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].mtlCode" maxlength="120" value="${poVal.mtlCode }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].mtlName" maxlength="120" value="${poVal.mtlName }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].mtlSize" maxlength="120" value="${poVal.mtlSize }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].mtlBatch" maxlength="120" value="${poVal.mtlBatch }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].stockNum" maxlength="120" value="${poVal.stockNum }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].checkNum" maxlength="120" value="${poVal.checkNum }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="checkNodeList[${stuts.index }].profitLossNum" maxlength="120" value="${poVal.profitLossNum }" type="text" style="width:120px;"></td>
				   <td align="left"><t:dictSelect field="checkNodeList[${stuts.index }].unit" defaultVal="${poVal.unit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				   <td align="left"><t:dictSelect field="checkNodeList[${stuts.index }].repositoryCode" defaultVal="${poVal.repositoryCode }" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				   <td align="left"><t:dictSelect field="checkNodeList[${stuts.index }].storageCode" defaultVal="${poVal.storageCode }" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>