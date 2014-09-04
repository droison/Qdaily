package com.qdaily.entity;

/**
 * Created by song on 9/4/14.
 */
public class Paper {

    private Integer paper_id;// 60,
    private String title;// "教你读懂emoji的真实含义！",
    private String description;// "Emoji迄今为止已有超过800个表情符号，许多人都表示这可能成为第一种真正全球性语言。但是读懂表情后面的深层含义是很重要的！这将使您成为一个通情达理的好人。 ",
    private String excerpt;// null,
    private String paperShow;// "qdaily.com/system/papers/papershows/60/mediumApp/60.jpg?1409790703",
    private String publish_time;// "2014-09-04T08;//30;//27+08;//00",
    private Integer genre;// 1,
    private Integer join_count;// 115

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getPaperShow() {
        return paperShow;
    }

    public void setPaperShow(String paperShow) {
        this.paperShow = paperShow;
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

    public Integer getJoin_count() {
        return join_count;
    }

    public void setJoin_count(Integer join_count) {
        this.join_count = join_count;
    }
}
