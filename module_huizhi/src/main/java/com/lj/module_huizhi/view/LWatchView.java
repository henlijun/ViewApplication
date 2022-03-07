package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LWatchView
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/8/24 15:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/24 15:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LWatchView extends View {


    public LWatchView(Context context) {
        super(context);
    }

    public LWatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LWatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LWatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //bg
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        RectF rectF = new RectF(0f,0f, 500f,500f);
        canvas.drawRect(rectF,paint);
        //watch
        //交叉线
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0f, 0f, 500f,500f,paint);
        canvas.drawLine(500f, 0f, 0,500f,paint);
        canvas.translate(250f, 250f);

        int startAngle = -135;
        int endAngle = 270;
        //一环 r=50
        float r1 = 50;
        //椭圆 + 圆可以实现圆心效果(覆盖绘制）
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        RectF arcOne1 = new RectF(-r1-10, -r1, r1+10, r1);
        canvas.drawOval(arcOne1, paint);
        paint.setColor(Color.BLACK);
        RectF arcOne = new RectF(-r1, -r1, r1, r1);
        canvas.drawArc(arcOne, startAngle, 360, false,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);

        //二环 r=200
        float r2 = 100;
        RectF arcTwo = new RectF(-r2, -r2, r2, r2);
        canvas.drawArc(arcTwo, startAngle, endAngle, false,paint);
        //三环 r=400
        float r3 = 150f;
        RectF arcThree = new RectF(-r3, -r3, r3, r3);
        canvas.drawArc(arcThree, startAngle, endAngle, false,paint);
        //四环 r=450
        float r4 = 170f;
        RectF arcFour = new RectF(-r4, -r4, r4, r4);
        canvas.drawArc(arcFour, 0, 360, false,paint);

        //刻度
        //圆弧长度/格数=间隔距离


        //指针
        Path zhizhen = new Path();
        zhizhen.moveTo(-80, 0);
        zhizhen.lineTo(-55,10);
        zhizhen.lineTo(140, 0);
        zhizhen.lineTo(-55, -10);
        zhizhen.moveTo(-80,0);
        zhizhen.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.BLUE);
        canvas.drawPath(zhizhen, paint);




    }
}
