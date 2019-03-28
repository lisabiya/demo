package com.example.administrator.myapplication.rxpopup

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.administrator.myapplication.R


fun showSimplePop(fragmentManager: FragmentManager?) {
    val rxPopup = object : RxPopup() {
        override fun onViewCreated(view: View) {
            setCancelable(true)
            val message = view.findViewById<TextView>(R.id.tvMessage)
            setViewOnClick(R.id.ivClose) {
                dismiss()
            }
            setViewOnClick(R.id.btSet) {
                message.text = String.format("btSet")
            }
        }
    }

    if (fragmentManager != null)
        rxPopup.showFragment(fragmentManager, R.layout.fragment_dialog)
}