package com.pedroarthursimao.masterapp.butterknife.model;

import android.graphics.Bitmap;

/**
 * Created by pedro on 8/10/15.
 */
public class ListItem {

    private String title;
    private String content;
    private Bitmap image;

    public ListItem() {}

    public ListItem(String title, String content, Bitmap image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
