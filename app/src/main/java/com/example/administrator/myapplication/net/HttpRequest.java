package com.example.administrator.myapplication.net;



import com.example.administrator.myapplication.net.rxjava.HttpCallback;
import com.example.administrator.myapplication.net.rxjava.ObservableDecorator;
import com.example.administrator.myapplication.net.rxjava.SimpleSubscriber;

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

}
