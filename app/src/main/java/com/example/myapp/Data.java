package com.example.myapp;


public class Data {
    private int imageId;
    private String text;
    private int type;

    public Data() {
    }

    public Data(int imageId, String text, int type) {
        this.imageId = imageId;
        this.text = text;
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

