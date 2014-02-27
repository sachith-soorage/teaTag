package com.example.teatags;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class record_list extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list);
 
        ArrayList<SearchResults> searchResults = GetSearchResults();
 
        final ListView lv = (ListView) findViewById(R.id.srListView);
        lv.setAdapter(new MyCustomBaseAdapter(this, searchResults));
 
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                SearchResults fullObject = (SearchResults)o;
                Toast.makeText(record_list.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
	
	private ArrayList<SearchResults> GetSearchResults(){
		
		SQLiteDatabase db = null;
		Cursor c = null;
		ArrayList<SearchResults> results = new ArrayList<SearchResults>();
		
		try{
			db = openOrCreateDatabase("TeaDB", MODE_PRIVATE, null);
			c = db.rawQuery("select u_name, location, tea_net from TeaTable",null);
			SearchResults sr;
			if (c.moveToFirst()){
				   do{
				      //data = c.getString(c.getColumnIndex("name"));
				         sr = new SearchResults();
					     sr.setName(c.getString(c.getColumnIndex("u_name")));
					     sr.setLocation(c.getString(c.getColumnIndex("location")));
					     sr.setTea_weight(c.getString(c.getColumnIndex("tea_net"))+" KGs");
					     results.add(sr);
				      
				      
				      
				   }while(c.moveToNext());
				}
				c.close();		
				db.close();
				
		}catch(RuntimeException e)
		{
			 Toast.makeText(record_list.this, "No data in the list", Toast.LENGTH_SHORT).show();
			 finish();
		}
		
	     return results;
	    }
}
 


