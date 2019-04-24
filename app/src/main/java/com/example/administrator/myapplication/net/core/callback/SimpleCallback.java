package com.example.administrator.myapplication.net.core.callback;


import android.text.TextUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.example.administrator.myapplication.net.core.callback.HttpCallback.parseHttpErrorInfo;

/**
 * 通用订阅者,用于统一处理回调
 */
public abstract class SimpleCallback<T> implements Observer<T> {

    private String tag;


    /**
     * @param tag 网络请求标识
     */
    public SimpleCallback(String tag) {
        this.tag = tag;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(tag)) {
            DisposableManager.addDisposable(tag, d);
        }
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            onFailed(400, "数据获取失败");
        } else {
            onSuccess(t);
        }
        onFinish();
    }


    @Override
    public void onError(Throwable throwable) {
        String errorInfo = parseHttpErrorInfo(throwable);
        onFailed(404, errorInfo);
        onFinish();
    }

    @Override
    public void onComplete() {
    }


    /*******************对外方法**********************/
    public abstract void onSuccess(T t);

    public abstract void onFailed(int code, String message);

    public void onFinish() {

    }

}
