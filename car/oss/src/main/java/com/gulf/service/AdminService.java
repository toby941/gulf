/*
 * Copyright 2013 5idea gulf team. All rights reserved.
 */
package com.gulf.service;

import org.nutz.dao.Cnd;

import com.gulf.domain.Admin;

/**
 * TestService.java
 *
 * @author ryenlea
 */
public class AdminService extends BaseService {
    /**
     * 管理员登录
     * 
     * @param username
     * @param password
     * @return
     */
    public Admin login(String username, String password) {

        Cnd condition = Cnd.where("name", "=", username).and("password", "=", password);
        return findByCondition(Admin.class, condition);
    }
}
