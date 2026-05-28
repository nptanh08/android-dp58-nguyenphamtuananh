package com.devpro.btvn.repository;

import com.devpro.btvn.model.FakeData;
import com.devpro.btvn.model.Product;

import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = FakeData.getData();
    }

    public List<Product> getProducts() {
        return products;
    }

}
