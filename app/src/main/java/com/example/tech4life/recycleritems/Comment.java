package com.example.tech4life.recycleritems;

public class Comment {

    private String authorImg;
    private String authorName;
    private String createDate;
    private String content;

    public Comment(String authorImg, String authorName, String createDate, String content) {
        this.authorImg = authorImg;
        this.authorName = authorName;
        this.createDate = createDate;
        this.content = content;
    }

    public Comment() {
        final String empty = "EMPTY";
        this.authorName = empty;
        this.createDate = empty;
        this.content = empty;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getContent() {
        return content;
    }

}
