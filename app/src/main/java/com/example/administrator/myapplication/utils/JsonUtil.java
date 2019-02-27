package com.example.administrator.myapplication.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.administrator.myapplication.net.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.List;


public class JsonUtil {

    /**
     * 把json string 转化成类对象
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        try {
            if (!TextUtils.isEmpty(json.trim())) {
                T res = JSON.parseObject(json, cls);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToBean(String json, Type type) {
        try {
            if (!TextUtils.isEmpty(json.trim())) {
                T res = JSON.parseObject(json, type);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> BaseResponse parseToMap(String json, Type type) {
        return JSON.parseObject(json,
                new TypeReference<BaseResponse>(type) {
                });
    }

    /**
     * 把json string 转化成类对象
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> List<T> jsonToList(String json, final Class<T> cls) {
        try {
            if (!TextUtils.isEmpty(json.trim())) {
                List<T> res = JSON.parseArray(json, cls);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把类对象转化成json string
     *
     * @param t
     * @return
     */
    public static <T> String toJsonString(T t) {
        try {
            return JSON.toJSONString(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isJsonObject(String json) {
        Object jsonElement = null;
        try {
            jsonElement = new JSONTokener(json).nextValue();
            if (jsonElement instanceof JSONObject) {
                return true;
            } else if (jsonElement instanceof JSONArray) {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}