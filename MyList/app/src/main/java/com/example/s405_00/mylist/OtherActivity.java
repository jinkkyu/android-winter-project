package com.example.s405_00.mylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Intent  i = getIntent();
        int nImg = i.getIntExtra("dimg", 0);
        String strDname = i.getStringExtra("dname");
        String strDcontent = i.getStringExtra("dcontent");

        TextView textView = findViewById(R.id.textViewTitle);
        textView.setText(strDname);
        ImageView imageView = findViewById(R.id.imageViewD);
        imageView.setImageResource(nImg);
        TextView textView1 = findViewById(R.id.textViewContent);
        textView1.setText(strDcontent);
    }
}



