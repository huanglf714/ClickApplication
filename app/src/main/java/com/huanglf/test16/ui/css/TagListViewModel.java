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

    public ITagRepository getTagRepository() {
        return tagRepository;
    }

    public void insertNewTag(String name, int image) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setImage(image);
        tagRepository.insertTag(tag);
    }

    public void updateNewTag(int id, String name, int image, int currrentNumber) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tag.setImage(image);
        tag.setNumber(currrentNumber);
        tagRepository.updateTag(tag);
    }

    public void deleteTag(Tag tag) {
        tagRepository.deleteTag(tag);
    }
}
