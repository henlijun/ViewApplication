package com.lj.module_jicheng.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.lj.module_jicheng.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_jicheng.dialog
 * @ClassName: LAlterDialog
 * @Description: java类作用描述
 *
 * @Author: 李军
 * @CreateDate: 2022/3/16 19:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/16 19:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LBaseDialog extends DialogFragment {

    // gravity center,bottom,top
    private int mGravity;
    // size (matchParent, wrapContent
    private boolean mIsWrapContent;
    private boolean mIsCanceledOnTouchOut;

    public LBaseDialog(){
       this(true);
    }

    public LBaseDialog(boolean isWrapContent){
        super();
        mGravity = Gravity.CENTER;
        mIsWrapContent = isWrapContent;
        mIsCanceledOnTouchOut = true;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if(dialog != null){
            if(!mIsCanceledOnTouchOut){
                dialog.setCanceledOnTouchOutside(false);
            }
            if(!mIsWrapContent && mGravity != Gravity.CENTER){
                Window window = dialog.getWindow();
                if(window != null){
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    window.getDecorView().setPadding(dp2px(16),0,dp2px(16),0);
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.gravity = mGravity;
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    getDialog().getWindow().setAttributes(params);
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void setGravity(int mGravity) {
        this.mGravity = mGravity;
    }

    public void setCanceledOnTouchOutside(boolean cancelable) {
        mIsCanceledOnTouchOut = cancelable;
        if(getDialog() != null)
            getDialog().setCanceledOnTouchOutside(cancelable);
    }

    public void setIsWrapContent(boolean mIsWrapContent) {
        this.mIsWrapContent = mIsWrapContent;
    }

    private int dp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }


}
