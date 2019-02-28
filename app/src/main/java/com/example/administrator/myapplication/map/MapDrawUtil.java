package com.example.administrator.myapplication.map;

import android.graphics.BitmapFactory;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class MapDrawUtil {
    public static HashMap<String, Marker> markerHashMap = new HashMap<>();


    public static void drawMark(AMap map, LatLng position) {
        clearMark("LocationMark");
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(position);
        markerOption.title("测试点").snippet("自定义Mark");
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_blue_anchor));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Marker marker = map.addMarker(markerOption);

        markerHashMap.put("LocationMark", marker);
    }

    //删除标记点
    public static void clearMark() {
        clearMark("LocationMark");
    }

    public static void clearMark(String key) {
        Marker marker = markerHashMap.get(key);
        if (marker != null) marker.remove();
    }

    public static void onDestroy() {
        for (Map.Entry<String, Marker> marker : markerHashMap.entrySet()) {
            marker.getValue().destroy();
        }
    }
}
