package com.lj.module_huizhi.viewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LViewGroup2
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/8/4 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/4 11:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LViewGroup2 extends ViewGroup {
    public LViewGroup2(Context context) {
        super(context);
    }

    //ViewGroup 测量 View，再ViewGroup
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public void forceLayout() {
        super.forceLayout();
    }


}
