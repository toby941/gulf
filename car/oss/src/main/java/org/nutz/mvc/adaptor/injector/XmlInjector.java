package org.nutz.mvc.adaptor.injector;

import java.lang.reflect.Type;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.adaptor.ParamInjector;

public class XmlInjector implements ParamInjector {
    private final Type type;
    private final String name;

    public XmlInjector(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Object get(ServletContext sc, HttpServletRequest req, HttpServletResponse resp, Object refer) {
        return name;
    }

}
