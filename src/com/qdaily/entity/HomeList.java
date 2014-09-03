package com.qdaily.entity;

import java.util.List;

/**
 * Created by song on 9/2/14.
 */
public class HomeList {
    private List<PPT> ppts;
    private String page;

    public List<PPT> getPpts() {
        return ppts;
    }

    public void setPpts(List<PPT> ppts) {
        this.ppts = ppts;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
