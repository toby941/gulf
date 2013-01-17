/*
 * Copyright 2013 5idea gulf team
 */
package com.gulf.web.controller.front;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

/**
 * HomeModule.java
 * 
 * @author ryenlea
 */
public class HomeModule {

    @At("/")
    @GET
    @Ok("jsp:jsp.front.index")
    public String home() {
        return "你好";
    }

    @At("/test")
    @GET
    @Ok("jsp:jsp.front.index")
    public String homet() {
        return "你好t";
    }
}
