package com.example.administrator.myapplication.rxpopup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.administrator.myapplication.utils.AnimationUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.AsyncSubject
import kotlinx.android.synthetic.main.fragment_dialog.*

class RxPopupFragment : DialogFragment() {


    private val asyncSubject = AsyncSubject.create<View>()
    private var isLive = true;//判断是否可用

    companion object {
        private var resourceId = 0;
        fun instance(resource: Int): RxPopupFragment {
            resourceId = resource
            return RxPopupFragment()
        }
    }


    fun subscribe(observer: Observer<View>) {
        asyncSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .takeWhile {
                    return@takeWhile isLive
                }
                .subscribe(observer)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(resourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asyncSubject.onNext(view)
        asyncSubject.onComplete()
        initView()
    }

    private fun initView() {
//        isCancelable = false
        AnimationUtil.doAnimation(tvMessage, AnimationUtil.AnimationType.PRESS)
        AnimationUtil.doAnimation(tvMessage, AnimationUtil.AnimationType.ROTATION)
        btSet.setOnClickListener {

        }
    }

    override fun onDestroy() {
        isLive = false
        super.onDestroy()
    }

}
