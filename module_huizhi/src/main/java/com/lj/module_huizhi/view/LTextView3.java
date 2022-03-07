package com.lj.module_huizhi.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Editable;
import android.text.GetChars;
import android.text.InputFilter;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.textclassifier.TextClassifier;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;
import java.util.Locale;


/**
 * @ProjectName: ViewApplication
 * @Package: com.lj.module_huizhi.view
 * @ClassName: LTextView3
 * @Description: java类作用描述
 * @Author: 李军
 * @CreateDate: 2022/3/2 10:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/3/2 10:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LTextView3 extends View implements ViewTreeObserver.OnPreDrawListener {

    static final String LOG_TAG = "L_TextView_3";
    static final boolean DEBUG_EXTRACT = false;
    private static final float[] TEMP_POSITION = new float[2];

    private static final int DEFAULT_TYPEFACE = -1;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int MONOSPACE = 3;

    //ellipsize 省略号 配合singleLine
    private static final int ELLIPSIZE_NOT_SET = -1;
    private static final int ELLIPSIZE_NONE = 0;
    private static final int ELLIPSIZE_START = 1;
    private static final int ELLIPSIZE_MIDDLE = 2;//跑马灯,需要focus
    private static final int ELLIPSIZE_END = 3;
    private static final int ELLIPSIZE_MARQUEE = 4;

    //numeric 数字键盘 自动换行 整数和小数：小数点(integer,decimal, signed)
    private static final int SIGNED = 2;
    private static final  int DECIMAL = 4;

    //跑马灯渐变效果
    private static final int MARQUEE_FADE_NORMAL= 0;
    private static final int MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS = 1;
    private static final int MARQUEE_FADE_SWITCH_SHOW_FADE = 2;

    private static final int LINES = 1;
    private static final int EMS = LINES;
    private static final int PIXELS = 2;

    private static final RectF TEMP_RECTF = new RectF();

    static final int VERY_WIDE = 1024 * 1024;
    private static final int ANIMATED_SCROLL_GAP = 250;
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final Spanned EMPTY_SPANNED = new SpannedString("");

    private static final int CHANGE_WATCHER_PRIORITY = 100;

    private static final int[] MULTILINE_STATE_SET = {android.R.attr.state_multiline};

    private static final int ACCESSIBILITY_ACTION_SHARE = 0x10000000;
    private static final int ACCESSIBILITY_ACTION_PROCESS_TEXT_START_ID = 0x10000100;

    static final int PROCESS_TEXT_REQUEST_CODE = 100;

    //reture code of doKeyDown
    private static final int KEY_EVENT_NOT_HANDLED = 0;
    private static final int KEY_EVENT_HANDLED = -1;
    private static final int KEY_DOWN_HANDLED_BY_KEY_LISTENER = 1;
    private static final int KEY_DOWN_HANDLED_BY_MOVEMENT_METHOD = 2;

    private static final int FLOATING_TOOLBAR_SELECT_ALL_REFRESH_DELAY = 500;
    static long sLastCutCopyOrTextChangedTime;

    private ColorStateList mTextColor;
    private ColorStateList mHintTextColor;
    private ColorStateList mLinkTextColor;

    private int mCurTextColor;
    private int mCurHintTextColor;
    private boolean mFreezesText;

    private Editable.Factory mEditableFactory = Editable.Factory.getInstance();
    private Spannable.Factory mSpannableFactory = Spannable.Factory.getInstance();

    private float mShadowRadius, mShadowDx, mShadowDy;
    private int mShadowColor;

    private boolean mPreDrawRegistered;
    private boolean mPreDrawListenerDetched;

    private TextClassifier mTextClassifier;
    private TextClassifier mTextClassificationSession;

    private boolean mPreventDefaultMovement;
    private TextUtils.TruncateAt Ellipsize;

    static class Drawables{
        static final int left  = 0;
        static final int top = 1;
        static final int right = 2;
        static final int bottom = 3;

        static final int DRAWABLE_NONE = -1;
        static final int DRAWABLE_RIGHT = 0;
        static final int DRAWABLE_LEFT = 1;

        final Rect mCompoundRect = new Rect();
        final Drawable[] mShowing = new Drawable[4];
        ColorStateList mTintList;
        PorterDuff.Mode mTintMode;
        boolean mHasTint;
        boolean mHasTintMode;

        Drawable mDrawableStart, mDrawableEnd, mDrawableError, mDrawalbeTemp;
        Drawable mDrawableLeftInitial, mDrawableRightInitial;

        boolean mIsRtlCompatibilityMode;
        boolean mOverride;

        int mDrawableSizeTop, mDrawableSizeBottom,mDrawableSizeLeft,mDrawableSizeRight,
            mDrawableSizeStart, mDrawableSizeEnd, mDrawableSizeError, mDrawableSizeTemp;
        int mDrawableWidthTop, mDrawableWidthBottom, mDrawableHeightLeft, mDrawableHeightRight,
            mDrawableHeightStart, mDrawableHeightEnd, mDrawableHeightError, mDrawableHeightTemp;

        int mDrawablePadding;
        int mDrawableSaved = DRAWABLE_NONE;

        public Drawables(Context context){
            final int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
            mIsRtlCompatibilityMode = targetSdkVersion < Build.VERSION_CODES.JELLY_BEAN_MR1;
            mOverride = false;
        }

        public boolean hasMetadata(){
            return mDrawablePadding != 0 || mHasTint || mHasTintMode;
        }

        public boolean resolveWithLayoutDirection(int layoutDirection){
            final Drawable previousLeft = mShowing[Drawables.left];
            final Drawable previousRight = mShowing[Drawables.right];

            mShowing[Drawables.left] = mDrawableLeftInitial;
            mShowing[Drawables.right] = mDrawableRightInitial;

            if(mIsRtlCompatibilityMode){
                //use start as left
                if(mDrawableStart != null && mShowing[Drawables.left] == null){
                    mShowing[Drawables.left] = mDrawableStart;
                    mDrawableSizeLeft = mDrawableSizeStart;
                    mDrawableHeightLeft = mDrawableHeightLeft;
                }
                //use end as right
                if(mDrawableEnd != null && mShowing[Drawables.right] == null){
                    mShowing[Drawables.right] = mDrawableEnd;
                    mDrawableSizeRight = mDrawableSizeEnd;
                    mDrawableHeightRight = mDrawableHeightEnd;
                }
            }else {
                switch (layoutDirection){
                    case LAYOUT_DIRECTION_RTL:
                        if(mOverride){
                            mShowing[Drawables.right] = mDrawableStart;
                            mDrawableSizeRight = mDrawableSizeStart;
                            mDrawableHeightRight = mDrawableHeightStart;

                            mShowing[Drawables.left] = mDrawableEnd;
                            mDrawableSizeLeft = mDrawableSizeEnd;
                            mDrawableHeightLeft = mDrawableHeightEnd;
                        }
                        break;
                    case LAYOUT_DIRECTION_LTR:
                    default:
                        if(mOverride){
                            mShowing[Drawables.left] = mDrawableStart;
                            mDrawableSizeLeft = mDrawableSizeStart;
                            mDrawableHeightLeft = mDrawableHeightStart;

                            mShowing[Drawables.right] = mDrawableEnd;
                            mDrawableSizeRight = mDrawableSizeEnd;
                            mDrawableHeightRight = mDrawableHeightEnd;
                        }
                        break;
                }
            }
            applyErrorDrawableIfNeeded(layoutDirection);
            return mShowing[Drawables.left] != previousLeft ||
                    mShowing[Drawables.right] != previousRight;

        }

        public void setErrorDrawable(Drawable dr , LTextView3 tv){
            if(mDrawableError != dr && mDrawableError != null){
                mDrawableError.setCallback(null);
            }
            mDrawableError = dr;

            if(mDrawableError != null){
                final Rect compoundRect = mCompoundRect;
                final int[] state = tv.getDrawableState();
                mDrawableError.setState(state);
                mDrawableError.setBounds(compoundRect);
                mDrawableError.setCallback(tv);
                mDrawableSizeError = compoundRect.width();
                mDrawableHeightError = compoundRect.height();
            }else {
                mDrawableSizeError = mDrawableHeightError = 0;
            }
        }



        private void applyErrorDrawableIfNeeded(int layoutDirection){
            switch (mDrawableSaved){
                case DRAWABLE_LEFT:
                    mShowing[Drawables.left] = mDrawalbeTemp;
                    mDrawableSizeLeft = mDrawableSizeTemp;
                    mDrawableHeightLeft = mDrawableHeightTemp;
                    break;
                case DRAWABLE_RIGHT:
                    mShowing[Drawables.right] = mDrawalbeTemp;
                    mDrawableSizeRight = mDrawableSizeTemp;
                    mDrawableHeightRight = mDrawableHeightTemp;
                    break;
                case DRAWABLE_NONE:
                default:
            }
            if(mDrawableError != null){
                switch (layoutDirection){
                    case LAYOUT_DIRECTION_RTL:
                            mDrawableSaved = DRAWABLE_LEFT;
                            mDrawalbeTemp = mShowing[Drawables.left];
                            mDrawableSizeTemp = mDrawableSizeLeft;
                            mDrawableHeightTemp = mDrawableHeightLeft;

                            mShowing[Drawables.left] = mDrawableError;
                            mDrawableSizeLeft = mDrawableSizeError;
                            mDrawableHeightLeft = mDrawableHeightError;
                        break;
                    case LAYOUT_DIRECTION_LTR:
                    default:
                        mDrawableSaved = DRAWABLE_RIGHT;
                        mDrawalbeTemp = mShowing[Drawables.right];
                        mDrawableSizeTemp = mDrawableSizeRight;
                        mDrawableHeightTemp = mDrawableHeightRight;
                        mShowing[Drawables.right] = mDrawableError;
                        mDrawableSizeRight = mDrawableSizeError;
                        mDrawableHeightRight = mDrawableHeightError;
                        break;
                }
            }
        }
    }

    Drawables mDrawables;
    private CharWrapper mCharWrapper;

    private Marquee mMarquee;
    private boolean mRestartMarquess;

    private int mMarqueeRepeatLimit = 3;
    private int mLastLayoutDirection = -1;
    private int mMarqueeFadeMode = MARQUEE_FADE_NORMAL;

    private Layout mSavedMarqueeModeLayout;
    private CharSequence mText;
    private Spannable mSpannable;
    private PrecomputedText mPrecomputed;

    private CharSequence mTransformed;
    private TextView.BufferType mBufferType = TextView.BufferType.NORMAL;

    private CharSequence mHint;
    private Layout mHintLayout;
    private MovementMethod mMovement;

    private TransformationMethod mTransformation;
    private boolean mAllowTransformationLengthChange;
    private ChangeWatcher mChangeWatcher;

    private ArrayList<TextWatcher> mListener;

    private TextPaint mTextPaint;
    private boolean mUserSetTextScaleX;
    private Layout mLayout;
    private boolean mLocalesChanged = false;

    private boolean mListenerChanged = false;
    private boolean mUseInternationalizedInput;
    boolean mUseFallbackLineSpacing;

    private int mGravity = Gravity.TOP | Gravity.START;
    private boolean mHorizontallyScrolling;

    private int mAutoLinkMask;
    private boolean mLinksClickable = true;

    private float mSpacingMult = 1.0f;
    private float mSpacingAdd = 0.0f;

    private int mBreakStrategy;
    private int mHyphenationFrequency;
    private int mJustificationMode;

    private int mMaximum = Integer.MAX_VALUE;
    private int mMaxMode = LINES;
    private int mMinmum = 0;
    private int mMinMode = LINES;

    private int mOldMaximum = mMaximum;
    private int mOldMaxMode = mMaxMode;

    private int mMaxWidth = Integer.MAX_VALUE;
    private int mMaxWidthMode = PIXELS;
    private int mMinWidth = 0;
    private int mMinWidthMode = PIXELS;

    private boolean mSingleLine;
    private int mDesiredHeightAtMeasure = -1;
    private boolean mIncludePad = true;
    private int mDeferScroll = -1;

    private Rect mTempRect;
    private long mLastScroll;
    private Scroller mScroller;
    private TextPaint mTempTextPaint;

    private BoringLayout.Metrics mBoring, mHintBoring;
    private BoringLayout mSavedLayout, mSavedHintLayout;

    private TextDirectionHeuristic mTextDir;

    private InputFilter[] mFilters = NO_FILTERS;
    private volatile Locale mCurrentSpellCheckerLocalCache;

    int mHighLightColor = 0x6633B5E5;

    private Path mHighlightPath;
    private Paint mHighLightPaint;
    private boolean mHighLightPathBogus = true;

    int mCursorDrawableRes;

    int mTextSelectHandleLeftRes;
    int mTextSelectHandleRightRes;
    int mTextSelectHandleRes;
    int mTextEditSuggestionItemLayout;
    int mTextEditSuggestionContainerLayout;
    int mTextEditSuggestionHighLightStyle;

    //todo editor
//    public Editor editor;

    private static final int DEVICE_PROVISIONED_UNKNOWN = 0;
    private static final int DEVICE_PROVISIONED_NO = 1;
    private static final int DEVICE_PROVISIONED_YES = 2;

    private int mDevicePrevisionedState = DEVICE_PROVISIONED_UNKNOWN;

    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;

    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1F;
    private int mAutoSizeTextType = AUTO_SIZE_TEXT_TYPE_NONE;
    private boolean mNeedAutoSizeText = false;
    private float mAutoSizeStepGranularityInPx = UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE;

    private float mAutoSizeMinTextSizeInPx = UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE;
    private float mAutoSizeMaxTextSizeInPx = UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE;

    private int[] mAutoSizeTextSizesInPx = new int[0];

    private boolean mHasPresetAutoSizeValues = false;
    private boolean mTextSetFormXmlOrResourceId = false;

    private int mTextId = Resources.ID_NULL;
    private CharSequence mLastValueSentToAutoFillManager;

    public static void preloadFontCache(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT);
        paint.measureText("H");
    }

    public interface OnEditorActionListener{
        boolean onEditorAction(LTextView3 v, int actionId, KeyEvent keyEvent);
    }

    public LTextView3(Context context) {
        this(context, null);
    }

    public LTextView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public LTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        if(getImportantForAutofill() == IMPORTANT_FOR_AUTOFILL_AUTO){
            setImportantForAutofill(IMPORTANT_FOR_AUTOFILL_YES);
        }

        setTextInternal("");
        final Resources res = getResources();
        //todo
//        final CompatibilityInfo compat = res.getCompatibilityInfo();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = res.getDisplayMetrics().density;

        mHighLightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mMovement = getDefaultMovementMethod();

        mTransformation = null;
        final TextApp
    }

    @Override
    public boolean onPreDraw() {
        return false;
    }

    private void setTextInternal(CharSequence text){
        mText = text;
        mSpannable = (text instanceof Spannable) ? (Spannable) text : null;
        mPrecomputed = (text instanceof PrecomputedText) ? (PrecomputedText) text : null;
    }

    protected MovementMethod getDefaultMovementMethod() {
        return null;
    }


    private static class CharWrapper implements CharSequence, GetChars{

        @Override
        public void getChars(int start, int end, char[] dest, int destoff) {

        }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public char charAt(int index) {
            return 0;
        }

        @NonNull
        @Override
        public CharSequence subSequence(int start, int end) {
            return null;
        }
    }

    private static class Marquee{

    }
    private static class ChangeWatcher{

    }
    public interface GraphicsOperations extends CharSequence{
        void drawText(Canvas c, int start, int end, float x, float y, Paint paint);
    }


}
