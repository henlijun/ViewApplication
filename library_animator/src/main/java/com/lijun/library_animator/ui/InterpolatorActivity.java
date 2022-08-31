package com.lijun.library_animator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioGroup;

import com.lijun.library_animator.R;
import com.lijun.library_animator.databinding.ActivityInterpolatorBinding;

/**
 * Interpolator（插值器）  动画速度控制，动画渲染器
 * http://t.zoukankan.com/tonny-li-p-4174437.html
 */
public class InterpolatorActivity extends AppCompatActivity {

    ActivityInterpolatorBinding binding;

    Animation animation;

    Interpolator interpolator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interpolator);
        animation = new AlphaAnimation(0f, 1f);
        interpolator = new LinearInterpolator();
        binding.animGroup.setOnCheckedChangeListener((view, rid) ->{
            if(rid == R.id.alpha_button){
                animation = new AlphaAnimation(0f, 1f);
            }else if(rid == R.id.scale_button){
                animation = new ScaleAnimation(.2f,1.5f,.2f,1.5f, .5f,.5f);
            }else if(rid == R.id.transition_button){
                animation =  new TranslateAnimation(0, 300, 0,0);
            }else if(rid == R.id.rotate_button){
                animation = new RotateAnimation(0, 360);
            }else if(rid == R.id.mix_button){
                animation = AnimationUtils.loadAnimation(this, R.anim.anmi_set);
            }

            update();
        });

        binding.interpolatorGroup.setOnCheckedChangeListener((g, rid) -> {
            if(rid == R.id.button_linear){
                interpolator = new LinearInterpolator();
            }else if(rid == R.id.button_acc)
                interpolator = new AccelerateInterpolator();
            else if(rid == R.id.button_acc_dec)
                interpolator = new AccelerateDecelerateInterpolator();
            else if(rid == R.id.button_dec)
                interpolator = new DecelerateInterpolator();
            else if(rid == R.id.button_cycle)
                interpolator = new CycleInterpolator(1);
            else if(rid == R.id.button_antic)
                interpolator = new AnticipateInterpolator();
            else if(rid == R.id.button_overshoot)
                interpolator = new OvershootInterpolator();
            else if(rid == R.id.button_antic_overshoot)
                interpolator = new AnticipateOvershootInterpolator();
            else if(rid == R.id.button_bounce)
                interpolator = new BounceInterpolator();


            update();
        });
    }

    public void onClick(View view){

    }

    public void update(){
        if(animation != null && interpolator != null){
            animation.setDuration( 6* 1000);
            animation.setInterpolator(interpolator);
            binding.imageView.startAnimation(animation);
        }
    }
}