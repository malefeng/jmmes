package com.jeecg.controller.login;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class Login extends BaseController{

    @RequestMapping(params = "/pdalogin")
    public AjaxJson login(TSUser user, HttpServletRequest req){
        return null;
    }
}
