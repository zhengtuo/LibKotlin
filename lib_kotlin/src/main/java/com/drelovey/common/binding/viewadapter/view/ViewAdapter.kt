package com.drelovey.common.binding.viewadapter.view

import android.annotation.SuppressLint
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.drelovey.common.binding.listener.BindingCommand

/**
 * @Author: Drelovey
 * @CreateDate: 2020/5/14 14:52
 */
object ViewAdapter {

    //防重复点击间隔(秒)
    const val CLICK_INTERVAL = 1

    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter(value = ["onClickCommand", "isThrottle", "interval"], requireAll = false)
    fun onClickCommand(
        view: View,
        clickCommand: BindingCommand<*>?,
        isThrottle: Boolean,
        interval: Long
    ) {
        view.setOnClickListener {
            clickCommand?.execute()
        }
    }

    /**
     * view的显示隐藏
     */
    @JvmStatic
    @BindingAdapter(value = ["isVisible"], requireAll = false)
    fun isVisible(view: View, visibility: Boolean?) {
        if (visibility != null && visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["marginTop"])
    fun setMargin(view: View?, margin: Int) {
        if (view != null) {
            try {
                val layoutParams =
                    view.layoutParams as ConstraintLayout.LayoutParams
                //layoutParams.topMargin = ScreenUtils.dip2px(margin)
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("invisible")
    fun visibleInvisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE


    }

    @Suppress("ObjectLiteralToLambda")
    @JvmStatic
    @BindingAdapter(value = ["xmlClick"])
    fun xmlClick(view: View?, method: String) {
//        view?.setOnClickListener(object : View.OnClickListener {
//            @Except
//            override fun onClick(v: View?) {
//                try {
//                    //利用反射通过方法名获取method,再使用invoke进行方法的调用
//                    ReflectionUtils.resolveMethod(view.context, method)?.invoke(view.context, view)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//
//        })
    }
}