package com.dev4lazy.notepad.utils;

import com.dev4lazy.notepad.data.Note;
import com.dev4lazy.notepad.data.Note2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestNotes {

    private ArrayList<Note> notes;
    private ArrayList<Note2> notes2;

    public TestNotes() {
        notes = new ArrayList<Note>();
        notes2 = new ArrayList<Note2>();
        createNotes();
    }

    private void createNotes() {
        notes.add(
            new Note.NoteBuilder()
                .priority(0)
                .creationDate(System.currentTimeMillis())
                .title("Auchan")
                .content("spodnie, buty, krawat")
                .kind(NoteKind.SHOPPING)
                .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(1)
                    .creationDate(System.currentTimeMillis())
                    .title("BRW")
                    .content("komoda")
                    .kind(NoteKind.SHOPPING)
                    .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(0)
                    .creationDate(System.currentTimeMillis())
                    .title("PWR")
                    .content("zjazd")
                    .kind(NoteKind.PLACE)
                    .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(0)
                    .creationDate(System.currentTimeMillis())
                    .title("Skoda")
                    .content("Codiac")
                    .kind(NoteKind.SHOPPING)
                    .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(1)
                    .creationDate(System.currentTimeMillis())
                    .title("Brzeziński")
                    .content("Rozpoczęcie sezonu")
                    .kind(NoteKind.PERSON)
                    .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(0)
                    .creationDate(System.currentTimeMillis())
                    .title("Kino Muza")
                    .content("MEGAPOLIS")
                    .kind(NoteKind.PLACE)
                    .build()
        );
        notes.add(
            new Note.NoteBuilder()
                    .priority(1)
                    .creationDate(System.currentTimeMillis())
                    .title("Pasibus")
                    .content("")
                    .kind(NoteKind.PLACE)
                    .build()
        );

    }

    private void createNotes2() {
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(0)
                        .creationDate(1000)
                        .title("Auchan")
                        .content("spodnie, buty, krawat")
                        .kind(NoteKind.SHOPPING)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(1)
                        .creationDate(1001)
                        .title("BRW")
                        .content("komoda")
                        .kind(NoteKind.SHOPPING)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(0)
                        .creationDate(1002)
                        .title("PWR")
                        .content("zjazd")
                        .kind(NoteKind.PLACE)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(0)
                        .creationDate(1003)
                        .title("Skoda")
                        .content("Codiac")
                        .kind(NoteKind.SHOPPING)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(1)
                        .creationDate(1004)
                        .title("Brzeziński")
                        .content("Rozpoczęcie sezonu")
                        .kind(NoteKind.PERSON)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(0)
                        .creationDate(1005)
                        .title("Kino Muza")
                        .content("MEGAPOLIS")
                        .kind(NoteKind.PLACE)
                        .build()
        );
        notes2.add(
                new Note2.NoteBuilder()
                        .priority(1)
                        .creationDate(1006)
                        .title("Pasibus")
                        .content("")
                        .kind(NoteKind.PLACE)
                        .build()
        );

    }

    public List<Note2> getNotes2() {
        return notes2;
    }

    public List<Note> getNotes() {
        return notes;
    }
}
