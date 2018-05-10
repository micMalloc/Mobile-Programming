package com.example.heesue.temp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.TransitionDrawable;
import android.os.TransactionTooLargeException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView01);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("쓰레드에서 받은 값: " + value);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        running = true;
        Thread thread = new BackgroundThread();
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        running = false;
        value = 0;
    }

    class  BackgroundThread extends Thread {

        public void run () {

            while (running) {
                try {
                    Thread.sleep(1000);
                    value ++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
