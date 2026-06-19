package com.devpro.takenote.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.takenote.R;
import com.devpro.takenote.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    int layout;
    onCickItem listener;

    List<Note> notes;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

    public NoteAdapter(int layout, List<Note> notes) {
        this.layout = layout;
        this.notes = notes;
    }
    public void setClick(onCickItem listeners) {
        listener = listeners;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        Date date = new Date(note.getNgayTao());
        holder.txt_thoiGian.setText(format.format(date).toLowerCase());
        holder.txt_thoiLuong.setText(note.getThoiLuong() == null ? "00:00" : note.getThoiLuong());
        holder.img_pic.setImageResource(note.getType());
        holder.txt_title.setText(note.getTieuDe());
        holder.img_detail.setOnClickListener(v->{
            listener.onClickDetail(note);
        });


    }

    @Override
    public int getItemCount() {
        if (notes == null){
            return 0;
        }
        return notes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Note> n) {
        // Khởi tạo danh sách nếu nó chưa từng tồn tại
        if (notes == null) {
            notes = new ArrayList<>();
        }

        notes.clear();

        // Chỉ thêm vào nếu danh sách n truyền vào có dữ liệu
        if (n != null) {
            notes.addAll(n);
        }
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img_pic;
        TextView txt_title;
        TextView txt_thoiGian;
        TextView txt_thoiLuong;
        ImageView img_detail;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            img_pic = itemView.findViewById(R.id.img_pic);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_thoiGian = itemView.findViewById(R.id.txt_thoiGian);
            txt_thoiLuong = itemView.findViewById(R.id.txt_thoiLuong);
            img_detail = itemView.findViewById(R.id.img_detail);
        }
    }
    private String getTime(long ngayTao) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        Date date = new Date(ngayTao);
        return format.format(date).toLowerCase();
    }
}
