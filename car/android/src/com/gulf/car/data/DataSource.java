package com.gulf.car.data;

import java.util.List;

import com.gulf.car.model.MetaRecord;

/*
 * 数据源接口
 */
public interface DataSource {

	public static final String BROADCAST_DATA_CHANGED = "com.gulf.car.data.changed";
	/**
	 * 增加一条记录
	 * @param date 时间
	 * @param oilNum 油号
	 * @param charge 金额
	 * @param price 单价
	 * @param distance 里程数
	 */
	boolean wirteRecord(String date, String oilNum ,float charge, float price, long distance);
	
	List<MetaRecord> readRecords(); 
}
