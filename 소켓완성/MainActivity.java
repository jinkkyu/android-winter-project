package com.example.s405_00.androidsockettest;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    InputStream inputStream;
    OutputStream outputStreamStream;
    ReadAsync readAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
    }
    public void onConnect( View v){
        try {
            //데이터 소켓 생성후, 접속
            byte[] buffer = new byte[256];
            Socket socket = new Socket("220.67.115.90",4500);
            inputStream= socket.getInputStream();
            outputStreamStream= socket.getOutputStream();
            readAsync = new ReadAsync();
            readAsync.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ReadAsync extends AsyncTask<String,String,String>{
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]);
        }
        @Override
        protected String doInBackground(String... strings){
            while (isCancelled()==false){
                byte[] buffer = new byte[256];
                try {
                    int nRead = inputStream.read(buffer);
                    String s = new String(buffer,0,nRead);
                    publishProgress(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    public void onSend( View v){
        String s =editText.getText().toString();
        try {
            outputStreamStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        readAsync.cancel(true);
    }
}




