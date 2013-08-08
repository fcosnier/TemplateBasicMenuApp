package com.example.flofichou.basicmenuapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@SuppressLint("NewApi")
public class MainActivity extends SherlockFragmentActivity implements View.OnClickListener{
	
	public static final String TAG = "MainActivity";

	public SharedPreferences prefs;
	public String usernamePrefs;
	public boolean checkBoxPrefs;
	public TextView usernameTextView;
	public TextView positionTextView;
	
	public EmptySupportFragment mEmptySupportFragment;

	public Context context;
	
	private  SlidingMenu menuLeft;
	private  SlidingMenu menuRight;
	
	public OnSharedPreferenceChangeListener listener;
	public static final String KEY_PREF_EXERCISES = "pref_changes";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*** Left Menu ***/
		menuLeft = new SlidingMenu(this);
		menuLeft.setMode(SlidingMenu.LEFT);
		menuLeft.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		menuLeft.setShadowWidthRes(R.dimen.shadow_width);
		menuLeft.setShadowDrawable(R.drawable.shadow);
		menuLeft.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menuLeft.setFadeDegree(0.35f);
		menuLeft.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		menuLeft.setMenu(R.layout.menu_left);
		
		mEmptySupportFragment = new EmptySupportFragment();
		
		findViewById(R.id.HomeMenu).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG,"OnHomeMenu");
				// TODO Auto-generated method stub
				menuLeft.toggle();

				getFragmentManager().beginTransaction()
				.replace(R.id.main_frame,new EmptyFragment())
				.commit();	
				
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_frame, mEmptySupportFragment)
				.commit();
			}
		});
		
	}

	
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getSupportMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    		ActionBar actionBar = getSupportActionBar();
	    		actionBar.setDisplayHomeAsUpEnabled(true);
	    	}
	        return true;
	 }
	
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case android.R.id.home:
    			menuLeft.toggle();
    			break;

    	}
    	
    	return super.onOptionsItemSelected(item);
    }
	
	 public void onClick(View v) {
			// mContentTextView.setText("Active item: " + ((TextView) v).getText());
			menuLeft.toggle();
	  }

}
