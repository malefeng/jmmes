package test;

import org.jeecgframework.codegenerate.generate.onetomany.CodeGenerateOneToMany;
import org.jeecgframework.codegenerate.pojo.onetomany.CodeParamEntity;
import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 代码生成器入口【一对多】
 * @author 张代浩
 * @site www.jeecg.com
 *
 */
public class JeecgOneToMainUtil {

	/**
	 * 一对多(父子表)数据模型，生成方法
	 * @param args
	 */
	public static void main(String[] args) {
		//第一步：设置主表配置
		CodeParamEntity codeParamEntityIn = new CodeParamEntity();
		codeParamEntityIn.setTableName("t_semi_finished_production_list");//表名
		codeParamEntityIn.setEntityName("SemiFinishedProduction");	 //实体名
		codeParamEntityIn.setEntityPackage("production");	 //包名
		codeParamEntityIn.setFtlDescription("半成品生产列表");	 //描述

		//第二步：设置子表集合配置
		List<SubTableEntity> subTabParamIn = new ArrayList<SubTableEntity>();
		//[1].子表一
		SubTableEntity po = new SubTableEntity();
		po.setTableName("t_semi_finished_production_node_list");//表名
		po.setEntityName("SemiFinishedProductionNode");	    //实体名
		po.setEntityPackage("production");	        //包名
		po.setFtlDescription("半成品生产物料详情");       //描述
		//子表外键参数配置
		/*说明:
		 * a) 子表引用主表主键ID作为外键，外键字段必须以_ID结尾;
		 * b) 主表和子表的外键字段名字，必须相同（除主键ID外）;
		 * c) 多个外键字段，采用逗号分隔;
		 */
		po.setForeignKeys(new String[]{"semi_finished_serino"});
		subTabParamIn.add(po);
		//[2].子表二
		/*SubTableEntity po2 = new SubTableEntity();
		po2.setTableName("t_production_requisition_org_node");		//表名
		po2.setEntityName("ProductionRequisitionOrgNode");			//实体名
		po2.setEntityPackage("invoices"); 				//包名
		po2.setFtlDescription("销售出库原始物料详情");			//描述
		//子表外键参数配置
		*//*说明:
		 * a) 子表引用主表主键ID作为外键，外键字段必须以_ID结尾;
		 * b) 主表和子表的外键字段名字，必须相同（除主键ID外）;
		 * c) 多个外键字段，采用逗号分隔;
		 *//*
		po2.setForeignKeys(new String[]{"inspect_id"});
		subTabParamIn.add(po2);*/
		codeParamEntityIn.setSubTabParam(subTabParamIn);

		//第三步：一对多(父子表)数据模型,代码生成
		CodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn);
	}
}
