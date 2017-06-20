package com.example.ativ.javaccom;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by ATIV on 2017-06-19.
 */
public class GpsManager extends Service implements LocationListener {

    private final Context mContext;

    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean isGetLocattion = false;

    private Location location;
    private double latitude;
    private double longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    protected LocationManager locationManager;

    public GpsManager (Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation () {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
            //GPS 정보 가져오기
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            //NETWORK 상태 값 가져오기
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled) {
                Log.d("진입", "실패");
            } else {
                Log.d("진입", "성공");
                this.isGetLocattion = true;

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATE, this
                    );

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATE, this
                    );

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return location;
    }

    public void stopUsingGPS () {
        try {
            if (locationManager != null) {
                locationManager.removeUpdates(GpsManager.this);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public double getLatitude () {
        if (location != null) {
            latitude = location.getLatitude();
        } return latitude;
    }

    public double getLongtitude () {
        if (location != null) {
            longitude = location.getLongitude();
        } return longitude;
    }

    public boolean isGetLocation () {
        return this.isGetLocattion;
    }

    public void showSettingAlert () {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("GPS 사용유무 설정");
        alertDialog.setMessage("GPS 설정이 되지 않았을 수도 있습니다. \n" +
                "설정창으로 가시겠습니까?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                   public void onClick (DialogInterface dialog, int which) {
                       Intent i = new Intent (Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                       mContext.startActivity(i);
                   }
                });
        alertDialog.setNegativeButton("Cancle",
                new DialogInterface.OnClickListener() {
                   public void onClick (DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
                });

        alertDialog.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("로케이션 : ", "성공");
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