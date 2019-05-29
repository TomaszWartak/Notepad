package com.dev4lazy.notepad.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dev4lazy.notepad.data.Note;
import com.dev4lazy.notepad.data.NotesRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private NotesRepository repository;
    // todo private LiveData<List<Note>> allNotes;
    private MutableLiveData<List<Note>> notes;

    public MainViewModel (Application application) {
        super(application);
        repository = new NotesRepository(application);
        fetchNotes();
        // todo
        // notes = repository.getNotes();
    }

    MutableLiveData<List<Note>> getNotes() {
        return notes;
    }

    /* todo
    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    */

    public void insertNote(Note note) {
        repository.insertNote(note);
    }

    public void deleteNote(Note note) {
        repository.deleteNote(note);
    }

    public void findNote(String name) {
        // todo repository.findNote(name);
    }

    public void fetchNotes() {
        repository.fetchNotes();
        notes = repository.getNotes();
    }

}
