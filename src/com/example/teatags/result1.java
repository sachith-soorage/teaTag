package com.example.teatags;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class result1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result1);
		
		Button btSave = (Button) findViewById(R.id.button1);
		
		final EditText etName = (EditText) findViewById(R.id.editText1);
		final EditText etAddress = (EditText) findViewById(R.id.editText2);
		final EditText etLocation = (EditText) findViewById(R.id.editText3);
		final EditText etLastDate = (EditText) findViewById(R.id.editText4);
		final EditText etTeaWeight = (EditText) findViewById(R.id.editText5);
		
		
    	String input = getIntent().getExtras().getString("input");
    	String[] set = input.split(" ");  
    	//String output = "Read content: " + set[0];
		  
    	etName.setText(set[0]);
    	etAddress.setText(set[1]);
    	etLocation.setText(set[2]);
    	etLastDate.setText(set[3]);
		//tv.setText(output);
    	
    	
    	
    	
    	btSave.setOnClickListener(new OnClickListener() {
			
    		
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = openOrCreateDatabase("TeaDB", MODE_PRIVATE, null);
				db.execSQL("create table if not exists TeaTable (name varchar, address varchar, location varchar);");
				db.execSQL("insert into TeaTable values ('"+etName.getText().toString()+"','"+etAddress.getText().toString()+"','"+etLocation.getText().toString()+"');");
				db.close();
				
				Toast.makeText(result1.this, "data saved",Toast.LENGTH_LONG).show();
				
				finish();
				
			}
		});
    	
    	
    	
	}
}
