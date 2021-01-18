package com.example.tech4life.recycleritems;

public class Category {
    private String id;
    private String name;
    private String image;

    public Category() {
        String empty = "EMPTY";
        name = empty;
        id = empty;
    }
    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
