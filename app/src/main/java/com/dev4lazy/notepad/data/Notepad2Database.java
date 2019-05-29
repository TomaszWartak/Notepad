package com.dev4lazy.notepad.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dev4lazy.notepad.utils.TestNotes;

@Database(entities = {Note2.class}, version = 1)
public abstract class Notepad2Database extends RoomDatabase {

    private final static String databaseName = "notepad2_database";
    private static volatile Notepad2Database INSTANCE;
    public abstract Note2Dao note2Dao();

    static Notepad2Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Notepad2Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Notepad2Database.class, databaseName )
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
}
