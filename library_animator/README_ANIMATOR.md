# 动画

## 逐帧动画 frame animation
    多张图片，逐个播放；每张时间可控；可循环播放；
## 补间动画 tween animation
    对View进行一系列的动画操作，包括淡入淡出、缩放、平移、旋转四种。
    AlphaAnimation, 透明度渐变效果[0,1]。可设开始和结束透明度；持续时间;
    ScaleAnimation, 缩放渐变效果。可设开始及结束的缩放比；缩放参考点；持续时间；
    TranslateAnimation, 位移渐变效果。可设开始及结束位置；持续时间；
    RotateAnimation, 旋转效果。可设开始及结束的旋转角度；可设置轴心；持续时间；
    AnimationSet: 组件渐变，前面的多种组合；

    Interpolator, 动画速度控制，动画渲染器。
        LinearInterpolator; 匀速动画
        AccelerateInterpolator; 加速动画
        AccelerateDecelerateInterpolator; 加减速动画
        CycleInterpolator; 动画循环，速度按正弦曲线变化：Math.sin( 2 * cycleTimes * Math.PI * input)
        DecelerateInterpolator; 减速动画
        AnticipateInterpolator; 反加速动画
        AnticipateOvershootInterpolator; 回甩动画
        BounceInterpolator; 跳跃动画
        OvershootInterpolator; 回弹动画

## 属性动画 property animation
    一种不断地对值进行操作，并将值复制到指定对象的指定属性上。
    补间动画针对的对象是UI控件，只改变View的显示效果，不改变View的属性。
    Animator {ValueAnimator, ObjectAnimator, AnimationSet , AnimatorSet, Evaluator}

    Animator 针对视图属性的动画实现，动画被应用时对象属性产生变化。
    Animation 针对视图外观的动画实现，动画被应用时外观改变但视图的触发点不会变化，还是在原来的定义位置。
    AnimationSet (Animation)，动画并行开始，startAnimation();
    AnimatorSet (Animator) ,调用play(),before(),after()等方法设置动画执行顺序，start()开始。
    ValueAnimator, 负责 初始值和结束值间的过度动画，内部采用一种时间循环的机制
    计算值与值之间的动画过度，能自动完成从初始值平滑过度到结束值的效果。能设置动画
    播放次数，播放模式，以及动画设置监听器等！

    ObjectAnimator, 实际使用较多，允许对指定对象的属性执行动画。

    ValueAnimator 手动赋值，间接操作属性。 ObjectAnimator 自动复制，直接操作属性

    AnimationSet ,组合多个Animator, 并制定多个Animator 按照次序播放或者同时播放。

    IntEvaluator, 用于计算int类型属性值的计算器。
        FloatEvaluator, ArgbEvaluator,TypeEvaluator;


## 过度动画 transition animation
    实现Activity或View过度动画效果。
## 应用场景，View销毁及创建及其与Activity的关系；


todo 每一个属性值对应的意义
todo view的生命周期
todo 常用的类 AnimationSet AnimatorSet
todo https://blog.csdn.net/tianjian4592/article/details/44155147

参考：
http://t.zoukankan.com/tonny-li-p-4174437.html
https://www.runoob.com/w3cnote/android-tutorial-valueanimator.html
https://blog.csdn.net/Vaccae/article/details/111595974
https://blog.csdn.net/weixin_45558166/article/details/110310651
https://blog.csdn.net/tianjian4592/article/details/44155147

https://zhuanlan.zhihu.com/p/532425900