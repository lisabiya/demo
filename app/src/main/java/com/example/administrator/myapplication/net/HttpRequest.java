package com.example.administrator.myapplication.net;


import android.content.Context;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseApplication;
import com.example.administrator.myapplication.bean.User;
import com.example.administrator.myapplication.net.core.callback.HttpCallback;
import com.example.administrator.myapplication.net.core.callback.ObservableDecorator;
import com.example.administrator.myapplication.net.core.callback.SimpleCallback;
import com.example.administrator.myapplication.utils.HYAppUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

import static com.example.administrator.myapplication.net.core.RetrofitUtil.getApiService;


/**
 */

public class HttpRequest {

    /**
     * 示例 HttpCallback
     */
    public static void loginUser(String account, String pwd, HttpCallback<String> callback) {
        Observable<String> observable = getApiService(ApiService.class).loginRequest(getHttpHeaders(), account, pwd);
        ObservableDecorator.decorateHttp(observable, String.class).subscribe(callback);
    }

    private static HashMap<String, String> getHttpHeaders() {
        Context context = BaseApplication.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("device_uuid", HYAppUtils.getMacAddress(context));
        params.put("device_model", android.os.Build.MODEL);
        params.put("device_name", android.os.Build.MODEL);
        params.put("device_os_name", "android");
        params.put("device_os_version", android.os.Build.VERSION.RELEASE);
        params.put("app_version", HYAppUtils.getAppVersionName(context));
        params.put("local_language", HYAppUtils.getAppLanguage(context));
        params.put("app_name", context.getResources().getString(R.string.app_name));
        return params;
    }


    /**
     * 直接获取String 不解析
     */
    public static void getInfo(SimpleCallback<String> subscriber) {
        Observable<String> observable = getApiService(ApiService.class).
                getInfo("http://gank.io/api/today");
        ObservableDecorator.decorate(observable)
                .subscribe(subscriber);
    }

    /**
     * 获取实体类 已解析
     */
    public static void getHappiness(SimpleCallback<String> subscriber) {
        Observable<String> observable = getApiService(ApiService.class).getInfo(String.format(Locale.CHINA,
                "http://gank.io/api/data/%%E7%%A6%%8F%%E5%%88%%A9/%d/%d", 200, 1));
        ObservableDecorator.decorateEntity(observable, String.class).subscribe(subscriber);
    }

    //获取List
    public static void getList(SimpleCallback<List<User>> subscriber) {
        Observable<String> observable = getApiService(ApiService.class).getInfo("");
        ObservableDecorator.decorateList(observable, User.class).subscribe(subscriber);
    }
}
