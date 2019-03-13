package com.example.administrator.myapplication;

import com.example.administrator.myapplication.bean.User;
import com.example.administrator.myapplication.fragment.UserCenterFragment;


import org.jetbrains.annotations.NotNull;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserCenterViewModel extends ViewModel implements UserCenterFragment.UserCenterListener {
    private MutableLiveData<User> users;

    public UserCenterFragment.UserCenterListener getListener() {
        return this;
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
        users.postValue(new User("正经"));
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
