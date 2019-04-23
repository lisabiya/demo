package com.example.administrator.myapplication.net.core.callback;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 标准已解析数据回调
 * 🐵 需要接口返回值格式与 BaseResponse参数一致
 * 🐵 子线程已解析数据，性能优化
 */
public abstract class HttpCallback<T> implements Observer<BaseResponse> {

    private String tag;

    protected HttpCallback() {
    }

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
        String errorInfo = parseHttpErrorInfo(throwable);
        Log.e("ErrorInfo", "错误信息:" + errorInfo);
        ToastUtils.showLong(errorInfo);
        onFinish();
        throwable.printStackTrace();
    }


    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(BaseResponse response, T t);

    private void onFailed(int code, String message) {
        onFinish();
    }

    public abstract void onFinish();

    /**
     * 解析服务器错误信息
     */
    private static String parseHttpErrorInfo(Throwable throwable) {
        String errorInfo = throwable.getMessage();
        if (throwable instanceof HttpException) {
            // 如果是Retrofit的Http错误,则转换类型,获取信息
            HttpException exception = (HttpException) throwable;
            ResponseBody responseBody = exception.response().errorBody();
            MediaType type = responseBody.contentType();
        } else {
            if (throwable instanceof UnknownHostException) {
                errorInfo = "无法连接到服务器";
            }
        }

        return errorInfo;
    }
}
