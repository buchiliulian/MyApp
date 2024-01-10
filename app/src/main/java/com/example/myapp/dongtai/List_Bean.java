package com.example.myapp.dongtai;

import java.io.Serializable;

public class List_Bean implements Serializable {

    private String content;
    private String imgpath;
    private String time;

    public List_Bean(String content, String imgpath, String time) {
        this.content = content;
        this.imgpath = imgpath;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
