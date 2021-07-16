package com.drelovey.realize.ui.lib.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.realize.R
import com.drelovey.provider.router.RouterPath
import com.drelovey.realize.databinding.ActivityDrawableDslBinding
import com.drelovey.realize.ui.lib.viewModel.DrawableDslVM
import com.github.forjrking.drawable.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

//WhatIf使用详解
@Route(path = RouterPath.PATH_DRAWABLE_DSL)
@AndroidEntryPoint
class DrawableDslActivity : BaseActivity<ActivityDrawableDslBinding, DrawableDslVM>(R.layout.activity_drawable_dsl) {


    override fun initialization() {
        binding {
            lifecycleOwner = this@DrawableDslActivity
            vm = mViewModel
        }

        binding.linear.dividerDrawable = shapeDrawable {
            shape(Shape.RECTANGLE)
            solid("#84232323")
            size(-2f, 1f)
        }

        binding.iv1.background = shapeDrawable {
            shape(Shape.RECTANGLE)
            solid("#ABE2E3")
            stroke(android.R.color.white, 2f, 5f, 8f)
        }

        binding.iv2.src = shapeDrawable {
            shape(Shape.OVAL)
            solid(android.R.color.transparent)
            stroke(android.R.color.black, 12f)
            size(200f, 200f,isDp = false)
        }

//     点击效果等
        binding.iv3 src selectorDrawable {
            normal = shapeDrawable {
                corner(20f)
                gradient(90, R.color.color_f97794, R.color.color_623aa2)
            }
            pressed = shapeDrawable {
                corner(20f)
                solid("#84232323")
            }
        }

        //添加padding
        binding.iv4 src insetDrawable {
            drawable = shapeDrawable {
                corner(10f)
                solid("#0396FF")
            }
            setInset(10)
        }
        binding.iv3.setOnClickListener {
            Thread {
                saveBitmap(
                    BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                    File(filesDir, "img.png").absolutePath
                )
            }.start()
        }

        binding.iv5 src resourceDrawable(R.mipmap.ic_launcher_round)

        binding.iv6 src layerDrawable {
            addLayer(shapeDrawable {
                solid("#ffff00")
            })
            addLayer(resourceDrawable(R.mipmap.ic_launcher))
        }

    }

    private fun saveBitmap(bitmap: Bitmap, path: String?) {
        try {
            val filePic = File(path)
            if (!filePic.exists()) {
                filePic.parentFile.mkdirs()
                filePic.createNewFile()
            }
            val fos = FileOutputStream(filePic)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
            Log.i("TAG", "saveBitmap: ")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}