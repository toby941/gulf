package com.gulf.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("WEIBO_USER")
public class WeiboUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8549776839985206803L;

    @Column("ACCESS_TOKER")
    private String accessToken;
    @Column("ADD_TIME")
    private Date addTime;

    @Id
    private int id;

    @Column("NAME")
    private String name;

    @Column("UID")
    private Integer uid;

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonCard() {
        return personCard;
    }

    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }
    @Column("UPDATE_TIME")
    private Date updateTime;

    private String homePage;
    private String imageUrl;
    private Boolean approve;
    private String addr;
    private String gender;
    private String personCard;

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

    public String getAccessToken() {
        return accessToken;
    }

    public Date getAddTime() {
        return addTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getUid() {
        return uid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
