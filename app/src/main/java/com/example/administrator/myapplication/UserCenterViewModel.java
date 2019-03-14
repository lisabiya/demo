package com.example.administrator.myapplication;

import com.example.administrator.myapplication.bean.User;
import com.example.administrator.myapplication.fragment.UserCenterFragment;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

public class UserCenterViewModel extends ViewModel implements UserCenterFragment.UserCenterListener {
    private MutableLiveData<User> users;
    private PublishSubject<String> subject;

    public UserCenterFragment.UserCenterListener getListener() {
        subject = PublishSubject.create();
        return this;
    }

    @Override
    protected void onCleared() {
        subject.onComplete();
        super.onCleared();
    }

    @NotNull
    @Override
    public LiveData<User> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        Observable<Integer> observable = Observable.just("")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String query) throws Exception {
                        return query.length();
                    }
                });
        Disposable disposable = observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                users.postValue(new User("正经"));
            }
        });
    }


    //View操作监听
    @Override
    public void sysTem() {
        users.postValue(new User("sysTem"));
    }

    @Override
    public void verified() {
        users.postValue(new User("verified"));
    }

    @Override
    public void myOrder() {
        users.postValue(new User("myOrder"));
    }

    @Override
    public void myPurse() {
        users.postValue(new User("myPurse"));
    }
}
