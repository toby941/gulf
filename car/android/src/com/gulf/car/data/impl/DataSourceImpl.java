package com.gulf.car.data.impl;

import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gulf.car.data.DataSource;
import com.gulf.car.data.MySqlOpenHelper;
import com.gulf.car.data.Table;
import com.gulf.car.model.MetaRecord;

public class DataSourceImpl implements DataSource {

	private SQLiteDatabase database;
	private MySqlOpenHelper dbHelper;
	private static final String[] ALL_COLUMNS = { };

	public DataSourceImpl(Context context) {
		dbHelper = new MySqlOpenHelper(context);
	}

	private void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	private void close() {
		dbHelper.close();
	}

	/*public void createComment(String comment) {
		open();
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
				values);
		close();
		return newComment;
	}

	public void deleteComment(Comment comment) {
		long id = comment.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Comment> getAllComments() {
		List<Comment> comments = new ArrayList<Comment>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return comments;
	}

	private Comment cursorToComment(Cursor cursor) {
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}*/


	@Override
	public List<MetaRecord> readRecords() {
		// TODO Auto-generated method stub
		return null;
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
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close();
		}
	}

}
