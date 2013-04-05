/*
		Lackmann Mobile Android Application
		Version 1.0
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Fall 2012																	*/

package com.gamma.lackmann.mobile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Location_Select extends Activity 
{
	Context ctx = this;
	
	Button ward_haffey, murphy, cyber, cardinal, fishbowl;
	
	final File path = Environment.getExternalStorageDirectory(); 
	final File menu = new File(path + "/menu.xls");
	final File nutrition = new File(path + "/nutrition.xls");
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_select);
        
        setupVariables();
        CopyReadAssets();
        
		Calendar c = Calendar.getInstance();
		Date last = new Date(menu.lastModified());
		Date last2 = new Date(nutrition.lastModified());
		SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd");
		String s = cal.format(c.getTime());
		
		/* Checks file creation dates to make sure data files are up-to-date */ 
		
		// BEGIN Method ID# 3.0 
		if(s.equals(last.toString()) && s.equals(last2.toString()) )
		{	
			AlertDialog.Builder nutrition_box = new AlertDialog.Builder(ctx);
			nutrition_box.setTitle("Nutritional Information");
			nutrition_box.setIcon(R.drawable.lackmann_twitterpic);
			nutrition_box.setNeutralButton("OK!", null);
			nutrition_box.setMessage("Click on menu items to display nutrition facts."
										+ "\n\n" + "*** Nutritional information is provided by Lackmann" +
					" and may not be accurate.");
			nutrition_box.show();
		}
		else
		{
			AlertDialog.Builder nutrition_box = new AlertDialog.Builder(ctx);
			nutrition_box.setTitle("Nutritional Information");
			nutrition_box.setIcon(R.drawable.lackmann_twitterpic);
			nutrition_box.setNeutralButton("OK!", null);
			nutrition_box.setMessage("Data files appear out-of-date"
									+ "\n\n" + "*** Information may not be accurate.");
			nutrition_box.show();
		}
        // END Method ID# 3.0 
		
        ward_haffey.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		try
        		{
        			Class ourClass = Class.forName("com.gamma.lackmann.mobile.Ward_Haffey_Menu");
        			Intent ourIntent = new Intent(ctx, ourClass);
        			startActivity(ourIntent);
        		}
        		catch(ClassNotFoundException e)
        		{
        			e.printStackTrace();
        		} 
        	}
        }); 
        
        murphy.setOnClickListener(new View.OnClickListener() 
        {         
        	public void onClick(View v) 
        	{
        		try
        		{
        			Class ourClass = Class.forName("com.gamma.lackmann.mobile.Murphy_Menu");
        			Intent ourIntent = new Intent(ctx, ourClass);
        			startActivity(ourIntent);			
        		}
        		catch(ClassNotFoundException e)
        		{
        			e.printStackTrace();
        		} 
        	}
        });
    
        cyber.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		try
        		{
        			Class ourClass = Class.forName("com.gamma.lackmann.mobile.Cyber_Menu");
        			Intent ourIntent = new Intent(ctx, ourClass);
					startActivity(ourIntent);
        		}
        		catch(ClassNotFoundException e)
        		{
        			e.printStackTrace();
        		} 
        	}
        });
    
        cardinal.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		try
        		{
        			Class ourClass = Class.forName("com.gamma.lackmann.mobile.Cardinal_Menu");
        			Intent ourIntent = new Intent(ctx, ourClass);
        			startActivity(ourIntent);
        		}
        		catch(ClassNotFoundException e)
        		{
        			e.printStackTrace();
        		} 
        	}
        });
    
        fishbowl.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		Intent ourIntent = new Intent(Intent.ACTION_VIEW);
        		ourIntent.setDataAndType(Uri.parse("file://" + getFilesDir() + "/fishbowl.pdf"),
    	                "application/pdf");;
	            startActivity(ourIntent); 	
			}
        });
    }
    
    /*  Bowth Methods Reads FishBowl Menu PDF from Assets Folder	*/
    
    // BEGIN Method ID# 3.1.0
    private void CopyReadAssets()
    {
    	AssetManager assetmanager = getAssets();
    	
        InputStream in = null;
        OutputStream out = null;
        
        File file = new File(getFilesDir(), "fishbowl.pdf");
        
        try
        {
        	in = assetmanager.open("fishbowl.pdf");
        	out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
        		
        	copyFile(in,out);
        	in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } 
        catch (Exception e)
        {
        	Log.e("tag", e.getMessage());
        }
    }
    // END Method ID# 3.1.0
    
    // BEGIN Method ID# 3.1.1
    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    } 
    // END Method ID# 3.1.1
    
    private void setupVariables() 
    {
    	ward_haffey = (Button) findViewById(R.id.wardhaffey);
        murphy = (Button) findViewById(R.id.murphy);
        cyber = (Button) findViewById(R.id.cyber);
        cardinal = (Button) findViewById(R.id.cardinal);
        fishbowl = (Button) findViewById(R.id.fishbowl);
	} 
}