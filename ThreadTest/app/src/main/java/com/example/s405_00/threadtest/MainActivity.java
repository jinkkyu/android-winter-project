package com.example.s405_00.threadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyThread myThread = new MyThread();
    TextView textView;
    MyHandler myHandler = new MyHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
    public void Fn(){
        int n=0;
        while (true)
        {
            Log.i("threadtest","n="+n);
            n++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void onClick( View v)
    {
        //Fn();// 일반함수 call thread call...
        //myThread.run(); // gen func call
        //myThread.start(); //thread run call
        runOnUiThread(new Runnable()
        {
            int n=0;
            String[] str = {"나는","자랑스런","태극기"};
            @Override
            public void run()
            {
                for( int i=0;i<10; i++)
                {
                    if (n == 3)
                        n = 0;
                    textView.setText(str[n]);
                    n++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Toast.makeText(this, "call",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        myThread.stopThread();
        super.onDestroy();
    }

    class MyThread extends Thread{
        boolean isStop= false;

        public void stopThread(){
            isStop = true;
        }
        @Override
        public void run(){
            int n=0;
            while (true){
                if( isStop)
                    break;
                Log.i("threadtest","n="+n);
                myHandler.sendEmptyMessage(0);
                n++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyHandler extends Handler{
        String[] str = {"나는","자랑스런","태극기"};
        int n=0;
        @Override
        public void handleMessage(Message msg) {
            if(n==3)
                n=0;
            textView.setText( str[n] );
            n++;
        }
    }

}



