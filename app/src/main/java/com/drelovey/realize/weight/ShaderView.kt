package com.drelovey.realize.weight

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.drelovey.realize.R


/**
 * shader学习、练习view
 */
@Suppress("unused")
class ShaderView : View {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mWidth = 0
    private var mHeight = 0

    private var modeName = "bitmapFactory"

    private var mX = 0F
    private var mY = 0F

    private val DEFAULT_RADIUS = 50F

    private var mCurRadius = 0F

    private var mAnimator: ObjectAnimator? = null

    private var mRadialGradient: RadialGradient? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        //init()
    }

    private fun init() {
        //创建画笔
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        if (mWidth == 0) mWidth = 1080
        if (mHeight == 0) mHeight = 1920
        //bitmapFactory()
        //linearGradient()
        //sweepGradient()
        //radiusGradient()
        //composeShader()
        waterRipple()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        init()
        canvas?.drawColor(Color.GRAY)
        when (modeName) {
            "bitmapFactory" -> {
                canvas?.drawRect(0F, 0F, mWidth.toFloat(), mHeight.toFloat(), paint)
            }
            "linearGradient" -> {

            }
            "radiusGradient" -> {
                canvas?.drawCircle(
                    (mWidth / 2).toFloat(),
                    (mHeight / 2).toFloat(),
                    (mWidth / 2).toFloat(),
                    paint
                )
            }
            "waterRipple" -> {
                canvas?.drawCircle(mX, mY, mCurRadius, paint)
            }
        }

    }

    @SuppressLint("ObjectAnimatorBinding", "ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (modeName == "waterRipple") {
            if (mX != event.x || mY != event.y) {
                mX = event.x
                mY = event.y
                setRadius(DEFAULT_RADIUS)
            }
            if (event.action == MotionEvent.ACTION_DOWN) {
                //如果不返回true,后续的事件收不到
                return true
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (mAnimator != null && mAnimator!!.isRunning) {
                    mAnimator?.cancel()
                }
                if (mAnimator == null) {
                    //这里第一个对象传递当前对象，在当前对象中设置了setRadius方法，所以这里传递radius
                    //每当值变化时就会调用这个setRadius方法
                    mAnimator = ObjectAnimator.ofFloat(this, "radius", DEFAULT_RADIUS, width.toFloat())
                }
                mAnimator?.interpolator = AccelerateInterpolator()
                mAnimator?.addListener(object:Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        setRadius(0F)
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationRepeat(animation: Animator?) {

                    }
                })
                mAnimator?.start()
            }
        }

        return super.onTouchEvent(event)
    }

    //位图渲染
    private fun bitmapFactory() {
        modeName = "bitmapFactory"
        //创建bitmap
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.draw)

        /**
         * 用一张图片进行着色 参数1.bitmap:要处理的bitmap对象 2.tileX:X轴处理模式 3.tileY:Y轴处理模式
         * TileMode：拉伸模式
         * CLAMP ：拉伸最后一个像素铺满
         * MIRROR：横向纵向不足处不断翻转镜像平铺
         * REPEAT ：横向纵向不足的重复放置
         */
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR)
        //val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT)
        paint.shader = bitmapShader
    }

    //线性渲染
    private fun linearGradient() {
        // x0,y0：渐变的起点，x1,y1：渐变的终点坐标， color0,color1：起点的颜色和终点的颜色
        val linearGradient =
            LinearGradient(0F, 0F, 800F, 100F, Color.RED, Color.BLACK, Shader.TileMode.CLAMP)
        paint.shader = linearGradient


    }

    //梯度渐变，亦称扫描式渐变
    @Suppress("LocalVariableName", "SpellCheckingInspection")
    private fun sweepGradient() {
        val gradient_colors = intArrayOf(
            Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.WHITE, Color.RED
        )
        val gradient_positons = floatArrayOf(
            0.0f, 0.5f, 0.55f, 0.6f, 0.65f, 1.0f
        )
        // cx,cy：渐变效果的中心点，color0,color1表示渐变的起点色和终点色
        val sweepGradient = SweepGradient(500F, 500F, gradient_colors, gradient_positons)
        paint.shader = sweepGradient
    }

    //环形渐变/光束渲染
    private fun radiusGradient() {
        modeName = "radiusGradient"
        //centerX:圆心的X坐标,centerY:圆心的Y坐标，radius:圆的半径,centerColor:中心颜色,edgeColor:边缘颜色
        val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        val stops = floatArrayOf(0f, 0.3f, 0.7f, 1f)
//        val radialGradient = RadialGradient(
//            (mWidth / 2).toFloat(),
//            (mHeight / 2).toFloat(),
//            (mWidth / 2).toFloat(),
//            Color.GREEN,
//            Color.RED,
//            Shader.TileMode.CLAMP
//        )
//        val radialGradient =
//            RadialGradient(
//                (mWidth / 2).toFloat(),
//                (mHeight / 2).toFloat(),
//                (mWidth / 2).toFloat(),
//                colors,
//                stops,
//                Shader.TileMode.CLAMP
//            )
        val radialGradient =
            RadialGradient(
                (mWidth / 2).toFloat(),
                (mHeight / 2).toFloat(),
                200F,
                colors,
                stops,
                Shader.TileMode.REPEAT
            )
        paint.shader = radialGradient
    }

    //组合渲染
    private fun composeShader() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.draw)
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val radialGradient =
            RadialGradient(100F, 100F, 100F, Color.RED, Color.BLUE, Shader.TileMode.CLAMP)
        //混合产生新的Shader  PorterDuff.Mode
        //CLEAR 清除模式,[0, 0]，即图像中所有像素点的alpha和颜色值均为0
        //SRC [Sa, Sc],只保留源图像的 alpha 和 color ，所以绘制出来只有源图
        //DST [Da, Dc]，只保留了目标图像的alpha和color值,
        //SRC_OVER [Sa + (1 - Sa)*Da, Rc = Sc + (1 - Sa)*Dc]，在目标图像上层绘制源图像,
        //DST_OVER [Sa + (1 - Sa)*Da, Rc = Dc + (1 - Da)*Sc]，与SRC_OVER相反，此模式是目标图像被绘制在源图像的上方
        //SRC_IN [Sa * Da, Sc * Da]，在两者相交的地方绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响
        //DST_IN [Sa * Da, Sa * Dc]，可以和SRC_IN 进行类比，在两者相交的地方绘制目标图像，并且绘制的效果会受到源图像对应地方透明度的影响
        //SRC_OUT [Sa * (1 - Da), Sc * (1 - Da)]，表示如果相交处的目标色的alpha是完全不透明的，这时候源图像会完全被过滤掉，否则会受到相交处目标色 alpha 影响，呈现出对应色值。
        //DST_OUT [Da * (1 - Sa), Dc * (1 - Sa)]，可以类比SRC_OUT , 在不相交的地方绘制目标图像，相交处根据源图像alpha进行过滤，完全不透明处则完全过滤，完全透明则不过滤
        //SRC_ATOP [Da, Sc * Da + (1 - Sa) * Dc]，源图像和目标图像相交处绘制源图像，不相交的地方绘制目标图像，并且相交处的效果会受到源图像和目标图像alpha的影响
        //DST_ATOP [Sa, Sa * Dc + Sc * (1 - Da)]，源图像和目标图像相交处绘制目标图像，不相交的地方绘制源图像，并且相交处的效果会受到源图像和目标图像alpha的影响
        //XOR [Sa + Da - 2 * Sa * Da, Sc * (1 - Da) + (1 - Sa) * Dc]，在不相交的地方按原样绘制源图像和目标图像，相交的地方受到对应alpha和颜色值影响，按公式进行计算，如果都完全不透明则相交处完全不绘制
        //DARKEN [Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + min(Sc, Dc)]，该模式处理过后，会感觉效果变暗，即进行对应像素的比较，取较暗值，如果色值相同则进行混合； 从算法上看，alpha值变大，色值上如果都不透明则取较暗值，非完全不透明情况下使用上面算法进行计算，受到源图和目标图对应色值和alpha值影响。
        //LIGHTEN [Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)]，LIGHTEN 的目的则是变亮，如果在均完全不透明的情况下，色值取源色值和目标色值中的较大值，否则按上面算法进行计算。
        //SCREEN [Sa + Da - Sa * Da, Sc + Dc - Sc * Dc]，滤色，保留两个图层中较白的部分，较暗的部分被遮盖；当一层使用了滤色模式时，图层中纯黑的部分变成完全透明，纯白部分完全不透明，其他的颜色根据颜色级别产生半透明的效果。
        //ADD Saturate(S + D)，饱和度叠加
        //OVERLAY 像素是进行 Multiply （正片叠底）混合还是 Screen （屏幕）混合，取决于底层颜色，但底层颜色的高光与阴影部分的亮度细节会被保留
        val composeShader = ComposeShader(bitmapShader, radialGradient, PorterDuff.Mode.SRC)
        paint.shader = composeShader
    }

    private fun waterRipple() {
        modeName = "waterRipple"
    }


    @Suppress("SameParameterValue")
    private fun setRadius(radius: Float) {
        mCurRadius = radius
        if (mCurRadius > 0) {
            mRadialGradient =
                RadialGradient(mX, mY, mCurRadius, Color.parseColor("#FFFFFF"),  Color.parseColor("#58FAAC"), Shader.TileMode.CLAMP)
            paint.shader = mRadialGradient
        }
        invalidate()
    }
}