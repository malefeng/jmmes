package com.jeecg.util;

import com.alibaba.fastjson.JSONObject;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryUtil {

    private static final String DEP_DIC = "depDic";
    private String USER_DIC = "userDic";

    @Autowired
    private SystemService service;

    private Map<String,String> getDicList(String tab, String keyProperty, String valProperty){
        List<DictEntity> dictEntities = service.queryDict(tab, keyProperty, valProperty);
        if(dictEntities!=null&& dictEntities.size()>0){
            Map result = new HashMap(dictEntities.size());
            for (DictEntity dictEntity: dictEntities) {
                result.put(dictEntity.getTypecode(),dictEntity.getTypename());
            }
            return result;
        }
        return null;
    }

    public Map getDicList(String dicType){
        switch (dicType){
            case "userDic" : return getDicList("t_s_base_user","username","realname");//人员
            case "depDic" : return getDicList("t_s_depart","id","departname");//组织，机构，部门
            case "suplDic" : return getDicList("t_supplier_list","supplier_code","supplier_name");//供应商
            case "suplTypeDic" : return getDicList("","supplAttr","");//供应商属性
            case "custDic" : return getDicList("t_customer_list","customer_code","customer_name");//客户
            case "carrierDic" : return getDicList("t_common_carrier_list","trans_code","trans_name");//承运商
            case "workshopDic" : return getDicList("","workshop","");//车间
            case "repostDic" : return getDicList("t_repository_list","repository_code","repository_name");//仓库
            case "storageDic" : return getDicList("t_storage_list","storage_code","storage_name");//库位
            case "housUnitDic" : return getDicList("","housUnit","");//库存单位
            case "ioStateDic" : return getDicList("","io_type","");//库存状态
            case "housTypeDic" : return getDicList("","housType","");//库存类型
            case "finishioStateDic" : return getDicList("","rip_s_type","");//熟成库存状态
            case "currencyDic" : return getDicList("","currency","");//币别
            case "orderStateDic" : return getDicList("","orderState","");//单据状态
            case "unitDic" : return getDicList("","unit","");//单位
            case "finshTypeDic" : return getDicList("","rip_p_type","");//熟成产品类型
            case "matTypeDic" : return getDicList("","matType","");//物料类型
            case "rmReason" : return getDicList("","r_m_reason","");//退料原因
            case "rpReason" : return getDicList("","r_p_reason","");//退货原因
            case "checkType" : return getDicList("","check_type","");//盘点类型
            case "checkStaut" : return getDicList("","checkStaut","");//盘点状态
            case "materType" : return getDicList("","mater_type","");//物资类型
            case "maintType" : return getDicList("","maintType","");//维护类型
            case "maintWay" : return getDicList("","maintWay","");//维护方式
            case "maintState" : return getDicList("","maintState","");//维护状态
            case "maintRes" : return getDicList("","maintRes","");//维护结果
            default:
                return null;
        }
    }

    public String getVal(String dicType,String key){
        Map<String,String> dicMap = getDicList(dicType);
        if(dicMap!=null){
            return dicMap.get(key);
        }
        return null;
    }

    public boolean writeDicList(HttpServletRequest request, String ... dicTypes){
        for (String dictype : dicTypes) {
            request.setAttribute(dictype, JSONObject.toJSON(this.getDicList(dictype)));
        }
        return true;
    }

}
