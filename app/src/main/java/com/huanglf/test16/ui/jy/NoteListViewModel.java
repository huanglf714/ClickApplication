package com.huanglf.test16.ui.jy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.impl.NoteRepository;

import java.util.List;

/**
 * Date: 2019/7/9
 * Author: JinYue
 * description:
 */
public class NoteListViewModel extends ViewModel {
    private LiveData<List<Note>> noteList;
    private final INoteRepository noteRepository;

    public NoteListViewModel() {
        this.noteRepository = NoteRepository.getInstance();
    }

    /**
     * 获取消息列表
     */
    public void getNotes() {
        noteList = noteRepository.loadAllNotes();
    }

    /**
     * 更改收藏状态
     */
    public void setFavor(Note note) {
        noteRepository.addStar(note);
    }

    public void removeItem(Note note) {
        noteRepository.deleteOneNote(note);
    }


    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }

}
