package com.qdaily.entity;

/**
 * Created by song on 9/3/14.
 */
public class Category {
    private Integer category_id; //25,
    private Integer genre; //4,
    private String title; //"这个设计了不起",
    private String icon; //"qdaily.com/img/missing_category_black.png",
    private String num; //"11",
    private Integer article_id; //1935,
    private String article_title; //"这个设计了不起之今日最佳（测试，不要发）",
    private String articleIndex; //"qdaily.com/system/pictures/designs/35/medium/35.jpg?1408427877",
    private String publish_time; //"08月19日",
    private String author; //"胡莹",
    private String author_face; //"qdaily.com/img/missing_article.png"

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(String articleIndex) {
        this.articleIndex = articleIndex;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_face() {
        return author_face;
    }

    public void setAuthor_face(String author_face) {
        this.author_face = author_face;
    }
}
