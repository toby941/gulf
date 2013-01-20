package com.gulf.car.data;

/**
 * 数据库结构类：保存了数据库相关的表结构
 * @author fortransit
 *
 */
public class Table {

	public static final String NAME = "record";
	public static final String ID = "id";
	public static final String DATE = "date";
	public static final String OIL_NUM = "oilNum";
	public static final String CHARGE = "charge";
	public static final String PRICE = "price";
	public static final String DISTANCE =  "distance";
	
	public static final String STATEMENT_CREATE = "CREATE TABLE "
		      + NAME + "(" 
			  + ID + " TEXT primary key , " 
		      + DATE + " TEXT not null , "
		      + OIL_NUM + " TEXT not null ,"
		      + CHARGE + " NUMERIC not null ,"
		      + PRICE + "  NUMERIC not null ,"
		      + DISTANCE + " INTEGER not null"
		      +");";
}
