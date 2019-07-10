package com.huanglf.test16.repository.impl;

import androidx.lifecycle.LiveData;

import com.huanglf.test16.repository.INoteRepository;
import com.huanglf.test16.repository.database.AppDatabase;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.database.NoteDAO;
import com.huanglf.test16.util.AppExecutor;

import java.util.List;


/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
public class NoteRepositoryImpl implements INoteRepository {
    private static NoteDAO noteDAO;
    private static NoteRepositoryImpl noteRepositoryImpl;

    public static NoteRepositoryImpl getInstance() {
        if (noteRepositoryImpl == null) {
            noteRepositoryImpl = new NoteRepositoryImpl();
        }
        return noteRepositoryImpl;
    }

    private NoteRepositoryImpl() {
        noteDAO = AppDatabase.getInstance().getNoteDAO();
    }

    @Override
    public void insertNote(final Note note) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.insertNote(note);
            }
        });

    }

    @Override
    public void updateNote(final Note note) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.updateNote(note);
            }
        });

    }

    @Override
    public void deleteOneNote(final Note note) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.deleteOneNote(note);
            }
        });

    }

    @Override
    public void deleteNotes(final Note[] notes) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.deleteNotes(notes);
            }
        });

    }

    @Override
    public void combineNotes(final Note[] notes) {
        final Note[] notesTmp = new Note[notes.length - 1];
        for (int i = 1; i < notes.length; i++) {
            notes[0].setContent(notes[i].getContent());
            notesTmp[i - 1] = notes[i];
        }
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.updateNote(notes[0]);
                noteDAO.deleteNotes(notesTmp);
            }
        });

    }

    @Override
    public void addStar(final Note note) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.updateNote(note);
            }
        });

    }

    @Override
    public void cancelStar(final Note note) {
        AppExecutor executor = AppExecutor.getInstance();
        executor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.updateNote(note);
            }
        });
    }

    @Override
    public LiveData<List<Note>> loadAllNotes() {
        return noteDAO.loadAllNotes();
    }

    @Override
    public LiveData<List<Note>> loadStartNotes() {
        return noteDAO.loadStarNotes();
    }
}
