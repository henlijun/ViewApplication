package com.lj.module_jicheng.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
    //backGround + corner

    public LBaseDialog(){
        super();
        mGravity = Gravity.CENTER;
        mIsWrapContent = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if(dialog != null){
            Window window = dialog.getWindow();
            if(window != null){
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.getDecorView().setPadding(0,0,0,0);
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = mGravity;
                params.width = mIsWrapContent ?
                        ViewGroup.LayoutParams.WRAP_CONTENT: ViewGroup.LayoutParams.MATCH_PARENT;
                getDialog().getWindow().setAttributes(params);
            }
        }

        /*if (mContentLayoutId != 0) {
            return inflater.inflate(mContentLayoutId, container, false);
        }
        return null;*/
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
