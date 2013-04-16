package com.gulf.web.controller.api.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.gulf.constants.ApiConstants;
import com.gulf.domain.News;
import com.gulf.service.NewsService;
import com.gulf.util.MarkdownUtils;

/**
 * @author toby hope app 主输出api
 */
@IocBean
@InjectName
@At("/api/hope")
public class HopeApiController {
    @Inject
    private NewsService newsService;

    @At("/news/category/?")
    @GET
    @Ok("JSON")
    public Map<String, Object> getList(Integer type, @Param("page") Integer page) {
        if (page == null) {
            page = 0;
        }
        List<News> list = newsService.getList4App(page, type);
        List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        for (News news : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", news.getTitle());
            map.put("summary", news.getSummary());
            map.put("time", DateFormatUtils.format(news.getAddTime(), "yyyy-MM-dd"));
            map.put("img", news.getImgUrl());
            map.put("href", newsService.getConfig("host") + "/api/hope/news/" + String.valueOf(news.getId()));
            newsList.add(map);
        }
        Map<String, Object> result = ApiTemplate.fillCommonData();
        result.put(ApiConstants.RESPONSE, newsList);
        return result;
    }

    @At("/news/?")
    @GET
    @Ok("jsp:jsp.api.news")
    public News view(int id) {
        News news = newsService.getNews(id);
        if (news != null) {
            String content = news.getContent();
            String html = MarkdownUtils.makeHtml(content);
            news.setContent(html);
        }
        return news;
    }
}
