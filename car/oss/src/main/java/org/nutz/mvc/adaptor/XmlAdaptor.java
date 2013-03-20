package org.nutz.mvc.adaptor;

import java.lang.reflect.Type;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.nutz.lang.Lang;
import org.nutz.mvc.adaptor.injector.XmlInjector;
import org.nutz.mvc.annotation.Param;

public class XmlAdaptor extends PairAdaptor {

    @Override
    protected ParamInjector evalInjector(Type type, Param param) {
        return new XmlInjector(type, param.value());
    }

    @Override
    public Object getReferObject(ServletContext sc, HttpServletRequest req, HttpServletResponse resp, String[] pathArgs) {
        // Read all as String
        try {
            return IOUtils.toString(req.getInputStream());
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }
}
