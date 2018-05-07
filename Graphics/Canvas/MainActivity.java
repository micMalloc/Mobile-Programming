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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyView myView = new MyView(getApplicationContext());
        setContentView(myView);
    }

    protected class MyView extends View {
        public MyView (Context c) {
            super(c);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();

            paint.setColor(Color.BLUE);
            canvas.drawColor(Color.RED);
            canvas.drawCircle(200, 200, 50, paint);
        }
    }
}
