package com.example.heesue.temp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Inflation of MyDraw and Map MainActivity and MyDraw Canvas */
        MyDraw myDraw = new MyDraw(this);
        setContentView(myDraw);

    }

    protected class MyDraw extends View {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Path path = new Path();

        /**
         * Constructor : Set and init Paint for drawing
         * @param c
         */
        public MyDraw (Context c) {
            super(c);

            /* Init Paint for Drawing something */
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
        }

        /**
         * Draw the path set by onTouchEvent
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawColor(Color.WHITE);
            /* Draw the Path set by user's touch */
            canvas.drawPath(path, paint);
        }

        /**
         * Handling event when Touch Event occur
         * @param event
         * @return true
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            /* Get x and y coordinates */
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN : {
                    /* Move to the input x, y coordinate */
                    path.moveTo(x, y);
                    break;
                }
                case MotionEvent.ACTION_MOVE : {
                    /* Save the line into the path */
                    path.lineTo(x, y);
                    break;
                }
                case MotionEvent.ACTION_UP : {
                    break;
                }
            } invalidate(); // Redraw

            return true;
        }
    }


}
