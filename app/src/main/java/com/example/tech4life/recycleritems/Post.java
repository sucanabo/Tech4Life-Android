package com.example.tech4life.recycleritems;

public class Post {
    private String authorName;
    private String authorImg;
    private String dateCreated;
    private String img;
    private String title;

    public Post() {
        String empty= "EMPTY";
        this.authorName = empty;
        this.authorImg = empty;
        this.dateCreated = empty;
        this.img = empty;
        this.title = empty;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public Post(String authorName, String authorImg, String dateCreated, String img, String title) {
        this.authorName = authorName;
        this.authorImg = authorImg;
        this.dateCreated = dateCreated;
        this.img = img;
        this.title = title;
    }
}
