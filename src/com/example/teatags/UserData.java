package com.example.teatags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class UserData extends Activity {
	
	EditText et_name;
	EditText et_id;
	EditText et_location;
	EditText et_mobile;
	
	double LATITUDE;
	double LONGITUDE;
	
	ClassicSingleton record = ClassicSingleton.getInstance();
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        
        et_name = (EditText) findViewById(R.id.user_et1);
        et_id = (EditText) findViewById(R.id.user_et2);
        et_location = (EditText) findViewById(R.id.user_et3);
        et_mobile = (EditText) findViewById(R.id.user_et4);
        
        et_name.setText(getIntent().getStringExtra("s_name"));
        et_id.setText(getIntent().getStringExtra("s_id"));
        et_location.setText(getIntent().getStringExtra("s_location"));
        et_mobile.setText(getIntent().getStringExtra("s_phone"));
        
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
	    Location lastKnownLocation = lm.getLastKnownLocation(locationProvider);
	    
        LATITUDE = lastKnownLocation.getLatitude();
        LONGITUDE = lastKnownLocation.getLongitude();
        
        	LocationListener ll = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) { 
				LATITUDE = location.getLatitude();
				LONGITUDE = location.getLongitude();
				
				//Toast.makeText(UserData.this, LATITUDE+" "+LONGITUDE , Toast.LENGTH_LONG).show();
				 
			}
		};
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,500 , 0 , ll);
        
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

	    try {
	    	List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
	 
	    	if(addresses != null) {
	    		Address returnedAddress = addresses.get(0);
	    		StringBuilder strReturnedAddress = new StringBuilder();
	    		for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
	    			strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
	    		}
	    		//myAddress.setText(strReturnedAddress.toString());
	    		et_location.setText(strReturnedAddress.toString());
	    	}
	    	else{
	    		//myAddress.setText("No Address returned!");
	    		et_location.setText("N/A");
	    	}
	    } catch (IOException e) {
	    		e.printStackTrace();
	    	//myAddress.setText("Canont get Address!");
	    		et_location.setText("N/A");	
	    }
        
        
    }
	
	@Override
	protected void onPause() {
		
		record.setS_Name(et_name.getText().toString());
		record.setS_Id(et_id.getText().toString());
		record.setS_Location(et_location.getText().toString());
		record.setMobile(et_mobile.getText().toString());
		
		super.onPause();
	}
	
	

}
