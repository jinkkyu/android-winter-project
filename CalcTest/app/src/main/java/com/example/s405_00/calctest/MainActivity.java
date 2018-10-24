package com.example.s405_00.calctest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.s405_00.calctest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
    }
    public void btnClick(View v)
    {
        String strNum1 = mainBinding.editTextNum1.getText().toString();
        int n1 = Integer.parseInt(strNum1);
        String strNum2 = mainBinding.editTextNum2.getText().toString();
        int n2 = Integer.parseInt(strNum2);
        int sum = n1+n2;
        mainBinding.editTextResult.setText("결과:"+sum);

//        EditText num1 = findViewById(R.id.editTextNum1);
//        EditText num2 = findViewById(R.id.editTextNum2);
//        EditText result = findViewById(R.id.editTextResult);
//        int n1 = Integer.parseInt(num1.getText().toString() );
//        int n2 = Integer.parseInt(num2.getText().toString() );
//        int sum = n1+n2;
//        result.setText("결과:"+sum);
    }
}




