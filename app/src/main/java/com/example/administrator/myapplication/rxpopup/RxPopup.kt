package com.example.administrator.myapplication.rxpopup

import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.administrator.myapplication.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


fun showSimplePop(fragmentManager: FragmentManager, resource: Int, onViewCreate: (View) -> String) {
    val rxPopupFragment = RxPopupFragment.instance(R.layout.fragment_dialog);
    val observer = object : Observer<View> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(view: View) {
            onViewCreate(view)
        }

        override fun onError(e: Throwable) {
        }

    }
    rxPopupFragment.subscribe(observer)
    rxPopupFragment.show(fragmentManager, "RxPopupFragment")
}