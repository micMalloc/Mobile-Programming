package com.example.ativ.myalam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public final String TAG = "MyAlam";
    Intent mAlamServiceIntent = null;

    public MyReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Receiver OnReceive");

        mAlamServiceIntent = new Intent(context, MyAlamService.class);
        context.startService(mAlamServiceIntent);
    }
}
