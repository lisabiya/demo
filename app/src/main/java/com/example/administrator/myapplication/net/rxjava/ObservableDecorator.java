package com.example.administrator.myapplication.net.rxjava;

import android.text.TextUtils;

import com.example.administrator.myapplication.net.BaseResponse;
import com.example.administrator.myapplication.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 观察者装饰器
 */
public class ObservableDecorator {

    @NonNull
    public static <T> Observable<T> decorate(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()); // FIXME 模拟延迟,用于观察加载框等效果
    }

    @NonNull
    public static <T> Observable<BaseResponse<T>> decorateHttp(Observable<String> observable, Class<T> cls) {
        return observable
                .map(new HttpResultFunc<>(cls))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()); // FIXME 模拟延迟,用于观察加载框等效果
    }


    public static class HttpResultFunc<T> implements Function<String, BaseResponse<T>> {

        private Class<T> cls;

        HttpResultFunc(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public BaseResponse apply(@io.reactivex.annotations.NonNull String response) throws Exception {
            if (TextUtils.isEmpty(response)) {
                return null;
            }
            BaseResponse<T> result = JsonUtil.jsonToBean(response, BaseResponse.class);
            if (result == null)
                return null;
            String json = result.getResult();
            if (!TextUtils.isEmpty(json)) {
                try {
                    Object jsonElement = new JSONTokener(json).nextValue();
                    if (jsonElement instanceof JSONObject) {
                        T t = JsonUtil.jsonToBean(json, cls);
                        result.setObject(t);
                    } else if (jsonElement instanceof JSONArray) {
                        List<T> list = JsonUtil.jsonToList(json, cls);
                        result.setList(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

}
