package com.example.android2_1.ui.board;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2_1.Descriptions;
import com.example.android2_1.OnItemClickListener;
import com.example.android2_1.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private final Context context;

    public BoardAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Descriptions descriptions = new Descriptions();
        TextView txtTitle, txtDescription;
        ImageView imgIcon;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title_board);
            txtDescription = itemView.findViewById(R.id.txt_title_board2);
            imgIcon = itemView.findViewById(R.id.img_board);
            button = itemView.findViewById(R.id.btn_start);
            button.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void onBind(int position) {
            txtTitle.setText(descriptions.getTitle(position));
            txtDescription.setText(descriptions.getDescription(position));
            imgIcon.setImageDrawable(context.getResources().getDrawable(descriptions.getIcon(position)));
            if (position == 2)
                button.setVisibility(View.VISIBLE);
        }
    }
}
