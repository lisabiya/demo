package com.example.administrator.myapplication.map;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.myapplication.activity.MapActivityIml;
import com.example.administrator.myapplication.base.BaseApplication;

public class MapViewManager implements LifecycleObserver, AMapLocationListener, MapActivityIml {
    private MapView mMapView;
    private AMapLocation aMapLocation;
    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient;

    public MapViewManager(MapView mMapView) {
        this.mMapView = mMapView;
        MapInitKt.initMap(mMapView.getMap());
        requestLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        MapDrawUtil.onDestroy();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        if (locationClient != null) locationClient.startLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
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
    private void requestLocation() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        initLocation();
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("定位失败");
                    }
                }).request();
    }

    private void initLocation() {
        //初始化定位
        locationClient = new AMapLocationClient(BaseApplication.getInstance());
        //设置定位回调监听
        locationClient.setLocationListener(this);
        initLocationOption();
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
        mLocationOption.setOnceLocation(true);

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
        location();
    }

    /**
     * 获取定位信息
     *
     * @return 高德定位信息
     */
    public AMapLocation getLocation() {
        return aMapLocation;
    }


    /******************地图操作*******************/
    public MapActivityIml inject() {
        return this;
    }


    @Override
    public void location() {
        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
        if (aMapLocation == null) {
            return;
        }
        LatLng position = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        CameraUpdate mCameraUpdate =
                CameraUpdateFactory.newCameraPosition(
                        new CameraPosition(position, 17, 0, 0));
        mMapView.getMap().moveCamera(mCameraUpdate);
        //绘制点
        MarkerOptions options = new MarkerOptions();
        mMapView.getMap().addMarker(options);

//        Marker marker = mMapView.getMap()
//                .addMarker(new MarkerOptions().position(position)
////                        .title("默认点标记").snippet("DefaultMarker")
//                );
        MapDrawUtil.drawMark(mMapView.getMap(), position);
    }

    @Override
    public void clear() {
        MapDrawUtil.clearMark();
    }


}
