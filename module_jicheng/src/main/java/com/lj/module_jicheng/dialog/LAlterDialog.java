package com.lj.module_jicheng.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
 * todo 数据
 * @Author: 李军
 * @CreateDate: 2022/3/16 19:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/16 19:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LAlterDialog extends LBaseDialog {

    public LAlterDialog(){
        super();
        setIsWrapContent(true);
    }

    private TextView mTitleView, mSubTitleView, mSureView, mCancelView;
    private View mLineView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container,savedInstanceState);
        if(layout == null){
            layout = inflater.inflate(R.layout.view_lalter_dialog,container,false);
        }
        mTitleView = layout.findViewById(R.id.titleDiaView);
        mSubTitleView = layout.findViewById(R.id.subTitleDiaView);

        mSureView = layout.findViewById(R.id.sureDiaView);
        if(mSureView != null){
            mSureView.setOnClickListener(v -> {

            });
        }
        mCancelView = layout.findViewById(R.id.cancelDiaView);
        if(mCancelView != null){
            mCancelView.setOnClickListener(v -> {

            });
        }
        mLineView = layout.findViewById(R.id.centerDiaLine);
        return layout;
    }

    //sure| know
    public void setNotifyStyle(){
        setCanceledOnTouchOutside(false);
    }

    //delete/cancel
    public void setDeleteStyle(){

    }

    //sure/cancel
    public void setAlterStyle(){

    }



}
