package com.lj.module_jicheng.util_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_jicheng.util_view
 * @ClassName: BaselineItemDecoration
 * @Description: java类作用描述
 * RecycleView 分割线
 * @Author: 李军
 * @CreateDate: 2022/3/23 11:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/23 11:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaselineItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "BaselineItemDecoration";
    private Paint paint;

    private int mLeft, mTop, mRight, mBottom;

    private boolean isLineTop;
    private boolean isLineBottom;
    private int testLine;

    public BaselineItemDecoration(Context context){
        this(context, 0, 1, 0, 1);
    }

    public BaselineItemDecoration(Context context, float left, float top, float right, float bottom){
        mLeft = left > 0 ? dp2px(context,left) : 0;
        mTop = top > 0 ? dp2px(context,top) : 0;
        mRight = right > 0 ? dp2px(context,right) : 0;
        mBottom = bottom > 0 ? dp2px(context,bottom) : 0;
        testLine = dp2px(context, 2);
        isLineTop = false;
        isLineBottom = false;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if( position == 0 || position == state.getItemCount() -1){
            if(position == 0){
                if(isLineTop)
                    outRect.set(mLeft,mTop,mRight,mBottom);
                else
                    outRect.set(mLeft,0,mRight,mBottom);
            }else {
                if(isLineBottom)
                    outRect.set(mLeft,mTop,mRight,mBottom);
                else
                    outRect.set(mLeft,mTop,mRight,0);
            }
        } else
            outRect.set(mLeft, mTop, mRight, mBottom);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for(int i= 0; i< count; i++){
            View view = parent.getChildAt(i);
            Rect bound = new Rect();
            parent.getLayoutManager().getDecoratedBoundsWithMargins(view, bound);
            if(i == 0 || i == count -1){
                if(i == 0){
                    if(isLineTop && mTop > 0){
                        c.drawRect(bound.left, bound.top, bound.right, bound.top + mTop, getPaint());
                    }
                    if(mBottom > 0){
                        c.drawRect(bound.left, bound.bottom - mBottom, bound.right, bound.bottom,getPaint());
                    }
                }else {
                    if(mTop > 0){
                        c.drawRect(bound.left, bound.top, bound.right, bound.top + mTop, getPaint());
                    }
                    if(isLineBottom && mBottom > 0){
                        c.drawRect(bound.left, bound.bottom - mBottom, bound.right, bound.bottom,getPaint());
                    }
                }

            }else {
                if(mTop > 0)
                    c.drawRect(bound.left, bound.top, bound.right, bound.top + mTop, getPaint());
                if(mBottom > 0)
                    c.drawRect(bound.left, bound.bottom - mBottom, bound.right, bound.bottom,getPaint());
            }
        }
    }

    private Paint  getPaint(){
        if(paint == null){
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
        }
        return paint;
    }

    public void setLineBottom(boolean lineBottom) {
        isLineBottom = lineBottom;
    }

    public void setLineTop(boolean lineTop) {
        isLineTop = lineTop;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private int dp2px(Context context, float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    private int sp2px(Context context, float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics());
    }
}
