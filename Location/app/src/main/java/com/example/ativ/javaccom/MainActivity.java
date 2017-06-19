package com.example.ativ.javaccom;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int MY_ACCESS = 1;
    private static final int MY_ACCESS_COARSE_LOCATION = 2;
    private static int ONE_MINUTE = 5626;
    private LocationManager locationManager;
    private String locationProvider = null;
    private Location lastKnownLocation = null;
    private TextView msg;

    private GpsManager gps = null;
    public Button showLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = (TextView)findViewById(R.id.infoWindow);

        showLocation = (Button)findViewById(R.id.locaButton);
        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GpsManager(MainActivity.this);

                if (gps.isGetLocation()) {

                    double longtitude = gps.getLongtitude();
                    double latitude = gps.getLatitude();

                    showLocation.setText(String.valueOf(longtitude) + "\n"
                                        + String.valueOf(latitude));

                } else {
                    gps.showSettingAlert();
                }
            }
        });
    }


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("로케이션 : ", "성공");
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lastKnownLocation = location;
            msg.setText("위도 : " + location.getLatitude() + "\n경도 : " + location.getLongitude());
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
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_ACCESS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "하하하하성공", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "실패", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}