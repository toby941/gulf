package com.gulf.service;

import java.text.MessageFormat;
import java.util.Calendar;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.util.WeiboConfig;

import com.gulf.domain.WeiboUser;

@IocBean
public class WeiboService extends BaseService {

    public String login_templete = "{0}?client_id={1}&&response_type=code&redirect_uri={2}";

    /**
     * weibo登陆URL
     * 
     * @return
     */
    public String getLoginUrl() {
        String authorizeURL = WeiboConfig.getValue("authorizeURL");
        String cliendId = WeiboConfig.getValue("client_ID");
        String redirectURI = WeiboConfig.getValue("redirect_URI");
        return MessageFormat.format(login_templete, authorizeURL, cliendId, redirectURI);

    }

    public User getUser(WeiboUser weiboUser) throws WeiboException {
        Users users = new Users();
        users.setToken(weiboUser.getAccessToken());
        User u = users.showUserById(String.valueOf(weiboUser.getUid()));
        return u;
    }

    public WeiboUser getLastestUser(String code) throws WeiboException, JSONException {
        Oauth oauth = new Oauth();
        WeiboUser weiboUser = null;
        AccessToken token = oauth.getAccessTokenByCode(code);
        if (token != null) {
            Account account = new Account();
            String tokenStr = token.getAccessToken();
            account.setToken(tokenStr);
            Integer uid = account.getUid().getInt("uid");

            Users users = new Users();
            users.setToken(tokenStr);
            User u = users.showUserById(String.valueOf(uid));

            Cnd condition = Cnd.where("uid", "=", uid);

            weiboUser = findByCondition(WeiboUser.class, condition);
            if (weiboUser == null) {
                weiboUser = new WeiboUser(uid, u.getName(), tokenStr);
                weiboUser = save(weiboUser);
            }
            else {
                weiboUser.setUpdateTime(Calendar.getInstance().getTime());
                weiboUser.setUid(uid);
                weiboUser.setName(u.getName());
                weiboUser.setAccessToken(tokenStr);
                update(weiboUser);
            }
        }
        return weiboUser;
    }
}
