package com.qdaily.entity.Research;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 9/6/14.
 */
public class QuestionPaperBase {
    private int paper_id;// 11,
    private String title;// "这么多年过去了，对哈利波特感情还在吗？",
    private String description;// "《哈利波特》作者J.K罗琳虽已明言不会再出续集，但仍满足了许多哈利迷的愿望，近日在一篇短篇小说中，描述了哈利成人之后的中年故事。你现在对《哈利波特》是什么样的感情？ ",
    private String paperShow;// "qdaily.com/system/papers/papershows/11/large/11.jpg?1404899801",
    private Date publish_time;// "2014-07-09T17:56:42+08:00",
    private int join_count;// 413,
    private List<QuestionBase> questions;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
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

    public String getPaperShow() {
        return paperShow;
    }

    public void setPaperShow(String paperShow) {
        this.paperShow = paperShow;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public int getJoin_count() {
        return join_count;
    }

    public void setJoin_count(int join_count) {
        this.join_count = join_count;
    }

    public List<QuestionBase> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBase> questions) {
        this.questions = questions;
    }
}
