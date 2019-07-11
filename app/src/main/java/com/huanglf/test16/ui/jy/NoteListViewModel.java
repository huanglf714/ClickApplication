package com.huanglf.test16.ui.jy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.impl.NoteRepositoryImpl;

import java.util.List;

/**
 * Date: 2019/7/9
 * Author: JinYue
 * description:
 */
public class NoteListViewModel extends ViewModel {
    private LiveData<List<Note>> mNoteList;
    private LiveData<List<Note>> mFavorNoteList;
    private final INoteRepository mNoteRepository;

    public NoteListViewModel() {
        this.mNoteRepository = NoteRepositoryImpl.getInstance();
    }

    /**
     * 获取消息列表
     */
    public void getNotes(boolean isFavor) {
        if (isFavor) {
            mFavorNoteList = mNoteRepository.loadStartNotes();
        } else {
            mNoteList = mNoteRepository.loadAllNotes();
        }
    }

    /**
     * 更改收藏状态
     */
    public void setFavor(Note note) {
        mNoteRepository.addStar(note);
    }

    public void removeItem(Note note) {
        mNoteRepository.deleteOneNote(note);
    }


    public LiveData<List<Note>> getNoteList(boolean isFavor) {
        if (isFavor) {
            return mFavorNoteList;
        } else {
            return mNoteList;
        }
    }

}
