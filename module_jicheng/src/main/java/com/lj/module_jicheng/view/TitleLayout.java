package com.lj.module_jicheng.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lj.module_jicheng.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_jicheng.view
 * @ClassName: TitleLayout
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2022/3/30 17:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/30 17:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TitleLayout extends FrameLayout {

    private TextView mTitleView;
    private TextView mSubTitleView;
    private ImageView mSubTitleImage;

    private TitleViewListener mListener;
    private boolean isBlueStyle;

    private String mTitle, mSubTitle;
    private int mTitleColor, mSubTitleColor;
    private Drawable mBackground, mBackDrawable, mSubTitleDrawable;



    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        inflate(context, R.layout.title_layout, this);
        FrameLayout layout = findViewById(R.id.rootFrameLayout);
        layout.setBackground(mBackground);

        ImageView backImage = findViewById(R.id.baseBackImageView);
        if (backImage != null){
            backImage.setImageDrawable(mBackDrawable);
            backImage.setOnClickListener(v -> {
                if (mListener != null) mListener.titleBackClick();
            });
        }

        mTitleView = findViewById(R.id.textTitleView);
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleColor);

        mSubTitleView = findViewById(R.id.textSubTitleView);
        if (mSubTitleView != null) {
            setSubTitle(mSubTitle);
            mSubTitleView.setTextColor(mSubTitleColor);
            mSubTitleView.setOnClickListener(v -> {
                if (mListener != null)
                    mListener.subTitleClick(v);
            });
        }

        mSubTitleImage = findViewById(R.id.imageSubTitleView);
        if (mSubTitleImage != null) {
            setSubTitle(mSubTitleDrawable);
            mSubTitleImage.setOnClickListener(v -> {
                if (mListener != null)
                    mListener.subTitleClick(v);
            });
        }
        /*RelativeLayout linearLayout = findViewById(R.id.headView);
        int barHeight = ScreenSetting.getStatusBarHeight(context);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        if (params != null)
            params.setMargins(0, barHeight, 0, 0);*/
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        if (attributes != null) {
            boolean isBlueStyle = attributes.getBoolean(R.styleable.TitleLayout_titleStyleBlue, false);
            //BackGround
            Drawable background = attributes.getDrawable(R.styleable.TitleLayout_titleBackground);
            //Left
            mBackDrawable = attributes.getDrawable(R.styleable.TitleLayout_titleBack);
            //Title
            mTitle = attributes.getString(R.styleable.TitleLayout_titleText);
            int titleColor = attributes.getColor(R.styleable.TitleLayout_titleColor,
                    0);
            //Right
            mSubTitle = attributes.getString(R.styleable.TitleLayout_titleSubText);
            int subTextColor = attributes.getColor(R.styleable.TitleLayout_titleSubColor, 0);
            //右边第一个图标
            mSubTitleDrawable = attributes.getDrawable(R.styleable.TitleLayout_titleSubImage);
            if(isBlueStyle){
                if(background == null)
                    mBackground = getResources().getDrawable(R.drawable.dot_red);
                if(mBackDrawable == null)
                    mBackDrawable = getResources().getDrawable(R.drawable.black);
                if(titleColor == 0)
                    mTitleColor = Color.WHITE;
                if(subTextColor == 0)
                    mSubTitleColor = Color.WHITE;
            }else {
                if(background == null)
                    mBackground = getResources().getDrawable(R.color.white);
                if(mBackDrawable == null)
                    mBackDrawable = getResources().getDrawable(R.drawable.dot_red);
                if(titleColor == 0)
                    mTitleColor = Color.BLACK;
                if(subTextColor == 0)
                    mSubTitleColor = Color.BLACK;
            }
        }
        attributes.recycle();
    }


    public void setTitle(String title) {
        mTitle = title;
        if (null != mTitleView)
            mTitleView.setText(mTitle);
    }

    public void setTitle(String title, String subTitle) {
        setTitle(title);
        setSubTitle(subTitle);
    }


    public void setSubTitle(Drawable drawable) {
        mSubTitleDrawable = drawable;
        if (null != mSubTitleImage && mSubTitleDrawable !=  null){
            mSubTitleImage.setVisibility(VISIBLE);
            mSubTitleImage.setImageDrawable(mSubTitleDrawable);
        }
    }

    public void setSubTitle(int color) {
        mSubTitleColor = color;
        if (mSubTitleView != null)
            mSubTitleView.setTextColor(mSubTitleColor);
    }

    public void setSubTitle(String text) {
        mSubTitle = text;
        if (mSubTitleView != null && !TextUtils.isEmpty(mSubTitle))
            mSubTitleView.setVisibility(VISIBLE);
            mSubTitleView.setText(mSubTitle);
    }

    public void setSubTitle(String text, int color) {
        setSubTitle(text);
        setSubTitle(color);
    }


    public void setTitleOnClickListener(TitleViewListener listener) {
        this.mListener = listener;
    }
}

interface TitleViewListener {
    void titleBackClick();
    void subTitleClick(View view);
}