<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addPurchaseReceiptNodeBtn').linkbutton({
	    iconCls: 'icon-add'
	});
	$('#delPurchaseReceiptNodeBtn').linkbutton({
	    iconCls: 'icon-remove'
	});
	$('#addPurchaseReceiptNodeBtn').bind('click', function(){
 		 var tr =  $("#add_purchaseReceiptNode_table_template tr").clone();
	 	 $("#add_purchaseReceiptNode_table").append(tr);
	 	 resetTrNum('add_purchaseReceiptNode_table');
    });
	$('#delPurchaseReceiptNodeBtn').bind('click', function(){
      	$("#add_purchaseReceiptNode_table").find("input[name$='ck']:checked").parent().parent().remove();
        resetTrNum('add_purchaseReceiptNode_table');
    });
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addPurchaseReceiptNodeBtn" href="#">添加</a> <a id="delPurchaseReceiptNodeBtn" href="#">删除</a>
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="purchaseReceiptNode_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td data-print="false" align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">原料代码</td>
				  <td align="left" bgcolor="#EEEEEE">原料名称</td>
				  <td align="left" bgcolor="#EEEEEE">规格型号</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">批号</td>
				  <td align="left" bgcolor="#EEEEEE">实到数量</td>
				  <td align="left" bgcolor="#EEEEEE">实收数量</td>
				  <td align="left" bgcolor="#EEEEEE">拒收数量</td>
				  <td align="left" bgcolor="#EEEEEE">仓库</td>
				  <td align="left" bgcolor="#EEEEEE">库位</td>
				  <td align="left" bgcolor="#EEEEEE">库存状态</td>
				  <td align="left" bgcolor="#EEEEEE">拒收原因</td>
	</tr>
	<tbody id="add_purchaseReceiptNode_table">
	<c:if test="${fn:length(purchaseReceiptNodeList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				<td align="left" class="select_rawMaterialCode"><t:dictSelect field="purchaseReceiptNodeList[0].rawMaterialCode" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td align="left" class="select_rawMaterialName"><t:dictSelect field="purchaseReceiptNodeList[0].rawMaterialName" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].rawMaterialSize" maxlength="120" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="purchaseReceiptNodeList[0].rawMaterialUnit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].attr1" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].actualArrivalNumber" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].actualReceivedNumber" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].rejectionNumber" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="purchaseReceiptNodeList[0].repositoryCode" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="purchaseReceiptNodeList[0].repositorySpace" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect></td>
				  <td align="left"><t:dictSelect field="purchaseReceiptNodeList[0].inventoryStatus" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="purchaseReceiptNodeList[0].rejectionReason" maxlength="256" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(purchaseReceiptNodeList)  > 0 }">
		<c:forEach items="${purchaseReceiptNodeList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td data-print="false" align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="purchaseReceiptNodeList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				<td class="select_rawMaterialCode" align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].rawMaterialCode" defaultVal="${poVal.rawMaterialCode }" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect></td>
				<td class="select_rawMaterialName" align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].rawMaterialName" defaultVal="${poVal.rawMaterialName }" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].rawMaterialSize" maxlength="120" value="${poVal.rawMaterialSize }" type="text" style="width:120px;"></td>
				    <td align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].rawMaterialUnit" defaultVal="${poVal.rawMaterialUnit }" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].attr1" maxlength="" value="${poVal.attr1 }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].actualArrivalNumber" maxlength="" value="${poVal.actualArrivalNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].actualReceivedNumber" maxlength="" value="${poVal.actualReceivedNumber }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].rejectionNumber" maxlength="" value="${poVal.rejectionNumber }" type="text" style="width:120px;"></td>
                    <td align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].repositoryCode" defaultVal="${poVal.repositoryCode }" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect></td>
                    <td align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].repositorySpace" defaultVal="${poVal.repositorySpace }" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect></td>
				    <td align="left"><t:dictSelect field="purchaseReceiptNodeList[${stuts.index }].inventoryStatus" defaultVal="${poVal.inventoryStatus }" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				   <td align="left"><input name="purchaseReceiptNodeList[${stuts.index }].rejectionReason" maxlength="256" value="${poVal.rejectionReason }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>
</div>
<script>
	$(document).ready(function(){
		$(document).on('change', 'td.select_rawMaterialCode',function(){
			var rawMaterialCode = $(this).children("select").find("option:selected").val();
			var tds = $(this).find("~ td");
			if(!!rawMaterialCode){
				$.getJSON("rawMaterialController.do?getByParam",{rawMaterialCode:rawMaterialCode},function(data){
					if(!!data&&data.length>0){
						tds.eq(0).children()[0].value = data[0]['rawMaterialName'];
						tds.eq(1).children()[0].value = data[0]['rawMaterialSize'];
						tds.eq(2).children()[0].value = data[0]['rawMaterialUnit'];
					}
				})
			}else{
				tds.eq(0).children()[0].value = '';
				tds.eq(1).children()[0].value = '';
				tds.eq(2).children()[0].value = '';
			}

		})
		$(document).on('change',"td.select_rawMaterialName",function(){
			var tds = $(this).find("~ td");
			var rawMaterialName = $(this).children("select").find("option:selected").val();
			var rawMaterialCodeTag = $(this).prev("td");
			if(!!rawMaterialName){
				$.getJSON("rawMaterialController.do?getByParam",{rawMaterialName:rawMaterialName},function(data){
					if(!!data&&data.length>0){
						rawMaterialCodeTag.children()[0].value = data[0]['rawMaterialCode'];
						tds.eq(0).children()[0].value = data[0]['rawMaterialSize'];
						tds.eq(1).children()[0].value = data[0]['rawMaterialUnit'];
					}
				})
			}else{
				rawMaterialCodeTag.children()[0].value = '';
				tds.eq(0).children()[0].value = '';
				tds.eq(1).children()[0].value = '';
			}
		})
	})
</script>