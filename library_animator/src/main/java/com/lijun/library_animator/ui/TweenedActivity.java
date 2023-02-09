package com.lijun.library_animator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.lijun.library_animator.R;
import com.lijun.library_animator.databinding.ActivityTweenedBinding;

/**
 * 补件动画
 *      透明度渐变动画
 *      缩放渐变动画
 *      平移渐变动画
 *      旋转渐变动画
 *      组合动画
 * todo
 *      ObjectAnimation 应用
 */
public class TweenedActivity extends AppCompatActivity {
    ActivityTweenedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tweened);


    }

    public void onClick(View view){
        int rid = view.getId();

        if(rid == R.id.alpha_button){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
            binding.imageView.startAnimation(animation);
        }else  if(rid == R.id.scale_button){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
            binding.imageView.startAnimation(animation);
        }else  if(rid == R.id.transition_button){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmi_translate);
            binding.imageView.startAnimation(animation);
        }else  if(rid == R.id.rotate_button){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmi_rotate);
            binding.imageView.startAnimation(animation);
        }else  if(rid == R.id.mix_button){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmi_set);
            binding.imageView.startAnimation(animation);
        }

        else  if(rid == R.id.button16){
            Animation animation = new AlphaAnimation(0f, 1f);
            animation.setDuration(3000);
            binding.imageView.startAnimation(animation);
        }
        else  if(rid == R.id.button17){
            Animation animation = new ScaleAnimation(.2f,1.5f,.2f,1.5f, .5f,.5f);
            animation.setDuration(3000);
            binding.imageView.startAnimation(animation);
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

            //todo 组合动画
            AnimationSet animationSet = new AnimationSet(true);

            Animation alpha_animation = new AlphaAnimation(0f, 1f);
            alpha_animation.setDuration(3000);
            animationSet.addAnimation(alpha_animation);

            Animation scale_animation = new ScaleAnimation(.2f,1.5f,.2f,1.5f, .5f,.5f);
            scale_animation.setDuration(3000);
            animationSet.addAnimation(scale_animation);

            Animation translate_animation = new TranslateAnimation(0, 300, 0,0);
            translate_animation.setDuration(3000);
            animationSet.addAnimation(translate_animation);

            Animation rotate_animation = new RotateAnimation(0, 360);
            rotate_animation.setDuration(3000);
            animationSet.addAnimation(translate_animation);

            binding.imageView.startAnimation(animationSet);

        }
    }
}