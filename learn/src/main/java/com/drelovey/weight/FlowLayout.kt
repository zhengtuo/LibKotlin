package com.drelovey.weight

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class FlowLayout : ViewGroup {

    val lineHeight: ArrayList<Int> = ArrayList()
    val lineView: ArrayList<List<View>> = ArrayList()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    //自主测量
    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        lineHeight.clear()
        lineView.clear()
        //获取parent宽高
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //获取parent模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var currentWidth = 0
        var currentHeight = 0

        var lineW = 0
        var lineH = 0

        //宽高为parent宽高
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            currentWidth = widthSize
            currentHeight = heightSize
        } else {

            var views: ArrayList<View> = ArrayList()

            for (index in 0 until childCount) {
                val child = getChildAt(index)

                //测量child
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                //child.measure(widthMeasureSpec, heightMeasureSpec)
                val params = child.layoutParams as MarginLayoutParams
                //child的宽度为测量的宽度加两边的margin
                val childWidth = child.measuredWidth + params.leftMargin + params.rightMargin
                val childHeight = child.measuredHeight + params.topMargin + params.bottomMargin
                //判断是否需要换行
                if (childWidth + lineW > widthSize) {
                    currentHeight += lineH
                    currentWidth = Math.max(currentWidth, lineW)

                    lineHeight.add(lineH)
                    lineView.add(views)
                    views = ArrayList()
                    //换行
                    lineW = childWidth
                    lineH = childHeight
                    views.add(child)
                } else {
                    //不换行
                    lineW += childWidth
                    //判断大小，取本行最大
                    lineH = lineH.coerceAtLeast(childHeight)
                    views.add(child)
                    if (index == childCount-1) {
                        currentHeight += lineH
                        currentWidth = Math.max(currentWidth, lineW)
                        lineHeight.add(lineH)
                        lineView.add(views)
                    }

                }

            }
        }

        setMeasuredDimension(currentWidth, currentHeight)
    }

    //自主摆放
    @Suppress("UNUSED_VARIABLE")
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        val right = 0
        var top = 0
        val bottom = 0
        for (i in lineView.indices) {
            val views = lineView[i]
            for (j in views.indices) {
                val view = views[j]
                val params = view.layoutParams as MarginLayoutParams
                val viewLeft = left + params.leftMargin
                val viewTop = top + params.topMargin
                val viewBottom = viewTop + view.measuredHeight
                val viewRight = viewLeft + view.measuredWidth
                //摆放
                view.layout(viewLeft, viewTop, viewRight, viewBottom)
                left += viewRight
            }
            top += lineHeight[i]
            left = 0
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}