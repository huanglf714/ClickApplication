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

    //选择笔记标签
    public void selectTag(int tagId,Note note){
        note.setTagId(tagId);
        noteRepository.updateNote(note);
    }

    //保存笔记
    public void saveNote(Note note, Boolean isNew) {
        if(isNew) {
            noteRepository.insertNote(note);
        }else {
            noteRepository.updateNote(note);
        }
    }
}
