<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addSalesReleaseOrgNodeBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delSalesReleaseOrgNodeBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addSalesReleaseOrgNodeBtn').bind('click', function(){   
 		 var tr =  $("#add_salesReleaseOrgNode_table_template tr").clone();
	 	 $("#add_salesReleaseOrgNode_table").append(tr);
	 	 resetTrNum('add_salesReleaseOrgNode_table');
    });  
	$('#delSalesReleaseOrgNodeBtn').bind('click', function(){   
      	$("#add_salesReleaseOrgNode_table").find("input[name$='ck']:checked").parent().parent().remove();   
        resetTrNum('add_salesReleaseOrgNode_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addSalesReleaseOrgNodeBtn" href="#">添加</a> <a id="delSalesReleaseOrgNodeBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="salesReleaseOrgNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">成品代码</td>
				  <td align="left" bgcolor="#EEEEEE">成品名称</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">供应商属性</td>
				  <td align="left" bgcolor="#EEEEEE">宽幅</td>
				  <td align="left" bgcolor="#EEEEEE">库存单位</td>
				  <td align="left" bgcolor="#EEEEEE">应发数量</td>
				  <td align="left" bgcolor="#EEEEEE">实发数量</td>
				  <td align="left" bgcolor="#EEEEEE">计价单位</td>
				  <td align="left" bgcolor="#EEEEEE">计价数量</td>
				  <td align="left" bgcolor="#EEEEEE">单价</td>
				  <td align="left" bgcolor="#EEEEEE">含税单价</td>
				  <td align="left" bgcolor="#EEEEEE">是否赠品</td>
				  <td align="left" bgcolor="#EEEEEE">批号</td>
				  <td align="left" bgcolor="#EEEEEE">仓库</td>
				  <td align="left" bgcolor="#EEEEEE">生产订单号</td>
				  <td align="left" bgcolor="#EEEEEE">库存状态</td>
				  <td align="left" bgcolor="#EEEEEE">备注</td>
	</tr>
	<tbody id="add_salesReleaseOrgNode_table">	
	<c:if test="${fn:length(salesReleaseOrgNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				<td align="left"><t:dictSelect field="salesReleaseNodeList[0].finishedCode" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="salesReleaseNodeList[0].finishedName" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].finishedSize" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[0].supplierAttr" typeGroupCode="supplAttr" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].wideInWidth" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].inventoryUnit" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].shouldSendNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].actualSendNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].valuationUnit" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].valuationNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].unitPrice" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].unitPriceWithTax" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].isFree" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].batchNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[0].repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].productionOrderNumber" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="salesReleaseOrgNodeList[0].repositoryStatus" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="salesReleaseOrgNodeList[0].remark" maxlength="120" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(salesReleaseOrgNodeList)  > 0 }">
		<c:forEach items="${salesReleaseOrgNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="salesReleaseOrgNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				<td align="left"><t:dictSelect field="salesReleaseNodeList[${stuts.index }].finishedCode" defaultVal="${poVal.finishedCode }" dictTable="t_finished_product_list" dictField="finished_code" dictText="finished_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="salesReleaseNodeList[${stuts.index }].finishedName" defaultVal="${poVal.finishedName }" dictTable="t_finished_product_list" dictField="finished_name" dictText="finished_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].finishedSize" maxlength="120" value="${poVal.finishedSize }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="salesReleaseOrgNodeList[${stuts.index }].supplierAttr" defaultVal="${poVal.supplierAttr }" typeGroupCode="supplAttr" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].wideInWidth" maxlength="120" value="${poVal.wideInWidth }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].inventoryUnit" maxlength="120" value="${poVal.inventoryUnit }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].shouldSendNumber" maxlength="120" value="${poVal.shouldSendNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].actualSendNumber" maxlength="120" value="${poVal.actualSendNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].valuationUnit" maxlength="120" value="${poVal.valuationUnit }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].valuationNumber" maxlength="120" value="${poVal.valuationNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].unitPrice" maxlength="120" value="${poVal.unitPrice }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].unitPriceWithTax" maxlength="120" value="${poVal.unitPriceWithTax }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].isFree" maxlength="120" value="${poVal.isFree }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].batchNumber" maxlength="120" value="${poVal.batchNumber }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="salesReleaseOrgNodeList[${stuts.index }].repositoryCode" defaultVal="${poVal.repositoryCode }" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].productionOrderNumber" maxlength="120" value="${poVal.productionOrderNumber }" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="salesReleaseOrgNodeList[${stuts.index }].repositoryStatus" defaultVal="${poVal.repositoryStatus }" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="salesReleaseOrgNodeList[${stuts.index }].remark" maxlength="120" value="${poVal.remark }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>