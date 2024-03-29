package com.gulf.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class WeiboClient {
    static final String LOGON_SITE = "login.sina.com.cn";
    static final int LOGON_PORT = 80;
    static final String preLoginUrl =
            "http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su=&rsakt=mod&client=ssologin.js(v1.4.5)&_=1363935";
    static final String loginurl = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.5)";
    static final String defaultUser = "18001585210";
    static final String defaultPasswordOld = "toby11toby";
    static final String homePageUrl = "http://weibo.com/";
    static final String searchURL = "http://s.weibo.com/user/&work=%E7%BB%AB%E8%87%B4";

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
        // 原先采用自己合并COOKIE，但是出现问题，用此方法OK
        DefaultHttpParams.getDefaultParams().setBooleanParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);
        client.getParams().setParameter(HttpMethodParams.USER_AGENT,
                "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
        String ajaxLoginUrl = Login(client, preLogin(client));
        String uniqueid = ajaxLogin(client, ajaxLoginUrl);
        getContent(client, searchURL);

    }

    public static HttpClient getLoginClient() throws HttpException, IOException, Exception {
        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
        // 原先采用自己合并COOKIE，但是出现问题，用此方法OK
        DefaultHttpParams.getDefaultParams().setBooleanParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);
        client.getParams().setParameter(HttpMethodParams.USER_AGENT,
                "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
        String ajaxLoginUrl = Login(client, preLogin(client));
        String uniqueid = ajaxLogin(client, ajaxLoginUrl);
        return client;
    }

    /**
     * 获取用户的主页
     * 
     * @param client
     * @param url
     * @throws Exception
     */
    public static void getContent(HttpClient client, String url) throws Exception {
        GetMethod get = new GetMethod(url);
        try {
            client.executeMethod(get);
        }
        catch (HttpException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String response = get.getResponseBodyAsString();
        System.out.println(response);
        readBySearchHtml(response);
    }

    public static String getContent(String url) throws Exception {
        HttpClient client = getLoginClient();
        GetMethod get = new GetMethod(url);
        try {
            client.executeMethod(get);
        }
        catch (HttpException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String response = get.getResponseBodyAsString();
        return response;
    }

    /**
     * 对 http://s.weibo.com/user/&work=%E7%BB%AB%E8%87%B4&page=2 的html响应做解析 返回搜索结果的uid 集合
     * 
     * @param content
     * @return
     * @throws Exception
     */
    public static List<String> readBySearchHtml(String content) throws Exception {
        List<String> uidList = new ArrayList<String>();
        Parser myParser;
        myParser = Parser.createParser(content, "utf8");
        HtmlPage visitor = new HtmlPage(myParser);
        myParser.visitAllNodesWith(visitor);
        NodeFilter filter =
                new CssSelectorNodeFilter("div[class=\"person_detail\"] p[class=\"person_name\"] a[uid=*.*]");
        NodeList allPersonList = visitor.getBody().extractAllNodesThatMatch(filter, true);
        for (int i = 0; i < allPersonList.size(); i++) {
            Node node = allPersonList.elementAt(i);
            if (node instanceof LinkTag) {
                LinkTag tag = (LinkTag) node;
                if (tag.getAttribute("title") != null) {
                    uidList.add(tag.getAttribute("uid"));
                }
            }

        }
        return uidList;
    }

    /**
     * preLogin获得servertime和一个定长随机的字符串nonce 用户账号采用默认账号
     * 
     * @param client
     * @return
     * @throws Exception
     */
    public static Map<String, String> preLogin(HttpClient client) throws Exception {
        return preLogin(client, defaultUser);
    }

    /**
     * preLogin获得servertime和一个定长随机的字符串nonce 用户账号采用输入账号
     * 
     * @param client
     * @param userEmail
     * @return
     * @throws Exception
     */
    public static Map<String, String> preLogin(HttpClient client, String userEmail) throws Exception {
        GetMethod get = new GetMethod(preLoginUrl + "&usr=" + userEmail);
        try {
            client.executeMethod(get);
        }
        catch (HttpException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String response = get.getResponseBodyAsString();

        // System.out.println(response);
        // System.out.println(get.getFollowRedirects());
        // System.out.println(get.getPath());
        // for (Header h : get.getResponseHeaders()) {
        // System.out.print(h);
        // }
        get.releaseConnection();
        printCookie(client);
        mergeCookie(client);
        printCookie(client);
        return responseBodyToMap(response);
    }

    /**
     * prelogin后拿到2个参数用来生成加密的密码，正式登陆。 账户，密码是系统默认
     * 
     * @param client
     * @param serverTimeAndNonce
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String Login(HttpClient client, Map<String, String> serverTimeAndNonce) throws IOException,
            HttpException {
        return Login(client, serverTimeAndNonce, defaultUser, defaultPasswordOld);
    }

    /**
     * prelogin后拿到2个参数用来生成加密的密码，正式登陆。 账户，密码采用输入参数
     * 
     * @param client
     * @param serverTimeAndNonce
     * @param userEmail
     * @param passwordOld
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String Login(HttpClient client, Map<String, String> serverTimeAndNonce, String userEmail,
            String passwordOld) throws IOException, HttpException {
        PostMethod post = new PostMethod(loginurl);
        // 初始化POST方法的content
        NameValuePair entry = new NameValuePair("entry", "weibo");
        NameValuePair gateway = new NameValuePair("gateway", "1");
        NameValuePair refer =
                new NameValuePair("pagerefer",
                        "http://login.sina.com.cn/sso/logout.php?entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%2F");
        NameValuePair from = new NameValuePair("from", "");
        NameValuePair savestate = new NameValuePair("savestate", "7");
        NameValuePair useticket = new NameValuePair("useticket", "1");
        NameValuePair ssosimplelogin = new NameValuePair("ssosimplelogin", "1");

        String su = SinaSSOEncoder.encodeAccount(userEmail);
        String sp = encryption(passwordOld, serverTimeAndNonce);
        System.out.println("su:" + su + "  sp:" + sp);
        NameValuePair username = new NameValuePair("su", su);
        NameValuePair password = new NameValuePair("sp", sp);

        NameValuePair service = new NameValuePair("service", "miniblog");
        NameValuePair servertime = new NameValuePair("servertime", serverTimeAndNonce.get("servertime"));
        NameValuePair nonce = new NameValuePair("nonce", serverTimeAndNonce.get("nonce"));
        // wsse rsa2
        NameValuePair pwencode = new NameValuePair("pwencode", "rsa2");

        NameValuePair encoding = new NameValuePair("encoding", "utf-8");
        String loginUrl =
                "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack";
        // String
        // loginUrl="http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack";
        NameValuePair url = new NameValuePair("url", loginUrl);
        NameValuePair returntype = new NameValuePair("returntype", "META");
        NameValuePair vsnf = new NameValuePair("vsnf", "1");
        NameValuePair prelt = new NameValuePair("prelt", "111");
        NameValuePair rsakv = new NameValuePair("rsakv", serverTimeAndNonce.get("rsakv"));
        post.addParameters(new NameValuePair[]{rsakv, prelt, vsnf, refer, entry, gateway, from, savestate, useticket,
                ssosimplelogin, username, service, servertime, nonce, pwencode, password, encoding, url, returntype});

        int status = client.executeMethod(post);
        String responseBodyAsString = post.getResponseBodyAsString();

        /*
         * System.out.println(status); System.out.println(response); System.out.println(post.getFollowRedirects());
         * System.out.println(post.getPath()); for (Header h : post.getResponseHeaders()) { System.out.print(h); }
         */
        // printCookie(client);
        // mergeCookie(client);
        // printCookie(client);
        // System.out.println("-------------Login结束--------------");
        // System.out.println("Login结束 response :" + responseBodyAsString);
        post.releaseConnection();

        return getAjaxUrl(responseBodyAsString);
    }

    /**
     * 加密密码
     * 
     * @param passwordOld
     * @param serverTimeAndNonce
     * @return
     */
    private static String encryption(String passwordOld, Map<String, String> serverTimeAndNonce) {
        String servertime = serverTimeAndNonce.get("servertime");
        String nonce = serverTimeAndNonce.get("nonce");
        String publickey = serverTimeAndNonce.get("pubkey");
        return SinaSSOEncoder.RSAencode(publickey, servertime, nonce, passwordOld);
    }

    /**
     * login后密码验证成功后跳入ajaxLogin，获得唯一标示码。
     * 
     * @param client
     * @param ajaxUrl
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String ajaxLogin(HttpClient client, String ajaxUrl) throws IOException, HttpException {
        GetMethod getByAjax = new GetMethod(ajaxUrl);
        client.executeMethod(getByAjax);
        System.out.println(getByAjax.getURI());
        // printCookie(client);
        // mergeCookie(client);
        // printCookie(client);
        // System.out.println("-------------ajaxLogin结束--------------");
        // System.out.println(getByAjax.getResponseBodyAsString());
        String responseBodyAsString = getByAjax.getResponseBodyAsString();
        getByAjax.releaseConnection();
        System.out.println(responseBodyAsString);
        printCookie(client);
        return getUniqueid(responseBodyAsString);
    }

    /**
     * 从AjaxLogin返回的信息中获得唯一标识码
     * 
     * @param responseBodyAsString
     * @return
     */
    private static String getUniqueid(String responseBodyAsString) {
        // TODO Auto-generated method stub
        int start = responseBodyAsString.indexOf("uniqueid") + 11;
        int end = responseBodyAsString.indexOf("userid") - 3;
        // System.out.println(responseBodyAsString);
        // System.out.println(responseBodyAsString.indexOf("uniqueid"));
        // System.out.println(responseBodyAsString.indexOf("userid"));
        // System.out.println(responseBodyAsString.substring(start, end));
        String uniqueid = responseBodyAsString.substring(start, end);
        // System.out.println("uniqueid:" + uniqueid);
        return uniqueid;
    }

    /**
     * 将preLogin返回信息提取需要的二个参数转为MAP形式
     * 
     * @param response
     * @return
     */
    private static Map<String, String> responseBodyToMap(String response) {
        Map<String, String> result = new HashMap<String, String>();
        int start = response.indexOf("{") + 1;
        int end = response.indexOf("}");
        String body = response.substring(start, end);
        String[] strings = body.split(",");
        for (int i = 0; i < strings.length; i++) {
            String[] elements = strings[i].split(":");
            result.put(elements[0].replace("\"", ""), elements[1].replace("\"", ""));
        }

        return result;
    }

    /**
     * 构建阶段时输出cookie值
     * 
     * @param client
     */
    private static void printCookie(HttpClient client) {
        Cookie[] cookies = client.getState().getCookies();
        // System.out.println("目前有" + cookies.length + "条cookie");
        int index = 0;
        for (Cookie cookie : cookies) {
            System.out.println("cookie[" + index + "]:{" + cookie.getName() + "," + cookie.getValue() + "}");
            index++;
        }

    }

    /**
     * 合并cookie信息放到一个cookie中 此方法会出错，无效。
     * 
     * @param client
     */
    private static void mergeCookie(HttpClient client) {
        Cookie[] cookies = client.getState().getCookies();
        if (cookies != null && cookies.length > 0) {
            String cook = cookies[0].getValue();
            for (int i = 1; i < cookies.length; i++) {
                cook += "; " + cookies[i].getName() + "=" + cookies[i].getValue();
            }
            cookies[0].setValue(cook);
            HttpState state = new HttpState();
            state.addCookie(cookies[0]);
            client.setState(state);
        }
    }

    /**
     * 从login方法后返回的信息中获得下一步AjaxLogin的URL
     * 
     * @param responseBodyAsString
     * @return
     */
    private static String getAjaxUrl(String responseBodyAsString) {
        int start = responseBodyAsString.indexOf("replace") + 9;
        int end = responseBodyAsString.indexOf("</script>") - 6;
        // System.out.println(responsBodyAsString);
        // System.out.println(responsBodyAsString.indexOf("</script>"));
        // System.out.println(responsBodyAsString.substring(start, end));
        return responseBodyAsString.substring(start, end);
    }
}
