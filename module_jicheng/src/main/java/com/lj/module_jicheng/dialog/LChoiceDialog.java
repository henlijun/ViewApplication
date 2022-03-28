package com.lj.module_jicheng.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lj.module_jicheng.R;
import com.lj.module_jicheng.util_view.BaselineItemDecoration;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class LChoiceDialog<T> extends LBaseDialog {

    public LChoiceDialog(){
        super(false);
        setGravity(Gravity.BOTTOM);
    }

    private String mTitleText;

    private T mData;

    private Map<String, String> mViewData = new LinkedHashMap<>(8);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);
        if (layout == null) {
            layout = inflater.inflate(R.layout.view_lchoice_dialog, container, false);
        }
        TextView mTitleView = layout.findViewById(R.id.titleDiaView);
        if (mTitleView != null && !TextUtils.isEmpty(mTitleText)) {
            mTitleView.setVisibility(View.VISIBLE);
            mTitleView.setText(mTitleText);
        }

        TextView mCancelView = layout.findViewById(R.id.cancelDiaView);
        if (mCancelView != null) {
            mCancelView.setOnClickListener(v -> {
                dismiss();
            });
        }

        RecyclerView recyclerView = layout.findViewById(R.id.choiceDiaRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new BaselineItemDecoration(getContext()));
        //todo shopping
        ChoiceAdapter adapter = new ChoiceAdapter();
        recyclerView.setAdapter(adapter);
        return layout;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewData.clear();
    }
}

class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ViewHold>{


    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHold(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_choice_dialog, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class ViewHold extends RecyclerView.ViewHolder{

        public ViewHold(@NonNull View itemView) {
            super(itemView);
        }
    }


}


