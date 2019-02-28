package com.example.administrator.myapplication.map;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.route.RouteSearch;
import com.example.administrator.myapplication.base.BaseApplication;

public class RouteQueryUtil {


    public static void driveRouteQuery(LatLng fromPosition, LatLng toPosition,
                                       RouteSearch.OnRouteSearchListener routeSearchListener) {
        RouteSearch routeSearch = new RouteSearch(BaseApplication.getInstance());
        routeSearch.setRouteSearchListener(routeSearchListener);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo();

        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo,
                RouteSearch.DRIVING_SINGLE_DEFAULT, null, null, "");
    }

}
