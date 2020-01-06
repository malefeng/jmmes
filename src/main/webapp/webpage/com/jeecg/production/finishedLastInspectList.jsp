<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFinishedLastInspectBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFinishedLastInspectBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFinishedLastInspectBtn').bind('click', function(){   
 		 var tr =  $("#add_finishedLastInspect_table_template tr").clone();
	 	 $("#add_finishedLastInspect_table").append(tr);
	 	 resetTrNum('add_finishedLastInspect_table');
    });  
	$('#delFinishedLastInspectBtn').bind('click', function(){   
      	$("#add_finishedLastInspect_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_finishedLastInspect_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addFinishedLastInspectBtn" href="#">添加</a> <a id="delFinishedLastInspectBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="finishedLastInspect_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">末检编号</td>
				  <td align="left" bgcolor="#EEEEEE">末检次数</td>
				  <td align="left" bgcolor="#EEEEEE">末检状态</td>
				  <td align="left" bgcolor="#EEEEEE">末检结果</td>
				  <td align="left" bgcolor="#EEEEEE">末检时间</td>
				  <td align="left" bgcolor="#EEEEEE">总数量</td>
				  <td align="left" bgcolor="#EEEEEE">合格数量</td>
				  <td align="left" bgcolor="#EEEEEE">不合格数量</td>
	</tr>
	<tbody id="add_finishedLastInspect_table">	
	<c:if test="${fn:length(finishedLastInspectList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="finishedLastInspectList[0].lastInspectNumber" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="finishedLastInspectList[0].lastInspectTimes" maxlength="" value="1" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[0].lastInspectState" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[0].lastInspectResult" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				<td align="left"><input name="finishedLastInspectList[0].lastInspectDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><input name="finishedLastInspectList[0].count"  maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><input name="finishedLastInspectList[0].qualifiedCount" maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><input name="finishedLastInspectList[0].unqualifiedCount" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(finishedLastInspectList)  > 0 }">
		<c:forEach items="${finishedLastInspectList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="finishedLastInspectList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="finishedLastInspectList[${stuts.index }].lastInspectNumber" maxlength="" value="${poVal.lastInspectNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedLastInspectList[${stuts.index }].lastInspectTimes" maxlength="" value="${poVal.lastInspectTimes }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[${stuts.index }].lastInspectState" defaultVal="${poVal.lastInspectState }" typeGroupCode="inspeType" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="finishedLastInspectList[${stuts.index }].lastInspectResult" defaultVal="${poVal.lastInspectResult }" typeGroupCode="inspeRes" readonly="true"></t:dictSelect></td>
				<td align="left"><input name="finishedLastInspectList[${stuts.index }].lastInspectDate" class="Wdate" value="${poVal.lastInspectDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				   <td align="left"><input name="finishedLastInspectList[${stuts.index }].count" maxlength="" value="${poVal.count }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedLastInspectList[${stuts.index }].qualifiedCount" maxlength="" value="${poVal.qualifiedCount }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="finishedLastInspectList[${stuts.index }].unqualifiedCount" maxlength="" value="${poVal.unqualifiedCount }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>