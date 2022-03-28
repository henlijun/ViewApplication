package com.lj.module_jicheng.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.lj.module_jicheng.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_jicheng.dialog
 * @ClassName: LAlterDialog
 * @Description: java类作用描述
 * 弹框 - 居中显示
 * title - content - （sure/cancel know cancel/delete)
 * 数据
 * todo 泛型 与 builder, 参数回调
 * @Author: 李军
 * @CreateDate: 2022/3/16 19:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/16 19:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LAlterDialog<T> extends LBaseDialog {

    public LAlterDialog(){
        super();
    }
    public LAlterDialog(Builder builder){
        super();
        mTitleText = builder.title;
        mSubTitleText = builder.content;
        mData = (T) builder.data;
        mPositiveListener = builder.lPositiveListener;
    }
    private TextView mTitleView, mSubTitleView, mSureView, mCancelView;
    private View mLineView;
    private CharSequence mTitleText, mSubTitleText;

    private T mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container,savedInstanceState);
        if(layout == null){
            layout = inflater.inflate(R.layout.view_lalter_dialog,container,false);
        }
        mTitleView = layout.findViewById(R.id.titleDiaView);
        mTitleView.setText(mTitleText);

        mSubTitleView = layout.findViewById(R.id.subTitleDiaView);
        mSubTitleView.setText(mSubTitleText);
        mSubTitleView.setMovementMethod(LinkMovementMethod.getInstance());

        mSureView = layout.findViewById(R.id.sureDiaView);
        if(mSureView != null){
            mSureView.setOnClickListener(v -> {
                if(mPositiveListener != null)
                    mPositiveListener.onPositiveClick(mData);
                dismiss();
            });
        }

        mCancelView = layout.findViewById(R.id.cancelDiaView);
        if(mCancelView != null){
            mCancelView.setOnClickListener(v -> {
                if(mNegativeListener != null)
                    mNegativeListener.onNegativeClick(mData);
                dismiss();
            });
        }
        mLineView = layout.findViewById(R.id.centerDiaLine);
        return layout;
    }

    //sure| know
    public void setNotifyStyle(){
        setCanceledOnTouchOutside(false);
        if(mCancelView != null)
            mCancelView.setVisibility(View.GONE);
        if(mSureView != null){
            mSureView.setText("知道了");
        }
    }

    //delete/cancel
    public void setDeleteStyle(){
        if(mSureView != null){
            mSureView.setTextColor(Color.parseColor("#FF3E6D"));
            mSureView.setText("删除");
        }
    }


    private LPositiveListener mPositiveListener;
    private LNegativeListener mNegativeListener;
    private LNeutralListener mNeutralListener;

    public void setLPositiveListener(LPositiveListener listener){
        setLPositiveListener(null, listener);
    }
    public void setLPositiveListener(T data, LPositiveListener listener){
        this.mData = data;
        this.mPositiveListener = listener;
    }

    public void setLNegativeListener(LNegativeListener listener){
        setLNegativeListener(null, listener);
    }
    public void setLNegativeListener(T data, LNegativeListener listener){
        this.mData = data;
        this.mNegativeListener = listener;
    }

    public void setLNeutralListener(LNeutralListener listener){
        setLNeutralListener(null, listener);
    }
    public void setLNeutralListener(T data, LNeutralListener listener){
        this.mData = data;
        this.mNeutralListener = listener;
    }

    public void setContent(CharSequence spannableString){
        mSubTitleText = spannableString;
        if(mSubTitleView != null)
            mSubTitleView.setText(mSubTitleText);
    }

    public static class Builder<T>{
        public String title, content;
        public LPositiveListener lPositiveListener;
        private T data;

        public Builder(){}

        public Builder setContent(String content){
           this.content = content;
            return this;
        }
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setData(T data){
            this.data = data;
            return this;
        }

        public Builder setlPositiveListener(LPositiveListener lPositiveListener) {
            this.lPositiveListener = lPositiveListener;
            return this;
        }

        public LAlterDialog create(){
            return new LAlterDialog(this);
        }
    }


    public interface LPositiveListener<T> {
        void onPositiveClick(T data);
    }
    public interface LNegativeListener<T> {
        void onNegativeClick(T data);
    }
    public interface LNeutralListener<T> {
        void onNeutralClick(T data);
    }

}


