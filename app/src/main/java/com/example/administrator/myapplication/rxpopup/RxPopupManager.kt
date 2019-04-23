package com.example.administrator.myapplication.rxpopup

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.administrator.myapplication.R
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


fun showSimplePop(fragmentManager: FragmentManager?) {
    if (fragmentManager == null) return
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
    rxPopup.showFragment(fragmentManager, R.layout.fragment_dialog)
}

interface Subject {

    fun hello(str: String)

    fun bye(): String
}

class RealSubject : Subject {
    override fun hello(str: String) {
        System.out.println("Hello  " + str);
    }

    override fun bye(): String {
        System.out.println("Goodbye");
        return "Over";
    }
}

fun main() {
    // 我们要代理的真实对象
    val realSubject = RealSubject()

    val subject1 = ProxyFactory(realSubject).proxyInstance as Subject;
    //执行方法
    System.out.println("hello return=${subject1.hello("kitty")}");
    System.out.println("bye return=${subject1.bye()}");
}

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 */
class ProxyFactory(private val target: Any) {

    //给目标对象生成代理对象
    //执行目标对象方法
    val proxyInstance: Any
        get() = Proxy.newProxyInstance(
                target.javaClass.classLoader,
                target.javaClass.interfaces,
                object : InvocationHandler {
                    @Throws(Throwable::class)
                    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any? {
                        println("开始事务")
                        //执行目标对象方法
                        val returnValue: Any? = if (args == null) {
                            method.invoke(target)
                        } else {
                            method.invoke(target, *args)
                        }
                        println("提交事务1")
                        println()
                        return returnValue
                    }
                }
        )

}