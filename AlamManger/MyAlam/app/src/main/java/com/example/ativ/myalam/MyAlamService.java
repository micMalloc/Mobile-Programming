package com.example.ativ.myalam;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

public class MyAlamService extends Service {
    public final String TAG = "MyAlam";
    public MediaPlayer mMediaPlayer = null;
    Ringtone ringtone = null;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        Log.d(TAG, "Service OnCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isCancle = false;
        Log.d(TAG, "Service OnStartCommand");
        /* ContentProvider : 핸드폰 리소스 접근 */
        //uri : 벨소리 주소
        //mMediaPlayer = MediaPlayer.create(this, R.raw.test);
        //mMediaPlayer.start();
        Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                getApplicationContext(),
                RingtoneManager.TYPE_RINGTONE
        );
        if (ringtone == null)
            ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
        if (intent != null) {
            Log.d(TAG, "Intent Not NULL");
            if (!intent.getBooleanExtra("cancle", false)) {
                Log.d(TAG, "Cancle False");
                if (ringtone != null) {
                    ringtone.setStreamType(AudioManager.STREAM_ALARM);
                    ringtone.play();
                    Intent shakeActivity = new Intent(this, ShakeActivity.class);
                    startActivity(shakeActivity);
                    //Intent alamIntent = new Intent(this, AlamActivity.class);
                    //startActivity(alamIntent);
                }
            } else {
                Log.d(TAG, "Cancle True");
                ringtone.stop();
            }
        }
        return START_STICKY; //: 좀비처럼 되살아난다.
        //return START_NOT_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
