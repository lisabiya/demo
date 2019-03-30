package com.example.administrator.myapplication.fragment

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.UserCenterViewModel
import com.example.administrator.myapplication.activity.MyPurseActivity
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.bean.User
import kotlinx.android.synthetic.main.fragment_user_center.*

class UserCenterFragment : BaseFragment() {
    interface UserCenterListener {
        //业务
        fun getUsers(): LiveData<User>


        //View操作监听
        fun sysTem(fragmentManager: FragmentManager?)

        fun verified()

        fun myOrder()

        fun myPurse(activity: Activity?)
    }

    private lateinit var userCenterListener: UserCenterListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_user_center, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userCenterListener = ViewModelProviders.of(this)
                .get(UserCenterViewModel::class.java).listener
        initView()
        initData()
    }

    override fun initView() {
        rlSystemInformation.setOnClickListener {
            userCenterListener.sysTem(getFragmentManager())
        }
        rlVerified.setOnClickListener {
            userCenterListener.verified()
        }
        rlMyOrder.setOnClickListener {
            userCenterListener.myOrder()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        }

        rlMyPurse.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val compat = ActivityOptions.makeSceneTransitionAnimation(activity, Pair(rlMyPurse, "rlMyPurse"))
                activity!!.startActivity(Intent(activity, MyPurseActivity::class.java), compat.toBundle())
            }
//            userCenterListener.myPurse(activity)
        }
    }

    override fun initData() {
        portrait.setActualImageResource(R.drawable.ic_my)
        userCenterListener.getUsers().observe(this, Observer { user ->
            tvName.text = user.name
        })

    }


}