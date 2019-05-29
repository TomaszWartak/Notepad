package com.dev4lazy.notepad.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.dev4lazy.notepad.utils.TestNotes;

@Database(entities = {Note.class}, version = 1)
public abstract class NotepadDatabase extends RoomDatabase {

    private final static String databaseName = "notepad_database";
    private static volatile NotepadDatabase INSTANCE;
    public abstract NoteDao noteDao();

    static NotepadDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotepadDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotepadDatabase.class, databaseName )
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback =
        new RoomDatabase.Callback(){
            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
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
}