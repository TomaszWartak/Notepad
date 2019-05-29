package com.dev4lazy.notepad.data;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.platform.app.InstrumentationRegistry;

import com.dev4lazy.notepad.utils.NoteKind;
import com.dev4lazy.notepad.utils.TestNotes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class Note2DaoTest {

    static Notepad2Database database;

    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),Notepad2Database.class)
                .addCallback(roomDatabaseCallback)
                .build();
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
        private final Note2Dao dao;
        private final TestNotes testNotes;

        PopulateDbAsync(Notepad2Database db) {
            dao = db.note2Dao();
            testNotes = new TestNotes();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            for (Note2 note2 : testNotes.getNotes2()) {
                dao.insert(note2);
            }
            return null;
        }
    }

    @Test
    public void insertNote() {
        Note2 note2 = new Note2.NoteBuilder()
                .priority(0)
                .creationDate(3000)
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        database.note2Dao().insert(note2);
        List<Note2> notes2 = database.note2Dao().getAllNotes();
        assertNotNull(notes2);
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }
}