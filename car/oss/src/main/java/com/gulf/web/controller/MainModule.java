package com.gulf.web.controller;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;

@Modules(scanPackage = true)
public class MainModule {

    @At("/index")
    @Ok("jsp:jsp.index")
    public void index() {
    }

}
