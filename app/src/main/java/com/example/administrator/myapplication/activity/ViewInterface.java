package com.example.administrator.myapplication.activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public interface ViewInterface extends Observer {

    @Override
    void onSubscribe(Disposable d);

    @Override
    void onNext(Object o);
}
