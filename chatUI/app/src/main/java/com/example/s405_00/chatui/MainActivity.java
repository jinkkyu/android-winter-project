package com.example.s405_00.chatui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ChatAdapter chatAdapter;
    boolean isSend=false;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        chatAdapter = new ChatAdapter();
        listView.setAdapter(chatAdapter);
    }
    public  void onRecv( View v)
    {
        isSend = false;
        chatAdapter.add(editText.getText().toString());
        chatAdapter.notifyDataSetChanged();

    }
    public void onSend( View v)
    {
        isSend = true;
        chatAdapter.add(editText.getText().toString());
       // chatAdapter.notifyDataSetChanged();

    }
    class ChatAdapter extends BaseAdapter {

        ArrayList<String> arr = new ArrayList<>();

        public void add(@Nullable String object) {
            arr.add(object);
        }

        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int i) {
            return arr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
            LinearLayout v = (LinearLayout)convertView;
            if( v == null)
                v = (LinearLayout) View.inflate(MainActivity.this,R.layout.listlay,null);

            TextView textView = v.findViewById(R.id.textView);
            if(isSend) {

                textView.setBackgroundResource(R.drawable.outbox2);
                textView.setText(arr.get(position));
                v.setGravity(Gravity.RIGHT);
            }
            else {

                textView.setBackgroundResource(R.drawable.inbox2);
                textView.setText(arr.get(position));
                v.setGravity(Gravity.LEFT);
            }

            return v;
        }
    }
}






