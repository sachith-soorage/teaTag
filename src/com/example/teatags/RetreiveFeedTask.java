package com.example.teatags;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.os.AsyncTask;


class RetreiveFeedTask extends AsyncTask<String, Void, Boolean> {

	HttpClient httpclient = new DefaultHttpClient();

    protected Boolean doInBackground(String... urls) {
    	try {
    		
			HttpResponse response = httpclient.execute(new HttpGet("http://220.247.237.10:800/SMSRelay.php?msg="+
		    java.net.URLEncoder.encode(urls[0].toString(),"UTF-8")
    		+"&msisdn="+java.net.URLEncoder.encode(ClassicSingleton.getMobile().toString(),"UTF-8")));
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    protected void onPostExecute(Boolean result) {
        
    }


}
