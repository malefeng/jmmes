package com.jeecg.controller.common;

import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dictionaryController")
public class DictionaryController {

    /**
     * 查询数据字典
     * @param diccode 字典编码
     * @return
     */
    @RequestMapping("/apiList/{diccode}")
    @ResponseBody
    public List<Map<String, Object>> apiList(@PathVariable("diccode") String diccode) {
        List<Map<String, Object>> dicDatas = new ArrayList<Map<String, Object>>();
        List<TSType> tstypes = ResourceUtil.getCacheTypes(diccode.toLowerCase());
        for(TSType t:tstypes){
            Map<String,Object> mp = new HashMap<String,Object>();
            mp.put(t.getTypecode(),MutiLangUtil.doMutiLang(t.getTypename(), null));
            dicDatas.add(mp);
        }
        return dicDatas;
    }
}
