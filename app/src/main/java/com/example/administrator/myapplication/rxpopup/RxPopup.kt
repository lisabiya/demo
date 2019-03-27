package com.example.administrator.myapplication.rxpopup

import android.view.View
import androidx.fragment.app.FragmentManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class RxPopup {
    private lateinit var rxPopupFragment: RxPopupFragment
    private lateinit var mView: View

    fun showFragment(fragmentManager: FragmentManager, resource: Int) {
        rxPopupFragment = RxPopupFragment.instance(resource)

        val observer = object : Observer<View> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(view: View) {
                mView = view;
                onViewCreated(view)
            }

            override fun onError(e: Throwable) {

            }
        }
        rxPopupFragment.subscribe(observer)
        rxPopupFragment.show(fragmentManager, "RxPopupFragment")
    }

    protected abstract fun onViewCreated(view: View)

    protected fun setViewOnClick(resource: Int, onClick: () -> Unit) {
        mView.findViewById<View>(resource).setOnClickListener {
            onClick()
        }
    }


    protected fun setCancelable(boolean: Boolean) {
        rxPopupFragment.isCancelable = boolean
    }

    protected fun dismiss() {
        rxPopupFragment.dismiss()
    }
}