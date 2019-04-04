package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.example.administrator.myapplication.activity.MyPurseActivity;
import com.example.administrator.myapplication.bean.User;
import com.example.administrator.myapplication.fragment.UserCenterFragment;
import com.example.administrator.myapplication.map.RouteQueryUtil;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

import static com.example.administrator.myapplication.rxpopup.RxPopupManagerKt.showSimplePop;

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
            loadUsers(new User("正经"));
        }
        return users;
    }

    private void loadUsers(User user) {
        users.postValue(user);
    }


    //View操作监听
    @Override
    public void sysTem(FragmentManager fragmentManager) {
        loadUsers(new User("sysTem"));
        RouteQueryUtil.getOKHttp();
        showSimplePop(fragmentManager);
    }

    @Override
    public void verified() {
        Disposable disposable = Observable.just("delay")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        loadUsers(new User(s));
                    }
                });
    }

    @Override
    public void myOrder() {
        loadUsers(new User("myOrder"));
    }

    @Override
    public void myPurse(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(activity);
            activity.startActivity(new Intent(activity, MyPurseActivity.class), compat.toBundle());
        }
        loadUsers(new User("myPurse"));
    }

    @Override
    public void set() {
        loadUsers(new User("set"));
        RouteQueryUtil.rxJavaBuffer();
    }

    @Override
    public void record() {
        loadUsers(new User("record"));
        RouteQueryUtil.rxJavaSCan();
    }
}
