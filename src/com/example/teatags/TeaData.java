package com.example.teatags;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

public class TeaData extends Activity {
	
	EditText et_tea_weight = null;
	EditText et_tea_containers = null;
	EditText et_tea_con_weight = null;
	Spinner spinner1 = null;
	
	ClassicSingleton record = ClassicSingleton.getInstance();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea);
        
        et_tea_weight = (EditText) findViewById(R.id.tea_et1);
        et_tea_containers = (EditText) findViewById(R.id.tea_et2);
        et_tea_con_weight = (EditText) findViewById(R.id.tea_et3);
        
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());

        
    }
	
	@Override
	protected void onPause() {
		
		record.setTea_wight(et_tea_weight.getText().toString());
		record.setNum_of_containers(et_tea_containers.getText().toString());
		record.setCon_weight(et_tea_con_weight.getText().toString());
		
		super.onPause();
	}
}
