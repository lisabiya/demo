package com.example.administrator.myapplication.net;

import android.text.TextUtils;

import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorInfoUtils {

    /**
     * 解析服务器错误信息
     */
    public static String parseHttpErrorInfo(Throwable throwable) {
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

    /**
     * 获取本地预设错误信息
     */
    private static String getLocalErrorInfo(ErrorResponse error) {
        String s = "";
        if (TextUtils.isEmpty(s)) {
            return error.getMsg();
        } else {
            return s;
        }
    }
}
