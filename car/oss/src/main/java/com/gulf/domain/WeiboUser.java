package com.gulf.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("WEIBO_USER")
public class WeiboUser implements Serializable {

    public WeiboUser() {
        super();
    }

    public WeiboUser(Integer uid, String name, String token) {
        super();
        this.updateTime = Calendar.getInstance().getTime();
        this.addTime = Calendar.getInstance().getTime();
        this.uid = uid;
        this.name = name;
        this.accessToken = token;
    }
    /**
     * 
     */
    private static final long serialVersionUID = -8549776839985206803L;
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column("ACCESS_TOKER")
    private String accessToken;
    @Column("NAME")
    private String name;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Column("UID")
    private Integer uid;
    @Column("ADD_TIME")
    private Date addTime;
    @Column("UPDATE_TIME")
    private Date updateTime;
}
