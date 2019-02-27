package com.example.administrator.myapplication.net.rxjava;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.myapplication.net.BaseResponse;
import com.example.administrator.myapplication.net.ErrorInfoUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public abstract class HttpCallback<T> implements Observer<BaseResponse> {

    private Context mContext;
    private Disposable disposable;

    protected HttpCallback(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(BaseResponse response) {
        if (response != null && response.isSuccess()) {
            if (response.getObject() != null) {
                onSuccess(response, (T) response.getObject());
            } else if (response.getList() != null) {
                onSuccess(response, (T) response.getList());
            } else {
                onSuccess(response, null);
            }
        } else {
            onFailed(response.getStatus(), response.getMsg());
        }
        onFinish();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        // 统一处理错误回调，显示Toast
        String errorInfo = ErrorInfoUtils.parseHttpErrorInfo(throwable);
        Log.e("ErrorInfo", "错误信息:" + errorInfo);
        ToastUtils.showLong(errorInfo);
        onFinish();
        throwable.printStackTrace();
    }


    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(BaseResponse response, T t);

    public void onFailed(int code, String message) {
        onFinish();
    }

    public abstract void onFinish();

}
