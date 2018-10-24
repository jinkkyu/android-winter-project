package com.example.s405_00.xmltest;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();
        listView = findViewById(R.id.listView);

    }
    public void onClick( View v)
    {
        arr.clear();
        String sUrl = "http://220.67.115.90:8081/MyWebServer/song.xml";
        String jUrl="http://rss.joins.com/joins_news_list.xml";
        try {
            Document doc = Jsoup.connect(jUrl).get();
            Elements items = doc.select("item");
            for( Element item : items){
                arr.add(item.select("title").text());
            }
            ArrayAdapter<String> adapter=
                    new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1,arr);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //            Element song = songs.get(0);
//            String s = song.select("title").text();
//            Toast.makeText(this,s
//                    , Toast.LENGTH_SHORT).show();

}


