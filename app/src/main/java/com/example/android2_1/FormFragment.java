package com.example.android2_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android2_1.Models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormFragment extends Fragment {
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.edit_txt);
        view.findViewById(R.id.btn_save).setOnClickListener(v -> save());
    }

    private void save() {
        String text = editText.getText().toString().trim();
        String time = getTime();
        Note note = new Note(text, time);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        getParentFragmentManager().setFragmentResult("rk_form", bundle);
        close();
    }

    private String getTime() {
        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return timeFormat.format(currentDate);
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}