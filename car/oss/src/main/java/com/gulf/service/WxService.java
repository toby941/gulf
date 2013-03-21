package com.gulf.service;

import java.text.MessageFormat;
import java.util.Calendar;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class WxService {

    private static String textTemplete =
            "<xml><ToUserName><![CDATA[{0}]]></ToUserName><FromUserName><![CDATA[{1}]]></FromUserName><CreateTime>{2}</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[{3}]]></Content><FuncFlag>0</FuncFlag></xml>";

    public String getTextResponse(String toUserName, String fromUser, String content) {
        String time = String.valueOf(Calendar.getInstance().getTime().getTime());
        return MessageFormat.format(textTemplete, toUserName, fromUser, time, content);

    }

}
