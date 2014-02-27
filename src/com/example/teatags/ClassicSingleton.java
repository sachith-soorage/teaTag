package com.example.teatags;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.format.DateFormat;

public class ClassicSingleton {

	   private static ClassicSingleton instance = null;
	   
	   private static String s_Name = null;
	   private static String s_Id = null;
	   private static String s_Location = null;
	   private static String tea_weight = null;
	   private static String num_of_containers = null;
	   private static String con_weight = null;
	   private static String tea_net_weight = null;
	   private static String tea_quality = null;
	   private static String mobile = null;
	   


	protected ClassicSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   public static ClassicSingleton getInstance() {
	      if(instance == null) {
	         instance = new ClassicSingleton();
	      }
	      return instance;
	   }
	   
	   public boolean isValid()
	   {
		   
		   
		   if(	getS_Name() == null || getS_Name().isEmpty() ||
				getS_Location() == null || getS_Location().isEmpty() ||
				getTea_wight() == null || getTea_wight().isEmpty() ||
				getNum_of_containers() == null || getNum_of_containers().isEmpty() ||
				getCon_weight() == null || getCon_weight().isEmpty() || 
				getS_Id() == null || getS_Id().isEmpty() ||
				getMobile() == null || getMobile().isEmpty() ||
				getTea_quality() == null || getTea_quality().isEmpty() || getTea_quality() == "Select" )
		   {
			   return false;
			   
		   }else
		   {
			   return true;
		   }
		   
	   }
	   
	   public String generateReport()
	   {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Date date = new Date();
		   
		   double tea_weight = Double.parseDouble(getTea_wight().toString()); 
		   double container_weight = Double.parseDouble(getCon_weight().toString());
		   int container_count = Integer.parseInt(getNum_of_containers().toString());
		   
		   double net_tea_weight =  tea_weight - (container_weight * container_count);
		   setTea_net_weight(Double.toString(net_tea_weight));
		   
		   String report_output = "Supplier Name 	  : "+getS_Name()+"\n";
		   report_output		+="Supplier Number    :"+getS_Id()+"\n";
		   report_output		+="Collected Location :"+getS_Location()+"\n";
		   report_output		+="Gross weight of tea leaves (Kg)     :"+getTea_wight()+"\n";
		   report_output		+="Number of containers                :"+getNum_of_containers()+"\n";
		   report_output		+="Leaf condition   	               :"+getTea_quality()+"\n";
		   report_output		+="Date and Time                       :"+dateFormat.format(date)+"\n";
		   report_output		+="Weight of one single container (Kg) :"+getCon_weight()+"\n\n";
		   
		   report_output		+="Net weight of tea leaves (Kg) :  "+net_tea_weight+"\n";
		   
		
		  return report_output;
	   }
	   
	   public String generateSmsReport()
	   {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Date date = new Date();
		   
		   double tea_weight = Double.parseDouble(getTea_wight().toString()); 
		   double container_weight = Double.parseDouble(getCon_weight().toString());
		   int container_count = Integer.parseInt(getNum_of_containers().toString());
		   
		   double net_tea_weight =  tea_weight - (container_weight * container_count);
		   setTea_net_weight(Double.toString(net_tea_weight));
		   
		   String report_output = "Name: "+getS_Name()+"\n";
		   report_output		+="Code: "+getS_Id()+"\n";
		   report_output		+="Location: "+getS_Location()+"\n";
		   report_output		+="Gross weight(Kg): "+getTea_wight()+"\n";
		   
		   report_output		+="Quality of goods: "+getTea_quality()+"\n";
		   report_output		+="Date and Time: "+dateFormat.format(date)+"\n\n";
		   
		   
		   report_output		+="Net weight of tea leaves (Kg): "+net_tea_weight+"\n";
		   
		
		  return report_output;
	   }
	   
	   
	   
	  
	   

	public static String getS_Name() {
		return s_Name;
	}
	public void setS_Name(String s_Name) {
		this.s_Name = s_Name;
	}
	public static String getS_Location() {
		return s_Location;
	}
	public void setS_Location(String s_Location) {
		this.s_Location = s_Location;
	}
	public static String getTea_wight() {
		return tea_weight;
	}
	public void setTea_wight(String tea_wight) {
		this.tea_weight = tea_wight;
	}
	public static String getNum_of_containers() {
		return num_of_containers;
	}
	public void setNum_of_containers(String num_of_containers) {
		this.num_of_containers = num_of_containers;
	}
	public static String getCon_weight() {
		return con_weight;
	}
	public void setCon_weight(String con_weight) {
		this.con_weight = con_weight;
	}
	public static String getS_Id() {
		return s_Id;
	}
	public void setS_Id(String s_Id) {
		this.s_Id = s_Id;
	}
	public static String getTea_net_weight() {
		return tea_net_weight;
	}
	public static void setTea_net_weight(String tea_net_weight) {
		ClassicSingleton.tea_net_weight = tea_net_weight;
	}
	public static String getTea_quality() {
		return tea_quality;
	}
	public static void setTea_quality(String tea_quality) {
		ClassicSingleton.tea_quality = tea_quality;
	}
	public static String getMobile() {
		return mobile;
	}
	public static void setMobile(String mobile) {
		ClassicSingleton.mobile = mobile;
	}



	}