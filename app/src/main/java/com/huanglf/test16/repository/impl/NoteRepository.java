package com.huanglf.test16.repository.impl;

import android.content.Context;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.AppDatabase;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.database.NoteDAO;


/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
public class NoteRepository implements INoteRepository {
    private static NoteDAO noteDAO;
    public NoteRepository(Context context){
        noteDAO = AppDatabase.getInstance(context).getNoteDAO();
    }

    @Override
    public void insertNote(Note note) {
        noteDAO.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        noteDAO.updateNote(note);
    }

    @Override
    public void deleteOneNote(Note note) {
        noteDAO.deleteOneNote(note);
    }

    @Override
    public void deleteNotes(Note[] notes) {
        noteDAO.deleteNotes(notes);
    }

    @Override
    public void combineNotes(Note[] notes) {
        Note[] notesTmp = new Note[notes.length-1];
        for(int i=1;i<notes.length;i++){
            notes[0].setContent(notes[i].getContent());
            notesTmp[i-1] = notes[i];
        }
        noteDAO.updateNote(notes[0]);
        noteDAO.deleteNotes(notesTmp);
    }

    @Override
    public void addStar(Note note) {
        noteDAO.updateNote(note);
    }

    @Override
    public void cancelStar(Note note) {
        noteDAO.updateNote(note);
    }

    @Override
    public Note[] loadAllNotes() {
        return noteDAO.loadAllNotes();
    }
}
