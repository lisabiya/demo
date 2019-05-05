package com.example.administrator.myapplication.utils


fun main() {
    val add: (name: String) -> String = { name ->
        val s = name.toString()
        s.toString()
    };
    fun start(): String {
        return add("ss")
    }
    print(start())
}
