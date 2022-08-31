package com.lijun.library_animator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.lijun.library_animator.R;
import com.lijun.library_animator.databinding.ActivityPropertyBinding;

/**
 *  属性动画
 */
public class PropertyActivity extends AppCompatActivity {
    ActivityPropertyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property);

        /*
        binding.imageView.setTranslationX();
        binding.imageView.setAlpha();
        binding.imageView.setRotation();
        binding.imageView.setScaleX();

        */


    }


    public void onClick(View view){
        int rid = view.getId();

        if(rid == R.id.alpha_button){
            ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
            animation.setDuration(6000);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (float) animation.getAnimatedValue();
                    binding.imageView.setAlpha(alpha);
                }
            });
            animation.setInterpolator(new LinearInterpolator());
            animation.start();
        }else  if(rid == R.id.scale_button){
            ValueAnimator animation = ValueAnimator.ofFloat(.2f, 1.5f);
            animation.setDuration(6000);
            animation.addUpdateListener(anim -> {
                float scale = (float) anim.getAnimatedValue();
                binding.imageView.setScaleX(scale);
                binding.imageView.setScaleY(scale);
            });
            animation.setInterpolator(new AnticipateInterpolator());
            animation.start();
        }else  if(rid == R.id.transition_button){
            ValueAnimator animation = ValueAnimator.ofFloat(0f, 200f);
            animation.setDuration(6000);
            animation.addUpdateListener(anim -> {
                float translate = (float) anim.getAnimatedValue();
                binding.imageView.setTranslationX(translate);
            });
            animation.setInterpolator(new AnticipateInterpolator());
            animation.start();
        }else  if(rid == R.id.rotate_button){
            ValueAnimator animation = ValueAnimator.ofFloat(0f, 360f);
            animation.setDuration(6000);
            animation.addUpdateListener(anim -> {
                float rotate = (float) anim.getAnimatedValue();
                binding.imageView.setRotation(rotate);
            });
            animation.setInterpolator(new AnticipateInterpolator());
            animation.start();
        }else  if(rid == R.id.mix_button){

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(new LinearInterpolator());

            ValueAnimator alpha_animation = ValueAnimator.ofFloat(0f, 1f);
            alpha_animation.setDuration(6000);
            alpha_animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (float) animation.getAnimatedValue();
                    binding.imageView.setAlpha(alpha);
                }
            });

            ValueAnimator scale_animation = ValueAnimator.ofFloat(.2f, 1.5f);
            scale_animation.setDuration(6000);
            scale_animation.addUpdateListener(anim -> {
                float scale = (float) anim.getAnimatedValue();
                binding.imageView.setScaleX(scale);
                binding.imageView.setScaleY(scale);
            });

            ValueAnimator translate_animation = ValueAnimator.ofFloat(0f, 200f);
            translate_animation.setDuration(6000);
            translate_animation.addUpdateListener(anim -> {
                float translate = (float) anim.getAnimatedValue();
                binding.imageView.setTranslationX(translate);
            });
            ValueAnimator rotate_animation = ValueAnimator.ofFloat(0f, 360f);
            rotate_animation.setDuration(6000);
            rotate_animation.addUpdateListener(anim -> {
                float rotate = (float) anim.getAnimatedValue();
                binding.imageView.setRotation(rotate);
            });

            animatorSet.play(alpha_animation).before(scale_animation)
                    .with(translate_animation)
                    .after(rotate_animation);
            animatorSet.start();
        }

        else  if(rid == R.id.button16){
            ObjectAnimator animator = ObjectAnimator.ofFloat(binding.imageView, "alpha",0f, 1f);
            animator.setDuration(5000);
            animator.start();
        }
        else  if(rid == R.id.button17){
            ObjectAnimator animator = ObjectAnimator.ofFloat(binding.imageView, "scaleX",.2f,1.5f);
            animator.setDuration(5000);
            animator.start();
        }
        else  if(rid == R.id.button18){
            Animation animation = new TranslateAnimation(0, 300, 0,0);
            animation.setDuration(3000);
            binding.imageView.startAnimation(animation);

        }else  if(rid == R.id.button19){
            Animation animation = new RotateAnimation(0, 360);
            animation.setDuration(3000);
            binding.imageView.startAnimation(animation);
        }else  if(rid == R.id.button20){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmi_set);
            binding.imageView.startAnimation(animation);
        }
    }
}