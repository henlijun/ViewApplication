package com.lj.module_huizhi.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.util.LayoutDirection;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LView
 * @Description: java类作用描述
 *
 * 学习View源码，抄袭复写
 * @Author: 李军
 * @CreateDate: 2021/7/22 20:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/22 20:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@UiThread
public class LView implements Drawable.Callback, KeyEvent.Callback,
        AccessibilityEventSource {

    public static final int SOUND_EFFECTS_ENABLED = 0x08000000;//声音效果（例：点击|长按）
    public static final int HAPTIC_FEEDBACK_ENABLED = 0x10000000;//触觉效果（例：长按）
    public static final int FOCUSABLE_AUTO = 0x00000010;

    public static final int TEXT_ALIGNMENT_GRAVITY = 1;
    public static final int TEXT_ALIGNMENT_RESOLVED_DEFAULT = TEXT_ALIGNMENT_GRAVITY;

    private static final int LAYOUT_DIRECTION_DEFAULT = LayoutDirection.INHERIT;
    private static final int TEXT_DIRECTION_DEFAULT = 0;
    private static final int TEXT_ALIGNMENT_DEFAULT = TEXT_ALIGNMENT_GRAVITY;
    static final int PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT = 2;//位移，获得水平布局方向。
    static final int PFLAG2_TEXT_ALIGNMENT_MASK_SHIFT = 13;
    static final int PFLAG2_TEXT_DIRECTION_MASK_SHIFT = 6;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK_SHIFT = 17;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_DEFAULT =
            TEXT_DIRECTION_DEFAULT << PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT;
    private static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_DEFAULT =
            TEXT_ALIGNMENT_RESOLVED_DEFAULT << PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK_SHIFT;

    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0x00000000;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 0x00000001;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 0x00000002;
    static final int IMPORTANT_FOR_ACCESSIBILITY_DEFAULT = IMPORTANT_FOR_ACCESSIBILITY_AUTO;
    static final int PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_SHIFT = 20;

    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_NEVER = 2;

    private static final int UNDEFINED_PADDING = Integer.MIN_VALUE;

    private Context mContext;
    private Resources mResources;
//    @UnsupportedAppUage(maxTargetSdk = Build.VERSION_CODES.P)
    int mViewFlags;
    int mPrivateFlags2;
    private int mTouchSlop;
    private int mOverScrollMode;

    int mUserPaddingStart;
    int mUserPaddingEnd;
    RenderNode mRenderNode;
    public LView(Context context){
        mContext = context;
        mResources = context != null? context.getResources() : null;
        mViewFlags = SOUND_EFFECTS_ENABLED | HAPTIC_FEEDBACK_ENABLED | FOCUSABLE_AUTO;
        mPrivateFlags2 =
                (LAYOUT_DIRECTION_DEFAULT << PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT) |
                (TEXT_DIRECTION_DEFAULT << PFLAG2_TEXT_DIRECTION_MASK_SHIFT) |
                (PFLAG2_TEXT_DIRECTION_RESOLVED_DEFAULT) |
                        (TEXT_ALIGNMENT_DEFAULT << PFLAG2_TEXT_ALIGNMENT_MASK_SHIFT) |
                (PFLAG2_TEXT_ALIGNMENT_RESOLVED_DEFAULT) |
                (IMPORTANT_FOR_ACCESSIBILITY_DEFAULT << PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_SHIFT);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOverScrollMode(OVER_SCROLL_IF_CONTENT_SCROLLS);
        mUserPaddingStart = UNDEFINED_PADDING;
        mUserPaddingEnd = UNDEFINED_PADDING;
    }


    public void setOverScrollMode(int overScrollMode){
        if(overScrollMode != OVER_SCROLL_ALWAYS &&
            overScrollMode != OVER_SCROLL_IF_CONTENT_SCROLLS &&
            overScrollMode != OVER_SCROLL_NEVER)
            throw new IllegalArgumentException("Invalid overscroll mode " + overScrollMode);
        mOverScrollMode = overScrollMode;
    }
    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {

    }

    @Override
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long l) {

    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {

    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int i, int i1, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void sendAccessibilityEvent(int i) {

    }

    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {

    }
}
