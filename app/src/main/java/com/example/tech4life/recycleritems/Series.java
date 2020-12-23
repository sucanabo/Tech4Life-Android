package com.example.tech4life.recycleritems;

public class Series {

    private String mUsername;
    private String mDate;
    private String mTitle;
    private String mContent;
    private int mImage;

    public Series(String mUsername, String mDate, String mTitle, String mContent, int mImage) {
        this.mContent = mUsername;
        this.mContent = mDate;
        this.mContent = mTitle;
        this.mContent = mContent;
        this.mImage = mImage;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

}
