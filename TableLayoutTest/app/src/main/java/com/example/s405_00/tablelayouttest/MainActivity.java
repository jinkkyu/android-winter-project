package com.example.s405_00.tablelayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int[] imgArr = { R.drawable.a, R.drawable.b, R.drawable.c};
    int nCnt =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }
    public void chImg( View v){
        nCnt--;
        if( nCnt== -1){
            nCnt = 0;
            Toast.makeText(this,"더이상없음",Toast.LENGTH_SHORT).show();
        }
        imageView.setImageResource( imgArr[nCnt] );
    }
    public void chNext( View v){
        nCnt++;
        if(nCnt==3) {
            nCnt = 2;
            Toast.makeText(this,"더이상없음",Toast.LENGTH_SHORT).show();
        }
        imageView.setImageResource( imgArr[nCnt] );
    }
}
