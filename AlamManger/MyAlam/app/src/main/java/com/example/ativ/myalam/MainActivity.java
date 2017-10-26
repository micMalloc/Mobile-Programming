package com.example.ativ.myalam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MyAlam";
    public final String ACTION = "com.example.ativ.myalam.alamaction";
    public Button onActiveBtn = null;
    public Button unActiveBtn = null;
    public TimePicker timePicker = null;
    private int hour = -1, minute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Connect View Components via findViewId method */
        onActiveBtn = (Button)findViewById(R.id.onActive);
        unActiveBtn = (Button)findViewById(R.id.unActive);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        /* Add Action Listener for OnActiveButton */
        onActiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onActiveButton Click");

                if (Build.VERSION.SDK_INT >= 23) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                } Log.d(TAG, "Input Time : " + hour + ":" + minute);

                onActiveAlam(MainActivity.this, hour, minute);
            }
        });
    }
    /* Create a PendingIntent and Send it to AlamManager */
    public void onActiveAlam (Context context, int crtHour, int crtMinute) {
        Log.d(TAG, "onActiveAlam Method");

        AlarmManager alarmManager = null;
        Intent intent = null;
        PendingIntent pendingIntent = null;
        Calendar calendar = null;

        // Create a intent for PendingIntent
        intent = new Intent(ACTION);
        // Create a pendingIntent for AlamManger
        pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // Create a Calendar Instant and Set the time to do something
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, crtHour);
        calendar.set(Calendar.MINUTE, crtMinute);
        calendar.set(Calendar.SECOND, 0);
        // Create a AlamManger Instant and Send the pendingIntent and calendar
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            Log.d(TAG, "SDK >= 23");
            alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        } else {
            Log.d(TAG, "SDK < 23");
            alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        }
    }
}
