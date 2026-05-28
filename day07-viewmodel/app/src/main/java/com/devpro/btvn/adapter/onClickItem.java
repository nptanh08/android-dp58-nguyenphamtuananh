package com.devpro.btvn.adapter;

import com.devpro.btvn.model.Product;

public interface onClickItem {
    void onClick(Product product);
    void onClickBack();
    void onClickFavorite(Product product);
}
