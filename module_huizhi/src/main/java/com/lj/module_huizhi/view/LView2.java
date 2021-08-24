package com.lj.module_huizhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LView2
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/8/4 10:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/4 10:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LView2 extends View {
    public LView2(Context context) {
        super(context);
    }

    public LView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    //View-Size
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    //View-Location
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void forceLayout() {
        super.forceLayout();
    }

    

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }
}
