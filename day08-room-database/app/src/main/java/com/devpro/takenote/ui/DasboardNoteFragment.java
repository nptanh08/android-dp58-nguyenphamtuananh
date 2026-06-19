package com.devpro.takenote.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.devpro.takenote.R;
import com.devpro.takenote.adapter.NoteAdapter;
import com.devpro.takenote.viewmodel.NoteViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class DasboardNoteFragment extends Fragment {
    RecyclerView recycle;
    ExtendedFloatingActionButton btn_add;
    NoteAdapter noteAdapter;
    NoteViewModel noteViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dasboard_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle = view.findViewById(R.id.recycle);
        btn_add = view.findViewById(R.id.btn_add);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        noteAdapter = new NoteAdapter(R.layout.note_item, noteViewModel.getAllNotes().getValue());

        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setAdapter(noteAdapter);

        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), n -> {
            noteAdapter.setList(n);
        });

        btn_add.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new AddNoteFragment())
                    .addToBackStack(null)
                    .commit();
        });

        noteAdapter.setClick(note -> {
            noteViewModel.setNoteIsCheck(note);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new DetailFragment())
                    .addToBackStack(null)
                    .commit();

        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}