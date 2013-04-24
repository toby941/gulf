package com.gulf.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.FieldFilter;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;

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

    public News getNews(Integer id) {
        Condition condition = Cnd.where("id", "=", id);
        News news = dao.fetch(News.class, condition);
        if (news != null && status_ok.equals(news.getStatus())) {
            return news;
        }
        else {
            return null;
        }
    }

    /**
     * 客户端输出
     * 
     * @param pageNumber
     * @param type
     * @return
     */
    public List<News> getList4App(int pageNumber, Integer type) {
        Condition condition = null;

        if (type > 0) {
            condition =
                    Cnd.where("status", "=", status_ok).and("news_type", "=", type)
                            .limit(pageNumber, Constants.PAGE_SIZE).desc("update_time");

        }
        else {
            condition = Cnd.where("status", "=", status_ok).limit(pageNumber, Constants.PAGE_SIZE).desc("update_time");
        }

        return dao.query(News.class, condition);
    }

    public List<News> getList(int pageNumber, boolean includeDelNews) {
        Condition condition = null;
        if (includeDelNews) {
            condition = Cnd.limit().limit(pageNumber, Constants.PAGE_SIZE).desc("update_time");
        }
        else {
            condition = Cnd.where("status", "=", status_ok).limit(pageNumber, Constants.PAGE_SIZE).desc("update_time");
        }
        return dao.query(News.class, condition);
    }

    public void updateNews(final News news) {
        news.setUpdateTime(Calendar.getInstance().getTime());
        news.setStatus(status_ok);
        FieldFilter.locked(News.class, "addTime").run(new Atom() {
            @Override
            public void run() {
                dao.update(news);
            }
        });
    }
}
