package com.huanglf.test16.ui.ty;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.impl.NoteRepository;

import java.util.List;

/**
 * Date: 2019/7/9
 * Author: huanglf
 * description:
 */
public class TestViewModel extends ViewModel {
    INoteRepository noteRepository = NoteRepository.getInstance();
    public LiveData<List<Note>> loadAllUsers(){
        return noteRepository.loadAllNotes();
    }
}
