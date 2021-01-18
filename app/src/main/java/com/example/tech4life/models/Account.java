package com.example.tech4life.models;

public class Account {
    public Account() {
    }

    private String username;
    private String TOKEN;
    private String displayName;
    private String email;
    private String avatar;
    private String id;

    public String getUsername() {
        return username;
    }

    public Account(String username, String TOKEN, String displayName, String email, String avatar, String id) {
        this.setUsername(username);
        this.TOKEN = TOKEN;
        this.setDisplayName(displayName);
        this.setEmail(email);
        this.setAvatar(avatar);
        this.id = id;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
