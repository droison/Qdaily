package com.qdaily.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 9/2/14.
 */
public class Magazine {
    private Integer magazine_id;
    private String title;
    private List<Article> articles;
    private String subtitle;//＃副标题
    private String super_tag;//＃超级标签
    private Date publish_time;//＃发布时间
    private Integer  genre;//                    :default => 1＃文章类型（1普通，2组合＃，3专题，4话语＃，5数字，6链接，7专栏，8视频＃，9播客＃，10图片，11广告＃）
    private String  articleIndex;//＃普通文章列表图
    private String category_title;// "Best on the Web",
    private String category_icon;// "qdaily.com/system/categories/iconwhites/12/medium/12.png?1399153208",
    private Integer comment_count;//             :default => 0＃评论量
    private String  author;//＃作者名称
    private String author_face;// "qdaily.com/img/missing_article.png"

    public Integer getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(Integer magazine_id) {
        this.magazine_id = magazine_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
class Article{
    private Integer article_id;
    private String title;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
