package com.drelovey.practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drelovey.practice.ui.view.PracticeFragment

/**
 * 单独运行
 */
class PracticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root, PracticeFragment()).commit()
    }
}