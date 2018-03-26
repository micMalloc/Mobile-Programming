package com.example.ativ.lecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public ImageView img0 = null;
    public ImageView img1 = null;
    public int imgIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main layout inflation
        setContentView(R.layout.activity_main);

        img0 = (ImageView) findViewById(R.id.android);
        img1 = (ImageView) findViewById(R.id.apple);
    }

    /**
     * When image change Button is clicked, Call 'changeImage' method
     */
    public void onButtonClicked (View v) {
        changeImage();
    }

    /**
     * Use 'imgIdx' var to determine what the image is seen on Screen
     * If imgIdx == 0, Android img will be seen
     * If imgIdx == 1, Apple img will be seen
     */
    public void changeImage () {
        if (imgIdx == 0) {
            img0.setVisibility(View.INVISIBLE);
            img1.setVisibility(View.VISIBLE);
            imgIdx = 1;
        } else {
            img0.setVisibility(View.VISIBLE);
            img1.setVisibility(View.INVISIBLE);
            imgIdx = 0;
        }
    }
}
