<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addSalesReleaseNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delSalesReleaseNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addSalesReleaseNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_salesReleaseNode_table_template tr").clone();
	 	 $("#add_salesReleaseNode_table").append(tr);
	 	 resetTrNum('add_salesReleaseNode_table');
    });  
	$('#delSalesReleaseNodeBtn').bind('click', function(){   
      	$("#add_salesReleaseNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_salesReleaseNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addSalesReleaseNodeBtn" href="#">添加</a> <a id="delSalesReleaseNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="salesReleaseNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td data-print="false" align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">成品编号</td>
				  <td align="left" bgcolor="#EEEEEE">成品代码</td>
				  <td align="left" bgcolor="#EEEEEE">成品名称</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">批号</td>
				  <td align="left" bgcolor="#EEEEEE">单价</td>
				  <td align="left" bgcolor="#EEEEEE">含税单价</td>
				  <td align="left" bgcolor="#EEEEEE">仓库</td>
				  <td align="left" bgcolor="#EEEEEE">生产订单号</td>
	</tr>
	<tbody id="add_salesReleaseNode_table">	
	<c:if test="${fn:length(salesReleaseNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="salesReleaseNodeList[0].finishedSerino" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left" class="select_finishedCode"><t:dictSelect field="salesReleaseNodeList[0].finishedCode" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left" class="select_finishedName"><t:dictSelect field="salesReleaseNodeList[0].finishedName" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseNodeList[0].finishedSize" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseNodeList[0].batchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseNodeList[0].unitPrice" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseNodeList[0].unitPriceWithTax" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="salesReleaseNodeList[0].repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseNodeList[0].productionOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(salesReleaseNodeList)  > 0 }">
		<c:forEach items="${salesReleaseNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="salesReleaseNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].finishedSerino" maxlength="120" value="${poVal.finishedSerino }" type="text" style="width:120px;"></td>
					<td align="left" class="select_finishedCode"><t:dictSelect field="salesReleaseNodeList[${stuts.index }].finishedCode" defaultVal="${poVal.finishedCode }" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
					<td align="left" class="select_finishedName"><t:dictSelect field="salesReleaseNodeList[${stuts.index }].finishedName" defaultVal="${poVal.finishedName }" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].finishedSize" maxlength="120" value="${poVal.finishedSize }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].batchNumber" maxlength="120" value="${poVal.batchNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].unitPrice" maxlength="120" value="${poVal.unitPrice }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].unitPriceWithTax" maxlength="120" value="${poVal.unitPriceWithTax }" type="text" style="width:120px;"></td>
					<td align="left"><t:dictSelect field="salesReleaseNodeList[${stuts.index }].repositoryCode" defaultVal="${poVal.repositoryCode }" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseNodeList[${stuts.index }].productionOrderNumber" maxlength="120" value="${poVal.productionOrderNumber }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>
<script>
	$(document).ready(function(){
		$(document).on('change', 'td.select_finishedCode',function(){
			var finishedCode = $(this).children("select").find("option:selected").val();
			var tds = $(this).find("~ td");
			if(!!finishedCode){
				$.getJSON("finishedProductController.do?getByParam",{finishedCode:finishedCode},function(data){
					if(!!data&&data.length>0){
						tds.eq(0).children()[0].value = data[0]['finishedName'];
						tds.eq(1).children()[0].value = data[0]['finishedSize'];
					}
				})
			}else{
				tds.eq(0).children()[0].value = '';
				tds.eq(1).children()[0].value = '';
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
						tds.eq(0).children()[0].value = data[0]['finishedSize'];
					}
				})
			}else{
				finishedCode.children()[0].value = '';
				tds.eq(0).children()[0].value = '';
			}
		})
	})
</script>