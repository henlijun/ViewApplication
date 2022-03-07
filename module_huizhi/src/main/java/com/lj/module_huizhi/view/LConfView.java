package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class LConfView extends View {
    private static final String TAG = "LConfView";
    private String time;
    private boolean isLock;
    private String name;
    private String otherMsg;



    public LConfView(Context context) {
        this(context, null);
    }

    public LConfView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LConfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LConfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = getMeasureSize(widthMeasureSpec);
        int measureHeight = getMeasureSize(heightMeasureSpec);

        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int getMeasureSize(int measureSpec){
        int pSize = MeasureSpec.getSize(measureSpec);
        int pMode = MeasureSpec.getMode(measureSpec);
        int size = 0;
        if(pMode == MeasureSpec.EXACTLY){
            size = pSize;
        }else {
            //view 自我度量， 宽、高 度量 （换行，等，有关联性）， imageView, 文字， 保存度量相关参数在draw中使用
            size = 100;

        }
        return size;
    }

    /**
     * View = wrap_content ----> MeasureSpec_AT_MOST
     * View = match_parent ----> MeasureSpec_Exactly
     * View = 100dp -----> Measure_Exactly
     * @param measureSpec
     * @return
     */
    //measureSpec 参考 parentView
   /* private int getMeasureSize(int measureSpec){
        int pSize = MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "getMeasureSize: " + pSize);
        int pMode = MeasureSpec.getMode(measureSpec);
        int size = 0;

        switch (pMode){
            case MeasureSpec.UNSPECIFIED:
                //系统级别的，ScrollerView, ListView
                *//**
                 * ScrollView
                 *    <ScrollView
                 *         android:layout_width="match_parent"
                 *         android:layout_height="wrap_content">
                 *
                 *         <com.lj.module_huizhi.view.LConfView
                 *             android:layout_width="match_parent"
                 *             android:layout_height="match_parent"/>
                 *     </ScrollView>
                 *
                 *     measureSpec 指parentView 可见长度， view的长度超出后，依然能够滑动，长度确实有效
                 *//*
                Log.d(TAG, "getMeasureMode: UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                //自适应, wrap_content 自定度量，（绘制范围，点击范围...)
                Log.d(TAG, "getMeasureMode: AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                //match_parent 100dp
                Log.d(TAG, "getMeasureMode: EXACTLY");
                size = pSize;
                break;
            default:
        }

        return size;
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);
    }




}
