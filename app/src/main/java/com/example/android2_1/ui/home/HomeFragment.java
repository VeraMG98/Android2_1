package com.example.android2_1.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2_1.App;
import com.example.android2_1.Models.Note;
import com.example.android2_1.OnItemClickListener;
import com.example.android2_1.Prefs;
import com.example.android2_1.R;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment{
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private boolean setUpAdd = false;
    private int position;
    List<Note> list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
        setHasOptionsMenu(true);
        loadData();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort_for_alf) {
            adapter.sortList(App.getAppDataBase().noteDao().sortAll());
            return true;
        } else if(item.getItemId() == R.id.sort_for_time){
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            setUpAdd = false;
            openForm(null);
        });
        setFragmentListener();
        init();
    }

    private void loadData() {
        list = App.getAppDataBase().noteDao().getAll();
        adapter.setList(list);
    }

    private void init() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                setUpAdd = true;
                position = pos;
                Note note = adapter.getItem(pos);
                openForm(note);
            }

            @Override
            public void longClick(int pos) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Хотите удалить ?")
                        .setMessage("Запись будет удалена")
                        .setPositiveButton("OK", (dialog, which) ->
                        {
                            App.getAppDataBase().noteDao().delete(adapter.getItem(pos));
                            App.getAppDataBase().noteDao().update(adapter.getItem(pos));
                            adapter.remove(pos);
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
                    if (setUpAdd){
                        adapter.setItem(note, position);
                    } else {
                        adapter.addItem(note);
                    }
                });
    }

    private void openForm(Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.form_fragment, bundle);
    }
}