package com.qdaily.entity.Research;

import java.util.List;

/**
 * Created by song on 9/6/14.
 */
public class QuestionInfoBase {
    private boolean status;// true,
    private QuestionPaperBase paper;// {},
    private List<QuestionCommentBase> comments;// []

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public QuestionPaperBase getPaper() {
        return paper;
    }

    public void setPaper(QuestionPaperBase paper) {
        this.paper = paper;
    }

    public List<QuestionCommentBase> getComments() {
        return comments;
    }

    public void setComments(List<QuestionCommentBase> comments) {
        this.comments = comments;
    }
}
