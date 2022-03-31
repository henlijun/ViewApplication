package com.lj.module_jicheng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lj.module_jicheng.ui.DialogActivity;

public class JiChengActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_cheng);
    }

    public void onClick(View view){
        int rid = view.getId();
        switch (rid){
            case R.id.button:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            default:
        }
    }
}