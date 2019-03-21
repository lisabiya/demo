package com.example.administrator.myapplication;

import com.blankj.utilcode.util.GsonUtils;
import com.example.administrator.myapplication.base.BaseApplication;
import com.example.administrator.myapplication.bean.Info;
import com.example.administrator.myapplication.fragment.OrderFragment;
import com.example.administrator.myapplication.net.HttpRequest;
import com.example.administrator.myapplication.net.rxjava.SimpleSubscriber;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel implements OrderFragment.OrderListener {
    private MutableLiveData<Info> mInfo;

    public OrderFragment.OrderListener getListener() {
        return this;
    }

    @Override
    protected void onCleared() {
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


    private void getInfoWeb() {
        HttpRequest.getInfo(new SimpleSubscriber<String>(BaseApplication.getInstance()) {
            @Override
            public void onNext(String s) {
                super.onNext(s);
                Info info = GsonUtils.fromJson(s, Info.class);
                if (mInfo != null) {
                    mInfo.postValue(info);
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        });
    }
}
