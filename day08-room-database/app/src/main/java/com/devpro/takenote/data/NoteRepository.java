package com.devpro.takenote.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.devpro.takenote.model.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NoteRepository {
    public final NoteDao dao;
    public final LiveData<List<Note>> allNotes;

    public NoteRepository(Context context) {
        dao = NoteDatabase.getDatabase(context).noteDao();
        allNotes = dao.getAll();
    }

    public void insert(Note note) {
        NoteDatabase.EXECUTOR_SERVICE.execute(() -> dao.insert(note));
    }

    public void update(Note note) {
        NoteDatabase.EXECUTOR_SERVICE.execute(() -> dao.update(note));
    }

    public void delete(Note note) {
        NoteDatabase.EXECUTOR_SERVICE.execute(() -> dao.delete(note));
    }

    public void deleteAll(Note... notes) {
        NoteDatabase.EXECUTOR_SERVICE.execute(() -> dao.deleteAll(notes));
    }

    public LiveData<List<Note>> getAll() {
        return allNotes;
    }

    public void insertAll(Note... notes) {
        NoteDatabase.EXECUTOR_SERVICE.execute(() -> dao.insertAll(notes));
    }

}
