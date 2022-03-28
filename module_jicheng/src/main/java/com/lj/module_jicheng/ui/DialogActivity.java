package com.lj.module_jicheng.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lj.module_jicheng.R;
import com.lj.module_jicheng.databinding.ActivityDialog2Binding;
import com.lj.module_jicheng.dialog.LAlterDialog;
import com.lj.module_jicheng.dialog.LBaseDialog;
import com.lj.module_jicheng.dialog.LChoiceDialog;
import com.lj.module_jicheng.dialog.LFragThemeDialog;
import com.lj.module_jicheng.dialog.LFragmentDialog;

public class DialogActivity extends AppCompatActivity {
    ActivityDialog2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog2);

    }


    int a;
    public void onClick(View view){
        int rid = view.getId();
        switch (rid){
            case R.id.button3:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("标题")
                        .setMessage("弄啥嘞! ？？？")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNeutralButton(" ++a ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.textView.setText(String.valueOf(a++));
                            }
                        })
                        .setNeutralButton("nothing", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                alertDialog.show();
                break;
            case R.id.button4:
                final AlertDialog.Builder alterDig = new AlertDialog.Builder(this);
                View digLayout = View.inflate(this, R.layout.view_dilog_alter, null);
                Button button = digLayout.findViewById(R.id.button5);
                alterDig.setTitle("自定义Dialog").setMessage("咋地！！！").setView(digLayout)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alterDig.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.textView.setText(String.valueOf(a++));
                    }
                });
                break;
            case R.id.button5:
                final LFragmentDialog fDiglog = new LFragmentDialog();
                fDiglog.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.textView.setText(String.valueOf(a++));
                    }
                });
                fDiglog.show(getSupportFragmentManager(), "fragmentDialog");
                break;
            case R.id.button6:
                final LBaseDialog bDialog = new LBaseDialog();
                bDialog.show(getSupportFragmentManager(), "fragmentDialog_base");
                break;
            case R.id.button7:
                final LAlterDialog<Integer> aAltFragDialog = new LAlterDialog();
                aAltFragDialog.setLPositiveListener(1,data -> {
                    binding.textView.setText(String.valueOf(a++));
                });
                aAltFragDialog.show(getSupportFragmentManager(), "alter_fragmentDialog");
                aAltFragDialog.setCanceledOnTouchOutside(true);
                break;
            case R.id.button8:
                SpannableString spannableString = new SpannableString("你好，你过来啊");
                spannableString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        binding.textView.setText(String.valueOf(a++));
                    }
                }, spannableString.length() -4, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                final LAlterDialog<Integer> aAltFragDialog2 = new LAlterDialog();
                aAltFragDialog2.setLPositiveListener(1,data -> {
                    binding.textView.setText(String.valueOf(a++));
                });
                aAltFragDialog2.show(getSupportFragmentManager(), "alter_fragmentDialog");
                aAltFragDialog2.setContent(spannableString);
                aAltFragDialog2.setCanceledOnTouchOutside(true);
                break;
            case R.id.button9:

                final LAlterDialog<Integer> aAltFragDialog3 = new LAlterDialog();
                aAltFragDialog3.setLPositiveListener(1,data -> {
                    binding.textView.setText(String.valueOf(a++));
                });
                aAltFragDialog3.show(getSupportFragmentManager(), "alter_fragmentDialog");
                aAltFragDialog3.setCanceledOnTouchOutside(true);
                SpannableStringBuilder builder = new SpannableStringBuilder("jjjjjj---");
                Drawable drawable = getResources().getDrawable(R.drawable.dot_red);
                drawable.setBounds(0,0, 40, 40);
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

                builder.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                        Drawable drawable = getResources().getDrawable(isCheck?R.drawable.dot_red:R.drawable.black);
                        drawable.setBounds(0,0, 40, 40);
                        ImageSpan imageSpan2 = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                        builder.setSpan(imageSpan2, 0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        aAltFragDialog3.setContent(builder);
                        binding.textView.setText(String.valueOf(a++));
                        isCheck = !isCheck;
                    }
                }, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                aAltFragDialog3.setContent(builder);
                break;
            case R.id.button10:
                final LFragThemeDialog fThemeDialog = new LFragThemeDialog();
                fThemeDialog.show(getSupportFragmentManager(), "fragmentDialog_theme");
                break;
            case R.id.button11:
                final LChoiceDialog lChoiceDialog = new LChoiceDialog();
                lChoiceDialog.show(getSupportFragmentManager(), "fragmentDialog_choice");
                break;
            case R.id.button12:
                new LAlterDialog.Builder()
                        .setTitle("title")
                        .setContent("content")
                        .setData(new Student())
                        .setlPositiveListener((LAlterDialog.LPositiveListener<Student>) d ->
                                binding.textView.setText(d.id + (a++)))
                      /*  .setlPositiveListener(new LAlterDialog.LPositiveListener<Student>() {
                            @Override
                            public void onPositiveClick(Student d) {
                                binding.textView.setText(d.id + (a++));
                            }
                        })*/
                        .create()
                        .show(getSupportFragmentManager(),"fff");
                break;
            default:
        }
    }

    boolean isCheck = false;

    class Student{
        public String name;
        public String id;
        public Student(){
            name = "幺儿";
            id = "jklefjklfeajlfe";
        }
    }
}