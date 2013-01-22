package com.gulf.car.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 原始的记录信息
 * @author fortransit
 *
 */
public class MetaRecord {

	private String id;//记录的ID
	private long distance;//行驶里程数
	private float charge;//充值金钱
	private float fuel;
	private String oilNum;//油耗
	private float price;//单价
	private Date time;
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");  
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}
	public float getFuel() {
		return fuel;
	}
	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
	public String getOilNum() {
		return oilNum;
	}
	public void setOilNum(String oilNum) {
		this.oilNum = oilNum;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setTimeByStr(String timeStr)
	{
		try {
			time = timeFormat.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getFormattedTime()
	{
		String res = "";
		if(time != null)
		{
			res = timeFormat.format(time);
		}
		return res;
	}
	
}
