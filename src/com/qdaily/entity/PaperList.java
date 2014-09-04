package com.qdaily.entity;

import java.util.List;

/**
 * Created by song on 9/4/14.
 */
public class PaperList {
    private String page;
    private List<Paper> papers;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }
}
