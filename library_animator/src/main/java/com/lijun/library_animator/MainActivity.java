package com.lijun.library_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lijun.library_animator.ui.FBF_Activity;
import com.lijun.library_animator.ui.InterpolatorActivity;
import com.lijun.library_animator.ui.PropertyActivity;
import com.lijun.library_animator.ui.TweenedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        int rid = view.getId();
        if(rid == R.id.button){
            startActivity(new Intent(this, FBF_Activity.class));
        }else if(rid == R.id.button2){
            startActivity(new Intent(this, TweenedActivity.class));
        }else if(rid == R.id.button3){
            startActivity(new Intent(this, PropertyActivity.class));
        }else if(rid == R.id.button4){
            startActivity(new Intent(this, InterpolatorActivity.class));
        }else if(rid == R.id.button5){

        }else if(rid == R.id.button6){

        }else if(rid == R.id.button7){

        }else if(rid == R.id.button8){

        }
    }
}