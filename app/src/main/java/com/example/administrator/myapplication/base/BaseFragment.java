package com.example.administrator.myapplication.base;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragment extends Fragment {

    protected View rootView = null;

    public FragmentManager fragmentManager = null;


    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


}
