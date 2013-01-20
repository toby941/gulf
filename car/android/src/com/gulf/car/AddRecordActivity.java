package com.gulf.car;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gulf.car.fragment.AddRecord;

/**
 * 增加记录
 * @author fortransit
 *
 */
public class AddRecordActivity extends SherlockFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragmentContainer, AddRecord.getInstance());
		ft.setTransition(FragmentTransaction.TRANSIT_NONE);
		ft.commit();
		
	}

	
}
