package com.example.administrator.myapplication;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.GsonUtils;
import com.example.administrator.myapplication.bean.Happiness;
import com.example.administrator.myapplication.bean.Info;
import com.example.administrator.myapplication.fragment.OrderFragment;
import com.example.administrator.myapplication.net.HttpRequest;
import com.example.administrator.myapplication.net.core.callback.BaseResponse;
import com.example.administrator.myapplication.net.core.callback.DisposableManager;
import com.example.administrator.myapplication.net.core.callback.HttpCallback;
import com.example.administrator.myapplication.net.core.callback.SimpleCallback;
import com.example.administrator.myapplication.net.core.callback.SimpleLifeCycleCallback;

import org.jetbrains.annotations.NotNull;

public class OrderViewModel extends ViewModel implements OrderFragment.OrderListener {
    private MutableLiveData<Info> mInfo;
    private MutableLiveData<Happiness> happiness;

    private static final String TAG = "MyTAG";

    public OrderFragment.OrderListener getListener() {
        return this;
    }

    @Override
    protected void onCleared() {
        DisposableManager.removeTag(TAG);
        super.onCleared();
    }


    @NotNull
    @Override
    public LiveData<Info> getInfo() {
        if (mInfo == null) {
            mInfo = new MutableLiveData<>();
            getInfoWeb();
        }
        return mInfo;
    }


    @NotNull
    @Override
    public LiveData<Happiness> getHappiness() {
        if (happiness == null) {
            happiness = new MutableLiveData<>();
            getHappinessWeb();
        }
        return happiness;
    }

    public LiveData<Happiness> getHappiness(int page, Lifecycle lifecycle) {
        if (happiness == null) {
            happiness = new MutableLiveData<>();
        }
        return happiness;
    }

    @Override
    public void onRefresh() {
        getHappinessWeb();
    }

    //获取每日推荐
    private void getInfoWeb() {
        HttpRequest.getInfo(new SimpleCallback<String>(TAG) {
            @Override
            public void onSuccess(String s) {
                Info info = GsonUtils.fromJson(s, Info.class);
                if (mInfo != null) {
                    mInfo.postValue(info);
                }
            }

            @Override
            public void onFailed(int code, String message) {
                mInfo.postValue(null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }


    //获取每日推荐
    private void getHappinessWeb() {
        HttpRequest.getHappiness(new SimpleCallback<String>(TAG) {
            @Override
            public void onSuccess(String s) {
                Happiness info = GsonUtils.fromJson(s, Happiness.class);
                if (happiness != null) {
                    happiness.postValue(info);
                }
            }

            @Override
            public void onFailed(int code, String message) {
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
        getList();
    }

    //获取每日推荐
    public void getHappinessWeb(int page, Lifecycle lifecycle) {
        HttpRequest.getHappiness(page, new SimpleLifeCycleCallback<String>(lifecycle) {
            @Override
            public void onSuccess(String s) {
                Happiness info = GsonUtils.fromJson(s, Happiness.class);
                if (happiness != null) {
                    happiness.postValue(info);
                }
            }

            @Override
            public void onFailed(int code, String message) {
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
        getList();
    }


    private void getList() {
        HttpRequest.loginUser("", "", new HttpCallback<String>("TAG") {

            @Override
            public void onSuccess(BaseResponse response, String s) {
            }

            @Override
            public void onFailed(int code, String message) {
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
