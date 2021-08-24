package com.lj.module_huizhi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LTextView
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/7/22 20:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/22 20:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LTextView extends View {
    private static final String TAG = "LTextView";
    /**
     * 在java代码中new的时候使用
     * @param context
     */
    public LTextView(Context context) {
        super(context);
    }

    /**
     * 在xml布局文件中使用时自动调用
     * @param context
     * @param attrs
     */
    public LTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 不会自动调用，有默认style时，在第二个构造函数中调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.LTextView);
        String lText = typedArray.getString(R.styleable.LTextView_ltext);
        int lTextColor = typedArray.getInteger(R.styleable.LTextView_ltextColor, -1);
        Log.d(TAG, "LTextView: ltext = " + lText +", lTextColor = "+ lTextColor);
        typedArray.recycle();
    }

    /**
     * 不会自动调用，api>21时，有默认style时，在第二个构造函数中调用
     * @param context
     * @param attributeSet
     * @param defStyleAttr
     * @param defStyleRes
     */
    public LTextView(Context context, @NonNull AttributeSet attributeSet, int defStyleAttr, int defStyleRes){
        super(context,attributeSet,defStyleAttr,defStyleRes);
    }

    /**
     * 测量View的宽度、高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 默认实现
         * setMeasuredimension(
         *      getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
         *      getDefaultSize(getSuggestedMinimumWidth(), heightMeasureSpec));
         */
        Log.d(TAG, "onMeasure: width = " + getWidth() + " , height = " + getHeight());
    }

    /**
     * 计算当前View以及子View的位置
     * 坐标系（左上角（0，0））
     * @param l
     * @param t
     * @param r
     * @param b 左、上、右、下四个边界相对于其父View的距离。
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }


    /**
     * 视图的绘制工作
     * @param canvas
     *
     * 系统默认
     *  1. draw the background
     *  2. save canvas layers
     *  3. draw content
     *  4. draw the children
     *  5. draw the fade effect and restore layers
     *  6. draw decorations
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(5f);

        canvas.drawCircle(100,100,10,paint);
        canvas.translate(0, 100);

        RectF rectF = new RectF(0f,0f, 100,100);
        canvas.drawArc(rectF, 0,90, false,paint);
        canvas.translate(0, 100);

        canvas.drawLine(10,10,100,100,paint);
        canvas.translate(0, 100);

        RectF oval = new RectF(0,0, 30,60);
        canvas.drawOval(oval, paint);
        canvas.translate(0, 100);

        canvas.drawRoundRect(0,0,90,90,10,16,paint);
        canvas.translate(0, 100);

        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(100,100);
        path.lineTo(40,70);
        path.lineTo(10,10);
        path.close();
        canvas.drawPath(path,paint);
        canvas.translate(0, 100);

        Path path1 = new Path();
        path1.moveTo(0,0);
        path1.lineTo(35,80);
        path1.lineTo(100,100);
//        path1.close();
//        canvas.drawPath(path1,paint);
        canvas.drawTextOnPath("android2456565151",path1,30,10,paint);

    }
}
