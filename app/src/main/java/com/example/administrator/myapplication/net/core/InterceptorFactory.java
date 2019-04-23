package com.example.administrator.myapplication.net.core;

import com.example.administrator.myapplication.base.BaseApplication;
import com.example.administrator.myapplication.base.Constant;
import com.example.administrator.myapplication.utils.HYAppUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by Administrator on 2019/4/23
 * 设置okHttp拦截器
 */
class InterceptorFactory {

    static Interceptor getHeadInterceptor() {
        return getPersonHead();
    }

    //添加自定义head
    private static Interceptor getPersonHead() {
        return new Interceptor() {
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.addHeader("device_model", android.os.Build.MODEL)
                        .addHeader("device_uuid", HYAppUtils.getMacAddress(BaseApplication.getInstance()))
                        .addHeader("device_name", "")
                        .addHeader("device_os", "Android")
                        .addHeader("device_os_version", android.os.Build.VERSION.RELEASE)
                        .addHeader("device_local", BaseApplication.getInstance().getResources().getConfiguration().locale.getLanguage())
                        .addHeader("app_version", HYAppUtils.getAppVersionName(BaseApplication.getInstance()))
                        .addHeader("api_version", "1.10")
                        .addHeader("app_key", Constant.APP_KEY)
                        .addHeader("app_secret", Constant.APP_SECRET);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

}
