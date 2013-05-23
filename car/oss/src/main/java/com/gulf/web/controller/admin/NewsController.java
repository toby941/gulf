package com.gulf.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.gulf.constants.Constants;
import com.gulf.domain.News;
import com.gulf.service.NewsService;
import com.gulf.web.filter.AuthFilter;
import com.gulf.web.tag.PageTag;

@IocBean
@InjectName
@At("/admin/news")
@Filters({@By(type = AuthFilter.class)})
public class NewsController {

    private static final Log log = Logs.getLog(NewsController.class);

    @Inject
    private NewsService newsService;

    @At("/list")
    @Ok("jsp:jsp.admin.news_list")
    public Map<String, Object> list(@Param("page") Integer page) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        if (page == null) {
            page = 1;
        }
        List<News> list = newsService.getList(page, true);
        Integer count = newsService.getTotalCount();
        result.put("list", list);
        String tag = PageTag.getHtml("/admin/news/list?page=PAGENUM", page, count, Constants.PAGE_SIZE);
        result.put("tag", tag);
        return result;
    }

    @At("/add")
    @Ok("jsp:jsp.admin.news_add")
    @GET
    public String[] add() {
        return Constants.NEWS_TYPES;
    }

    @At("/add")
    @Ok("redirect:/admin/news/list")
    @POST
    public String doAdd(@Param("..") News news) {
        if (news != null) {
            newsService.addNews(news);
        }
        return "main";
    }

    @At("/view/?")
    @Ok("jsp:jsp.admin.news_view")
    @GET
    public Map<String, Object> view(int id) {
        News news = newsService.getNews(id);
        String[] types = Constants.NEWS_TYPES;
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("news", news);
        result.put("types", types);
        return result;
    }

    @At("/edit")
    @Ok("redirect:/admin/news/list")
    @POST
    public void view(@Param("..") News news) {
        if (news != null) {
            newsService.updateNews(news);
        }
    }

    @At("/del/?")
    @Ok("redirect:/admin/news/list")
    @GET
    public void dej(int id) {
        newsService.delNews(id);
    }

}
