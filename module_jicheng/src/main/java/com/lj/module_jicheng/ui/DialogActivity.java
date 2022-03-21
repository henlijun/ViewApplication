package com.lj.module_jicheng.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lj.module_jicheng.R;
import com.lj.module_jicheng.databinding.ActivityDialog2Binding;
import com.lj.module_jicheng.dialog.LBaseDialog;
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
                bDialog.show(getSupportFragmentManager(), "fragmentDialog");
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                final LFragThemeDialog fThemeDialog = new LFragThemeDialog();
                fThemeDialog.show(getSupportFragmentManager(), "fragmentDialog");
                break;
            default:
        }
    }
}