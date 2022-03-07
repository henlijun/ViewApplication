package com.lj.module_huizhi.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考教程 ，抄袭
 * 流式布局，满行换行的效果
 */
public class LFlowLayout extends ViewGroup {
    public LFlowLayout(Context context) {
        super(context);
    }
    //layout.xml 反射
    public LFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //主题切换
    public LFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public LFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * @param heightMeasureSpec parent 传入高度
     * @param widthMeasureSpec parent 传入宽度
     *
     * size - parent,self,child
     * LayoutParams ~ MeasureSpec 的转换关系
     * LayoutParams: ViewGroup的内部类
     * MeasureSpec: View的内部类，mode (int) 32位，首2位 mode, 其他30位 表宽高。 UNSPECIFIED ,AT_MOST,EXACTLY
     *
     */
    //水平，垂直间隔
    private int mHorizontalSpacing = dp2px(16);
    private int mVerticalSpacing = dp2px(8);

    private List<List<View>> allLines;
    private List<Integer> mLineHeights = new ArrayList<>();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initMeasureParams();
        int lineWidthUsed = 0;
        int lineHeight = 0;
        List<View> childLineViews = new ArrayList<>();

        //参考 盒子大小,ViewGroup 解析的宽高
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        // measure 中,ViewGroup 子View要求的父View的宽高
        int parentNeededWidth = 0;
        int parentNeededHeight = 0;
        //度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        for(int i=0; i<childCount; i++){
            View cView = getChildAt(i);
            //获取childView的LayoutParams布局参数，转化为MeasureSpec传递给ChildView 的onMeasure 函数实际度量
            LayoutParams cParams = cView.getLayoutParams();
            int cMeasureSpecWidth = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,cParams.width);
            int cMeasureSpecHeight = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, cParams.height);
            cView.measure(cMeasureSpecWidth, cMeasureSpecHeight);

            int cMeasuredWidth = cView.getMeasuredWidth();
            int cMeasureHeight = cView.getMeasuredHeight();

            //换行
            if(lineWidthUsed + cMeasuredWidth + mHorizontalSpacing > selfWidth){
                mLineHeights.add(lineHeight);
                allLines.add(childLineViews);

                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;

                childLineViews = new ArrayList<>();
                lineWidthUsed = 0;
            }
            childLineViews.add(cView);
            lineWidthUsed = lineWidthUsed + cMeasuredWidth + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight, cMeasureHeight);
        }


        //度量自己
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int realWidth = (widthMode == MeasureSpec.EXACTLY)? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY)? selfHeight : parentNeededHeight;

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = allLines.size();
        int curLeft = getPaddingLeft();
        int curTop = getPaddingTop();
        for(int i =0; i< count; i++){
            List<View> lineViews = allLines.get(i);
            int lineHeight = mLineHeights.get(i);
            for(int j =0; j < lineViews.size(); j++){
                View cView = lineViews.get(j);
                int l = curLeft;
                int t = curTop;
                int r = l + cView.getMeasuredWidth();
                int bot = t + cView.getMeasuredHeight();
                cView.layout(l,t,r,bot);
                curLeft = r + mHorizontalSpacing;
            }

            curLeft = getPaddingLeft();
            curTop = curTop + lineHeight + mVerticalSpacing;
        }
    }


    private void initMeasureParams(){
        if(allLines == null)
            allLines = new ArrayList<>();
        else
            allLines.clear();
        if(mLineHeights == null)
            mLineHeights = new ArrayList<>();
        else
            mLineHeights.clear();
    }

    private int dp2px(int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }




}
