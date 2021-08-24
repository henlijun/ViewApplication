package com.lj.module_huizhi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                startActivity(new Intent(this, LTextViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, WatchActivity.class));
                break;
        }
    }
}