package com.huanglf.test16;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.huanglf.test16.repository.database.AppDatabase;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.database.NoteDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    AppDatabase db;
    NoteDAO noteDAO;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = AppDatabase.getInstance(context);
        noteDAO = db.getNoteDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void NoteTest() {
        Note note = new Note();
        note.setContent("11001");
        note.setCreateDate(new Date().toString());
        noteDAO.insertNote(note);
        Note[] notes = noteDAO.loadAllNotes();
        for (Note note1:notes
        ) {
            System.out.println(note1);
        }
    }
}
