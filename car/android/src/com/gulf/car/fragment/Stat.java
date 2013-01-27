package com.gulf.car.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.gulf.car.R;
import com.gulf.car.data.DataSource;
import com.gulf.car.data.impl.DataSourceImpl;

/**
 * 统计界面
 * @author fortransit
 *
 *<string name="oilConsumption_title">百公里油耗：</string>
    <string name="current_distance_title">当前里程：</string>
    <string name="per_kilo_charge_title">每公里花费：</string>
    <string name="daily_cost_title">每天行驶成本：</string>
    <string name="daily_distance_title">每天行驶里程：</string>
 */
public class Stat extends SherlockFragment {

	private TextView oilConsumption;
	private TextView current_distance;
	private TextView per_kilo_charge;
	private TextView daily_cost;
	private TextView daily_distance;
	
	private DataSource carDataSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		carDataSource = new DataSourceImpl(getActivity()); 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View v = inflater.inflate(R.layout.stat, null);
		oilConsumption = (TextView)v.findViewById(R.id.oilConsumption);
		current_distance = (TextView)v.findViewById(R.id.current_distance);
		per_kilo_charge = (TextView)v.findViewById(R.id.per_kilo_charge);
		daily_distance = (TextView)v.findViewById(R.id.daily_distance);
		daily_cost = (TextView)v.findViewById(R.id.daily_cost);
		return v;
	}
	
	public static Stat getInstance()
	{
		return new Stat();
	}
}
