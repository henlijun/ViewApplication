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
   4. 自定义属性：编写values/attrs.xml -> styleable;item,通过typedArray获取
   5. 坐标远点：左上角  |y, --x
   6. canvas
      canvas 画布， 绘制坐标系， paint
      canvas: translate, rotate, scale, skew(扭曲)
   7. colorFilter
   8. Point
   9. RectF
   10. arcs
   11. arge 、color
   12. bitmap
   13. circle oval
   14. line
       12 Rect
   15. RoundRect
   16. vertices
   17. path
2. 常见View自绘
3. 常见Layout自会
4. 图表数据刷新动效，渐变色，动效，多色等

## Activity & Fragment & View 生命周期

### Activity

> onCreate()
>
> onStart()
> onResume()
> onPause()
>
> onStop()
> onDestroy()

### Fragment

> onAttach()
> onCreate()
> onCreateView()
> onActivityCreated()
>
> onStart()
> onResume()
> onPause
>
> onDestroyView()
> onDestroy()
> onDetach()

### lifecycleState

> initialized
> created
> started
> resumed
> destroyed

### View

> onFinishInflate()
> onMeasure()
> onLayout()
> onSizeChanged()
> onDraw()
> onKeyDown()
> onKeyUp()
> onTrackballEvent()
> onTouchEvent()
> onFocusChanged()
> onWindowFocusChanged()
> onAttachedToWindow()
> onDetachedFromWindow()
> onWindowVisibilityChanged()

#### Activity & View

##### Activity从xml文件中加载View


| Activity | View              |
| ---------- | ------------------- |
| onCreate | Constructors    |
|          | onFinishInflate |
| onStart |     |
| onResume | onAttachedToWindow |
|           | onWindowVisibilityChanged |

##### Activity通过addView 动态加载View

##### Activity & View， focus 变化

##### Activity & View， key 变化

##### Activity & View， touch 变化

##### Activity被finish时View变化

##### Activity 到后台时View的变化

##### Activity ,restart 到后台时View的变化
