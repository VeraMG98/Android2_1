package com.example.android2_1.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android2_1.Prefs;
import com.example.android2_1.R;
import com.example.android2_1.ui.board.BoardFragment;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    ImageView icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        icon = view.findViewById(R.id.profil_icon);
        icon.setOnClickListener(v -> mGetContext.launch("image/*"));
        setHasOptionsMenu(true);
        return view;
    }

    ActivityResultLauncher<String> mGetContext = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    icon.setImageURI(result);
                }
            });

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_pref_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.skip) {
            new Prefs(requireContext()).deleteStatus();
            requireActivity().finish();
            return true;
        } else
            return super.onOptionsItemSelected(item);

    }

}