package com.example.administrator.myapplication.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.adapter.SystemInfoAdapter
import com.example.administrator.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_order.*

class MyOrderActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        initView()
        initData()
    }

    private fun initView() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun initData() {
        val list = ArrayList<String>()
        for (index in 1..10) {
            list.add("订单$index")
        }
        rvMyOrder.layoutManager = LinearLayoutManager(this)
        rvMyOrder.adapter = SystemInfoAdapter(this, list, SystemInfoAdapter.OnItemClickListener {
            ToastUtils.showShort(list[it])
        })
    }
}