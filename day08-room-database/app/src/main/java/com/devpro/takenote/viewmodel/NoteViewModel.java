package com.devpro.takenote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.devpro.takenote.data.NoteRepository;
import com.devpro.takenote.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository repository;
    private final LiveData<List<Note>> allNotes;
    MutableLiveData<Note> noteIsCheck = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAll();
    }
    public LiveData<Note> getNoteIsCheck() {
        return noteIsCheck;
    }
    public void setNoteIsCheck(Note noteIsCheck) {
        this.noteIsCheck.setValue(noteIsCheck);
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
    public void insert(Note note){
        repository.insert(note);
    }
    public void update(Note note){
        repository.update(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public void deleteAll(Note... notes){
        repository.deleteAll(notes);
    }
    public void insertAll(Note... notes){
        repository.insertAll(notes);
    }
}
