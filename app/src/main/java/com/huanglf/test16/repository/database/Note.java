package com.huanglf.test16.repository.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NonNull;

import java.io.Serializable;


/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
@Entity
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String content;

    @ColumnInfo(name = "create_date")
    private String createDate;

    @ColumnInfo(name = "update_date")
    private String updateDate;

    @ColumnInfo(name = "is_star")
    private Boolean isStar = false;

    @ColumnInfo(name = "tag_id")
    @NonNull
    private int tagId = 1;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getStar() {
        if (this.isStar == null) {
            this.isStar = false;
        }
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", isStar=" + isStar +
                ", tagId=" + tagId +
                '}';
    }
}
