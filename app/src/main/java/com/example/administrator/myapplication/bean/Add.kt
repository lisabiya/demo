package com.example.administrator.myapplication.bean

class Add {
    private var id: Int = 0
    private var title: String? = null
    private var time: String = ""

    constructor(id: Int) {
        this.id = id
    }

    constructor(title: String) {
        this.title = title
    }

    constructor(id: Int, title: String, time: String) {
        this.id = id
        this.title = title
        this.time = time
    }
}
