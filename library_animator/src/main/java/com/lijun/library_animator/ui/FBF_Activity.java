package com.lijun.library_animator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.lijun.library_animator.R;
import com.lijun.library_animator.databinding.ActivityFbfBinding;

/**
 * 帧动画
 *
 * https://www.xp.cn/b.php/95295.html
 */
public class FBF_Activity extends AppCompatActivity {
    ActivityFbfBinding binding;

    public  AnimationDrawable animationDrawable;

    public int duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbf);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fbf);

        binding.imageView2.setBackgroundResource(R.drawable.animation_a);
        ((AnimationDrawable)  binding.imageView2.getBackground()).start();

        animationDrawable = (AnimationDrawable) binding.imageView2.getBackground();

        binding.oneShotButton.setOnClickListener(v->{
            clear();
            animationDrawable = (AnimationDrawable)
                    getDrawable(R.drawable.animation_a);
            animationDrawable.setOneShot(true);
            binding.imageView2.setBackground(animationDrawable);
            animationDrawable.start();
        });

        binding.moreShotButton.setOnClickListener(v->{
            clear();
            animationDrawable = new AnimationDrawable();
            for (int i=1; i< 9; i++){
                int rid = getResources().getIdentifier("a_" + i, "drawable",getPackageName());
                animationDrawable.addFrame(getDrawable(rid), 300);
            }
            animationDrawable.setOneShot(false);
            binding.imageView2.setBackground(animationDrawable);
            animationDrawable.start();
        });

        binding.oneDurationButton.setOnClickListener(v -> {
            try {
                String text = binding.editTextTextPersonName.getText().toString();
                duration = Integer.parseInt(text);
                if(duration > 0){
                    clear();
                    animationDrawable = new AnimationDrawable();
                    for (int i=1; i< 9; i++){
                        int rid = getResources().getIdentifier("a_" + i, "drawable",getPackageName());
                        animationDrawable.addFrame(getDrawable(rid), duration);
                    }
                    animationDrawable.setOneShot(false);
                    binding.imageView2.setBackground(animationDrawable);
                    animationDrawable.start();
                }
            }catch (NumberFormatException e){
                duration = 0;
            }
        });

        binding.clearButton.setOnClickListener(v->{
            clear();
        });
    }

    public void clear(){
        if(animationDrawable != null){
            animationDrawable.stop();
            animationDrawable = null;
            binding.imageView2.setBackground(null);
        }
    }
}