package com.example.administrator.myapplication.map

import android.graphics.Color
import com.amap.api.maps.AMap
import com.amap.api.maps.model.MyLocationStyle

//地图基本初始化
fun initMap(map: AMap) {
    map.uiSettings.isRotateGesturesEnabled = false
    map.uiSettings.isZoomControlsEnabled = false
    map.uiSettings.isCompassEnabled = false
}

//初始化定位(系统自带定位)
fun initMapLocation(map: AMap) {
    val myLocationStyle = MyLocationStyle()
    myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)//定位一次，且将视角移动到地图中心点
    myLocationStyle.strokeWidth(2f)
    myLocationStyle.strokeColor(Color.parseColor("#6495ED"))
    myLocationStyle.radiusFillColor(Color.parseColor("#666495ED"))

    map.myLocationStyle = myLocationStyle//设置定位蓝点的Style
    map.uiSettings.isMyLocationButtonEnabled = true;//设置默认定位按钮是否显示，非必需设置。
    map.isMyLocationEnabled = true// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
}