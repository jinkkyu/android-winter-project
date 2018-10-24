package com.example.s405_00.androiddbinsert;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editTextName;
    EditText editTextAge;
    ListView listView;
    ArrayList<String>  arrayList = new ArrayList<>();
    MyAsync myAsync = new MyAsync();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        listView = findViewById(R.id.listView);
    }
    public void onClick(View v){
        myAsync.execute(editTextName.getText().toString(),
                editTextAge.getText().toString());
    }
    class MyAsync extends AsyncTask<String, String,String>{
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //textView.setText( values[0] );
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);
        }
        @Override
        protected String doInBackground(String... strings) {
//            String strUrl =
//                    "http://220.67.115.90:8081/MyWebServer/insert1.jsp?myname=aaa&myage=30";
//            String strUrl=
//    String.format("http://220.67.115.90:8081/MyWebServer/insert2.jsp?myname=%s&myage=%s",
//                                strings[0], strings[1]);
            String strUrl =
                    "http://220.67.115.90:8081/MyWebServer/select.jsp";
            try {
                byte[] buffer = new byte[4096];
                URL url = new URL(strUrl);
                InputStream inResonse =url.openStream();//request
                int nRead = inResonse.read(buffer);//response
                String str = new String(buffer,0,nRead);
                parseJSON(str);
                publishProgress();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        public void parseJSON( String data)
        {
            try {
                JSONArray arr = new JSONArray(data);
                for(int n=0;n<arr.length(); n++)
                {
                    JSONObject jo = arr.getJSONObject(n);
      arrayList.add(jo.getString("name")+jo.getInt("age"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}



