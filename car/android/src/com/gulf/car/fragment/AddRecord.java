package com.gulf.car.fragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.gulf.car.R;
import com.gulf.car.data.DataSource;
import com.gulf.car.data.impl.DataSourceImpl;

public class AddRecord extends SherlockFragment{

	private TextView dateView;
	private Spinner oilNumView;
	private EditText priceView;
	private EditText distanceView;
	private EditText chargeView;
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
	private static final DecimalFormat NUM_FORMAT = new DecimalFormat("00");
	
	private String[] oilNumTypesAndPrice;
	private DataSource carDataSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		carDataSource = new DataSourceImpl(getActivity()); 
		oilNumTypesAndPrice = getResources().getStringArray(R.array.oilNumTypeAndPrice);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View v = inflater.inflate(R.layout.record_add, null);
		dateView = (TextView)v.findViewById(R.id.dateView);
		dateView.setText(DATE_FORMAT.format(new Date()));
		oilNumView = (Spinner)v.findViewById(R.id.oilNum);
		priceView = (EditText)v.findViewById(R.id.price);
		distanceView = (EditText)v.findViewById(R.id.distance);
		chargeView = (EditText)v.findViewById(R.id.charge);
		return v;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, oilNumTypesAndPrice){
					@Override
					public String getItem(int position) {
						return getOilNumType(position);
					}
		};
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oilNumView.setAdapter(adapter);
        oilNumView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				priceView.setText(getOilNumPrice(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		dateView.setOnKeyListener(null);
		dateView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popDatePicker();
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.add_record, menu);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
	}
	
	private void popDatePicker()
	{
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(DATE_FORMAT.parse(dateView.getText().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		OnDateSetListener listener = new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
				dateView.setText(year + "-" + NUM_FORMAT.format(monthOfYear + 1)+ "-" + NUM_FORMAT.format(dayOfMonth));
			}
		};
		DatePickerDialog dpd = new DatePickerDialog(getActivity(), listener, date.get(Calendar.YEAR), 
				date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
		dpd.show();
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_add_record:
			{
				boolean res = 
						carDataSource.wirteRecord(
						dateView.getText().toString(), 
						getOilNumType(oilNumView.getSelectedItemPosition()), 
						Float.parseFloat(chargeView.getText().toString()), 
						Float.parseFloat(priceView.getText().toString()), 
						Long.parseLong(distanceView.getText().toString())
						);
				Toast.makeText(getActivity(), res + "", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		//Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
		return super.onOptionsItemSelected(item);
	}

	public static AddRecord getInstance()
	{
		return new AddRecord();
	}
	
	private String getOilNumType(int pos)
	{
		String oStr = oilNumTypesAndPrice[pos];
		return oStr.substring(0, oStr.indexOf(','));
	}
	
	private String getOilNumPrice(int pos)
	{
		String oStr = oilNumTypesAndPrice[pos];
		return oStr.substring(oStr.indexOf(',') + 1);
	}

}