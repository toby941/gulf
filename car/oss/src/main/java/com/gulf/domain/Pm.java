package com.gulf.domain;

import java.text.MessageFormat;
import java.util.Date;

public class Pm {

    private Integer aqi;
    private String area;
    private String pm2_5;
    private String pm2_5_24h;
    private String position_name;
    private String primary_pollutant;
    private String quality;

    private String station_code;
    private Date time_point;

    public String toClient() {
        if (position_name == null) {
            return MessageFormat.format("整体空气质量:{0} 空气指数:{1} PM2.5:{2}", quality, aqi, pm2_5);
        }
        return MessageFormat.format("空气质量:{0} 空气指数:{1} PM2.5:{2} 监测点:{3}", quality, aqi, pm2_5, position_name);
    }

    @Override
    public String toString() {
        return "Pm [aqi=" + aqi + ", area=" + area + ", pm2_5=" + pm2_5 + ", pm2_5_24h=" + pm2_5_24h
                + ", position_name=" + position_name + ", primary_pollutant=" + primary_pollutant + ", quality="
                + quality + ", station_code=" + station_code + ", time_point=" + time_point + "]";
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getPm2_5_24h() {
        return pm2_5_24h;
    }

    public void setPm2_5_24h(String pm2_5_24h) {
        this.pm2_5_24h = pm2_5_24h;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPrimary_pollutant() {
        return primary_pollutant;
    }

    public void setPrimary_pollutant(String primary_pollutant) {
        this.primary_pollutant = primary_pollutant;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public Date getTime_point() {
        return time_point;
    }

    public void setTime_point(Date time_point) {
        this.time_point = time_point;
    }

}
