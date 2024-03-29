package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * 彩色 字体 SpannableString todo 对应的Span Size度量
 */
public class LConfView3 extends View {
    private static final String TAG = "LConfView3";
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


    public LConfView3(Context context) {
        this(context, null);
    }

    public LConfView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LConfView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }
    public LConfView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        //time
        Rect rect = new Rect();
        paint.getTextBounds(time, 0,time.length(), rect);
        SpannableString span = new SpannableString("好好学习，天天向上");
        ForegroundColorSpan foreColor = new ForegroundColorSpan(Color.RED);
        span.setSpan(foreColor, 0,2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan foreColor2 = new ForegroundColorSpan(Color.GREEN);
        span.setSpan(foreColor2, 5,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Log.d(TAG, "onDraw: @@@@@@@@@@@@@AAAAAAAAAAAAAAAA" + span.length());
        TextPaint paint1 = new TextPaint(paint);
        DynamicLayout dynamicLayout = new DynamicLayout(span,paint1, width,
                Layout.Alignment.ALIGN_NORMAL, 0, 0, false);
        dynamicLayout.draw(canvas);
//        canvas.drawText(time, 0, rect.height(), paint);


        //detail
        Rect rect1 = new Rect();
        paint.getTextBounds(otherMsg, 0, otherMsg.length(), rect1);
        canvas.drawText(otherMsg, width - rect1.width(), height - rect1.bottom, paint);

    }


    private int dp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    private int sp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }


}
