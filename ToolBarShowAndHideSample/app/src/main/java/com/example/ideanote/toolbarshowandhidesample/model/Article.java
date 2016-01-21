package com.example.ideanote.toolbarshowandhidesample.model;

public class Article {

    private String mTitle;

    private String mDescription;

    public Article(String mTitle, String mDescription) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
