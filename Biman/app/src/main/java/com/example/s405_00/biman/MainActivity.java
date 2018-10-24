package com.example.s405_00.biman;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText editTextHeight;
    EditText editTextWeight;
    ImageView imageView;
    EditText editTextResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextResult = findViewById(R.id.editTextResult);
        imageView = findViewById(R.id.imageView);

    }
    public void calcBiman( View v)
    {
        //표준체중 = (신장 - 100) * 0.85
        //비만도 =  현재체중/표준체중*100
//        90이하 저체중
//        90 ~ 110 정상
//        110 ~ 120 과체중
//        120 이상 비만 
InputMethodManager imm =
(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        int nH = Integer.parseInt(editTextHeight.getText().toString());
        int nW = Integer.parseInt(editTextWeight.getText().toString());
        double stdWeight = (nH-100)*0.85;
        double obesity = nW/stdWeight*100;
        String result = "나의키:"+nH+"\n"+"몸무게:"+nW+"\n";
        if( obesity<= 90){
            result += "결과:저체중";
            imageView.setImageResource(R.drawable.a);
        }else if( obesity <=110){
            result += "결과:정상";
            imageView.setImageResource(R.drawable.b);
        }else if( obesity <=120 ){
            result += "결과:과체중";
            imageView.setImageResource(R.drawable.c);
        }else if( obesity > 120){
            result += "결과:비만";
            imageView.setImageResource(R.drawable.c);
        }
        editTextResult.setText(result);
    }
}



