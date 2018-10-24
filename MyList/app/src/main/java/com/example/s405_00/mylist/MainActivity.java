package com.example.s405_00.mylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener
{
    ListView listView;
    ArrayList<Map<String, Object>> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        initListView();
    }
    public void initListView()
    {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("dimg", R.drawable.a);
        item1.put("dname", "드라마1");
        item1.put("dcontent", "kbs 아침드라마..");
        arr.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("dimg", R.drawable.b);
        item2.put("dname", "드라마2");
        item2.put("dcontent", "kbs 월화드라마..");
        arr.add(item2);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("dimg", R.drawable.c);
        item3.put("dname", "드라마3");
        item3.put("dcontent", "kbs 주말드라마..");
        arr.add(item3);

        SimpleAdapter simpleAdapter=
                new SimpleAdapter(this,arr,
                        R.layout.listlay,
                        new String[]{"dimg","dname"},
                        new int[]{R.id.imageViewDrama,R.id.textViewDrama});
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String,Object> map = arr.get(i);
        int nImg = (int) map.get("dimg");
        String strDName = (String) map.get("dname");
        String strDContent = (String) map.get("dcontent");


        Intent intent = new Intent(this,OtherActivity.class);
        intent.putExtra("dimg", nImg);
        intent.putExtra("dname", strDName);
        intent.putExtra("dcontent", strDContent);

        startActivity(intent);

    }
}
