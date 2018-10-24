package com.example.s405_00.xmltest2;

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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
    }
    public void onClick(View v)
    {
        String kUrl="http://web.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";

        try {
            Document doc = Jsoup.connect(kUrl).timeout(330000).get();
            Elements trs = doc.select("city");
            for( Element tr : trs)
            {
                arr.add(tr.text());
            }
            ArrayAdapter<String> adapter=
                    new ArrayAdapter<String>(
                            this,android.R.layout.simple_list_item_1,
                            arr );
            listView.setAdapter(adapter);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void onClick2( View v)
    {
        String strUrl =
                "http://220.67.115.90:8081/MyWebServer/a.html";
        try {
            Document doc = Jsoup.connect(strUrl).get();
//            doc.select("#mytable tbody")
            //doc.select(".table tbody")
            Elements trs = doc.select("tbody tr");
            for( Element tr : trs)
            {
                //tr.child(0).text()
                //tr.child(1).text()
                arr.add(tr.child(0).text()+tr.child(1).text());
            }
            ArrayAdapter<String> adapter=
                    new ArrayAdapter<String>(
                            this,android.R.layout.simple_list_item_1,
                            arr );
            listView.setAdapter(adapter);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void onClick1( View v)
    {
        String strUrl =
                "http://220.67.115.90:8081/MyWebServer/song.xml";
        try {
            Document doc = Jsoup.connect(strUrl).get();
            Elements songElm = doc.select("song");
            Element song0 = songElm.get(0);
            Element title = song0.selectFirst("title");
            Element sname = title.nextElementSibling();
//            Element title1 = sname.previousElementSibling();
//            Elements siblings = sname.siblingElements();
            Element songP = sname.parent();
            Element song1= songP.nextElementSibling();

            //Element t1= song1.child(0);
            Elements song1Children = song1.children();

            for( Element e : song1Children)
            {
                Toast.makeText(this,
                        e.text(),
                        Toast.LENGTH_SHORT).show();
            }

//            Toast.makeText(this,
//                    song1Children.get(0).text(),
//                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




