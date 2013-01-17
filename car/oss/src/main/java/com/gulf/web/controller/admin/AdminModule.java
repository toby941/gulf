/*
 * Copyright 2013 5idea gulf team
 */
package com.gulf.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * AdminModule.java
 * 
 * @author ryenlea
 */
@At("/admin")
public class AdminModule {
	private static final Log log = Logs.getLog(AdminModule.class);
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
