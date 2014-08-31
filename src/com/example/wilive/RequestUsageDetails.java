package com.example.wilive;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class RequestUsageDetails extends AsyncTask<String, Void, String>
{
	    String deviceID = null;
	    String accessToken = null;
	    View frag = null;
	    RequestUsageDetails(View frag, String deviceID, String accessToken)
	    {
	    	this.frag=frag;
	    	this.deviceID =deviceID;
	    	this.accessToken = accessToken;
	    	
	    }
	    @Override
	    protected void onPreExecute()
	    {
	        super.onPreExecute();
	    }

	    @Override
	    protected String doInBackground(String... params)
	    {
	        String result = "";
	       
	       try{
	    	   HttpClient httpClient = new DefaultHttpClient();
	    	   
	    	   HttpPost httppost = new HttpPost("https://api.spark.io/v1/devices/" + deviceID + "/events/?access_token=" + accessToken);
	    	   
	    	   HttpResponse response = httpClient.execute(httppost);
	    	   
	    	   HttpEntity entity = response.getEntity();
	    	   
	    	   InputStream webs = entity.getContent();
	    	   
	    	   try{
	    		   BufferedReader reader = new BufferedReader(new InputStreamReader(webs,"iso-8859-1"),8);
	    		   StringBuilder sb = new StringBuilder();
	    		   String line = null;
	    		   while((line = reader.readLine())!=null){
	    			   sb.append(line + "\n");
	    			   
	    		   }
	    		   webs.close();
	    		   result = sb.toString();
	    		 
	    	   }
	    	   catch(Exception e)
	    	   {
	    		   Log.e("log_tag","Error in converting to string "+e.toString());
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   Log.e("log_tag","Error in http connection "+e.toString());
	       }
	       try{
	    	   JSONArray jArray = new JSONArray(result);
	    	   
	       }
	       catch(Exception e){
	    	   Log.e("log_tag","Error in parsing JSON "+e.toString());
	       }
	    
	        return (result);
	    }

	    @Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
	    	
			super.onProgressUpdate(values);
		}
		@Override
	    protected void onPostExecute(String result)
	    {  
//			TextView T1 = (TextView) c.findViewById(R.id.Voltage);
//		   T1.setText(result);
	        super.onPostExecute(result);

	  
	    }
}