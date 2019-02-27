package com.example.administrator.myapplication.map;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapView;
import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.myapplication.base.BaseApplication;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MapViewManager implements LifecycleObserver, AMapLocationListener {
    private MapView mMapView;
    private AMapLocation aMapLocation;
    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient;

    public MapViewManager(MapView mMapView) {
        this.mMapView = mMapView;
        MapInitKt.initMap(mMapView.getMap());
        MapInitKt.initMapLocation(mMapView.getMap());
        initLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        LogUtils.eTag("location", "onStart");
        if (locationClient != null) locationClient.startLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        LogUtils.eTag("location", "onStop");
        if (locationClient != null) locationClient.stopLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        if (locationClient != null) locationClient.startLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }


    /******************定位*******************/
    private void initLocation() {
        //初始化定位
        locationClient = new AMapLocationClient(BaseApplication.getInstance());
        //设置定位回调监听
        locationClient.setLocationListener(this);
        initLocationOption();
        LogUtils.eTag("location", "initLocation");
    }

    private void initLocationOption() {
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        // 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms
        mLocationOption.setInterval(10000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //获取一次定位结果,该方法默认为false。
//        mLocationOption.setOnceLocation(false);

        if (null != locationClient) {
            locationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            locationClient.stopLocation();
            locationClient.startLocation();
        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
        LogUtils.eTag("location", "onLocationChanged" + aMapLocation.getAddress());
    }

    public AMapLocation getLocation() {
        return aMapLocation;
    }
}
