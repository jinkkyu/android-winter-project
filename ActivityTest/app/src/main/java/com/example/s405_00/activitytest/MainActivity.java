package com.example.s405_00.activitytest;

import android.content.Intent;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK)
        {
           String v1 = data.getStringExtra("c1");
           Toast.makeText(this,v1,Toast.LENGTH_SHORT).show();
           EditText editText = findViewById(R.id.editTextMain);
           editText.setText(data.getStringExtra("c2"));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View v)
    {
        EditText editText = findViewById(R.id.editTextMain);
        Intent i = new Intent(this,
                OtherActivity.class);
        i.putExtra("key1", "hello");
        i.putExtra("key2",editText.getText().toString() );
        //startActivity(i);
        startActivityForResult(i, 0);
    }
}
