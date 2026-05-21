package com.devpro.day06_list_and_recycler_view.utils;

import com.devpro.day06_list_and_recycler_view.model.Product;

public interface onClickProductListener {
    void onClickAddProduct(Product product);
    void onClickChangeFavourite(Product product);
    void onClickRemoveProduct(Product product);
}