package com.gulf.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.IocBean;

import com.gulf.constants.Constants;
import com.gulf.domain.News;

@IocBean
public class NewsService extends BaseService {

    public final static Integer status_ok = 1;

    public void addNews(News news) {
        Date time = Calendar.getInstance().getTime();
        news.setAddTime(time);
        news.setUpdateTime(time);
        news.setStatus(status_ok);
        dao.insert(news);
    }

    public List<News> getList(int pageNumber) {
        Condition condition =
                Cnd.where("status", "=", status_ok).limit(pageNumber, Constants.PAGE_SIZE).asc("update_time");
        return dao.query(News.class, condition);
    }
}
