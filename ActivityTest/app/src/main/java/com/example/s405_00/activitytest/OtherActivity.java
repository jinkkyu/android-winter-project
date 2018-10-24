package com.example.s405_00.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtherActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        Intent i = getIntent();
        String v1 = i.getStringExtra("key1");
        Toast.makeText(this, v1, Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.textViewOther);
        textView.setText(i.getStringExtra("key2"));
    }
    public void closeActivity( View v)
    {
        EditText editText = findViewById(R.id.editTextOther);
        Intent i = getIntent();
        i.putExtra("c1", "hi");
        i.putExtra("c2", editText.getText().toString());
        setResult(RESULT_OK, i);// RESULT_CANCEL
        finish();
    }

    @Override
    public void onBackPressed() {
        EditText editText = findViewById(R.id.editTextOther);
        Intent i = getIntent();
        i.putExtra("c1", "hi");
        i.putExtra("c2", editText.getText().toString());
        setResult(RESULT_OK, i);// RESULT_CANCEL
        super.onBackPressed(); // finish()
    }
}



