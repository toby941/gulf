package com.gulf.car.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gulf.car.data.DataSource;
import com.gulf.car.data.MySqlOpenHelper;
import com.gulf.car.data.Table;
import com.gulf.car.model.MetaRecord;

public class DataSourceImpl implements DataSource {

	private SQLiteDatabase database;
	private MySqlOpenHelper dbHelper;
	private static final String[] ALL_COLUMNS = { 
		Table.ID, Table.DATE, Table.CHARGE, Table.PRICE, Table.DISTANCE};
	private Context context;

	public DataSourceImpl(Context context) {
		this.context = context;
		dbHelper = new MySqlOpenHelper(context);
	}

	private void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	private void close() {
		dbHelper.close();
	}

	@Override
	public List<MetaRecord> readRecords() {
		open();
		Cursor cursor = database.query(Table.NAME, ALL_COLUMNS, null, null, null, null, Table.DISTANCE);
		List<MetaRecord> list = new ArrayList<MetaRecord>();
		cursor.moveToFirst();
		while(cursor.moveToNext())
		{
			list.add(generateRecord(cursor));
		}
		cursor.close();
		close();
		return list;
	}
	
	private MetaRecord generateRecord(Cursor cursor)
	{
		MetaRecord record = new MetaRecord();
		record.setId(cursor.getString(0));
		record.setTimeByStr(cursor.getString(1));
		record.setCharge(cursor.getFloat(2));
		record.setPrice(cursor.getFloat(3));
		record.setDistance(cursor.getLong(4));
		return record;
	}

	@Override
	public boolean wirteRecord(String date, String oilNum, float charge,
			float price, long distance) {
		try {
			open();
			ContentValues values = new ContentValues();
			values.put(Table.ID, UUID.randomUUID().toString());
			values.put(Table.DATE, date);
			values.put(Table.OIL_NUM, oilNum);
			values.put(Table.CHARGE, charge);
			values.put(Table.PRICE, price);
			values.put(Table.DISTANCE, distance);
			long insertId = database.insert(Table.NAME, null, values);
			notifyDataChanged();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close();
		}
	}
	
	private void notifyDataChanged()
	{
		Intent intent = new Intent();
		intent.setAction(BROADCAST_DATA_CHANGED);
		context.sendBroadcast(intent); 
	}
}
