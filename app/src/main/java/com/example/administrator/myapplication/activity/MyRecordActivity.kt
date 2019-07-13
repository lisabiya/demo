package com.example.administrator.myapplication.activity

import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.bean.Order
import com.example.administrator.myapplication.databinding.ActivityMyorderBinding
import kotlinx.android.synthetic.main.wechat_layout.*


class MyRecordActivity : BaseActivity() {

    lateinit var binding: ActivityMyorderBinding
    var order = Order("标题")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myorder)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myorder)
        initView()
        initData()
    }

    private fun initView() {
    }


    private fun initData() {
        binding.order = order

        tv1.setTextNull("")
    }

    private fun TextView.setTextNull(text: String?) {
        if (text != null && text != "") {
            this.text = text
        } else {
            this.text = "暂无数据"
        }
    }

}