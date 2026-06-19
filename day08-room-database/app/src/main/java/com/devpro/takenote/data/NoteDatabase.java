package com.devpro.takenote.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.devpro.takenote.model.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    public static volatile NoteDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (NoteDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NoteDatabase.class,
                            "note_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
