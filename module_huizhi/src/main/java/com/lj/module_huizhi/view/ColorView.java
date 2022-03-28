package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: ColorView
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2022/3/23 16:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/23 16:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ColorView extends View {
    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private Paint paint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0, 100,100,getPaint());

        paint.setColor(Color.parseColor("#00FFFF"));
        canvas.drawRect(0,0, 50,50,paint);
    }

    private Paint getPaint(){
        if(paint == null){
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#ff0000"));
        }

        return paint;
    }
}
