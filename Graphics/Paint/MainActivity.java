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
            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();

            paint.setColor(Color.BLACK);

            paint.setStyle(Paint.Style.FILL); /* 속만 채우기 */
            canvas.drawCircle(50, 50, 40, paint);

            paint.setStyle(Paint.Style.STROKE); /* 테두리만 그리기 */
            canvas.drawCircle(150, 50, 40, paint);

            paint.setStyle(Paint.Style.FILL_AND_STROKE); /* 겉 속 둘다 */
            canvas.drawCircle(250, 50, 40, paint);

            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(50, 150, 40, paint);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(12);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(50, 150, 40, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(12);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(150, 150, 40, paint);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(150, 150, 40, paint);

        }
    }
}
