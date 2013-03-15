package com.gulf.web.controller;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

public class CarController {

    @At("/car/index")
    @Ok("jsp:jsp.front.index")
    public String index() {
        return "hello world";
    }

}
