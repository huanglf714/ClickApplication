package com.huanglf.test16.repository.impl;

import android.os.Message;

import androidx.lifecycle.LiveData;

import com.huanglf.test16.repository.ITagRepository;
import com.huanglf.test16.repository.database.AppDatabase;
import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.repository.database.TagDAO;
import com.huanglf.test16.util.AppExecutor;
import com.huanglf.test16.util.MessageUtil;

import java.util.List;

/**
 * @author chenshanshan
 * @time 2019/7/10 10:26
 */
public class TagRepositoryImpl implements ITagRepository {
    private static TagDAO tagDAO;
    private static TagRepositoryImpl tagRepositoryImpl;

    public static TagRepositoryImpl getInstance() {
        if (tagRepositoryImpl == null) {
            tagRepositoryImpl = new TagRepositoryImpl();
        }
        return tagRepositoryImpl;
    }

    private TagRepositoryImpl() {
        tagDAO = AppDatabase.getInstance().getTagDAO();
    }

    @Override
    public void insertTag(final Tag tag) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                tagDAO.insertNewTag(tag);
            }
        });
    }

    @Override
    public void deleteTag(final Tag tag) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                tagDAO.deleteOneTag(tag);
            }
        });
    }

    @Override
    public void updateTag(final Tag tag) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(tag.getId() == 1) {
                    MessageUtil.error("未标签不能被修改");
                }
                else{
                    tagDAO.updateTag(tag);
                }
            }
        });
    }

    @Override
    public LiveData<List<Tag>> queryAllTag() {
        return tagDAO.queryAllTags();
    }

    @Override
    public Tag queryTag() {
        return tagDAO.queryTag();
    }
}
