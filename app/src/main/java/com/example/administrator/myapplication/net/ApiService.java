package com.example.administrator.myapplication.net;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 *
 */

public interface ApiService {
    public String LOGIN = "00000.api";


    @GET
    Observable<String> getInfo(
            @Url String url);


    @GET
    Observable<String> showExample(
            @Url String url,
            @FieldMap HashMap<String, String> params);

    /**
     * 用户登录
     */
    @POST(LOGIN)
    @FormUrlEncoded
    Observable<String> loginRequest(
            @FieldMap HashMap<String, String> params,
            @Field("account") String account,
            @Field("password") String pwd);


}
