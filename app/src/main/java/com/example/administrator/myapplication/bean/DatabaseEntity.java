package com.example.administrator.myapplication.bean;

import java.io.Serializable;

/**
 * Create by Administrator on 2019/5/10
 * 中标库等各种数据库
 */
public class DatabaseEntity implements Serializable {
    private String name;
    private Integer imageRes;
    private String imageUrl;

    public DatabaseEntity(String name, Integer imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public DatabaseEntity(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageRes() {
        return imageRes;
    }

    public void setImageRes(Integer imageRes) {
        this.imageRes = imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
