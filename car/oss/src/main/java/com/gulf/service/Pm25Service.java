package com.gulf.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.nutz.ioc.loader.annotation.IocBean;

import com.gulf.domain.Pm;
import com.gulf.util.HttpUtils;
import com.gulf.util.UnicodeUtils;

@IocBean
public class Pm25Service {

    private static String key = "SyhqfqMB31Yq46EzvyR7";
    private static String singleCityPM = "http://pm25.in/api/querys/pm2_5.json?city={0}&token=" + key;

    private static String allCity = "http://pm25.in/api/querys.json?token=" + key;

    private static Map<String, String> cacheMap = new HashMap<String, String>();

    private static List<String> cacheAllCity = new ArrayList<String>();

    public static List<String> getAllCity() {
        if (CollectionUtils.isNotEmpty(cacheAllCity)) {
            return cacheAllCity;
        }
        String cityJson = getResponse(allCity);
        if (StringUtils.isNotBlank(cityJson) && cityJson.indexOf("cities") != -1) {
            JSONObject json = JSONObject.fromObject(cityJson);
            JSONArray jsonArray = json.getJSONArray("cities");
            List<String> cityList = JSONArray.toList(jsonArray);
            cacheAllCity = cityList;
        }
        else {
            System.err.println(cityJson);
        }
        return cacheAllCity;
    }

    public static boolean isCity(String userInput) {
        return getAllCity().contains(userInput);
    }

    /**
     * 获取get请求,对存在的Unicode进行转化
     * 
     * @param url
     * @return
     */
    private static String getResponse(String url) {
        String result = HttpUtils.doGetRequest(url);
        return UnicodeUtils.decode(result);
    }

    public static String getPmByCity(String city) throws ClientProtocolException, IOException {

        String result = cacheMap.get(city);
        if (result == null) {
            String requestUrl = MessageFormat.format(singleCityPM, URLEncoder.encode(city));
            result = getResponse(requestUrl);
            cacheMap.put(city, result);
        }

        if (StringUtils.isNotEmpty(result) && result.indexOf("error") == -1) {

            JSONArray jsonArray = JSONArray.fromObject(result);
            StringBuffer sb = new StringBuffer();
            for (Object obj : jsonArray) {
                JSONObject json = (JSONObject) obj;
                Pm pm = (Pm) JSONObject.toBean(json, Pm.class);
                sb.append(pm.toClient());
            }
            return sb.toString();
        }
        else {
            return MessageFormat.format("亲 输入的城市: {0} 暂时还没有PM监测数据哦", city);
        }
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        List<String> city = getAllCity();
        System.out.println(city.contains("南京"));
        // String s = getPmByCity("南京");
        // System.out.println(s);
    }
}
