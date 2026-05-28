package com.devpro.btvn.model;

public class Product {
    private int id;
    private int imageProduct;
    private String nameProduct;
    private String moTa;
    private String thoiGianNau;
    private String mucDo;
    private String servings;
    private boolean isFavorite;

    public Product() {
    }

    public Product(int id, int imageProduct, String nameProduct, String moTa, String thoiGianNau, String mucDo, String servings, boolean isFavorite) {
        this.id = id;
        this.imageProduct = imageProduct;
        this.nameProduct = nameProduct;
        this.moTa = moTa;
        this.thoiGianNau = thoiGianNau;
        this.mucDo = mucDo;
        this.servings = servings;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThoiGianNau() {
        return thoiGianNau;
    }

    public void setThoiGianNau(String thoiGianNau) {
        this.thoiGianNau = thoiGianNau;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
