package com.devpro.takenote.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.devpro.takenote.R;
import com.devpro.takenote.viewmodel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DetailFragment extends Fragment {
    NoteViewModel noteViewModel;
    TextView txt_detail_title;
    TextView txt_detail_date;
    TextView txt_detail_duration;
    TextView txt_detail_content;
    ImageView img_detail_type;
    ImageButton btn_back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        txt_detail_title = view.findViewById(R.id.txt_detail_title);
        txt_detail_date = view.findViewById(R.id.txt_detail_date);
        txt_detail_duration = view.findViewById(R.id.txt_detail_duration);
        txt_detail_content = view.findViewById(R.id.txt_detail_content);
        img_detail_type = view.findViewById(R.id.img_detail_type);
        btn_back = view.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

//        var notes = noteViewModel.getNoteIsCheck().getValue();
//            System.out.printf("du lieu trong");
//            Log.d("du lieu trong", "du lieu trong");

        noteViewModel.getNoteIsCheck().observe(getViewLifecycleOwner(), note -> {
            if (note != null) {

                txt_detail_title.setText(note.getTieuDe());
                txt_detail_date.setText(getTime(note.getNgayTao()));
                txt_detail_duration.setText(note.getThoiLuong() == null ? "00:00" : note.getThoiLuong());
                txt_detail_content.setText(note.getNoiDung());
                img_detail_type.setImageResource(note.getType());
            }

        });
    }


    private String getTime(long ngayTao) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        Date date = new Date(ngayTao);
        return format.format(date).toLowerCase();
    }
}