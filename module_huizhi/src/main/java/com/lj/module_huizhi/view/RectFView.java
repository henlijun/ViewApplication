package com.lj.module_huizhi.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.ui
 * @ClassName: RectFView
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/10/12 15:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/12 15:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RectFView extends View {

    private static final String TAG = "RectFView";

    private final static int defaultWidth = 300;
    private final static int defaultHeight = 300;


    public RectFView(Context context) {
        this(context, null);
    }

    public RectFView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectFView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public RectFView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RectFView);

            mStrokeWidth = ta.getInt(R.styleable.RectFView_skFWidth, 1);
            mStrokeColor = ta.getColor(R.styleable.RectFView_strokeColor, -1);
            int strokeColorRis = ta.getResourceId(R.styleable.RectFView_strokeColor, Resources.ID_NULL);

            mPaintCap = ta.getInt(R.styleable.RectFView_paintCap, 1);
            mPaintStyle = ta.getInt(R.styleable.RectFView_paintStyle, 1);
            mPaintColor = ta.getColor(R.styleable.RectFView_paintColor, -1);

            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth, defaultHeight);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth, heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize, defaultHeight);
        }
        // todo 是ScrollView有效， Paint.Join.MITER\ROUND\BEVEL 锐角，圆弧，直线
        setMeasuredDimension(defaultWidth, defaultHeight);
        Log.v(TAG, "onMeasure: width = " + getWidth() +
                " , height = " + getHeight() +
                " , widthMeasureSpec = " + widthMeasureSpec +
                " , heightMeasureSpec = " + heightMeasureSpec);
    }

    private int mStrokeWidth = 1;
    private int mStrokeColor;
    private int mStrokeColorRid;
    private int mPaintColor;
    private int mPaintCap;
    private int mPaintStyle;

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        Log.v(TAG, "onDraw : width = " + width + " , height = " + height);
        //bg
        canvas.drawARGB(255, 0,0,0);
        //rectF
        Paint paint = new Paint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setStyle(getPaintStyle());
        paint.setStrokeCap(getPaintCap());

        canvas.translate(40,40);
        Rect rect = new Rect(0,0,200,200);
        if(mPaintStyle == 2){//stroke
            paint.setColor(mStrokeColor);
            canvas.drawRect(rect, paint);
        }else {//fill
            paint.setColor(mPaintColor);
            canvas.drawRect(rect, paint);
            if(mPaintStyle == 3){
                paint.setStrokeWidth(6);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(mStrokeColor);
                canvas.drawRect(rect, paint);
            }
        }

    }


    private Paint.Style getPaintStyle(){
        if(mPaintStyle == 3)
            return Paint.Style.FILL_AND_STROKE;
        else if(mPaintStyle == 2)
            return Paint.Style.STROKE;
        else
            return Paint.Style.FILL;
    }

    private Paint.Cap getPaintCap(){
        if(mPaintCap == 3)
            return Paint.Cap.SQUARE;
        else if(mPaintCap == 2)
            return Paint.Cap.ROUND;
        else
            return Paint.Cap.BUTT;
    }
}
