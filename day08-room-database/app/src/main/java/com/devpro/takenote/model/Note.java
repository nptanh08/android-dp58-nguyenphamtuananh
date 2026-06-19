package com.devpro.takenote.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tieuDe;
    private String noiDung;
    private int type;
    private long ngayTao;
    private String thoiLuong;
    private boolean isNew;

    public Note(String tieuDe, String noiDung, int type, long ngayTao, String thoiLuong, boolean isNew) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.type = type;
        this.ngayTao = ngayTao;
        this.thoiLuong = thoiLuong;
        this.isNew = isNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(long ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
