package com.example.administrator.myapplication.net;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 */

public interface ApiService {

    // 上传图片接口
    @Multipart
    @POST("upload_10001.api")
    Observable<Object> fileUpload(@Part() List<MultipartBody.Part> parts);


    @GET
    Observable<String> getDocList(
            @Url String url);


    @GET
    Observable<String> getInfo(
            @Url String url);


    /**
     * 用户登录
     *
     * @param params
     * @param account
     * @param pwd
     * @return
     */
    @POST(Apis.LOGIN)
    @FormUrlEncoded
    Observable<String> loginRequest(
            @FieldMap HashMap<String, String> params,
            @Field("account") String account,
            @Field("password") String pwd
    );


}
