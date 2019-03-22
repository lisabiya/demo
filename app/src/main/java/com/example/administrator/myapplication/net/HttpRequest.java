package com.example.administrator.myapplication.net;


import com.example.administrator.myapplication.net.rxjava.HttpCallback;
import com.example.administrator.myapplication.net.rxjava.ObservableDecorator;
import com.example.administrator.myapplication.net.rxjava.SimpleSubscriber;

import java.util.Locale;

import io.reactivex.Observable;

import static com.example.administrator.myapplication.net.RetrofitUtil.getApiService;

/**
 */

public class HttpRequest {

    /**
     * @param account
     * @param pwd
     * @param callback
     */
    public static void loginUser(String account, String pwd, HttpCallback<String> callback) {
        Observable<String> observable = getApiService().loginRequest(RetrofitUtil.getHttpHeaders(), account, pwd);
        ObservableDecorator.decorateHttp(observable, String.class).subscribe(callback);
    }


    /**
     * 获取文件地址
     *
     * @param url 文件url
     */
    public static void getDocList(String url, SimpleSubscriber<String> subscriber) {
        Observable<String> observable = RetrofitUtil.getApiService().
                getDocList(url);
        ObservableDecorator.decorate(observable).subscribe(subscriber);
    }


    /**
     * 获取每日干货
     */
    public static void getInfo(SimpleSubscriber<String> subscriber) {
        Observable<String> observable = RetrofitUtil.getApiService().
                getInfo("http://gank.io/api/today");
        ObservableDecorator.decorate(observable).subscribe(subscriber);
    }

    /**
     * 获取每日干货
     */
    public static void getHappiness(SimpleSubscriber<String> subscriber) {
        Observable<String> observable = RetrofitUtil.getApiService().
                getInfo(String.format(Locale.CHINA,
                        "http://gank.io/api/data/%%E7%%A6%%8F%%E5%%88%%A9/%d/%d", 200, 1));
        ObservableDecorator.decorate(observable).subscribe(subscriber);
    }

}
