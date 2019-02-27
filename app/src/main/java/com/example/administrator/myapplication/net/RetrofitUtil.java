package com.example.administrator.myapplication.net;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseApplication;
import com.example.administrator.myapplication.base.Constant;
import com.example.administrator.myapplication.net.rxjava.ObservableDecorator;
import com.example.administrator.myapplication.utils.HYAppUtils;
import com.example.administrator.myapplication.utils.HYImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {

    private static Retrofit retrofit;

    private static OkHttpClient httpClient;

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    static {
        // OkHttpClient
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient().newBuilder()
                // 统一添加的Header
                .readTimeout(120, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String url = chain.request().url().toString();
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        if (url.contains(Apis.getGHJUrl())) {
                            requestBuilder.addHeader("User-Agent", HYAppUtils.getHttpUserAgent(BaseApplication.getInstance()));
                        } else if (url.contains(Apis.BASE_URL)) {
                            requestBuilder.addHeader("device_model", android.os.Build.MODEL)
                                    .addHeader("device_uuid", HYAppUtils.getMacAddress(BaseApplication.getInstance()))
                                    .addHeader("device_name", "")
                                    .addHeader("device_os", "Android")
                                    .addHeader("device_os_version", android.os.Build.VERSION.RELEASE)
                                    .addHeader("device_local", BaseApplication.getInstance().getResources().getConfiguration().locale.getLanguage())
                                    .addHeader("app_version", HYAppUtils.getAppVersionName(BaseApplication.getInstance()))
                                    .addHeader("api_version", "1.10")
                                    .addHeader("app_key", Constant.APP_KEY)
                                    .addHeader("app_secret", Constant.APP_SECRET);
//                            if (Constant.user != null) {
//                                requestBuilder.addHeader("uid", Constant.user.getIhooyah_uid() + "");
//                            }
                        }
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                //LOG
                .addInterceptor(logInterceptor)
                .build();

        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) // gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // rxjava 响应式编程
                .client(httpClient)
                .callbackExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .build();
    }


    public static HashMap<String, String> getHttpHeaders() {
        Context context = BaseApplication.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("device_uuid", HYAppUtils.getMacAddress(context));
        params.put("device_model", android.os.Build.MODEL);
        params.put("device_name", android.os.Build.MODEL);
        params.put("device_os_name", "android");
        params.put("device_os_version", android.os.Build.VERSION.RELEASE);
        params.put("app_version", HYAppUtils.getAppVersionName(context));
        params.put("local_language", HYAppUtils.getAppLanguage(context));
        params.put("app_name", context.getResources().getString(R.string.app_name));
        return params;
    }

    public static ApiService getApiService() {
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

    /**
     * 上传图片
     *
     * @param call    上传成功回调
     * @param context
     * @param uris    图片uri
     * @param call
     */
    public static void fileUpload(final Context context, List<Uri> uris, final Observer<Object> call) {
        final ApiService service = getApiService();
        final String filename = "avatar_" + System.currentTimeMillis() + ".jpg";
        List<MultipartBody.Part> list = new ArrayList<>();
        try {
            for (Uri uri : uris) {
                byte[] resource = HYImageUtils.getCompressImageBytes(uri);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), resource);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("files", filename, requestBody);
//                maps.put(filename, body);
                list.add(body);
            }
            ObservableDecorator.decorate(service.fileUpload(list)).subscribe(call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
