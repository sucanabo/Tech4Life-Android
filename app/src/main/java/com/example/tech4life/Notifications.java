package com.example.tech4life;

public class Notifications {

    private String mContent;
    private String mDate;
    private int mImage;

    public Notifications(String mName, String mDate, int mImage) {
        this.mContent = mName;
        this.mDate = mDate;
        this.mImage = mImage;

    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

}
