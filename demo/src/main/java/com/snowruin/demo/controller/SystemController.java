package com.snowruin.demo.controller;

import com.snowruin.web4j.framework.common.annotation.Action;
import com.snowruin.web4j.framework.common.annotation.Controller;
import com.snowruin.web4j.framework.common.bean.Param;
import com.snowruin.web4j.framework.common.bean.View;
import com.snowruin.web4j.framework.security.exception.AuthenticationException;
import com.snowruin.web4j.framework.security.helper.SecurityHelper;

/**
 * @ClassName SystemController
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/26 15:37
 * @Version 1.0
 **/

@Controller
public class SystemController  {

    @Action("get:/index")
    public View index(){
        return new View("index.jsp");
    }


    @Action("get:/login")
    public View login(){
        return new View("login.jsp");
    }


    @Action("post:/submitLogin")
    public View submitLogin(Param param){
        String username = param.getString("username");
        String password = param.getString("password");

        try {
            SecurityHelper.login(username,password);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new View("login.jsp");
        }
        return new View("/customer");
    }

    @Action("get:/logout")
    public View logout(){
        SecurityHelper.logout();
        return new View("/index");
    }


}
