package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
//        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        RectF rectF = new RectF(0,0, 600,600);
        canvas.drawRect(rectF,paint);

    }
}
