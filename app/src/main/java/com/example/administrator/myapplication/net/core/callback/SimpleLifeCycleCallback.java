package com.example.administrator.myapplication.net.core.callback;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.example.administrator.myapplication.net.core.callback.HttpCallback.parseHttpErrorInfo;

/**
 * 带有生命周期的订阅者,ON_DESTROY时自动取消网络请求
 */
public abstract class SimpleLifeCycleCallback<T> implements Observer<T>, LifecycleObserver {
    private Disposable disposable;

    /**
     * @param lifecycle 生命周期
     */
    protected SimpleLifeCycleCallback(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
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

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }


    /*******************对外方法**********************/
    public abstract void onSuccess(T t);

    public abstract void onFailed(int code, String message);

    public void onFinish() {
        onDestroy();
    }

}
