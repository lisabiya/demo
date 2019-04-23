package com.example.administrator.myapplication.net.core.callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Create by Administrator on 2019/4/23
 */
public class DisposableManager {
    /**
     * Tag标识 判断是否已注销 (确保Tag唯一性)
     */
    private static HashMap<String, List<Disposable>> TagsMap = new HashMap<>();


    //dispose
    public static synchronized void removeTag(String tag) {
        List<Disposable> disposables = TagsMap.get(tag);
        if (disposables != null) {
            for (Disposable d : disposables) {
                if (d != null && !d.isDisposed()) {
                    d.dispose();
                }
            }
            TagsMap.remove(tag);
        }
    }

    static synchronized void addDisposable(String tag, Disposable disposable) {
        if (TagsMap.get(tag) == null) {
            TagsMap.put(tag, new ArrayList<Disposable>());
        }
        List<Disposable> disposables = TagsMap.get(tag);
        if (disposables != null) {
            disposables.add(disposable);
        }
    }
}
