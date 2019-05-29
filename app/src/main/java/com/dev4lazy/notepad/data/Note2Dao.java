package com.dev4lazy.notepad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface Note2Dao {

    @Insert
    void insert(Note2 note);

    @Update
    void update(Note2 note);

    @Delete
    void delete(Note2 note);

    @Query("DELETE FROM notes2")
    void deleteAll();

    @Query("SELECT * FROM notes2 WHERE id= :id")
    List<Note2> findNoteById(String id);

    //todo findNoteByName like

    @Query("SELECT * FROM notes2 ORDER BY title ASC")
    List<Note2> getNotesByTitle();

    @Query("SELECT * FROM notes2 ORDER BY priority ASC, creation_date DESC")
    List<Note2> getNotesByPriorityAndCreationDate();

    @Query("SELECT * FROM notes2")
    List<Note2> getAllNotes();

    //todo findNotesByWholaTextSearch
    @RawQuery(observedEntities = Note2.class)
    List<Note2>getNotesViaQuery(SupportSQLiteQuery query) ;
}
