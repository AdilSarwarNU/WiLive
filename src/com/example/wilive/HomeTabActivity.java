package com.example.wilive;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class HomeTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_tab);
		final String []array = new String[4];
		array[0] = "Home";
		array[1] = "Usage";
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
	        TabSpec usagespec = tabHost.newTabSpec("Usage");
	        usagespec.setIndicator("Usage");
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
	        
	        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#2A6587"));
	        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_unselected);
	        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_unselected);
	        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_unselected);
	        
	        int tabCount = tabHost.getTabWidget().getTabCount();
	        for (int i = 0; i < tabCount; i++) {
	            final View view = tabHost.getTabWidget().getChildTabViewAt(i);
	            if ( view != null ) {
	                // reduce height of the tab
	                view.getLayoutParams().height *= 1;

	                //  get title text view
	                final View textView = view.findViewById(android.R.id.title);
	                if ( textView instanceof TextView ) {
	                    // just in case check the type

	                    // center text
	                    ((TextView) textView).setGravity(Gravity.CENTER);
	                    // wrap text
	                    ((TextView) textView).setSingleLine(false);

	                    // explicitly set layout parameters
	                    textView.getLayoutParams().height = ViewGroup.LayoutParams.FILL_PARENT;
	                    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
	                }
	            }
	        }
	        
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
