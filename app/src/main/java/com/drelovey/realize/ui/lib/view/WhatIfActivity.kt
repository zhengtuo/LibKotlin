package com.drelovey.realize.ui.lib.view

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.activity.BaseActivity
import com.drelovey.realize.R
import com.drelovey.realize.arouter.RouterPath
import com.drelovey.realize.databinding.ActivityWhatifBinding
import com.drelovey.realize.ui.lib.viewModel.WhatIfVM
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.showAlignTop
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfMap
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//ConstraintLayout使用详解
@Route(path = RouterPath.PATH_CONSTRAINT)
@AndroidEntryPoint
class WhatIfActivity : BaseActivity<ActivityWhatifBinding, WhatIfVM>(R.layout.activity_whatif) {

    val nullableBoolean: Boolean = true
    val nullableBoolean2: Boolean = true

    var nullableString: String? = null

    override fun initialization() {
        binding {
            lifecycleOwner = this@WhatIfActivity
        }

        // example0 : nullable Boolean true-false check.
        whatIf(nullableBoolean) {
            Timber.d("whatif %b", nullableBoolean)
        }

        // example1 : nullable Boolean true-false check extension.
        nullableBoolean.whatIf {
            Timber.d("whatif %b", nullableBoolean)
        }

        nullableBoolean2.whatIf {
            Timber.d("whatIf %b", nullableBoolean2)
        }

        // example2 : nullable Boolean true-false check extension with whatIfNot
        nullableBoolean.whatIf(
            whatIf = {
                Timber.d("not-null and true : $nullableBoolean")
            },
            whatIfNot = {
                Timber.d("null or false : $nullableBoolean")
            }
        )

        // example3 : nullable String true-false check extension with default value.
        nullableString = nullableBoolean.whatIfMap(nullableBoolean, "null") {
            "notNull"
        }
        // example4 : nullable any type null check extension.
        nullableString.whatIfNotNull {
            Timber.d("$it is not null.")
        }

        // example5 : nullable any type null check extension.
        val newString = nullableString.whatIfMap(
            given = nullableString!!.length > 3,
            whatIf = {
                Timber.d("$it is long.")
                "long"
            },
            whatIfNot = {
                Timber.d("$it is short.")
                "short"
            }
        )

        // example6 : what-if check in the builder pattern.
        val balloon = Balloon.Builder(this)
            .setArrowSize(10)
            .setArrowVisible(true)
            .whatIf(nullableBoolean) { setTextColor(Color.YELLOW) }
            .whatIf(nullableBoolean, { setText("Hello, whatIf") }, { setText("Good-Bye whatIf") })
            .setWidthRatio(1.0f)
            .setMargin(12)
            .setPadding(12)
            .setTextSize(15f)
            .setArrowPosition(0.5f)
            .setCornerRadius(4f)
            .setAlpha(0.9f)
            .setBackgroundColor(ContextCompat.getColor(baseContext, R.color.color_f))
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setLifecycleOwner(this@WhatIfActivity)
            .build()

        binding.button.showAlignTop(balloon)

    }
}