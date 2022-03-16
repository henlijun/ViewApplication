package com.lj.module_huizhi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.lj.module_huizhi.R;
import com.lj.module_huizhi.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {
    ActivityAnimationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation);

        AnimationDrawable animat = (AnimationDrawable) binding.imageView2.getBackground();
        animat.start();
    }
}