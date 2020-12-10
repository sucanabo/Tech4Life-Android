package com.example.tech4life;

public class Announcements {

        private String mContent;
        private int mImage;

        public Announcements(String mName, int mImage) {
            this.mContent = mName;
            this.mImage = mImage;
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
