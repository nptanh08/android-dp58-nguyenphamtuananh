package com.devpro.takenote.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.devpro.takenote.R;
import com.devpro.takenote.model.Note;
import com.devpro.takenote.viewmodel.NoteViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AddNoteFragment extends Fragment {
    NoteViewModel noteViewModel;
    TextInputEditText edt_title;
    EditText edt_content;
    RadioButton rb_video, rb_audio, rb_folder;
    Button btn_save, btn_cancel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        edt_title = view.findViewById(R.id.edt_title);
        edt_content = view.findViewById(R.id.edt_content);
        rb_video = view.findViewById(R.id.rb_video);
        rb_audio = view.findViewById(R.id.rb_audio);
        rb_folder = view.findViewById(R.id.rb_folder);
        btn_save = view.findViewById(R.id.btn_save);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(v -> {
            String title = edt_title.getText().toString();
            String content = edt_content.getText().toString();
            int type = 0;
            if (rb_video.isChecked()) {
                type = R.drawable.ytb;
            } else if (rb_audio.isChecked()) {
                type = R.drawable.micro;
            } else if (rb_folder.isChecked()) {
                type = R.drawable.file;
            }
            noteViewModel.insert(new Note(title, content, type, System.currentTimeMillis(), null, true));
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        btn_cancel.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }


}