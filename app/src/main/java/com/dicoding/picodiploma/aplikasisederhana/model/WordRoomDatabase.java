package com.dicoding.picodiploma.aplikasisederhana.model;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(application,
                            WordRoomDatabase.class,
                            "word_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
