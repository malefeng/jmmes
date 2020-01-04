<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>原料出入库列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
 </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="materialWarehousIOController.do?save">
			<input id="id" name="id" type="hidden" value="${materialWarehousIOPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">原料编号:</label></td>
			<td class="value">
				<input nullmsg="请填写原料编号" errormsg="原料编号格式不对" class="inputxt" id="materialSerino" name="materialSerino" ignore="ignore"	value="${materialWarehousIOPage.materialSerino}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">原料代码:</label></td>
			<td class="value">
				<t:dictSelect field="materialCode" defaultVal="${materialWarehousIOPage.materialCode}" dictTable="t_raw_material_list" dictField="raw_material_code" dictText="raw_material_code" hasLabel="false" type="list"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">原料名称:</label></td>
			<td class="value">
				<t:dictSelect field="materialName" defaultVal="${materialWarehousIOPage.materialName}" dictTable="t_raw_material_list" dictField="raw_material_name" dictText="raw_material_name" hasLabel="false" type="list"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">规格型号:</label></td>
			<td class="value">
				<input nullmsg="请填写规格型号" errormsg="规格型号格式不对" class="inputxt" id="materialSize" name="materialSize" ignore="ignore"	value="${materialWarehousIOPage.materialSize}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">批次:</label></td>
			<td class="value">
				<input nullmsg="请填写批次" errormsg="批次格式不对" class="inputxt" id="batchNumber" name="batchNumber" ignore="ignore"	value="${materialWarehousIOPage.batchNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">入库数量:</label></td>
			<td class="value">
				<input nullmsg="请填写入库数量" errormsg="入库数量格式不对" class="inputxt" id="warehousingNumber"<c:if test="${materialWarehousIOPage.id !=null }">readonly</c:if>  name="warehousingNumber" ignore="ignore"	value="${materialWarehousIOPage.warehousingNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入库人:</label></td>
			<td class="value">
				<t:dictSelect field="warehousingPersonCode" defaultVal="${materialWarehousIOPage.warehousingPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">入库时间:</label></td>
			<td class="value">
				<input nullmsg="请填写入库时间" errormsg="入库时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="warehousingDate" name="warehousingDate" ignore="ignore"	  value="<fmt:formatDate value='${materialWarehousIOPage.warehousingDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入库仓库:</label></td>
			<td class="value">
				<t:dictSelect field="warehousePositionCode" defaultVal="${materialWarehousIOPage.warehousePositionCode}" dictTable="t_repository_list" dictField="repository_code" dictText="repository_name" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">入库库位:</label></td>
			<td class="value">
				<t:dictSelect field="warehouseSpaceCode" defaultVal="${materialWarehousIOPage.warehouseSpaceCode}" dictTable="t_storage_list" dictField="storage_code" dictText="storage_name" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">库存数量:</label></td>
			<td class="value">
				<input readonly class="inputxt" id="warehouseOutNumber" name="warehouseOutNumber" ignore="ignore" value="${materialWarehousIOPage.warehouseOutNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">虚拟仓库数量:</label></td>
			<td class="value">
				<input readonly class="inputxt" id="virtualRepositoryNumber" name="virtualRepositoryNumber" ignore="ignore"	value="${materialWarehousIOPage.virtualRepositoryNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">单位:</label></td>
			<td class="value">
				<t:dictSelect field="unit" defaultVal="${materialWarehousIOPage.unit}" typeGroupCode="unit" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">虚拟仓库:</label></td>
			<td class="value">
				<t:dictSelect field="virtualRepository" typeGroupCode="workshop" defaultVal="${materialWarehousIOPage.virtualRepository}" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">最新出库人:</label></td>
			<td class="value">
				<t:dictSelect field="warehouseOutPersonCode" defaultVal="${materialWarehousIOPage.warehouseOutPersonCode}" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">最新出库时间:</label></td>
			<td class="value">
				<input nullmsg="请填写最新出库时间" errormsg="最新出库时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="warehouseOutDate" name="warehouseOutDate" ignore="ignore"	  value="<fmt:formatDate value='${materialWarehousIOPage.warehouseOutDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">库存状态:</label></td>
			<td class="value">
				<t:dictSelect field="ioType" defaultVal="${materialWarehousIOPage.ioType}" typeGroupCode="io_type" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">收料单号:</label></td>
			<td class="value">
				<input nullmsg="请填写收料单号" errormsg="收料单号格式不对" class="inputxt" id="receivingOrderNumber" name="receivingOrderNumber" ignore="ignore"	value="${materialWarehousIOPage.receivingOrderNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">收料单采购订单:</label></td>
			<td class="value">
				<input nullmsg="请填写收料单采购订单" errormsg="收料单采购订单格式不对" class="inputxt" id="purchaseOrderNumber" name="purchaseOrderNumber" ignore="ignore"	value="${materialWarehousIOPage.purchaseOrderNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">收料单销售订单:</label></td>
			<td class="value">
				<input nullmsg="请填写收料单销售订单" errormsg="收料单销售订单格式不对" class="inputxt" id="salesOrderNumber" name="salesOrderNumber" ignore="ignore"	value="${materialWarehousIOPage.salesOrderNumber}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">供应商:</label></td>
			<td class="value">
				<t:dictSelect field="supplierCode" dictTable="t_supplier_list" dictField="supplier_code" dictText="supplier_name" defaultVal="${materialWarehousIOPage.supplierCode}" readonly="true"></t:dictSelect>
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="materialWarehousIOController.do?materialWarehousNodeList&materialSerino=${materialWarehousIOPage.materialSerino}" icon="icon-search" title="原料出库详情列表" id="materialWarehousNode"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_materialWarehousNode_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].batchNumber" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].warehouseOutNumber" class="out_number" value="0" data-old="0" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].virtualRepositoryNumber" readonly maxlength="120" type="text" style="width:120px;"></td>
				<td align="left"><t:dictSelect field="materialWarehousNodeList[#index#].virtualRepository" typeGroupCode="workshop" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="materialWarehousNodeList[#index#].unit" typeGroupCode="unit" readonly="true"></t:dictSelect></td>
				<td align="left"><t:dictSelect field="materialWarehousNodeList[#index#].warehouseOutPersonCode" dictTable="t_s_base_user" dictField="username" dictText="realname" readonly="true"></t:dictSelect></td>
				<td align="left"><input name="materialWarehousNodeList[#index#].warehouseOutDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="" type="text" style="width:120px;" ></td>
				<td align="left"><t:dictSelect field="materialWarehousNodeList[#index#].warehouseOutState" typeGroupCode="io_type" readonly="true"></t:dictSelect></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].productionOrderNumber" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].prodReqOrderNumber" maxlength="120" type="text" style="width:120px;"></td>
				  <td align="left"><input name="materialWarehousNodeList[#index#].productionMaterialNumber" maxlength="120" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 <script>
	 $(function(){
	 	var id = $("#id").val();
	 	if(!!id){

		}
	 })
	 $(document).on("change",".out_number",function(e){
	 	//库存量
	 	var mainWarehouseOutNumber = $("#warehouseOutNumber");
	 	//虚拟仓库量
	 	var mainVirtualRepositoryNumber = $("#virtualRepositoryNumber");
	 	//原出库数量
		 var old_val = $(e.target).data("old");
	 	//现出库量
		 var new_val = e.target.value;
		 //出库增量
		 var add_num = new_val-old_val;
		 //本次入虚拟仓库数量
		 var virtualRepositoryNumber = $(e.target).parent().next().children("input");
		 if(mainWarehouseOutNumber.val()<add_num && mainVirtualRepositoryNumber.val()<add_num){
			 alert("剩余库存不足以扣除当前数量，请修改");
			 e.target.value = old_val.val();
			 return false;
		 }
		 if(mainWarehouseOutNumber.val()>0){
		 	//首次出库
			 mainVirtualRepositoryNumber.val(mainWarehouseOutNumber.val()-add_num);
		 	 mainWarehouseOutNumber.val("0");
		 }else{
		 	//二次出库
			 mainVirtualRepositoryNumber.val(mainVirtualRepositoryNumber.val()-add_num);
		 }
		 $(e.target).data("old",new_val);
		 virtualRepositoryNumber.val(mainVirtualRepositoryNumber.val());
	 })

	 $("#warehousingNumber").change(function(){
	 	$("#warehouseOutNumber").val($(this).val());
	 });
 </script>
 </body>