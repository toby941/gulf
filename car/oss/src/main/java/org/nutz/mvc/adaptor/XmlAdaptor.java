package org.nutz.mvc.adaptor;

import java.lang.reflect.Type;

import org.nutz.mvc.adaptor.injector.XmlInjector;
import org.nutz.mvc.annotation.Param;

public class XmlAdaptor extends PairAdaptor {

    @Override
    protected ParamInjector evalInjector(Type type, Param param) {
        return new XmlInjector(type, param.value());
    }

}
