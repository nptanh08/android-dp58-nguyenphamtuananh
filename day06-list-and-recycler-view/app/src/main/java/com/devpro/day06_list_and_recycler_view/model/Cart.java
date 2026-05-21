package com.devpro.day06_list_and_recycler_view.model;

public class Cart {
    private Product product;
    private int soLuong;

    public Cart(Product product, int soLuong) {
        this.product = product;
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return product.getPrice() * soLuong;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
