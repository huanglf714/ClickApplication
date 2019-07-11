package com.huanglf.test16.ui.css;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.ITagRepository;
import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.repository.impl.TagRepositoryImpl;

import java.util.List;

/**
 * @author chenshanshan
 * @time 2019/7/10 11:18
 */
public class TagListViewModel extends ViewModel {
    private LiveData<List<Tag>> tagList;
    private final ITagRepository tagRepository;

    public TagListViewModel() {
        this.tagRepository = TagRepositoryImpl.getInstance();
    }

    public LiveData<List<Tag>> getTagList() {
        tagList = tagRepository.queryAllTag();
        return tagList;
    }

}
