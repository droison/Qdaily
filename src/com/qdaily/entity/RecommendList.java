package com.qdaily.entity;

import java.util.List;

/**
 * Created by song on 9/3/14.
 */
public class RecommendList {
    private List<Category> categories;
    private Integer page;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
