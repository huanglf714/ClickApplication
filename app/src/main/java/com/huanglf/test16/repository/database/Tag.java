package com.huanglf.test16.repository.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.huanglf.test16.R;

import lombok.NonNull;

/**
 * @author chenshanshan
 * @time 2019/7/10 10:18
 */
@Entity
public class Tag {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    @NonNull
    private int number = 0;
    private String color = "#fff";
    @NonNull
    private int image = R.drawable.tag;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tag(int id, String name, int number, int image) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", image=" + image +
                '}';
    }
}
