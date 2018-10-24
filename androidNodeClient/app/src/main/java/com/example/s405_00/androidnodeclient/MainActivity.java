package com.example.s405_00.androidnodeclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

import static android.graphics.BitmapFactory.decodeResource;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Socket dataSocket;
    EditText editTextRoom;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        editTextRoom = findViewById(R.id.editTextRoom);
        imageView = findViewById(R.id.imageView);
    }
    public void onConnect(View v)
    {
        try {
            dataSocket =
                IO.socket("http://220.67.115.90:4600");
            dataSocket.connect();
            dataSocket.on("ssend",new SSendListener());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    class SSendListener implements Emitter.Listener{
        String s;
        //JSONObject jsonObject;
        @Override
        public void call(Object... args) {
            s = (String) args[0];
           // jsonObject = (JSONObject) args[0];
//            try {
//                s = "이름:"+jsonObject.get("name")+"나이:"+jsonObject.get("age");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            //textView.setText( args[0].toString() );
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //textView.setText( s );
                    byte[] bImg = Base64.decode(s, Base64.DEFAULT);
                    Bitmap bitmap =
                            BitmapFactory.decodeByteArray(bImg,0,bImg.length);
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }
    public void onSend(View v){
        //dataSocket.emit("recv", "hello" );
//        dataSocket.emit("recv",
//                editText.getText().toString() );

//        try {
//            JSONObject jo =new JSONObject();
//            jo.put("room",editTextRoom.getText().toString());
//            jo.put("chat", editText.getText().toString());
//            dataSocket.emit("recv", jo);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        onSend1(null);

    }
    public void onRoom( View v)
    {
        dataSocket.emit("join",
                editTextRoom.getText().toString());
    }
    public void onSend1(View v)
    {
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.a);
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,
                100, byteArrayOutputStream);
        byte[] bsendImg = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(bsendImg, Base64.DEFAULT);
        dataSocket.emit("recv",  encodedImage );

    }

}






