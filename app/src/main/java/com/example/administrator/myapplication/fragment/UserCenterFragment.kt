package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.UserCenterViewModel
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.bean.User
import com.example.administrator.myapplication.rxpopup.RxPopup
import com.example.administrator.myapplication.rxpopup.showSimplePop
import kotlinx.android.synthetic.main.fragment_user_center.*

class UserCenterFragment : BaseFragment() {
    public interface UserCenterListener {
        //业务
        fun getUsers(): LiveData<User>


        //View操作监听
        fun sysTem()

        fun verified()

        fun myOrder()

        fun myPurse()
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
            val fragmentManager: FragmentManager? = getFragmentManager();
            if (fragmentManager != null) {
                showSimplePop(fragmentManager, R.layout.fragment_dialog, object : RxPopup() {
                    override fun onViewCreated(view: View) {
                        setCancelable(true)
                        val message = view.findViewById<TextView>(R.id.tvMessage)
                        setViewOnClick(R.id.ivClose) {
                            dismiss()
                        }
                        setViewOnClick(R.id.btSet) {
                            message.text = "btSet"
                        }
                    }
                })
            }
            userCenterListener.sysTem()
        }
        rlVerified.setOnClickListener {
            userCenterListener.verified()
        }
        rlMyOrder.setOnClickListener {
            userCenterListener.myOrder()
        }
        rlMyPurse.setOnClickListener {
            userCenterListener.myPurse()
        }
    }

    override fun initData() {
        portrait.setActualImageResource(R.drawable.ic_my)
        userCenterListener.getUsers().observe(this, Observer { user ->
            tvName.text = user.name
        })

    }


}