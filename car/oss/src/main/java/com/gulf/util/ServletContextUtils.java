package com.gulf.util;

import javax.servlet.ServletContext;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class ServletContextUtils {
    private ServletContext sc;

    public String getPath(String path) {
        return sc.getRealPath(path);
    }
}
