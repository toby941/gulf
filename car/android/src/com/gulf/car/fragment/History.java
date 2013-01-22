package com.gulf.car.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.gulf.car.R;
import com.gulf.car.data.DataSource;
import com.gulf.car.data.impl.DataSourceImpl;
import com.gulf.car.model.MetaRecord;

public class History extends SherlockListFragment{
	private DataSource carDataSource;
	private List<MetaRecord> recordList = new ArrayList<MetaRecord>();
	private RecordItemAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		changed();
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		carDataSource = new DataSourceImpl(getActivity());
		adapter = new RecordItemAdapter();
		setListAdapter(adapter);
		super.onCreate(savedInstanceState);
	}
	
	public void changed()
	{
		recordList = carDataSource.readRecords();
		adapter.notifyDataSetChanged();
	}
	
	public static History getInstance()
	{
		return new History();
	}

	private class RecordItemAdapter extends BaseAdapter
	{
	
		@Override
		public int getCount() {
			return recordList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MetaRecord record = recordList.get(position);
			if(convertView == null)
			{
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.record_history_list_item, null);
			}
			((TextView)convertView.findViewById(R.id.record_date)).setText(record.getFormattedTime());
			((TextView)convertView.findViewById(R.id.record_distance)).setText(record.getDistance() + "");
			((TextView)convertView.findViewById(R.id.record_price)).setText(record.getPrice() + "");
			return convertView;
		}
		
	}
	
}
