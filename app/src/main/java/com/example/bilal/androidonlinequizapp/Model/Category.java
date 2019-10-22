package com.example.bilal.androidonlinequizapp.Model;

/**
 * Created by Bilal on 2/16/2018.
 */

//T2-->Start//
public class Category {
    private String Name;
    private String Image;

    public Category() {
    }

    public Category(String name, String image) {
        this.Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
//T2-->End//
