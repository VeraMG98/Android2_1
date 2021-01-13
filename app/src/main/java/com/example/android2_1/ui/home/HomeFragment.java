package com.example.android2_1.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2_1.Models.Note;
import com.example.android2_1.OnItemClickListener;
import com.example.android2_1.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        view.findViewById(R.id.fab).setOnClickListener(v -> openForm());
        setFragmentListener();
        init();
    }

    private void init() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Note note = adapter.getItem(pos);
                Toast.makeText(requireContext(), note.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void longClick(int pos) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Хотите удалить ?")
                        .setMessage("Запись будет удалена")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(pos);
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
            }
        });
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener(
                "rk_form",
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    Note note = (Note) result.getSerializable("note");
                    adapter.addItem(note);
                });

    }

    private void openForm() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.form_fragment);
    }
}