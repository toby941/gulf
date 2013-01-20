package com.gulf.car.data;

import java.util.List;

import com.gulf.car.model.MetaRecord;

/*
 * 数据源接口
 */
public interface DataSource {

	void wirteRecord(float money, long kilometers);
	
	List<MetaRecord> readRecords(); 
}
