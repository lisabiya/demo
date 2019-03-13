package com.example.administrator.myapplication.activity

import android.os.Bundle
import com.blankj.utilcode.util.BarUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_order.*

class MyPurseActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarLightMode(window, true)
        setContentView(R.layout.activity_my_purse)
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
}