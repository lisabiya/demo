package com.example.administrator.myapplication.net.core;

import android.os.AsyncTask;

import com.example.administrator.myapplication.base.BaseApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Retrofit核心类
 */
public class RetrofitUtil {

    private static Retrofit retrofit;

    private static OkHttpClient httpClient;

    public static final String BASE_URL = "http://222.85.161.222:8081/mapapi/";


    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    static {
        // OkHttpClient
        Cache cache = new Cache(BaseApplication.getInstance().getCacheDir(), 10 * 1024 * 1024);
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(20, TimeUnit.MINUTES)
                .writeTimeout(20, TimeUnit.MINUTES)
                .cache(cache)
                // 统一添加的Header
                .addInterceptor(InterceptorFactory.getHeadInterceptor())
                //LOG
                .addInterceptor(logInterceptor)
                .build();

        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) // gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // rxjava 响应式编程
                .client(httpClient)
                .callbackExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .build();
    }


    public static <T> T getApiService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    private static void changeBaseUrl(String baseUrl) {
        retrofit = retrofit.newBuilder().baseUrl(baseUrl).build();
    }

    //替换默认URL
    public static <T> T getApiService(Class<T> tClass, String baseUrl) {
        changeBaseUrl(baseUrl);
        return retrofit.create(tClass);
    }
}
