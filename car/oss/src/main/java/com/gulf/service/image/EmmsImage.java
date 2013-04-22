package com.gulf.service.image;

import java.util.Date;

public class EmmsImage {

    private Date addTime;
    private Integer belongToId;
    private byte[] content;
    private String path;
    private Integer high;
    private Integer id;
    private String userId;
    private String imageFormat;
    //
    private Integer flag;
    private Integer emmsCompanyId;
    private Date delTime;
    //
    private Integer addOper;
    private Integer delOper;
    //
    private Integer imageType;
    private String name;
    private Integer size;
    private Integer width;
    private Integer sort;
    private Integer yunsaved;

    //

    public String getImageFormat() {
        return imageFormat;
    }

    public Integer getAddOper() {
        return addOper;
    }

    public void setAddOper(Integer addOper) {
        this.addOper = addOper;
    }

    public Integer getDelOper() {
        return delOper;
    }

    public void setDelOper(Integer delOper) {
        this.delOper = delOper;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getEmmsCompanyId() {
        return emmsCompanyId;
    }

    public void setEmmsCompanyId(Integer emmsCompanyId) {
        this.emmsCompanyId = emmsCompanyId;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public void setImageFormat(String imgFormat) {
        this.imageFormat = imgFormat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getAddTime() {
        return addTime;
    }

    public Integer getBelongToId() {
        return belongToId;
    }

    public byte[] getContent() {
        return content;
    }

    public Integer getHigh() {
        return high;
    }

    public Integer getId() {
        return id;
    }

    public Integer getImageType() {
        return imageType;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getYunsaved() {
        return yunsaved;
    }

    public void saveToUpYun() {

    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setBelongToId(Integer belongToId) {
        this.belongToId = belongToId;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setYunsaved(Integer yunsaved) {
        this.yunsaved = yunsaved;
    }
}
