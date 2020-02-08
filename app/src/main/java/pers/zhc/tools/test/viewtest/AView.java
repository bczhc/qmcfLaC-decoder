package pers.zhc.tools.test.viewtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import pers.zhc.tools.floatingdrawing.MyCanvas;
import pers.zhc.tools.utils.GestureResolver;

@SuppressLint("ViewConstructor")
public class AView extends View {
    private Bitmap bitmap;
    private MyCanvas mCanvas;
    private Bitmap mBitmap;
    private int width = 0, height = 0;
    private Paint mBitmapPaint;
    private GestureResolver gestureResolver;
    private float scale = 1;

    AView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }

    private void init() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        Paint mPaint2 = new Paint();
        mPaint2.setColor(Color.GREEN);
        mPaint2.setStrokeWidth(5);
        this.mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.mCanvas = new MyCanvas(mBitmap);
        mBitmapPaint = new Paint();
        gestureResolver = new GestureResolver(new GestureResolver.GestureInterface() {
            @Override
            public void onTwoPointsScroll(float distanceX, float distanceY, MotionEvent motionEvent) {
                mCanvas.invertTranslate(distanceX, distanceY);
            }

            @Override
            public void onTwoPointsZoom(float firstMidPointX, float firstMidPointY, float midPointX, float midPointY, float firstDistance, float distance, float scale, float dScale, MotionEvent event) {
                mCanvas.invertScale(dScale, midPointX, midPointY);
            }

            @Override
            public void onTwoPointsUp() {

            }

            @Override
            public void onTwoPointPress() {

            }

            @Override
            public void onTwoPointsDown() {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width == 0 && height == 0) {
            width = getWidth();
            height = getHeight();
            init();
            System.out.println("init");
        }
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureResolver.onTouch(event);
        mCanvas.drawBitmap(this.bitmap, 0, 0, mBitmapPaint);
        invalidate();
        return true;
    }
}