package com.example.s405_00.dialogtest;

import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, DialogInterface.OnClickListener{
    Button btn;
    ConstraintLayout lay;
    TextView txtName;
    TextView txtAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getBaseContext(),
//                        "call", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public void onClick(View view)
    {
        lay = (ConstraintLayout) View.inflate(this,R.layout.mydlg, null);
        txtName = lay.findViewById(R.id.editText);
        txtAge = lay.findViewById(R.id.editText2);
        AlertDialog.Builder dlg =
                new AlertDialog.Builder(this);
        dlg.setView( lay);
        dlg.setTitle("다이아로그");
        dlg.setPositiveButton("확인", this);
        dlg.show();
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        String str = txtName.getText().toString()+txtAge.getText().toString();
        Toast.makeText(this,
                  str, Toast.LENGTH_SHORT).show();
    }
}






