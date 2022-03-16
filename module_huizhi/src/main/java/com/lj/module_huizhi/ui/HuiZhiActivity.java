package com.lj.module_huizhi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lj.module_huizhi.R;

public class HuiZhiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_zhi);
    }

    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.button:
                //基本图形
                startActivity(new Intent(this, LTextViewActivity.class));
                break;
            case R.id.button2:
                //表
                startActivity(new Intent(this, WatchActivity.class));
                break;
            case R.id.button3:
                //line
                startActivity(new Intent(this, LineActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, GroupFlowLayoutActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, SpanableStringActivity.class));
                break;

            case R.id.button6:
                startActivity(new Intent(this, AnimationActivity.class));
                break;
        }
    }
}