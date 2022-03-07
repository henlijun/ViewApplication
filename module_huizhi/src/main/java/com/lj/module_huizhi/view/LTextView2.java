package com.lj.module_huizhi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

import com.lj.module_huizhi.R;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LTextView2
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2021/10/9 17:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/9 17:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("AppCompatCustomView")
public class LTextView2 extends TextView {
    private static final String TAG = "LTextView2";
    public LTextView2(Context context) {
        super(context);
    }

    public LTextView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        Log.v(TAG, "XML 2");

    }

    public LTextView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        Log.v(TAG, "XML 3");

    }

    public LTextView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.v(TAG, "XML 4");
    }
}
