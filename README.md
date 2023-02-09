## 2022-02-27
1. 自定义  
    View | SurfView: onMeasure onDraw 控件  
    ViewGroup | Layout: onMeasure onLayout 组件  

    教程 FrameLayout  
        onMeasure  
            度量子View
                度量最大子View 的宽高
            度量自己
        onLayout


     自定义View onMeasure measureSpec
     ScrollView + TextView -----MeasureSPec.UNSPECIFIED
     if(EXACTLY){
        parentView -使确定
     }else{
        View 自动实际度量
     }
    /**
     * View = wrap_content ----> MeasureSpec_AT_MOST
     * View = match_parent ----> MeasureSpec_Exactly
     * View = 100dp -----> Measure_Exactly
     * @param measureSpec
     * @return
     */
    //measureSpec 参考 parentView
    private int getMeasureSize(int measureSpec){
        int pSize = MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "getMeasureSize: " + pSize);
        int pMode = MeasureSpec.getMode(measureSpec);
        int size = 0;

        switch (pMode){
            case MeasureSpec.UNSPECIFIED:
                //系统级别的，ScrollerView, ListView
                /**
                 * ScrollView
                 *    <ScrollView
                 *         android:layout_width="match_parent"
                 *         android:layout_height="wrap_content">
                 *
                 *         <com.lj.module_huizhi.view.LConfView
                 *             android:layout_width="match_parent"
                 *             android:layout_height="match_parent"/>
                 *     </ScrollView>
                 *
                 *     measureSpec 指parentView 可见长度， view的长度超出后，依然能够滑动，长度确实有效
                 */
                Log.d(TAG, "getMeasureMode: UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                //自适应, wrap_content 自定度量，（绘制范围，点击范围...)
                Log.d(TAG, "getMeasureMode: AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                //match_parent 100dp
                Log.d(TAG, "getMeasureMode: EXACTLY");
                size = pSize;
                break;
            default:
        }
        return size;
    }
    
    
Dialog : 继承Object, 可异步调用，不阻塞ui线程
            1. Object
            2. Dialog
            3.1 AlterDialog
                3.1.1 DatePickerDialog
                3.1.2 ProgressDialog
                3.1.3 TimePickerDialog
            3.2 CharacterPickerDialog
            
DialogFragment  
    归属于 FragmentManager 管理，返回键等
    
        <style name="Dialog.Base" parent="Theme.AppCompat.Dialog">
            <!-- windowIsFloating false-> windowWrapContent（不能） true(default)->windowMatchParent（可被软键盘顶起）-->
            <item name="android:windowIsFloating">false</item>
            <item name="android:windowBackground">@android:color/transparent</item>
            <item name="android:gravity">center_vertical</item>
        </style>
        windowIsFloating 
                    false wrapContent
        default    true matchParent 可被软键盘顶起
        
        parent
            <style name="Base.V7.Theme.AppCompat.Dialog" parent="Base.Theme.AppCompat">
                <item name="android:colorBackground">?attr/colorBackgroundFloating</item>
                <item name="android:colorBackgroundCacheHint">@null</item>
        
                <item name="android:windowFrame">@null</item>
                <item name="android:windowTitleStyle">@style/RtlOverlay.DialogWindowTitle.AppCompat</item>
                <item name="android:windowTitleBackgroundStyle">@style/Base.DialogWindowTitleBackground.AppCompat</item>
                <item name="android:windowBackground">@drawable/abc_dialog_material_background</item>
                <item name="android:windowIsFloating">true</item>
                <item name="android:backgroundDimEnabled">true</item>
                <item name="android:windowContentOverlay">@null</item>
                <item name="android:windowAnimationStyle">@style/Animation.AppCompat.Dialog</item>
                <item name="android:windowSoftInputMode">stateUnspecified|adjustPan</item>
        
                <item name="windowActionBar">false</item>
                <item name="windowActionModeOverlay">true</item>
        
                <item name="listPreferredItemPaddingLeft">24dip</item>
                <item name="listPreferredItemPaddingRight">24dip</item>
        
                <item name="android:listDivider">@null</item>
        
                <item name="android:buttonBarStyle">@style/Widget.AppCompat.ButtonBar.AlertDialog</item>
                <item name="android:borderlessButtonStyle">@style/Widget.AppCompat.Button.Borderless</item>
                <item name="android:windowCloseOnTouchOutside">true</item>
            </style>

        需求：可被顶起（有输入）
             width MatchParent
             height wrapContent
             gravity center or bottom
             corner
             sure/cancel login/register choice list 
        结论：
            windowIsFloating true
            width MatchParent
            height wrapContent
            backgroundColor android:color/transparent
            viewBackgroundColor drawableCorner
            
            sure/cancel
            login
            choice
            list
todo 弹框，自定义 滑动，循环 解析 recycleView