package com.example.s405_00.listviewtest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{
    ListView listView;
    ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }
    public void onClick(View v)
    {
//        String[] str={"이순신","강감찬", "홍길동","임꺽정"};
//        ArrayAdapter<String> adapter=
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1,str);
//
//        listView.setAdapter(adapter);
        //layout, data집합
        //String[] str={"이순신","강감찬", "홍길동","임꺽정"};
        arr.add("이순신");
        arr.add("강감찬");
        arr.add("홍길동");
        arr.add("임꺽정");
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this,
                        R.layout.mylay, R.id.textViewName, arr);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"i:"+arr.get(i), Toast.LENGTH_SHORT).show();
    }
}











