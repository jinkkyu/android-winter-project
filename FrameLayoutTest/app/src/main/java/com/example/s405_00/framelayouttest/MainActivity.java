package com.example.s405_00.framelayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout lay1;
    LinearLayout lay2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lay1 = findViewById(R.id.view1);
        lay2 = findViewById(R.id.view2);
    }
    public void firstView(View v)
    {
        lay1.setVisibility(View.VISIBLE);
        lay2.setVisibility(View.INVISIBLE);
    }
    public void secondView( View v){
        lay1.setVisibility(View.INVISIBLE);
        lay2.setVisibility(View.VISIBLE);
    }
}


