package com.example.administrator.myapplication.net.core.callback;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.administrator.myapplication.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * HttpCallback  观察者装饰器
 */
public class ObservableDecorator {

    @NonNull
    public static <T> Observable<T> decorate(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 观察者装饰器
     *
     * @param cls 目标类型转转实体类
     */
    public static <T> Observable<T> decorateEntity(Observable<String> observable, Class<T> cls) {
        return observable
                .map(new SimpleFunction<>(cls))
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 观察者装饰器
     *
     * @param cls 目标类型转List
     */
    public static <T> Observable<List<T>> decorateList(Observable<String> observable, Class<T> cls) {
        return observable
                .map(new SimpleListFunction<>(cls))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @NonNull
    public static <T> Observable<BaseResponse<T>> decorateHttp(Observable<String> observable, Class<T> cls) {
        return observable
                .map(new HttpResultFunction<>(cls))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 数据解析 转实体类
     */
    public static class SimpleFunction<T> implements Function<String, T> {

        private Class<T> cls;

        SimpleFunction(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public T apply(@io.reactivex.annotations.NonNull String response) throws Exception {
            if (TextUtils.isEmpty(response)) {
                return null;
            }
            Object jsonElement = new JSONTokener(response).nextValue();
            if (jsonElement instanceof JSONObject) {
                return JsonUtil.jsonToBean(response, cls);
            }
            return null;
        }
    }

    /**
     * 数据解析 转List
     */
    public static class SimpleListFunction<T> implements Function<String, List<T>> {

        private Class<T> cls;

        SimpleListFunction(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public List<T> apply(@io.reactivex.annotations.NonNull String response) throws Exception {
            if (TextUtils.isEmpty(response)) {
                return null;
            }
            Object jsonElement = new JSONTokener(response).nextValue();
            if (jsonElement instanceof JSONArray) {
                return JsonUtil.jsonToList(response, cls);
            }
            return null;
        }
    }


    /**
     * 数据解析 转BaseResponse
     */
    public static class HttpResultFunction<T> implements Function<String, BaseResponse<T>> {

        private Class<T> cls;

        HttpResultFunction(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public BaseResponse<T> apply(@NonNull String response) {
            if (TextUtils.isEmpty(response)) {
                return null;
            }
            BaseResponse result = JsonUtil.jsonToBean(response, BaseResponse.class);
            if (result == null)
                return null;
            BaseResponse<T> baseResponse = new BaseResponse<>();
            baseResponse.setCode(result.getCode());
            baseResponse.setStatus(result.getStatus());
            baseResponse.setMsg(result.getMsg());
            baseResponse.setResult(result.getResult());
            String json = baseResponse.getResult();
            if (!TextUtils.isEmpty(json)) {
                try {
                    Object jsonElement = new JSONTokener(json).nextValue();
                    if (jsonElement instanceof JSONObject) {
                        T t = JsonUtil.jsonToBean(json, cls);
                        baseResponse.setObject(t);
                    } else if (jsonElement instanceof JSONArray) {
                        List<T> list = JsonUtil.jsonToList(json, cls);
                        baseResponse.setList(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return baseResponse;
        }
    }
}
