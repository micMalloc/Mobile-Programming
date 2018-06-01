package com.example.heesue.temp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView0;
    ProgressBar bar;
    //ProgressHandler handler;

    Handler handler;
    ProgressRunnable runnable;

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView0 = findViewById(R.id.TextView01);
        bar = findViewById(R.id.progress);

        //handler = new ProgressHandler();
        handler = new Handler();
        runnable = new ProgressRunnable();
    }

    @Override
    protected void onStart() {
        super.onStart();

        bar.setProgress(0);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; ++ i) {
                        Thread.sleep(1000);

                        handler.post(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("MainActivity", "Exception in processing message", e);
                }
            }
        });
        isRunning = true;
        thread1.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    /* Method 1 */
    public class ProgressHandler extends Handler {
        public void handleMessage(Message msg) {
            bar.incrementProgressBy(5);
            if (bar.getProgress() == bar.getMax()) {
                textView0.setText("Done");
            } else {
                textView0.setText("Working .... " + bar.getProgress());
            }
        }
    }

    public class ProgressRunnable implements Runnable {
        public void run () {
            bar.incrementProgressBy(5);

            if (bar.getProgress() == bar.getMax()) {
                textView0.setText("Done");
            } else {
                textView0.setText("Working .... " + bar.getProgress());
            }
        }
    }
}
