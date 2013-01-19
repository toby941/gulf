/*
 * Copyright 2013 5idea gulf team. All rights reserved.
 */
package com.gulf.service;

import org.nutz.dao.Cnd;

import com.gulf.domain.Test;

/**
 * TestService.java
 *
 * @author ryenlea
 */
public class TestService extends BaseService {
    /**
     * 管理员登录
     * 
     * @param username
     * @param password
     * @return
     */
    public Test login(String username, String password) {

        Cnd condition = Cnd.where("username", "=", username).and("password", "=", password);
        return findByCondition(Test.class, condition);
    }
}
