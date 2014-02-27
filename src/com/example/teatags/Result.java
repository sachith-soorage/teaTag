package com.example.teatags;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Result extends Activity {
	
	ClassicSingleton record = ClassicSingleton.getInstance();
	
	Button refresh;
	Button submit;
	TextView tv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        refresh = (Button) findViewById(R.id.button1);
        submit = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView_output);
        
        submit.setEnabled(false);
       
        refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(record.isValid())
				{
					String output = record.generateReport();
					tv.setText(output.toString());
					submit.setEnabled(true);
					
					
				}else{
					Toast.makeText(Result.this, "Data incomplete",Toast.LENGTH_SHORT).show();
					
				}
			}
		});
        
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = openOrCreateDatabase("TeaDB", MODE_PRIVATE, null);
				db.execSQL("create table if not exists TeaTable (u_name varchar," +
							"u_id varchar, location varchar, tea_gross varchar, cont_count varchar," +
							"cont_weight varchar, tea_net varchar);");
				
				db.execSQL("insert into TeaTable values ('"+
						record.getS_Name().toString()+"','"+record.getS_Id().toString()+"','"+record.getS_Location().toString()+
						"','"+record.getTea_wight().toString()+"','"+record.getNum_of_containers().toString()+"','"+record.getCon_weight().toString()
						+"','"+record.getTea_net_weight().toString()+"');");
				
				db.close();
				
				
				String SmsFeed = record.generateSmsReport().toString().replaceAll("\n", ";");
				
				new RetreiveFeedTask().execute(SmsFeed);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				//dateFormat.format(date)
				
				String url = null;
				try {
					url = "http://118.139.161.58//tea/insert.php?name="+java.net.URLEncoder.encode(record.getS_Name().toString(),"UTF-8")+"&num="+java.net.URLEncoder.encode(record.getS_Id().toString(),"UTF-8")+
																				"&loc="+java.net.URLEncoder.encode(record.getS_Location().toString(),"UTF-8")+"&gross="+java.net.URLEncoder.encode(record.getTea_wight().toString() ,"UTF-8")+
																				"&con="+java.net.URLEncoder.encode(record.getNum_of_containers().toString(),"UTF-8")+"&net="+java.net.URLEncoder.encode(record.getTea_net_weight().toString(),"UTF-8") +
																				"&qual="+java.net.URLEncoder.encode(record.getTea_quality().toString(),"UTF-8")+"&date="+java.net.URLEncoder.encode(dateFormat.format(date),"UTF-8") +
																				"&c_weight="+java.net.URLEncoder.encode(record.getCon_weight().toString(),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url); 
				
				
				HttpResponse response;
				try {
					response = httpclient.execute(httpget);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				Toast.makeText(Result.this, "Data saved",Toast.LENGTH_SHORT).show();
				
				
				Intent intent = new Intent(Result.this,MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

				
			}
		});
        
        
        
    }
	
	@Override
	protected void onResume() {
		if(record.isValid())
		{
			String output = record.generateReport();
			tv.setText(output.toString());
			submit.setEnabled(true);
			
			
		}else{
			Toast.makeText(Result.this, "Data incomplete",Toast.LENGTH_SHORT).show();
			
		}
		super.onResume();
	}
	
	
}
