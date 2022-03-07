package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

public class LConfView2 extends View {
    private static final String TAG = "LConfView";
    private String time = "2022年2月12日";
    private boolean isLock = false;
    private String name = "天下乌贼";
    private String otherMsg = "详细";

    private String mYear = "2022";
    private String mMonth = "3";
    private String mDay = "12";

    private static final String YEAR = "年";
    private static final String DAY = "日";
    private static final String MONTH = "月";


    public LConfView2(Context context) {
        this(context, null);
    }

    public LConfView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LConfView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }
    public LConfView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = getMeasureSize(widthMeasureSpec);
        int measureHeight = getMeasureSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int getMeasureSize(int measureSpec){
        int pSize = MeasureSpec.getSize(measureSpec);
        int pMode = MeasureSpec.getMode(measureSpec);
        Log.d(TAG, "getMeasureSize: " + pSize + " , Mode = " + pMode);
        int size = 0;
        if(pMode == MeasureSpec.EXACTLY){
            size = pSize;
        }else {
            //view 自我度量， 宽、高 度量 （换行，等，有关联性）， imageView, 文字， 保存度量相关参数在draw中使用
            size = 100;

        }
        return size;
    }






    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.d(TAG, "onDraw, width = " + width + " , height = " + height);

        Paint paint = new Paint();
        paint.setTextSize(sp2px(16));
        paint.setColor(Color.BLACK);

        Rect rect = new Rect();
        paint.getTextBounds(time, 0,time.length(), rect);
        canvas.drawText(time, 0, rect.height(), paint);
        Log.d(TAG, "onDraw:!!!!!!!!!!!! rectHeight" + rect.height() + " , width = " + rect.width());

        Rect rect1 = new Rect();
        paint.getTextBounds(otherMsg, 0, otherMsg.length(), rect1);
        canvas.drawText(otherMsg, width - rect1.width(), height - rect1.bottom, paint);

        Log.d(TAG, "onDraw: ");
    }


    private int dp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    private int sp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }


}
