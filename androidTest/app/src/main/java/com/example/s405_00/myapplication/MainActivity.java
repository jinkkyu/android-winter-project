package com.example.s405_00.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ctrl + /
//        LinearLayout lay = new LinearLayout(this);
//        lay.setOrientation(LinearLayout.VERTICAL);
//        Button btn = new Button(this);
//        btn.setText("click");
//        TextView txt = new TextView(this);
//        txt.setText("test");
//        lay.addView(btn);
//        lay.addView(txt);
//        setContentView(lay);

    }
    public void myClick( View v)
    {
        //Toast.makeText(this,"test",
        //        Toast.LENGTH_SHORT).show();
        //
        EditText myedit =  findViewById(R.id.editText); //객체를 얻어...
        myedit.setText("대한민국");

    }
}






