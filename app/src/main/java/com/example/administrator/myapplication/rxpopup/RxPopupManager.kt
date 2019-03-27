package com.example.administrator.myapplication.rxpopup

import androidx.fragment.app.FragmentManager


fun showSimplePop(fragmentManager: FragmentManager, resource: Int, rxPopup: RxPopup) {
    rxPopup.showFragment(fragmentManager, resource)
}