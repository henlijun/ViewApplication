##自绘控件

1. 测试View自会
    学习：https://blog.csdn.net/sinat_27154507/article/details/79748010
    1. measure
    2. layout
    3. draw
    Activity(window-> 
             PhoneWindow->
             DecorView extend FrameLatout[View + LineaLayout] + ViewRootImpl[window与DecorView的联系])
    View测量流程：ViewGroup 测量 View，再ViewGroup
    View-Mode:
         Mode=EXACTLY :精准模式 match_parent
         Mode=AT_MOST :最大模式 wrap_content
         Mode=UNSPECIFIED :无限制 （一般系统内部使用）
    ViewGroup-Mode:
        Mode=EXACTLY :ViewGroup的Size是确定的，View：Match_parent, wrap_content,value
        Mode=AT_MOST :ViewGroup的Size是不确定的，View:match_parent,wrap_content,value = size
        Mode=UNSPECIFIED :
    学习：https://www.jianshu.com/p/705a6cb6bfee
    1. 自定义属性：编写values/attrs.xml -> styleable;item,通过typedArray获取
    2. 坐标远点：左上角  |y, --x
    3. canvas
        canvas 画布， 绘制坐标系， paint
        canvas: translate, rotate, scale, skew(扭曲)
    4. colorFilter
    5. Point
    6. RectF
    7. arcs
    8. arge 、color
    9. bitmap
    10. circle oval
    11. line
    12 Rect
    13. RoundRect
    14. vertices
    15. path
2. 常见View自绘
3. 常见Layout自会
4. 图表数据刷新动效，渐变色，动效，多色等  


1. 自定义  
   View | SurfView: onMeasure onDraw 控件  
   ViewGroup | Layout: onMeasure onLayout 组件

   教程 FrameLayout  
   onMeasure  
   度量子View
   度量最大子View 的宽高
   度量自己
   onLayout


     `自定义View onMeasure measureSpec
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
    `