package com.example.s405_00.androidhttp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();
        editText = findViewById(R.id.editText);
    }
    public void onClick( View v)
    {
        String strUrl =
                "http://220.67.115.90:8081/MyWebServer/a.jsp?myname=aaa&myage=20";
        try {
            byte[] buffer = new byte[4096];
            URL url = new URL(strUrl);
//            URLConnection con =  url.openConnection(); //request
//            InputStream inResonse = con.getInputStream();
            InputStream inResonse =url.openStream();
            int nRead = inResonse.read(buffer);//response
            String str = new String(buffer,0,nRead);
            editText.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






