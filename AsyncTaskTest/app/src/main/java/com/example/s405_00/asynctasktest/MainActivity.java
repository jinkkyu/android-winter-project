package com.example.s405_00.asynctasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    MyAsyncTask myAsyncTask = new MyAsyncTask();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
    public void onClick(View v)
    {
        myAsyncTask.execute("나는","자랑스런","태극기"); //thread.start()
         // run() --> doInBackground
        //myAsyncTask.cancel(true);//thread 종료

    }
    @Override
    protected void onDestroy() {
        myAsyncTask.cancel(true);
        super.onDestroy();
    }

    class MyAsyncTask extends AsyncTask<String,String,String>
    {
        int nCnt=0;
        //handler
        @Override
        protected void onProgressUpdate(String... values) {
            if( nCnt==3)
                nCnt=0;
            textView.setText( values[nCnt]);
            nCnt++;
            super.onProgressUpdate(values);
        }
       //thread
        @Override
        protected String doInBackground(String... strings) {
            int n =0;
            while (isCancelled()==false)
            {
                publishProgress(strings);//sendEmptyMessage(0)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
