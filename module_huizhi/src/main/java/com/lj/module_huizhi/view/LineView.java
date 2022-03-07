package com.lj.module_huizhi.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LineView
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/10/9 17:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/9 17:00
 * @UpdateRemark: 绘图坐标系、paint，绘制图形 ---> canvas.drawLines
 * @Version: 1.0
 */
public class LineView extends View implements Animatable, ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = "LineView";

    private final static int defaultWidth = 200;
    private final static int defaultHeight = 200;
    private boolean mAnimatorEnable;
    private boolean mShaderEnable;
    private boolean mLinesEnable;

    //代码中使用
    public LineView(Context context) {
        this(context, null);
    }

    //xml 布局中使用时被调用
    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        if( null != attrs){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineView);
            int strokeWidth = typedArray.getInt(R.styleable.LineView_skWidth, -1);
            int skWidthRid = typedArray.getResourceId(R.styleable.LineView_skWidth, Resources.ID_NULL);
            mAnimatorEnable = typedArray.getBoolean(R.styleable.LineView_animatorEnable, false);
            mShaderEnable = typedArray.getBoolean(R.styleable.LineView_shaderEnable, false);
            mLinesEnable = typedArray.getBoolean(R.styleable.LineView_linesEnable, false);
            typedArray.recycle();
        }
    }


    //exactly, at_most, unspecified
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
        }else if(heightMode == MeasureSpec.AT_MOST)
            setMeasuredDimension(widthSize, defaultHeight);

        // todo
        setMeasuredDimension(defaultWidth, defaultHeight);
        Log.v(TAG, "onMeasure---->  width = " + getWidth() + " , height = " + getHeight());
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Log.v(TAG, "ondraw----> width = " + getWidth() + " , height = " + getHeight());
        Paint paint = new Paint();
        //bg
        canvas.drawARGB(255, 0,0,0);
        //line
        paint.setColor(Color.RED);
        paint.setStrokeWidth(16);
        canvas.translate(0, height/2);
        clickRectF = new RectF(0, height/2 - 14, width, height/2 + 14);
        if(mAnimatorEnable){
            canvas.drawLine(0, 0, animatorValue, 0,paint);
        }else if(mShaderEnable){
            int[] colors = new int[]{Color.RED, Color.GREEN,Color.BLACK, Color.BLUE};
            float[] positions = new float[]{0.25f, 0.5f, 0.75f, 1f};
            Shader shader = new LinearGradient(0, 0,width,0, colors,positions, Shader.TileMode.MIRROR);
            paint.setShader(shader);
            canvas.drawLine(0, 0, width, 0,paint);
        } else if (mLinesEnable) {
            // lines(0,0)(80,40)(10,-5)(90,-40)
            paint.setStrokeWidth(2);
            canvas.drawLine(0,0,width,0,paint);
            float[] lines = new float[]{0,0,80,40,10,-5,90,-40};
            canvas.drawLines(lines, paint);
        }else {
            canvas.drawLine(0, 0, width, 0,paint);
        }

    }

    // 点击事件
    private long clickDownTime;
    private Toast toast;
    private RectF clickRectF;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                clickDownTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(System.currentTimeMillis() - clickDownTime < 2 * 1000){
                    //是否在范围内
                    float x = event.getX();
                    float y = event.getY();
                    if(clickRectF != null){
                        if(x < clickRectF.right && x > clickRectF.left &&
                            y < clickRectF.bottom && y > clickRectF.top){
                            if(null == toast){
                                toast = Toast.makeText(getContext(), "haha",Toast.LENGTH_SHORT);
                            };
                            toast.show();
                        }
                    }
                }
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(mAnimatorEnable)
            start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


    private ValueAnimator valueAnimator;
    private int animatorValue = 0;
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        animatorValue = (int) animation.getAnimatedValue();
        invalidate();
    }

    @Override
    public void start() {
        // todo 动效10， 30， 50，70， 100 ,每个用时1000 * 2 , 待优化
        if(null == valueAnimator){
            valueAnimator = ValueAnimator.ofInt(0, 30, 70, 100, 160,200);
            valueAnimator.setRepeatCount(Animation.INFINITE);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.setDuration(1000 * 2);
//            valueAnimator.setInterpolator(new AccelerateInterpolator());
        }
        if(valueAnimator.isStarted())
            return;
        valueAnimator.addUpdateListener(this);
        valueAnimator.start();

    }

    @Override
    public void stop() {
        if(null != valueAnimator){
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.removeAllListeners();
            valueAnimator.setRepeatCount(0);
            valueAnimator.setDuration(0);
            valueAnimator.end();
        }
    }

    @Override
    public boolean isRunning() {
        return valueAnimator.isRunning();
    }
}
