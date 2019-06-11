package com.example.administrator.myapplication.net.core.callback


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.administrator.myapplication.net.core.callback.HttpCallback.parseHttpErrorInfo
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * 带有生命周期的订阅者,ON_DESTROY时自动取消网络请求
 */
abstract class SimpleLifeCycleCallback<T>

/**
 * @param lifecycle 生命周期
 */
protected constructor(lifecycle: Lifecycle) : Observer<T>, LifecycleObserver {
    protected var disposable: Disposable? = null
        private set

    init {
        lifecycle.addObserver(this)
    }

    override fun onSubscribe(@NonNull d: Disposable) {
        disposable = d
    }

    override fun onNext(t: T) {
        if (t == null) {
            onFailed(400, "数据获取失败")
        } else {
            onSuccess(t)
        }
        onFinish()
    }


    override fun onError(throwable: Throwable) {
        val errorInfo = parseHttpErrorInfo(throwable)
        onFailed(404, errorInfo)
        onFinish()
    }

    override fun onComplete() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        LogUtils.e("BaseLifecycleCallback###onDestroy")
        disposable?.dispose()
    }


    /*******************对外方法 */
    abstract fun onSuccess(t: T)

    open fun onFailed(code: Int, message: String) {
        ToastUtils.showShort(message)
    }

    open fun onFinish() {}

}
