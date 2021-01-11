package com.example.tech4life.recycleritems;

public class Post {
    private String authorName;
    private int authorImg;
    private String dateCreated;
    private int img;
    private String title;

    public Post() {
        String empty= "EMPTY";
        this.authorName = empty;
        this.authorImg = 0;
        this.dateCreated = empty;
        this.img = 0;
        this.title = empty;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getAuthorImg() {
        return authorImg;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public Post(String authorName, int authorImg, String dateCreated, int img, String title) {
        this.authorName = authorName;
        this.authorImg = authorImg;
        this.dateCreated = dateCreated;
        this.img = img;
        this.title = title;
    }
}
