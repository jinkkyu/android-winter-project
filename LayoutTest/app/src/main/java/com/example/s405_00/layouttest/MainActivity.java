package com.example.s405_00.layouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnClick( View v)
    {
        EditText editName = findViewById(R.id.editName);
        EditText editAge = findViewById(R.id.editAge);
        String strName = editName.getText().toString();
        String strAge = editAge.getText().toString();
        Toast.makeText(this,
                strName+" "+strAge,Toast.LENGTH_SHORT).show();

    }
}






