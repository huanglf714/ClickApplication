package com.huanglf.test16.repository;

import androidx.lifecycle.LiveData;

import com.huanglf.test16.repository.database.Tag;

import java.util.List;

/**
 * @author chenshanshan
 * @time 2019/7/10 10:26
 */
public interface ITagRepository {
    void insertTag(Tag tag);

    void deleteTag(Tag tag);

    void updateTag(Tag tag);

    LiveData<List<Tag>> queryAllTag();
}
