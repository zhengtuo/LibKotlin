package com.drelovey.common.binding.viewadapter.view

import android.annotation.SuppressLint
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.common.binding.listener.BindingClickT
import com.drelovey.common.binding.listener.BindingCommand
import com.drelovey.common.binding.listener.CommonBinding
import com.drelovey.common.generated.callback.OnClickListener
import com.drelovey.common.utils.launch
import com.skydoves.whatif.whatIfMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


/**
 * @Author: Drelovey
 * @CreateDate: 2020/5/14 14:52
 */
object ViewAdapter {

    //防重复点击间隔(秒)
    const val CLICK_INTERVAL = 5000L

    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter(
        value = ["onClickCommand", "viewModel", "isDelayed", "interval"],
        requireAll = false
    )
    fun onClickCommand(
        view: View,
        clickCommand: BindingCommand<*>?,
        viewModel: BaseViewModel?,
        isDelayed: Boolean,
        interval: Long
    ) {
        viewModel?.launch({
            var job = launch({
                clickCommand?.click()
                delay(5000L)
            })
            view.setOnClickListener {


            }

        })
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
    @BindingAdapter(value = ["method", "viewModel"], requireAll = true)
    fun xmlClick(view: View?, method: (view: View) -> Unit = {}, viewModel: BaseViewModel) {
        view?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    //利用反射通过方法名获取method,再使用invoke进行方法的调用

                    viewModel.launch(
                        {
                            method.invoke(view)
                        }
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
}