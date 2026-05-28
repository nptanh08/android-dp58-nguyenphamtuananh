package com.devpro.btvn.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.devpro.btvn.model.Product;
import com.devpro.btvn.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<List<Product>> products;
    private MutableLiveData<Product> tempProduct;

    public LiveData<Product> getTempProduct() {
        return tempProduct;
    }

    public void setTempProduct(Product product) {
        tempProduct.setValue(product);
    }

    public ProductViewModel() {
        products = new MutableLiveData<>();
        tempProduct = new MutableLiveData<>();
        this.productRepository = new ProductRepository();
        products.setValue(productRepository.getProducts());
    }
    public void updateItem(Product product){
        List<Product> list = products.getValue();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == product.getId()) {
                    list.set(i, product);
                    break;
                }
            }
            products.setValue(list);
        }
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }


}
