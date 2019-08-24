package com.example.administrator.myapplication.activity

import android.os.Bundle
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity


class MyVerifiedActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_one)
        initView()
        initData()
    }

    private fun initView() {
    }


    private fun initData() {
    }

    override fun onResume() {
        super.onResume()
    }

}