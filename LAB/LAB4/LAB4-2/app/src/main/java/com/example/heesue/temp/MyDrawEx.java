package com.example.heesue.temp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

public class MyDrawEx extends View {
    private Paint mPaint;
    private Bitmap mAndroidGreen;
    private Bitmap mAndroidRed;
    private int nAngle = 0;

    public void init () {
        mPaint = new Paint();

        Resources res = getResources();
        mAndroidGreen = BitmapFactory.decodeResource(res, R.drawable.android_icon);
        mAndroidRed = BitmapFactory.decodeResource(res, R.drawable.android_red);
    }

    public MyDrawEx (Context c) {
        super(c);
        init();
    }

    public MyDrawEx (Context c, AttributeSet a) {
        super(c, a);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap sm = BitmapFactory.decodeResource(getResources(), R.drawable.tmp);

        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);

        Bitmap bitmap2 = Bitmap.createBitmap(sm, 0, 0, sm.getWidth() - 100, sm.getHeight() - 100);
        canvas.drawBitmap(bitmap2, 0, 0, paint);
    }
}
