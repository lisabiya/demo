package com.example.administrator.myapplication

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.example.administrator.myapplication.map.MapViewManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BarUtils.setNavBarColor(window, resources.getColor(R.color.cornflowerblue))
            BarUtils.setStatusBarColor(this, resources.getColor(R.color.cornflowerblue))
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        initView()
        initData()
    }


    private fun initData() {
        lifecycle.addObserver(MapViewManager(mMapView))
    }


    private fun initView() {
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState)
    }

}
