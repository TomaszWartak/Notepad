package com.dev4lazy.notepad.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;

public class NotesRepository /*todo implements AsyncResult */ {

    private NoteDao noteDao;
    private MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    // todo private LiveData<List<Note>> notes;

    public NotesRepository(Application application) {
        NotepadDatabase database = NotepadDatabase.getDatabase(application);
        noteDao = database.noteDao();
        // todo jeli jest zaożony filtr to FetchNotes go nie uwzgldni
        fetchNotes();
    }

    public MutableLiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        InsertAsyncTask task = new InsertAsyncTask(this);
        task.execute(note);
    }

    public void deleteNote(Note note) {
        DeleteAsyncTask task = new DeleteAsyncTask(this);
        task.execute(note);
    }

    public void fetchNotes() {
        FetchAsyncTask task = new FetchAsyncTask( this );
        task.execute();
    }

    public void filterNotes(String filter) {
        // todo tutaj trzeba poda quey SQL
        RawQueryAsyncTask task = new RawQueryAsyncTask(this);
        task.execute(filter);
    }

    private NoteDao getDao() {
        return noteDao;
    }

    private void asyncFinished(List<Note> results) {
        notes.setValue(results);
    }

    // todo a po insert i delete nie trzeba pobra nowej listy?
    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NotesRepository repository = null;

        protected InsertAsyncTask(NotesRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            repository.getDao().insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NotesRepository repository = null;

        protected DeleteAsyncTask(NotesRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            repository.getDao().delete(params[0]);
            return null;
        }
    }

    private static class FetchAsyncTask extends AsyncTask<String, Void, List<Note>> {

        private NotesRepository repository = null;

        protected FetchAsyncTask(NotesRepository repository) {
            this.repository = repository;
        }

        @Override
        protected List<Note> doInBackground(final String... params) {
            // todo return repository.getDao().getNotesByPriorityAndCreationDate().getValue();
            return repository.getDao().getAllNotes().getValue();
        }

        @Override
        protected void onPostExecute(List<Note> result) {
            repository.asyncFinished(result);
        }
    }


    private static class RawQueryAsyncTask extends AsyncTask<String, Void, List<Note>> {

        private NotesRepository repository = null;

        protected RawQueryAsyncTask(NotesRepository repository) {
            this.repository = repository;
        }

        @Override
        protected List<Note> doInBackground(final String... params) {
            return repository.getDao().getNotesViaQuery(new SimpleSQLiteQuery(params[0])).getValue();
        }

        @Override
        protected void onPostExecute(List<Note> result) {
            repository.asyncFinished(result);
        }
    }


}
