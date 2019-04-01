package com.example.administrator.myapplication.activity

import android.os.Bundle
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.map.MapViewManager
import kotlinx.android.synthetic.main.activity_my_order.*

class MyOrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        aMap.onCreate(savedInstanceState);
        initView()
        initData()
    }

    private fun initView() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun initData() {
        val manager = MapViewManager(aMap)
        lifecycle.addObserver(manager)
    }
}