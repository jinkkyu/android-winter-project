package com.example.s405_00.simpletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
    }
    public void onClick( View v)
    {
        List<Map<String, Object>> arr = new
                ArrayList<>();
        Map<String, Object> currentItem=
                new HashMap<>();
        currentItem.put("title", "신과함께");
        currentItem.put("actor", "하정우");
        currentItem.put("image", R.drawable.a);
        arr.add( new HashMap<String, Object>(currentItem));
        currentItem.put("title", "1987");
        currentItem.put("actor", "하정우");
        currentItem.put("image", R.drawable.b);
        arr.add( new HashMap<String, Object>(currentItem));

        SimpleAdapter adapter =
                new SimpleAdapter(this,arr,
                        R.layout.movie,
                        new String[]{"title","actor","image"},
                        new int[]{R.id.textViewMname,
                                R.id.textViewActor,
                                R.id.imageView});

        listView.setAdapter(adapter);
    }
}





