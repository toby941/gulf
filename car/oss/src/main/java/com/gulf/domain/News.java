package com.gulf.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("GULF_NEWS")
public class News implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -490256953903485837L;
    @Column("ADD_TIME")
    private Date addTime;

    @Column("CONTENT")
    private String content;
    @Id
    private int id;
    @Column("IMG_URL")
    private String imgUrl;
    /**
     * 状态 1-正常 0-删除
     */
    @Column("STATUS")
    private Integer status;

    @Column("SUMMARY")
    private String summary;

    @Column("TITLE")
    private String title;

    /**
     * 1-日记 2-医生 3-用药 4-进食 5-各种玩意
     */
    @Column("TYPE")
    private String type;

    @Column("UPDATE_TIME")
    private Date updateTime;

    public Date getAddTime() {
        return addTime;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
