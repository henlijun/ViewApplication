package com.lj.module_huizhi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LTextView2
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/10/9 17:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/9 17:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("AppCompatCustomView")
public class LTextView2 extends TextView {
    private static final String TAG = "LTextView2";
    public LTextView2(Context context) {
        super(context);
    }

    public LTextView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        Log.v(TAG, "XML 2");

    }

    public LTextView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        Log.v(TAG, "XML 3");

    }

    public LTextView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.v(TAG, "XML 4");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.d(TAG, "height = " + height);
        Paint paint = new Paint();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        float fonthalf =  (fontMetricsInt.bottom- fontMetricsInt.top);
        float fontBaseLineHeight2 =  (fontMetricsInt.bottom- fontMetricsInt.top)/2;
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        Rect rect = new Rect(0, 0,width, 160);
        canvas.drawRect(rect, paint);
        paint.setColor(Color.RED);
        canvas.drawLine(0, 80, width, 80,paint);
        paint.setTextSize(sp2px(16));
        paint.setColor(Color.BLACK);
        String text = "1aFg含_";

        Rect rect1 = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect1);
        Log.d(TAG, "text  height = " + rect1.height());
        float h1 = rect1.height()/2  - fonthalf;
        canvas.drawText(text,0,text.length(), 0, 160,paint);
        canvas.drawText(text,0,text.length(), 0, 80,paint);

        String text2 = "1aFg含_";
        canvas.drawText(text2,0,text2.length(), 200, 160/2 + h1,paint);
        String text3 = "1EFg含_";
        canvas.drawText(text3,0,text3.length(), 400, 80 - fontMetricsInt.top,paint);
        canvas.drawText(text3,0,text3.length(), 600, 80 + fonthalf,paint);
    }

    private int sp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }
}
