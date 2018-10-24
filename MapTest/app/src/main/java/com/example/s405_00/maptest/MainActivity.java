package com.example.s405_00.maptest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    MyLocationListener myLocationListener=new MyLocationListener();
    double nLng= 37.3800180;
    double nLg = 126.9286640;
    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng su = new LatLng(nLng, nLg );
        MarkerOptions m = new MarkerOptions();
        m.position(su);
        googleMap.addMarker(m);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(su, 16));
    }

    public void onClick(View v) {
        Toast.makeText(this,
                "call",
                Toast.LENGTH_SHORT).show();

        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);

            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0,
                0,
                myLocationListener);
        //gps port 개방.. gps 위성으로 데이터 수신

    }

    class MyLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {

            double lat = location.getLatitude();
            double lng = location.getLongitude();
            nLng = lat;
            nLg = lng;
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    LatLng su = new LatLng(nLng, nLg );
                    MarkerOptions m = new MarkerOptions();
                    m.position(su);
                    googleMap.addMarker(m);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(su, 16));
                }
            });

            Log.i("gps", "위도:" + lat + " 경도:" + lng);
            Toast.makeText(getBaseContext(),
                    "위도:" + lat + " 경도:" + lng,
                    Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}


