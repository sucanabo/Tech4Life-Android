package com.example.tech4life.recycleritems;

public class Series {

    private String mUsername;
    private String mDate;
    private String mTitle;
    private String mContent;
    private String mImg;

    public Series() {
        String empty= "EMPTY";
        this.mUsername = empty;
        this.mDate = empty;
        this.mTitle = empty;
        this.mContent = empty;
        this.mImg = empty;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
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


    public Series(String mUsername, String mDate, String mTitle, String mContent, String mImg) {
        this.mUsername = mUsername;
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mImg = mImg;
    }

}
