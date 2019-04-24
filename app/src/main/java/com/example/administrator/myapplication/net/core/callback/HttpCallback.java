package com.example.administrator.myapplication.net.core.callback;

import android.text.TextUtils;

import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 标准已解析数据回调
 * 🐵 需要接口返回值格式与 BaseResponse参数一致
 * 🐵 子线程已解析数据，性能优化
 */
public abstract class HttpCallback<T> implements Observer<BaseResponse<T>> {

    private String tag;


    protected HttpCallback(String tag) {
        this.tag = tag;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(tag)) {
            DisposableManager.addDisposable(tag, d);
        }
    }



    @Override
    @SuppressWarnings("unchecked")
    public void onNext(BaseResponse<T> response) {
        if (response != null && response.isSuccess()) {
            if (response.getObject() != null) {
                onSuccess(response, response.getObject());
            } else if (response.getList() != null) {
                onSuccess(response, (T) response.getList());
            } else {
                onSuccess(response, null);
            }
        } else {
            if (response != null) {
                onFailed(response.getStatus(), response.getMsg());
            } else {
                onFailed(400, "数据获取失败");
            }
        }
        onFinish();
    }

    @Override
    public void onError(Throwable throwable) {
        String errorInfo = parseHttpErrorInfo(throwable);
        onFailed(404, errorInfo);
        onFinish();
    }

    /**
     * 解析服务器错误信息
     */
    static String parseHttpErrorInfo(Throwable throwable) {
        String errorInfo = throwable.getMessage();
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            errorInfo = exception.message();
        } else {
            if (throwable instanceof UnknownHostException) {
                errorInfo = "无法连接到服务器";
            }
        }

        return errorInfo;
    }

    @Override
    public void onComplete() {
    }

    /*******************对外方法**********************/
    public abstract void onSuccess(BaseResponse response, T t);

    public abstract void onFailed(int code, String message);

    public void onFinish() {
    }
}
