package com.lj.module_huizhi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

/**
 * resource
 * 度量自己
 * 绘制与度量： 度量必须准确、正确的大小，才能绘制
 * todo 字样、文字的高度（汉字，数字、字母等、字间距度量 （一行文字对其，居中，上对齐、下对齐
 *
 * todo 点击事件优化
 *
 */
public class LConfView7 extends View implements View.OnClickListener {
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

    private boolean isSwitchCheck = true;

    public LConfView7(Context context) {
        this(context, null);
    }

    public LConfView7(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LConfView7(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }
    public LConfView7(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LConfView5);
            if(ta !=  null){
                //一次性遍历到集合，在解析
                for(int i =0; i< ta.getIndexCount(); i++){
                    int attr = ta.getIndex(i);
                    switch (attr){
                        case R.styleable.LConfView5_confPaddingStart:
                            mPaddingStart =(int) ta.getDimension(attr, -1);
                            break;
                        case R.styleable.LConfView5_confPaddingEnd:
                            mPaddingEnd = (int) ta.getDimension(attr, -1);
                            break;
                        default:
                    }
                }
                ta.recycle();
            }
        }
    }

    private int mBackground;
    private int mRadius;
    private int mPaddingStart;
    private int mPaddingEnd;
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

        Paint paint = new Paint();
        paint.setTextSize(sp2px(16));
        paint.setColor(Color.BLACK);

        //time
        Rect rect = new Rect();
        paint.getTextBounds(time, 0,time.length(), rect);
        canvas.drawText(time, 0, rect.height(), paint);

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

    long clickTime = 0l;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                clickTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                onClick(this);
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "toast",Toast.LENGTH_SHORT).show();
        isSwitchCheck = !isSwitchCheck;
        invalidate();
    }
}
