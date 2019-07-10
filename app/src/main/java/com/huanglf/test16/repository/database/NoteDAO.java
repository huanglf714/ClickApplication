package com.huanglf.test16.repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
@Dao
public interface NoteDAO {
    @Insert()
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteOneNote(Note note);

    @Delete
    void deleteNotes(Note[] notes);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> loadAllNotes();
}
