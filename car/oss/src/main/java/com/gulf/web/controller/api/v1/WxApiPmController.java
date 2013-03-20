package com.gulf.web.controller.api.v1;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.XmlAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.gulf.service.WxService;
import com.gulf.util.AddSHA1;

/**
 * 微信 PM2.5 公众平台接口
 * 
 * @author toby
 */
@IocBean
@InjectName
public class WxApiPmController {
    private static final Log log = Logs.getLog(WxApiPmController.class);
    public final static String token = "toby941";

    @Inject
    private WxService wxService;

    @At("/api/wx")
    @GET
    @Ok("raw")
    public String index(@Param("signature") String signature, @Param("timestamp") String timestamp,
            @Param("nonce") String nonce, @Param("echostr") String echostr) {
        String[] array = new String[]{token, timestamp, nonce};
        Arrays.sort(array);
        String code = array[0] + array[1] + array[2];
        String sh = AddSHA1.sha1(code);
        String result =
                MessageFormat.format("signature;{0},timestamp：{1}  nonce：{2}  echostr：{3}, code:{4},sh:{5}", signature,
                        timestamp, nonce, echostr, code, sh);
        // log.error(MessageFormat.format("signature;{0},timestamp：{1}  nonce：{2}  echostr：{3}, code:{4},sh:{5}",
        // signature, timestamp, nonce, echostr, code, sh));
        if (sh.equals(signature)) {
            return echostr;
        }
        else {
            return result;
        }
    }

    /**
     * <xml> <br/>
     * <ToUserName><![CDATA[toUser]]></ToUserName> <br/>
     * <FromUserName><![CDATA[fromUser]]></FromUserName> <br/>
     * <CreateTime>1348831860</CreateTime> <br/>
     * <MsgType><![CDATA[text]]></MsgType> <br/>
     * <Content><![CDATA[this is a test]]></Content><br/>
     * <MsgId>1234567890123456</MsgId> <br/>
     * </xml>
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws IOException
     */
    @At("/api/wx")
    @POST
    @Ok("raw")
    @AdaptBy(type = XmlAdaptor.class)
    public String handle(@Param("ToUserName") String toUser, @Param("FromUserName") String fromUser,
            @Param("CreateTime") String createTime, @Param("MsgType") String msgType, @Param("Content") String content,
            HttpServletRequest req) throws IOException {

        return wxService.getTextResponse(toUser, "我是大山的回声：" + content);
    }

    // http://weimp.sinaapp.com/api/wx?signature=06aa536f0916f83930b48451c32d65a0cb9396e2&echostr=5855573333999018911
    // &timestamp=1363338141&nonce=1363356904

    public static void main(String[] args) {
        String signature = "06aa536f0916f83930b48451c32d65a0cb9396e2";
        String echostr = "5855573333999018911";

        String timestamp = "1363338141";
        String nonce = "1363356904";
        String[] array = new String[]{token, timestamp, nonce};
        Arrays.sort(array);
        String code = array[0] + array[1] + array[2];
        String sh = AddSHA1.sha1(code);
        System.out.println(sh);
    }
}
