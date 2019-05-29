package com.dev4lazy.notepad.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dev4lazy.notepad.utils.DateConverter;
import com.dev4lazy.notepad.utils.NoteKind;
import com.dev4lazy.notepad.utils.NoteKindConverter;

import java.util.Date;

@Entity(
        tableName = "notes2"/*,
        indices = {
                @Index(name = "index_id_date", value = {"id", "creation_date"})}*/
)
/*
@TypeConverters({
        DateConverter.class,
        NoteKindConverter.class
})
*/

public class Note2 {

        @PrimaryKey(autoGenerate = true)
        private int id;
        private int priority;
        @ColumnInfo(name = "creation_date")
        private /*Date*/ int creationDate;
        private String title;
        private String content;
        private int kind;

        public Note2() {

        }

        public Note2(int priority, /*Date*/ int  date, String title, String content, int kind) {
            this.priority = priority;
            this.creationDate = date;
            this.title = title;
            this.content = content;
            this.kind = kind;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) { this.id = id; }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public /*Date*/ int getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(/*Long*/ int creationDate) {
            this.creationDate = creationDate /* Date(creationDate)*/;
        }

        /*public void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }*/
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

    //------------------- NoteBuilder
    public static class NoteBuilder {

        private int priority;
        private /*Date*/ int creationDate;
        private String title;
        private String content;
        private int kind;

        public Note2.NoteBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }

        /*public Note2.NoteBuilder creationDate(long date) {
            this.creationDate = new Date(new Long(date));
            return this;
        }

        public Note2.NoteBuilder creationDate(Date date) {
            this.creationDate = date;
            return this;
        }
        */
        public Note2.NoteBuilder creationDate(int date) {
            this.creationDate = date;
            return this;
        }

        public Note2.NoteBuilder title(String title) {
            String result = "";
            if ((title!=null)&&(!title.isEmpty())) {
                result = title;
            }
            this.title = result;
            return this;
        }

        public Note2.NoteBuilder content(String content) {
            String result = "";
            if ((content!=null)&&(!content.isEmpty())) {
                result = content;
            }
            this.content = result;
            return this;
        }

        public Note2.NoteBuilder kind(int kind) {
            this.kind = kind;
            return this;
        }

        public Note2.NoteBuilder kind(NoteKind noteKind) {
            // todo jakieś sprawdzanie null itp
            this.kind = noteKind.ordinal();
            return this;
        }

        public Note2 build() {
            return new Note2(priority, creationDate,title, content, kind);
        }
    }
}