package com.gulf.service;

import java.text.MessageFormat;

import org.nutz.ioc.loader.annotation.IocBean;

import com.gulf.util.HttpUtils;

@IocBean
public class WebService {
    private final String MobileCodeWS =
            "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo?mobileCode={0}&userID=";

    public String getMobileInfo(String phone) {
        if (phone.length() != 11) {
            return "亲，请输入正确的手机号码哦^_^";
        }
        String requestUrl = MessageFormat.format(MobileCodeWS, phone);
        try {
            String result = HttpUtils.doGetRequest(requestUrl);
            String out = result.substring(result.lastIndexOf("cn/\">") + 5, result.indexOf("</string>"));
            if (out.indexOf("http://www.webxml.com.cn") != -1) {
                return "手机号码错误";
            }
            return out;
        }
        catch (Exception e) {
            return "对不起，暂无记录";
        }
    }
}
