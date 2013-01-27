package com.gulf.car.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.gulf.car.R;
import com.gulf.car.data.DataSource;
import com.gulf.car.data.impl.DataSourceImpl;
import com.gulf.car.model.MetaRecord;

/**
 * 统计界面
 * @author fortransit
 *
 *
 */
public class Stat extends SherlockFragment {

	private TextView oilConsumption;
	private TextView current_distance;
	private TextView per_kilo_charge;
	private TextView daily_cost;
	private TextView daily_distance;
	
	private DataSource carDataSource;
	private List<MetaRecord> recordList = new ArrayList<MetaRecord>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		carDataSource = new DataSourceImpl(getActivity()); 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View v = inflater.inflate(R.layout.stat, null);
		oilConsumption = (TextView)v.findViewById(R.id.oilConsumption);//百公里油耗
		current_distance = (TextView)v.findViewById(R.id.current_distance);//当前里程
		per_kilo_charge = (TextView)v.findViewById(R.id.per_kilo_charge);//每公里花费
		daily_distance = (TextView)v.findViewById(R.id.daily_distance);//每天行驶里程
		daily_cost = (TextView)v.findViewById(R.id.daily_cost);//每天花费
		return v;
	}
	
	/**
	 * 统计计算
	 */
	public void calucute()
	{
		recordList = carDataSource.readRecords();
		List<DateFuel> statList = new ArrayList<DateFuel>(); 
		for(int i = 0; i <= recordList.size(); i++)
		{
			MetaRecord metaRecord = recordList.get(i);
			if(statList.isEmpty() || metaRecord.getTime().after(recordList.get(i - 1).getTime()))
			{
				DateFuel dateFuel = new DateFuel();
				dateFuel.time = metaRecord.getTime();
				dateFuel.distance = metaRecord.getDistance();
				dateFuel.setOilAmount(metaRecord.getCharge(), metaRecord.getPrice());
				statList.add(dateFuel);
			}
			else if(metaRecord.getTime().equals((recordList.get(i - 1).getTime())))
			{
				statList.get(statList.size() - 1).distance = metaRecord.getDistance();
				statList.get(statList.size() - 1).setOilAmount(metaRecord.getCharge(), metaRecord.getPrice());
			}
			else
			{
				//非法数据
			}
		}
		
		if(statList.size() > 1)
		{
			float allCharge = 0;
			float allOilConsumption = 0;
			for(int i = 0; i <= statList.size(); i++)
			{
				if(i - 1 != statList.size())
				{
					allCharge += statList.get(i).charge;
					allOilConsumption += statList.get(i).oilAmount;
				}
			}
			int days = DaysBetween(statList.get(0).time, statList.get(statList.size() - 1).time);
			long distaceDiff = statList.get(statList.size() - 1).distance - statList.get(0).distance;
			
			//百公里油耗
			float v1 = allOilConsumption * 100 / distaceDiff ; 
			//每公里花费
			float v2 = allCharge / distaceDiff;
			//每天行驶成本
			float v3 = allCharge / days;
			//每天行驶里程
			float v4 = distaceDiff / days;
		}
	}
	
	private int DaysBetween(Date date1, Date date2)
	{
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		
		int daysBetween = 0;
		 while (c1.before(c2)) 
		 {
	            c1.add(Calendar.DAY_OF_MONTH, 1);
	            daysBetween++;
	     }
	    return daysBetween;
	}
	
	public static Stat getInstance()
	{
		return new Stat();
	}
	
	private static class DateFuel
	{
		public Date time;
		public float charge;
		public long distance;
		public float oilAmount;
		
		void setOilAmount(float lastCharge ,float lastPrice)
		{
			charge += lastCharge;
			oilAmount += lastCharge / lastPrice;
		}
	}
}
