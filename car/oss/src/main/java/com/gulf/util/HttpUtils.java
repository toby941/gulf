package com.gulf.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtils {

    private final static Logger log = Logger.getLogger(HttpUtils.class);

    /**
     * 以流形式获取web资源，图片内容二进制获取
     * 
     * @param httpUrl
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static byte[] getResponse(String httpUrl) throws ClientProtocolException, IOException {
        try {
            final HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
            HttpGet httpGet = new HttpGet(httpUrl);
            HttpClient httpclient = new DefaultHttpClient(httpParams);
            HttpResponse response = httpclient.execute(httpGet);
            byte[] content = EntityUtils.toByteArray(response.getEntity());
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpclient);
            return content;
        }
        catch (Exception e) {
            log.error("getResponse error", e);
            return null;
        }
    }

    public static String doGetRequest(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 5000);// 超时设置
        httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 5000);// 连接超时
        try {
            HttpResponse response = httpclient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            httpGet.abort();
        }
        finally {
            httpclient.getConnectionManager().shutdown();
        }
        return StringUtils.EMPTY;
    }

    public static String doPostRequest(String url, HttpParams params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 5000);// 超时设置
        httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 5000);// 连接超时
        httpPost.setParams(params);

        try {
            HttpResponse response = httpclient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            httpPost.abort();
        }
        finally {
            httpclient.getConnectionManager().shutdown();
        }
        return StringUtils.EMPTY;
    }

    public static void main(String[] args) throws IOException {
        // String url = "http://img6.cache.netease.com/cnews/2012/11/6/20121106100038534e0.jpg";
        // String url = "http://127.0.0.1:9091/api/wx";
        // String url = "http://weimp.sinaapp.com/api/wx";
        String url =
                "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo?mobileCode=13605191042&userID=";
        // byte[] content = getResponse(url);
        // FileUtils.writeByteArrayToFile(new File("D:\\tmp\\xxx.png"), content);
        String result = doGetRequest(url);
        String out = result.substring(result.lastIndexOf("cn/\">") + 5, result.indexOf("</string>"));
        System.out.println(out);
    }
}
