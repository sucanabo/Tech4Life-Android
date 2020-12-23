package com.example.tech4life;

import java.util.Date;

public class Post_Item {
    private String title;
    private int avatar;
    private String author;
    private  int img;
    private String createDate;

    public Post_Item(String title,int avatar,String author,int img,String createDate){
        this.setTitle(title);
        this.setAvatar(avatar);
        this.setAuthor(author);
        this.setImg(img);
        this.setCreateDate(createDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
