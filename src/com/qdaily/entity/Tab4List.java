package com.qdaily.entity;

import java.util.List;

/**
 * Created by song on 9/10/14.
 */
public class Tab4List {
    private List<Category> categories;
    private List<Tag> tags;
    private List<Top> tops;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Top> getTops() {
        return tops;
    }

    public void setTops(List<Top> tops) {
        this.tops = tops;
    }

    public class Top{
        private int  top_id;// 26,
        private String title;// "苹果",
        private String  icon;// "qdaily.com/system/categories/iconapps/26/medium/26.png?1408349011"

        public int getTop_id() {
            return top_id;
        }

        public void setTop_id(int top_id) {
            this.top_id = top_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class Tag{
        private int tag_id;// 723,
        private String title;// "Windows"

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

