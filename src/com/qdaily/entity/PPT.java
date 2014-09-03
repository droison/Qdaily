package com.qdaily.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 9/2/14.
 */
public class PPT {

    private Integer ppt_id;
    private String title;
    private String subtitle;//＃副标题
    private String super_tag;//＃超级标签
    private Date publish_time;//＃发布时间
    private Integer  genre;//                    :default => 1＃文章类型（1普通，2组合＃，3专题，4话语＃，5数字，6链接，7专栏，8视频＃，9播客＃，10图片，11广告＃）
    private String  banner;//＃banner图
    private String category_title;// "Best on the Web",
    private String category_icon;// "qdaily.com/system/categories/iconwhites/12/medium/12.png?1399153208",
    private Integer comment_count;//             :default => 0＃评论量

    public Integer getPpt_id() {
        return ppt_id;
    }

    public void setPpt_id(Integer ppt_id) {
        this.ppt_id = ppt_id;
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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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
}
