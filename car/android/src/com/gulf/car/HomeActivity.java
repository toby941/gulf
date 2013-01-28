package com.gulf.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gulf.car.data.DataSource;
import com.gulf.car.fragment.AddRecord;
import com.gulf.car.fragment.History;
import com.gulf.car.fragment.Stat;
import com.viewpagerindicator.TabPageIndicator;

public class HomeActivity extends SherlockFragmentActivity {
	
	private AddRecord addRecordFragment;
	private History historyFragment;
	private Stat statFragment;
	private BroadcastReceiver receiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		FragmentPagerAdapter adapter = new TabAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        init();
        
		/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragmentContainer, AddRecord.getInstance());
		ft.setTransition(FragmentTransaction.TRANSIT_NONE);
		ft.commit();*/
    }
	
	private void init()
	{
		receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				historyFragment.changed();
				statFragment.calculate();
			}
		};
	}
	
	

    @Override
	protected void onStart() {
    	registerReceiver(receiver, new IntentFilter(DataSource.BROADCAST_DATA_CHANGED));
		super.onStart();
	}
    
    @Override
	protected void onStop() {
    	unregisterReceiver(receiver);
		super.onStart();
	}



	class TabAdapter extends FragmentPagerAdapter {
    	private String[] tabs;
        public TabAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
        	switch(position)
        	{
        		case 0:
    			{
    				addRecordFragment = AddRecord.getInstance();
    				return addRecordFragment;
    			} 
        		case 1:
    			{
    				historyFragment = History.getInstance();
    				return historyFragment;
    			}
        		case 2:
        		{
        			statFragment = Stat.getInstance();
        			return statFragment;
        		}
        	}
        	return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position % getCount()];
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }
	
	
}
