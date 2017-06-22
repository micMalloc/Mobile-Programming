package com.example.ativ.javaccom;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView msg = null;
    public Button showLocation = null;
    private GpsManager gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = (TextView) findViewById(R.id.infoWindow);
        showLocation = (Button) findViewById(R.id.locaButton);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부", Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(MainActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();

        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gps = new GpsManager(MainActivity.this);
                Log.d("GPS", "생성 직후");
                if (gps.isGetLocation()) {
                    Log.d("GPS", "생성 성공");
                    double longtitude = gps.getLongtitude();
                    double latitude = gps.getLatitude();

                    msg.setText(String.valueOf(longtitude) + "\n"
                            + String.valueOf(latitude));

                } else {
                    gps.showSettingAlert();
                }
            }
        });

    }
}