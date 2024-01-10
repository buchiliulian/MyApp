package com.example.myapp.chat;

import java.io.Serializable;

public class Chat implements Serializable {
    private String name;
    private String Intro;
    private int imageId;

    public Chat(String name,  int imageId, String intro) {
        this.name = name;
        this.Intro = intro;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


}
