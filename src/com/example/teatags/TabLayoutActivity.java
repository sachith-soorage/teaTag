package com.example.teatags;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost.TabSpec;

public class TabLayoutActivity extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);
        
        String s_name = null;
        String s_id = null;
        String s_location = null;
        String s_phone = null;
        
        try {
			
	        //data from nfc tag putting into supplier view 
	        String input = getIntent().getExtras().getString("input");
	        String[] values =  input.split(":");
	        s_name = values[0];
	        s_id = values[1];
	        s_location = values[2];
	        s_phone = values[3];
	        
		} catch (RuntimeException e) {
			Toast.makeText(this, "In-valid data or tag is corrupted",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
                
        
        final TabHost tabHost = getTabHost();
        
        //Tab for suppliers
        final TabSpec userspec = tabHost.newTabSpec("Suppliers");
        userspec.setIndicator("Suppliers", getResources().getDrawable(R.drawable.icon_user));
        final Intent userIntent = new Intent(this, UserData.class);
        userIntent.putExtra("s_name", s_name);
        userIntent.putExtra("s_id", s_id);
        userIntent.putExtra("s_location", s_location);
        userIntent.putExtra("s_phone", s_phone);
        userspec.setContent(userIntent);
        
        //Tab for tea info
        final TabSpec teaspec = tabHost.newTabSpec("Merchandise");
        teaspec.setIndicator("Merchandise", getResources().getDrawable(R.drawable.icon_tea));
        Intent teaIntent = new Intent(this, TeaData.class);
        teaspec.setContent(teaIntent);
        
        //Tab for report
        final TabSpec resultspec = tabHost.newTabSpec("Report");
        resultspec.setIndicator("Report", getResources().getDrawable(R.drawable.icon_result));
        final Intent resultIntent = new Intent(this, Result.class);
        resultspec.setContent(resultIntent);
        
               
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(userspec);
        tabHost.addTab(teaspec); 
        tabHost.addTab(resultspec);
        
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				
				
				if(tabId.toString() == "Report")
				{

				}
				
			}
		});
        
        
        

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.appmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.menu_exit)
		{
			finish();
		}else if(item.getItemId() == R.id.menu_home){
			
			Intent intent = new Intent(TabLayoutActivity.this,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			//startActivity(new Intent(TabLayoutActivity.this,MainActivity.class));
		}else if(item.getItemId() == R.id.menu_list){
			startActivity(new Intent(TabLayoutActivity.this,record_list.class));
		}
							
		return super.onOptionsItemSelected(item);
	}
}