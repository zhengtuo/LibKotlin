package com.drelovey.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drelovey.learn.ui.view.LearnFragment

/**
 * 单独测试Activity
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root, LearnFragment()).commit()
    }
}