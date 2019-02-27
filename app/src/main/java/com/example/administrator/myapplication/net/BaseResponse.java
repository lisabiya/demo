package com.example.administrator.myapplication.net;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class BaseResponse<T> {

    public static final int RESULT_SUCCESS = 1;
    public static final int RESULTCODE_SUCCESS = 200;
    private int code;
    private int status;
    private String msg;
    private String result;
    @JSONField(serialize = false)
    private T object;
    @JSONField(serialize = false)
    private List<T> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return status == RESULT_SUCCESS || code == RESULTCODE_SUCCESS;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
