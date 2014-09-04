package com.qdaily.entity;

import java.util.List;

/**
 * Created by song on 9/3/14.
 */
public class CategoryList {
    private String title;// "这个设计了不起",
    private Integer genre;// 4,
    private String icon;// "qdaily.com/img/missing_category_white.png",
    private List<Article> articles;// [],
    private String page;// "1"

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
