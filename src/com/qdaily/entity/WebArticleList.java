package com.qdaily.entity;

/**
 * Created by song on 9/4/14.
 */
public class WebArticleList {
    private WebArticle article; //{},
    private String url;// "qdaily.com/app/articles/webview?article_id=1418",
    private WebArticle before_article; //{},
    private WebArticle next_article;// {}

    public WebArticle getArticle() {
        return article;
    }

    public void setArticle(WebArticle article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebArticle getBefore_article() {
        return before_article;
    }

    public void setBefore_article(WebArticle before_article) {
        this.before_article = before_article;
    }

    public WebArticle getNext_article() {
        return next_article;
    }

    public void setNext_article(WebArticle next_article) {
        this.next_article = next_article;
    }
}
