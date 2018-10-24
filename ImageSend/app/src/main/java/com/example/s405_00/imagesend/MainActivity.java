package com.example.s405_00.imagesend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    byte[] bsendImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
    public void onClick( View v)
    {

        ImageView imageView;
        getPermission();
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setType("image/*");
        startActivityForResult(intent,0);
        //new ImgSendAsync().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,
                    100, byteArrayOutputStream);
            bsendImg = byteArrayOutputStream.toByteArray();
            //String encodedImage = Base64.encodeToString(bsendImg, Base64.DEFAULT);
            //socket.emit('image', { image: true, buffer: buf.toString('base64') });
            new ImgSendAsync().execute();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

//        try {
//            Uri sImg = data.getData();
//            String path = RealPathUtil.getRealPath(this, sImg);
//            Toast.makeText(this, path,
//                    Toast.LENGTH_SHORT).show();
//            new ImgSendAsync().execute(path);
//        }catch (Exception ex){
//            Toast.makeText(this, ex.getMessage(),
//                    Toast.LENGTH_SHORT).show();
//        }

    }

    class ImgSendAsync extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {

            try {
                RequestBody bodyReq = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("fname", "테스트")
                        .addFormDataPart("sfile", "my.jpg",
                                RequestBody.create(MultipartBody.FORM,
                                        bsendImg))
//                    .addFormDataPart( "sfile","my.jpg",
//                    RequestBody.create(MultipartBody.FORM,
//                            new File(strings[0]) ) )
                        .build();

                Request request = new Request.Builder()
                        .url("http://220.67.115.90:8081/MyWebServer/insertpart.jsp")
                        .post(bodyReq)
                        .build();

                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new MyResponse());
            }catch (Exception ex){
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]);
        }
        class MyResponse implements Callback{
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                publishProgress(str);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }

        }
    }


    public void getPermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }
    }
}




