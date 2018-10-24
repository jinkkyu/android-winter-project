package com.example.s405_00.imageselect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
    }
    void onClick( View v)
    {
        new SelectImage().execute();
    }
    class SelectImage extends AsyncTask<String, String,String>
    {
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            try {
                JSONArray jsonArray = new JSONArray(values[0]);
                for( int n=0; n< jsonArray.length();n++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(n);
                    String fname = jsonObject.getString("fname");
                    String fimg = jsonObject.getString("partdata");
                    byte[] bImg = Base64.decode(fimg, Base64.DEFAULT);
                    Bitmap bitmap =
                            BitmapFactory.decodeByteArray(bImg,0,bImg.length);
                    if(n==0) {
                        textView1.setText(fname);
                        imageView1.setImageBitmap(bitmap);
                    }else if(n==1){
                        textView2.setText(fname);
                        imageView2.setImageBitmap(bitmap);
                    }else if(n==2){
                        textView3.setText(fname);
                        imageView3.setImageBitmap(bitmap);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            Request request = new Request.Builder()
                    .url("http://220.67.115.90:8081/MyWebServer/selectpart.jsp")
                    .build();
            OkHttpClient client =new OkHttpClient();
            client.newCall(request).enqueue( new MyResponse() );
            return null;
        }
        class MyResponse implements Callback{

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJSON = response.body().string();
                publishProgress(strJSON);
            }
        }
    }
}



