package com.huanglf.test16.ui.ty;

import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.impl.NoteRepositoryImpl;
import com.huanglf.test16.util.MessageUtil;

/**
 * date：2019/7/8
 * desc：
 * TO do save note into room database
 *
 * @author TY
 */
public class SaveViewModel extends ViewModel {
    private INoteRepository noteRepository = NoteRepositoryImpl.getInstance();

    public String getTitle(String noteText) {
        String[] texts = noteText.split("\n");
        if (texts != null) {
            return texts[0];
        } else {
            MessageUtil.error("笔记为空");
            return null;
        }
    }

    //更新笔记
    public void saveNote(int id, String title, String content, String date) {
        Note note = new Note(title, content, date);
        note.setId(id);
        noteRepository.updateNote(note);
    }

    //新建笔记
    public void saveNote(String title, String content, String date) {
        Note note = new Note(title, content, date);
        note.setCreateDate(date);
        noteRepository.insertNote(note);
    }
}
