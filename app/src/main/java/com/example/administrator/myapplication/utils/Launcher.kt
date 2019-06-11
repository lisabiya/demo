package com.example.administrator.myapplication.utils


fun main() {
    val add: (name: String) -> String = { name ->
        val s = name.toString()
        s.toString()
    };
    fun start(): String {
        return add("ss")
    }
    println(start())
    val s = method("aa") {
        val la = it.length;
        "${la}第一行代码"
    }
    print(s)
}


fun <T> method(args: String, body: (a: String) -> T): T {
    return body(args)
}