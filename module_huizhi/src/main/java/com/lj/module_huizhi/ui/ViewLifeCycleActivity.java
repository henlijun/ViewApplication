package com.lj.module_huizhi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.lj.module_huizhi.R;

/**
 * 生命周期
 * todo
 *  Activity 生命周期
 *  View 生命周期
 *  Activity 与View 生命周期的关系
 *  Fragment 与Activity 生命周期
 *  Fragment & Activity & View 生命周期之间的联系
 */
public class ViewLifeCycleActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycleActivity.Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life_cycle);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }



    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "finish: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}