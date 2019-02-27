package com.example.administrator.myapplication.net.rxjava;


import android.content.Context;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 通用订阅者,用于统一处理回调
 */
public class SimpleSubscriber<T> implements Observer<T> {

    private Context context;

    public SimpleSubscriber(Context context) {
        this.context = context;
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

    }

    @Override
    public void onNext(T t) {
        // sub
        onComplete();
    }


}
