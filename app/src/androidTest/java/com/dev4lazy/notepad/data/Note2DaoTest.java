package com.dev4lazy.notepad.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import com.dev4lazy.notepad.utils.NoteKind;
import com.dev4lazy.notepad.utils.TestNotes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Note2DaoTest {

    static Notepad2Database database;
    private Note2Dao dao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        //database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),Notepad2Database.class)
        database = Notepad2Database.getDatabase(context);
        /*database = Room.inMemoryDatabaseBuilder(context,Notepad2Database.class)
                .build(); */
        dao = database.note2Dao();
    }

    @Test
    public void insert7Notes() {
        dao.deleteAll();
        addTestNotes();
        List<Note2> notes2 = database.note2Dao().getAllNotes();
        assertNotNull(notes2);
        assertEquals(7, notes2.size());
    }

    private void addTestNotes() {
        TestNotes testNotes = new TestNotes();
        for (Note2 note2 : testNotes.getNotes2()) {
            dao.insert(note2);
        }
    }

    @Test
    public void insert1Note() {
        dao.deleteAll();
        Note2 note2 = new Note2.NoteBuilder()
                .priority(0)
                .creationDate(3000)
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        dao.insert(note2);
        List<Note2> notes2 = dao.getAllNotes();
        assertNotNull(notes2);
        assertEquals(1, notes2.size());
    }

    @Test
    public void findNoteById() {
        dao.deleteAll();
        Note2 note2 = new Note2.NoteBuilder()
                .priority(0)
                .creationDate(3000)
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        dao.insert(note2);
        List<Note2> notes2 = dao.findNoteById("1");
        assertNotNull(notes2);
        assertEquals(1, notes2.get(0).getId());
    }

    @Test
    public void updateNote() {
        dao.deleteAll();
        Note2 note2 = new Note2.NoteBuilder()
                .priority(0)
                .creationDate(3000)
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        dao.insert(note2);
        note2 = dao.findNoteById("1").get(0);
        note2.setTitle("Carrefour");
        dao.update(note2);
        note2 = dao.findNoteById("1").get(0);
        assertEquals("Carrefour", note2.getTitle());
    }

    @Test
    public void deleteNote() {
        dao.deleteAll();
        Note2 note2 = new Note2.NoteBuilder()
                .priority(0)
                .creationDate(3000)
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        dao.insert(note2);
        note2 = dao.getAllNotes().get(0);
        dao.delete(note2);
        List<Note2> notes2 = dao.getAllNotes();
        assertEquals(0, notes2.size());
    }

    @Test
    public void getNotesByTitle() {
        dao.deleteAll();
        addTestNotes();
        List<Note2> notes2 = dao.getNotesByTitle();
        assertEquals("Auchan", notes2.get(0).getTitle());
    }

    @Test
    public void getNotesByPriorityAndCreationDate() {
        dao.deleteAll();
        addTestNotes();
        List<Note2> notes2 = dao.getNotesByPriorityAndCreationDate();
        assertEquals("Auchan", notes2.get(0).getTitle());
    }

    @Test
    public void getNotesViaQuery() {
        dao.deleteAll();
        addTestNotes();
        SupportSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM notes2 WHERE title = ?", new String[]{"Pasibus"});
        List<Note2> notes2 = dao.getNotesViaQuery(query);
        assertEquals("Pasibus", notes2.get(0).getTitle());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }
}