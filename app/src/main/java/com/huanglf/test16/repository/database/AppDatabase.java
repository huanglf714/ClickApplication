package com.huanglf.test16.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
@Database(entities = {Note.class, Tag.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "Database.db";
    private static volatile AppDatabase instance;

    public synchronized static AppDatabase getInstance() {
        return instance;
    }

    public static AppDatabase createInstance(Context context) {
        if (instance == null) {
//            instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            instance = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DB_NAME)
                    .addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_3).
                            addMigrations(MIGRATION_3_4).build();
        }
        return instance;
    }

    public abstract NoteDAO getNoteDAO();

    public abstract TagDAO getTagDAO();


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `tag` (" +
                    "  `id` INTEGER NOT NULL," +
                    "  `name` TEXT, " +
                    "  `color` TEXT DEFAULT '#fff'," +
                    "  `number` INTEGER NOT NULL DEFAULT 0," +
                    "  PRIMARY KEY (`id`));");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `tag` " +
                    "ADD COLUMN `image` INTEGER NOT NULL default 0;");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `note` " +
                    "ADD COLUMN `tag_id` INTEGER NOT NULL default 0;");
        }
    };
}
