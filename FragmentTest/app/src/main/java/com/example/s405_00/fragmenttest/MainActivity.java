package com.example.s405_00.fragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragOne fragOne;
    FragTwo fragTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragOne = new FragOne();
        fragTwo = new FragTwo();
        OneFrag();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if( id== R.id.item1){
            OneFrag();
        }else if( id== R.id.item2){
            TwoFrag();
        }

        return super.onOptionsItemSelected(item);
    }
    public void OneFrag()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragOne);
        fragmentTransaction.commit();
    }
    public void TwoFrag()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragTwo);
        fragmentTransaction.commit();

    }
}





