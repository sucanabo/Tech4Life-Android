package com.example.tech4life.recycleritems;

import android.os.Parcel;
import android.os.Parcelable;

public class ClipPost implements Parcelable {
        private String authorName;
        private String authorImg;
        private String dateCreated;
        private String img;
        private String title;

        private String content;

        private String view;
        private String vote;

        private String authorUsername;

        private String id;

        public String getContent() {
            return content;
        }

        public String getView() {
            return view;
        }

        public String getVote() {
            return vote;
        }

        public String getAuthorUsername() {
            return authorUsername;
        }

        public ClipPost(String authorName,
                    String authorImg,
                    String dateCreated,
                    String img,
                    String title,
                    String content,
                    String view,
                    String vote,
                    String authorUsername,
                    String id) {
            this.authorName = authorName;
            this.authorImg = authorImg;
            this.dateCreated = dateCreated;
            this.img = img;
            this.title = title;
            this.content = content;
            this.view = view;
            this.vote = vote;
            this.authorUsername = authorUsername;
            this.id = id;
        }


        public ClipPost() {
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

        public String getId() {
            return id;
        }

        public ClipPost(String authorName, String authorImg, String dateCreated, String img, String title) {
            this.authorName = authorName;
            this.authorImg = authorImg;
            this.dateCreated = dateCreated;
            this.img = img;
            this.title = title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringArray(new String[] {
                    this.authorName,
                    this.authorImg,
                    this.dateCreated,
                    this.img,
                    this.title});
        }
        public ClipPost(Parcel in) {
            String [] data = new String[5];

            in.readStringArray(data);
            this.authorName = data[0];
            this.authorImg = data[1];
            this.dateCreated = data[2];
            this.img = data[3];
            this.title = data[4];

        }
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public com.example.tech4life.recycleritems.Post createFromParcel(Parcel in) {
                return new com.example.tech4life.recycleritems.Post(in);
            }

            public com.example.tech4life.recycleritems.Post[] newArray(int size) {
                return new com.example.tech4life.recycleritems.Post[size];
            }
        };
}
