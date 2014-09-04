package com.qdaily.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 9/4/14.
 */
public class WebArticle {

    private int article_id;// 1383,
    private String url;// "qdaily.com/app/articles/webview?article_id=1383",
    private String pictures;// [ ],
    private String praised;// false,
    private String title;// "As Time Goes By",
    private String subtitle;// "Zed Nelson",
    private String super_tag;// null,
    private Date publish_time;// "2014-07-08T08;//20;//00+08;//00",
    private Integer genre;// 6,
    private String articleShow;// "qdaily.com/img/missing_article.png",
    private String category_title;// "Best on the Web",
    private String category_icon;// "qdaily.com/system/categories/iconwhites/12/medium/12.png?1399153208",
    private Integer comment_count;// 0,
    private Integer praise_count;// 0,
    private List<String> tags;// [ "Zed Nelson" ]

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPraised() {
        return praised;
    }

    public void setPraised(String praised) {
        this.praised = praised;
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

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getArticleShow() {
        return articleShow;
    }

    public void setArticleShow(String articleShow) {
        this.articleShow = articleShow;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
