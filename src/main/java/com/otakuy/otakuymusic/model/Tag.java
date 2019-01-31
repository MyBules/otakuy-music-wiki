package com.otakuy.otakuymusic.model;

public class Tag {
    private int tag_id;
    private String tag_name;
    private int doubanId;

    public Tag(String tag_name, int doubanId) {
        this.tag_name = tag_name;
        this.doubanId = doubanId;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(int doubanId) {
        this.doubanId = doubanId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id=" + tag_id +
                ", tag_name='" + tag_name + '\'' +
                ", doubanId=" + doubanId +
                '}';
    }
}
