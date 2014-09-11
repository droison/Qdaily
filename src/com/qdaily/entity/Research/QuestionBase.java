package com.qdaily.entity.Research;

import java.util.List;

/**
 * Created by song on 9/6/14.
 */
public class QuestionBase {
    private String question_id;// "",
    private String title;// "这么多年过去了，对哈利波特感情还在吗？",
    private String content;// "《哈利波特》作者J.K罗琳虽已明言不会再出续集，但仍满足了许多哈利迷的愿望，近日在一篇短篇小说中，描述了哈利成人之后的中年故事。你现在对《哈利波特》是什么样的感情？ ",
    private List<QuestionGridBase> options;

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<QuestionGridBase> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionGridBase> options) {
        this.options = options;
    }
}
