package com.example.ativ.myalam;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ShakeActivity extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    //private long mShakeTime;
    //private static final int SHAKE_SKIP_TIME = 0;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    
    public int shakeCnt = 10;
    private int count = 30;
    private CountDownTimer cdt = null;
    public TextView cdtViewer = null;
    public TextView cntViewer = null;

    private static final int MILLISINFUTURE = 31 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        cntViewer = (TextView)findViewById(R.id.count);
        cdtViewer = (TextView)findViewById(R.id.countDown);
        cntViewer.setText(String.valueOf(shakeCnt));
        cdtViewer.setText(String.valueOf(count));
        countDownTimer();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void countDownTimer () {
        Log.d("MyAlamCdt", "countDownTimer_Start");
        cdt = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long l) {
                Log.d("MyAlamCdt", " : " + String.valueOf(count));
                count --;
                cdtViewer.setText(String.valueOf(count));
            }

            @Override
            public void onFinish() {
                if (shakeCnt > 0) {
                    shakeCnt += 100;
                    cntViewer.setText(String.valueOf(shakeCnt));
                    timerReset();
                }
            }
        }.start();
    }

    private void timerReset () {
        cdt.cancel();
        count = 30;
        cdt.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            cdt.cancel();
        } catch (Exception e) {
            cdt = null;
        }
    }

    @Override
    protected void onResume() {
        /* onRegist */
        super.onResume();
        mSensorManager.registerListener(
                this,
                mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        /* unRegist */
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float axisX = sensorEvent.values[0];
            float axisY = sensorEvent.values[1];
            float axisZ = sensorEvent.values[2];

            float gravityX = axisX / SensorManager.GRAVITY_EARTH;
            float gravityY = axisY / SensorManager.GRAVITY_EARTH;
            float gravityZ = axisZ / SensorManager.GRAVITY_EARTH;

            Float f = (float)(Math.pow(gravityX, 2.0) + Math.pow(gravityY, 2.0) + Math.pow(gravityZ, 2.0));
            double squaredD = Math.sqrt(f.doubleValue());
            float gForce = (float) squaredD;

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                Log.d("Shake", "Shake Event Occur");
                shakeCnt --;
                if (shakeCnt <= 0) {
                    cntViewer.setText(String.valueOf(shakeCnt));
                    onPause();
                    //Todo 이 시점에서 알람음악 종료시키기
                    finish ();
                    Intent msgForService = new Intent(ShakeActivity.this, MyAlamService.class);

                    msgForService.putExtra("cancle", true);
                    startService(msgForService);
                }
                cntViewer.setText(String.valueOf(shakeCnt));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
