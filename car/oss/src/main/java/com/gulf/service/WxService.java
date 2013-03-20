package com.gulf.service;

import java.text.MessageFormat;
import java.util.Calendar;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class WxService {

    private static String textTemplete =
            "<xml><ToUserName><![CDATA[{0}]]></ToUserName><FromUserName><![CDATA[xzzrnj]]></FromUserName><CreateTime>{1}</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[{2}]]></Content><FuncFlag>0</FuncFlag></xml>";

    public String getTextResponse(String toUserName, String content) {
        String time = String.valueOf(Calendar.getInstance().getTimeInMillis());
        return MessageFormat.format(textTemplete, toUserName, time, content);

    }

}
