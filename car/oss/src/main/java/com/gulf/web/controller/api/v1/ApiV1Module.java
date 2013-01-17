/*
 * Copyright 2013 5idea gulf team
 */
package com.gulf.web.controller.api.v1;

import java.util.HashMap;
import java.util.Map;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * ApiV1Module.java
 * 
 * @author ryenlea
 */
@At("/api/v1")
public class ApiV1Module {

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
