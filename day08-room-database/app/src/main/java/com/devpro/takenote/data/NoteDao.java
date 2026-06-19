package com.devpro.takenote.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.devpro.takenote.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Insert
    void insertAll(Note... notes);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Delete
    void deleteAll(Note... notes);

    @Query("Select * from note ORDER By ngayTao DESC")
    LiveData<List<Note>> getAll();
}
