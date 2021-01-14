package com.example.android2_1.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2_1.Models.Note;
import com.example.android2_1.OnItemClickListener;
import com.example.android2_1.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final ArrayList<Note> list;
    private OnItemClickListener onItemClickListener;

    public NoteAdapter() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Note("Text", i + ""));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Note note) {
        list.add(0, note);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Note getItem(int pos) {
        return list.get(pos);
    }

    public void remove(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDate = itemView.findViewById(R.id.txt_date);
            itemView.setOnClickListener(v ->
                    onItemClickListener.onClick(getAdapterPosition()));
            itemView.setOnLongClickListener(v -> {
                onItemClickListener.longClick(getAdapterPosition());
                return true;
            });
        }

        public void onBind(Note note) {
            txtTitle.setText(note.getTitle());
            txtDate.setText(note.getDate());
        }
    }
}
