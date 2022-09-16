package com.lj.module_huizhi.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.module_huizhi.R;

/**
 * Fragment 生命周期
 */
public class ViewCycleFragment extends Fragment {
    private static final String TAG = "ViewCycleFragment.Class";

    public ViewCycleFragment() {
    }

    /////////////////////////////创建
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: 绑定Activity");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: R.layout.fragment_view_cycle");
        return inflater.inflate(R.layout.fragment_view_cycle, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }


    ////////////////////////////////运行


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: 可见");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: 交互");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: 不可见之前");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: 不可见");
    }
    /////////////////////////////销毁
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: 销毁fragment的UI");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: 解绑Activity");
    }
}