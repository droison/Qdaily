package com.qdaily.entity;

/**
 * Created by song on 9/3/14.
 */
public class Article {
    private Integer article_id;// 1938,
    private String ios_height;// "123",
    private String title;// "这个设计了不起之今日最佳（测试，不要发）",
    private String subtitle;// "",
    private String super_tag;// "",
    private String publish_time;// null,
    private Integer genre;// 10,
    private String articleIndex;// "qdaily.com/system/pictures/designs/46/small/46.jpg?1408428563",
    private String category_title;// "这个设计了不起",
    private String category_icon;// "qdaily.com/img/missing_category_white.png",
    private Integer comment_count;// 0,
    private Integer praise_count;// 0,
    private String author;// "胡莹",
    private String author_face;// "qdaily.com/img/missing_article.png"

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getIos_height() {
        return ios_height;
    }

    public void setIos_height(String ios_height) {
        this.ios_height = ios_height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSuper_tag() {
        return super_tag;
    }

    public void setSuper_tag(String super_tag) {
        this.super_tag = super_tag;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(String articleIndex) {
        this.articleIndex = articleIndex;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(Integer praise_count) {
        this.praise_count = praise_count;
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
