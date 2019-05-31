package com.dev4lazy.notepad.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.dev4lazy.notepad.utils.NoteKind;
import com.dev4lazy.notepad.utils.TestNotes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NoteDaoTest {

    static NotepadDatabase database;

    @Before
    public void setUp() throws Exception {
       // Context context = ApplicśationProvider.getApplicationContext();
//        database = NotepadDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().getContext());
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),NotepadDatabase.class)
                .addCallback(roomDatabaseCallback)
                .build();
        //NoteDao noteDao = database.noteDao();
    }

    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(database).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final NoteDao dao;
        private final TestNotes testNotes;

        PopulateDbAsync(NotepadDatabase db) {
            dao = db.noteDao();
            testNotes = new TestNotes();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            for (Note note : testNotes.getNotes()) {
                dao.insert(note);
            }
            return null;
        }
    }

    @Test
    public void insertNote() {
        Note note = new Note.NoteBuilder()
                .priority(0)
                .creationDate(System.currentTimeMillis())
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        database.noteDao().insert(note);
        List<Note> notes = database.noteDao().getAllNotes().getValue();
        assertNotNull(notes);
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }
}