package com.example.administrator.myapplication.net.core.callback;


import android.text.TextUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 通用订阅者,用于统一处理回调
 */
public class SimpleCallback<T> implements Observer<T> {

    private String tag;

    protected SimpleCallback() {
    }

    public SimpleCallback(String tag) {
        this.tag = tag;
    }

    @Override
    public void onError(Throwable throwable) {
        // 统一处理错误回调，显示Toast
//        String errorInfo = ErrorInfoUtils.parseHttpErrorInfo(throwable);
        throwable.printStackTrace();
        onComplete();
    }

    @Override
    public void onComplete() {

    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(tag)) {
            DisposableManager.addDisposable(tag, d);
        }
    }

    @Override
    public void onNext(T t) {
        onComplete();
    }


}
