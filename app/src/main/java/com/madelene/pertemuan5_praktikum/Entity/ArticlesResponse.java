package com.madelene.pertemuan5_praktikum.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticlesResponse {
    @SerializedName("name")
    private String name;
    @SerializedName("articles")
    private ArrayList<Articles> articles;

    public String getName() {
        return name;
    }

    public ArrayList<Articles> getArticles() {
        if(articles==null){
            articles = new ArrayList<>();

        }
        return articles;
    }
}
