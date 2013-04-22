/*
 * Copyright 2012 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.gulf.service.image;

import org.apache.commons.lang.StringUtils;

/**
 * UpYunUtils.java
 * 
 * @author liyuhang
 */
public class UpYunUtils {
    /**
     * 默认图片host,拼接图片地址输出至客户端时使用
     */
    private static String imageHost = "http://guoxiaomei.b0.upaiyun.com";
    private static UpYunUtils utils;

    public void init() {
        utils = this;
        utils.imageHost = imageHost;
    }

    public static String buildFullPicPath(String path) {
        if (StringUtils.isBlank(path)) {
            return "";
        }
        if (path.startsWith("http")) {
            return path;
        }
        return utils.imageHost + path;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

}
