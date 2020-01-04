<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFinishedInspectItemNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFinishedInspectItemNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFinishedInspectItemNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_finishedInspectItemNode_table_template tr").clone();
	 	 $("#add_finishedInspectItemNode_table").append(tr);
	 	 resetTrNum('add_finishedInspectItemNode_table');
    });  
	$('#delFinishedInspectItemNodeBtn').bind('click', function(){   
      	$("#add_finishedInspectItemNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_finishedInspectItemNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addFinishedInspectItemNodeBtn" href="#">添加</a> <a id="delFinishedInspectItemNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="finishedInspectItemNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">检验次数</td>
				  <td align="left" bgcolor="#EEEEEE">检验状态</td>
				  <td align="left" bgcolor="#EEEEEE">检验结果</td>
				  <td align="left" bgcolor="#EEEEEE">检验时间</td>
				  <td align="left" bgcolor="#EEEEEE">检验编号</td>
	</tr>
	<tbody id="add_finishedInspectItemNode_table">	
	<c:if test="${fn:length(finishedInspectItemNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="finishedInspectItemNodeList[0].inspectTimes" maxlength="32" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="finishedInspectItemNodeList[0].inspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedInspectItemNodeList[0].inspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="finishedInspectItemNodeList[0].inspectDate" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="finishedInspectItemNodeList[0].inspectNumber" maxlength="32" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(finishedInspectItemNodeList)  > 0 }">
		<c:forEach items="${finishedInspectItemNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="finishedInspectItemNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="finishedInspectItemNodeList[${stuts.index }].inspectTimes" maxlength="32" value="${poVal.inspectTimes }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedInspectItemNodeList[${stuts.index }].firstInspectState" defaultVal="${poVal.inspectState }" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedInspectItemNodeList[${stuts.index }].firstInspectResult" defaultVal="${poVal.inspectResult }" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="finishedInspectItemNodeList[${stuts.index }].inspectDate" maxlength="" value="${poVal.inspectDate }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedInspectItemNodeList[${stuts.index }].inspectNumber" maxlength="32" value="${poVal.inspectNumber }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>