package com.example.wilive;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

public class HomeTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_tab);
		final String []array = new String[4];
		array[0] = "Home";
		array[1] = "Usage Details";
		array[2] = "Info";
		array[3] = "Settings";
		
		  TabHost tabHost = getTabHost();
		  
//	        // Tab for Home
	        TabSpec homespec = tabHost.newTabSpec("Home");
	        homespec.setIndicator("Home");
	        Intent homeIntent = new Intent(this, HomeActivity.class);
	        homespec.setContent(homeIntent);
	        tabHost.addTab(homespec); // Adding home tab
	        
//	        // Tab for Usage Details
	        TabSpec usagespec = tabHost.newTabSpec("Usage Details");
	        usagespec.setIndicator("Usage Details");
	        Intent usageIntent = new Intent(this, UsageDetailsActivity.class);
	        usagespec.setContent(usageIntent);
	        tabHost.addTab(usagespec); // Adding usage details tab
//	        
//	        // Tab for Info
	        TabSpec infospec = tabHost.newTabSpec("Info");
	        infospec.setIndicator("Info");
	       
	        Intent infoIntent = new Intent(this, InfoActivity.class);
	        infospec.setContent(infoIntent);
	        tabHost.addTab(infospec); // Adding info tab

//	        // Tab for Settings
	        TabSpec settingspec = tabHost.newTabSpec("Settings");
	        settingspec.setIndicator("", getResources().getDrawable(R.drawable.settings_icon));
	        Intent settingIntent = new Intent(this, SettingsActivity.class);
	        settingspec.setContent(settingIntent);
	        tabHost.addTab(settingspec); // Adding Settings tab
	        
	        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
	        	
	        	public void onTabChanged(String arg0) {
	        		TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
	                for (int i = 0; i < tabWidget.getChildCount(); i++) {
	                    tabWidget.getChildAt(i)
	                            .setBackgroundResource(R.drawable.tab_unselected); // unselected
	                }
	                tabWidget.getChildAt(checkIndex(array, arg0))
	                        .setBackgroundResource(R.drawable.tab_selected); // selected

	            }
	        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_tab, menu);
		return true;
	}
	
	public int checkIndex(String []array, String key)
	{
		for(int i = 0; i<array.length; i++)
			if(array[i].compareToIgnoreCase(key) == 0)
				return i;
		return -1;
	}
}
