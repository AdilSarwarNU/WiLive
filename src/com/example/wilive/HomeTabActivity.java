package com.example.wilive;


import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeTabActivity extends FragmentActivity {
	 // Method to add a TabHost
    private static void AddTab(HomeTabActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new MyTabFactory(activity));
        tabHost.addTab(tabSpec);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_tab);
		final String []array = new String[4];
		array[0] = "Home";
		array[1] = "Usage";
		array[2] = "Info";
		array[3] = "Settings";
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

        // TODO Put here your Tabs
        HomeTabActivity.AddTab(this, tabHost, tabHost.newTabSpec("Home").setIndicator("Home"));
        HomeTabActivity.AddTab(this, tabHost, tabHost.newTabSpec("Usage").setIndicator("Usage"));
        HomeTabActivity.AddTab(this, tabHost, tabHost.newTabSpec("Info").setIndicator("Info"));
        HomeTabActivity.AddTab(this, tabHost, tabHost.newTabSpec("Settings").setIndicator("", getResources().getDrawable(R.drawable.settings_icon)));
        
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

	                    // Centre text
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
	                viewPager.setCurrentItem(checkIndex(array, arg0));

	            }
	        });
	        if(viewPager != null)
		    {
		    	//Problems here in adaptor
			    MyTabsAdapter mAdapter = new MyTabsAdapter(getSupportFragmentManager());
		        viewPager.setAdapter(mAdapter);
		        
		        viewPager.setOnPageChangeListener(
		                new ViewPager.SimpleOnPageChangeListener() {
		                    @Override
		                    public void onPageSelected(int position) {
		                    	tabHost.setCurrentTab(position);
		                    }
		                });
		        
		    }
	}
	public static class MyTabsAdapter extends FragmentPagerAdapter  {
			
			public MyTabsAdapter(android.support.v4.app.FragmentManager fragmentManager) {
	            super(fragmentManager);
	        }
		    @Override
		    public Fragment getItem(int index) {
		        switch (index) {
		        case 0:
		            // fragment activity
		        	return new HomeActivity();
		        case 1:
		            // fragment activity
		        	return new UsageDetailsActivity();
		        case 2:
		            // fragment activity
		        	return new InfoActivity();
		        	
		        default:
		        	return new SettingsActivity();
		        }
		    }
		     @Override
		    public int getCount() {
		        // get item count - equal to number of tabs
		        return 4;
		    }
	
		}
	public static class HomeActivity extends Fragment {
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_home, container, false);
            return rootView;
        }
    }
	public static class UsageDetailsActivity extends Fragment {
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_usage_details, container, false);
            return rootView;
        }
    }
	public static class InfoActivity extends Fragment {
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_info, container, false);
            return rootView;
        }
    }
	public static class SettingsActivity extends Fragment {
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_settings, container, false);
            return rootView;
        }
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
