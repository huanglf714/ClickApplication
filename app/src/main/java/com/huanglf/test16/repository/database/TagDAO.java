package com.huanglf.test16.repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author chenshanshan
 * @time 2019/7/10 10:18
 */
@Dao
public interface TagDAO {
    @Insert
    void insertNewTag(Tag tag);

    @Update
    void updateTag(Tag tag);

    @Delete
    void deleteOneTag(Tag tag);

    @Query("select * from tag")
    LiveData<List<Tag>> queryAllTags();

    @Query("select * from tag where id = 1")
    Tag queryTag();

    @Query("select * from tag where id = :tagId")
    LiveData<Tag> queryTagId(int tagId);

//    @Query("SELECT tag.id, count(*) as number FROM note join tag on note.tag_id = tag.id group by tag.id")
//    LiveData<List<Tag>> queryAllHaveNumberTags();
}
