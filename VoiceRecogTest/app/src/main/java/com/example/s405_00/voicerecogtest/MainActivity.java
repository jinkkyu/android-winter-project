package com.example.s405_00.voicerecogtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SpeechRecognizer sr;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        GetVoicePermission();
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new VoiceListener());
    }
    public void onClick( View v)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
        sr.startListening(intent); //음성을 듣기 시작...
    }
    class VoiceListener implements RecognitionListener
    {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }
        @Override
        public void onBeginningOfSpeech() {
        }
        @Override
        public void onRmsChanged(float v) {
        }
        @Override
        public void onBufferReceived(byte[] bytes) {
        }
        @Override
        public void onEndOfSpeech() {
        }
        @Override
        public void onError(int i) {
            Toast.makeText(MainActivity.this, "error:"+i, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onResults(Bundle results) {


            ArrayList<String> matches =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);


            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1,matches);
            listView.setAdapter(adapter);

        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    }
    public void GetVoicePermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
        }
    }
}
