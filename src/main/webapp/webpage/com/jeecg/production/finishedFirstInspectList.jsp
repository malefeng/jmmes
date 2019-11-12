<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFinishedFirstInspectBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFinishedFirstInspectBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFinishedFirstInspectBtn').bind('click', function(){   
 		 var tr =  $("#add_finishedFirstInspect_table_template tr").clone();
	 	 $("#add_finishedFirstInspect_table").append(tr);
	 	 resetTrNum('add_finishedFirstInspect_table');
    });  
	$('#delFinishedFirstInspectBtn').bind('click', function(){   
      	$("#add_finishedFirstInspect_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_finishedFirstInspect_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addFinishedFirstInspectBtn" href="#">添加</a> <a id="delFinishedFirstInspectBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="finishedFirstInspect_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">首检编号</td>
				  <td align="left" bgcolor="#EEEEEE">首检次数</td>
				  <td align="left" bgcolor="#EEEEEE">首检状态</td>
				  <td align="left" bgcolor="#EEEEEE">首检结果</td>
				  <td align="left" bgcolor="#EEEEEE">首检时间</td>
	</tr>
	<tbody id="add_finishedFirstInspect_table">	
	<c:if test="${fn:length(finishedFirstInspectList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="finishedFirstInspectList[0].firstInspectNumber" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="finishedFirstInspectList[0].firstInspectTimes" readonly maxlength="" value="1" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="finishedFirstInspectList[0].firstInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="finishedFirstInspectList[0].firstInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="finishedFirstInspectList[0].firstInspectDate" value="" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(finishedFirstInspectList)  > 0 }">
		<c:forEach items="${finishedFirstInspectList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="finishedFirstInspectList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
			   <td align="left"><input name="finishedFirstInspectList[${stuts.index }].firstInspectNumber" maxlength="" value="${poVal.firstInspectNumber }" type="text" style="width:120px;"></td>
			   <td align="left"><input name="finishedFirstInspectList[${stuts.index }].firstInspectTimes" readonly maxlength="" value="${poVal.firstInspectTimes }" type="text" style="width:120px;"></td>
			   <td align="left"><t:dictSelect field="finishedFirstInspectList[${stuts.index }].firstInspectState" defaultVal="${poVal.firstInspectState }" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
			   <td align="left"><t:dictSelect field="finishedFirstInspectList[${stuts.index }].firstInspectResult" defaultVal="${poVal.firstInspectResult }" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				<td align="left"><input name="finishedFirstInspectList[${stuts.index }].firstInspectDate" class="Wdate" value="${poVal.firstInspectDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>