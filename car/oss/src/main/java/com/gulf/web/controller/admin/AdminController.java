/*
 * Copyright 2013 5idea gulf team
 */
package com.gulf.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.gulf.domain.Test;
import com.gulf.service.TestService;

/**
 * AdminModule.java
 * 
 * @author ryenlea
 */
@At("/admin")
@IocBean
@InjectName
public class AdminController {
    private static final Log log = Logs.getLog(AdminController.class);

    @Inject
    private TestService testService;

    @At("/login")
    @Ok("jsp:jsp.admin.login")
    @GET
    public void login() {
    }

    @At("/login")
    @Ok("redirect:/admin/main")
    @POST
    public View doLogin(@Param("username") String username, @Param("password") String password, HttpServletRequest req) {
        Test admin = testService.login(username, password);
        if (admin != null) {
            req.getSession().setAttribute("admin", admin);
            return new ViewWrapper(new JspView("jsp.admin.test"), admin);
        }
        else {
            req.setAttribute("error", "登录失败");
            return new JspView("jsp.admin.login");
        }
    }

    @At("/logout")
    @Ok("redirect:/")
    @DELETE
    public void logout(HttpServletRequest req) {
        req.getSession().invalidate();
    }

    @At("/main")
    @Ok("jsp:jsp.admin.test")
    public String main() {
        log.error("报个错看看");
        return "test";
    }

    @At("/test")
    @Ok("json")
    public Map test() {
        Map test = new HashMap<String, Object>();
        Map sub = new HashMap<String, Object>();
        sub.put("brothers", new String[]{"aa", "bb", "cc"});
        test.put("name", "tom");
        test.put("age", 23);
        test.put("b", sub);
        return test;
    }
}
