package com.example.administrator.myapplication.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.map.MapViewManager
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlin.random.Random

class MyOrderActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        ivChange.setOnClickListener {
            val type = Random.nextInt(5)
            if (type == 0) return@setOnClickListener
            aMap.map.mapType = type
        }
    }


    private fun initData() {
        val manager = MapViewManager(aMap)
        lifecycle.addObserver(manager)
    }

}