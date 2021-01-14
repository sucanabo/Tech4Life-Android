package com.example.tech4life.recycleritems;

public class Announcements {

        private String mTitle;
        private int mImage;
        private String mString;


        public Announcements(String mTitle, int mImage,String String) {
            this.mTitle = mTitle;
            this.mImage = mImage;
            this.mString = String;
        }
        public Announcements() {
            this.mTitle = "";
            this.mImage = 0;
            this.mString = null;
        }
        public String getDate(){return mString;}
        public void setDate(String String){
            this.mString = String;
        }
        public String getContent() {
            return mTitle;
        }

        public void setContent(String mContent) {
            this.mTitle = mContent;
        }

        public int getImage() {
            return mImage;
        }

        public void setImage(int mImage) {
            this.mImage = mImage;
        }

}
