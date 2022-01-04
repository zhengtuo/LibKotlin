package com.drelovey.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drelovey.learn.ui.learn.view.LearnFragment

/**
 * 单独测试Activity
 */
class LearnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root, LearnFragment()).commit()
    }
}