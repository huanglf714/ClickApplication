package com.huanglf.test16.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "Database.db";
    private static volatile AppDatabase instance;

    public synchronized static AppDatabase getInstance(){
        return instance;
    }

    public static AppDatabase createInstance(Context context){
        if (instance==null){
            instance =  Room.databaseBuilder(context,AppDatabase.class,DB_NAME).build();
        }
        return instance;
    }

    public abstract NoteDAO getNoteDAO();

}
