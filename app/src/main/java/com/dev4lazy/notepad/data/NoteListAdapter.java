package com.dev4lazy.notepad.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.dev4lazy.notepad.R;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    private int noteItemLayout;
    private List<Note> noteList;

    public NoteListAdapter(int layoutId) {
        noteItemLayout = layoutId;
    }

    public void setNoteList(List<Note> notes) {
        noteList = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList == null ? 0 : noteList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(noteItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(noteList.get(listPosition).getId());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.note_row);
        }
    }
}
