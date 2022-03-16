package com.lj.module_huizhi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.lj.module_huizhi.R;
import com.lj.module_huizhi.databinding.ActivitySpanableStringBinding;

public class SpanableStringActivity extends AppCompatActivity {
    ActivitySpanableStringBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_spanable_string);


        SpannableString span = new SpannableString("aabb，ddcc");
        ForegroundColorSpan foreColor = new ForegroundColorSpan(Color.RED);
        span.setSpan(foreColor, 0,2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan foreColor2 = new ForegroundColorSpan(Color.GREEN);
        span.setSpan(foreColor2, 5,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.textView21.setText(span);
    }
}