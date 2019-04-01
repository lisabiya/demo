package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;

public abstract class BaseFragment extends Fragment {

    protected View rootView = null;

    public FragmentManager fragmentManager = null;

    private ObservableEmitter<View> emitter;

    private Boolean isAttach = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    public void subscribe(Observer<? super View> observer) {
        Observable<View> observable = Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(ObservableEmitter<View> e) throws Exception {
                emitter = e;
            }
        });
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .takeWhile(new Predicate<View>() {
                    @Override
                    public boolean test(View view) throws Exception {
                        return isAttach;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
    }

    protected void onViewShow() {
        if (emitter != null && rootView != null)
            emitter.onNext(rootView);
    }

    @Override
    public void onDetach() {
        isAttach = true;
        super.onDetach();
    }
}
