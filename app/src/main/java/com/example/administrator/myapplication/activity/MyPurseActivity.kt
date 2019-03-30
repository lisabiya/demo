package com.example.administrator.myapplication.activity

import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.annotation.RequiresApi
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_my_order.*


class MyPurseActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        setContentView(R.layout.activity_my_purse)
        initTransaction()
        initView()
        initData()
    }

    private fun initView() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initData() {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initTransaction() {
        val slide = Slide(Gravity.START)
        slide.duration = 300
        window.enterTransition = slide

        val slide2 = Slide(Gravity.END)
        slide2.duration = 300
        window.returnTransition = slide2
    }
}