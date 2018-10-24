package com.example.s405_00.okhttptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    MyAync myAync;
    TextView textView;
    EditText editTextName;
    EditText editTextAge;
    ArrayList<String> arrayList = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        listView = findViewById(R.id.listView);
    }
    public void onClick(View v)
    {
        myAync= new MyAync();
        myAync.execute(editTextName.getText().toString(),
                editTextAge.getText().toString());
    }
    public void onSelect(View v)
    {
        new MySelectAsync().execute();
    }
    class MySelectAsync extends AsyncTask<String, String,String>
    {
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<String>(getBaseContext(),
                    android.R.layout.simple_list_item_1,
                    arrayList);
            listView.setAdapter(arrayAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            Request request = new Request.Builder()
            .url("http://220.67.115.90:8081/MyWebServer/select.jsp")
            .build();

            OkHttpClient client =new OkHttpClient();
            client.newCall(request).enqueue(new SelectResponse());
            return null;
        }
        class SelectResponse implements Callback{
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJSON = response.body().string();
                try {
                    JSONArray jsonArray =
                            new JSONArray(strJSON);
                    for( int n=0; n<jsonArray.length(); n++)
                    {
                        JSONObject jo = jsonArray.getJSONObject(n);
                        arrayList.add("이름:"+jo.getString("name")+"나이:"+jo.getInt("age"));
                    }
                    publishProgress();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class MyAync extends AsyncTask<String,String,String>{
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]);
        }
        @Override
        protected String doInBackground(String... strings) {
            String strURL = "http://220.67.115.90:8081/MyWebServer/oka.jsp";
//            HttpUrl httpUrl = HttpUrl.parse(strURL)
//                    .newBuilder()
//                    .addQueryParameter("myname","홍길동")
//                    .addQueryParameter("myage","30")
//                    .build();
//            Request request = new Request.Builder()
//                    .url(httpUrl)
//                    .get()
//                    .build();

            RequestBody bodyReq = new FormBody.Builder()
                    .add("myname",strings[0] )
                    .add( "myage",strings[1] )
                    .build();
            Request request = new Request.Builder()
                    .url("http://220.67.115.90:8081/MyWebServer/insert2.jsp")
                    .post(bodyReq)
                    .build();

            OkHttpClient client =new OkHttpClient();
            try {
                //client.newCall(request).execute(); //request
                client.newCall(request).enqueue(new ResponseData());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        class ResponseData implements Callback{
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                publishProgress(data);
            }
        }

    }
}


