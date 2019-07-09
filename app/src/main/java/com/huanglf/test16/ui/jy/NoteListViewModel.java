package com.huanglf.test16.ui.jy;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.database.Note;

import java.util.List;

/**
 * Date: 2019/7/9
 * Author: JinYue
 * description:
 */
public class NoteListViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> noteList;

    public NoteListViewModel(MutableLiveData<List<Note>> noteList) {
        this.noteList = noteList;
    }

    /**
     * 获取消息列表
     */
    public void getNotes() {
    }

    /**
     * 更改收藏状态
     */
    public void setFavor(boolean status) {

    }


    public MutableLiveData getNoteList() {
        return noteList;
    }

}
