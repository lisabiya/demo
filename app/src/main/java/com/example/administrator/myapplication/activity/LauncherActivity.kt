package com.example.administrator.myapplication.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import com.gyf.barlibrary.ImmersionBar
import io.reactivex.Observable
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.concurrent.TimeUnit

/**
 * Create by wkx on 2019/6/15
 *
 */
class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
        relativeLayout {
            imageView(R.drawable.ic_launcher_background) {
                scaleType = ImageView.ScaleType.FIT_XY
                onClick {
                    startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
                }
            }.lparams(-1, -1)

            textView("启动页") {
                textColor = Color.WHITE
                textSize = 60f
                gravity = Gravity.CENTER
            }.lparams {
                width = -1
                height = -1
                centerInParent()
            }
        }
        load()
    }


    @SuppressLint("CheckResult")
    private fun load() {
        Observable.just("time").delay(1000, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
        }
    }
}