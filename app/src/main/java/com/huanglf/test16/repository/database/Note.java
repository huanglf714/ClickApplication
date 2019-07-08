package com.huanglf.test16.repository.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;

    @ColumnInfo(name = "create_date")
    private String createDate;

    @ColumnInfo(name = "update_date")
    private String updateDate;

    @ColumnInfo(name = "is_star")
    private Boolean isStar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", isStar=" + isStar +
                '}';
    }
}
