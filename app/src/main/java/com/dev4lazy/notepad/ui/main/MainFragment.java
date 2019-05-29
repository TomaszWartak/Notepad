package com.dev4lazy.notepad.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev4lazy.notepad.R;
import com.dev4lazy.notepad.data.Note;
import com.dev4lazy.notepad.data.NoteListAdapter;
import com.dev4lazy.notepad.utils.NoteKind;

import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private NoteListAdapter adapter;

    private TextView noteId;
    private FloatingActionButton floatingActionButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerSetup();
        floatingActionButtonSetup();
        // todo
        // noteId = getView().findViewById(R.id.note_row);
        observerSetup();
    }

    @Override
    public void onClick(View view) {
        /* todo
        Intent intent = new Intent(NotesListActivity.this, AddNoteActivity.class);
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
        */
        Note note = new Note.NoteBuilder()
                .priority(0)
                .creationDate(System.currentTimeMillis())
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build();
        mainViewModel.insertNote(note);
    }

    private void observerSetup() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                if (adapter == null) {
                    adapter = new NoteListAdapter(R.layout.note_liste_item);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.setNoteList(notes);
                }
            }
        });
    }

/* todo
        mainViewModel.getSearchResults().observe(
            this,
            new Observer<List<Note>>() {
                @Override
                public void onChanged(@Nullable final List<Note> notes) {

                    if (notes.size() > 0) {
                        noteId.setText(String.format(Locale.US, "%d",
                                notes.get(0).getId()));
                    } else {
                        noteId.setText("No Match");
                    }
                }
            });
*/


    private void recyclerSetup() {
        adapter = new NoteListAdapter(R.layout.note_liste_item);
        recyclerView = getView().findViewById(R.id.notes_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // todo ????
        recyclerView.setAdapter(adapter);
    }

    private void floatingActionButtonSetup() {
        floatingActionButton = getView().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
    }

}
