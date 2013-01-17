package com.gulf.web.controller;

import org.nutz.mvc.annotation.At;

public class CarController {

    @At("/car/index")
    public String index() {
        return "hello world";
    }

}
