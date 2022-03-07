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
        TextView textView = findViewById(R.id.textView2);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine(true);
        textView.setSelected(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
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
        }
    }
}