package com.devpro.day06_list_and_recycler_view.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.devpro.day06_list_and_recycler_view.R;
import com.devpro.day06_list_and_recycler_view.adapter.ProductAdapter;
import com.devpro.day06_list_and_recycler_view.model.Cart;
import com.devpro.day06_list_and_recycler_view.model.FakeData;
import com.devpro.day06_list_and_recycler_view.model.Product;
import com.devpro.day06_list_and_recycler_view.utils.Contains;
import com.devpro.day06_list_and_recycler_view.utils.onClickProductListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    TextView txtSumProductCart;
    ImageButton imgbOpenCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coffee_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recycle_menu);
        imgbOpenCart = findViewById(R.id.imgb_open_cart);
        txtSumProductCart = findViewById(R.id.txt_sum_product_cart);

        var productList = FakeData.getCoffeeMenu();
        List<Cart> cartList = layData();

        txtSumProductCart.setText(String.valueOf(cartList.size()));

        imgbOpenCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        productAdapter = new ProductAdapter(productList, R.layout.produc_item);
        productAdapter.updateCartList(cartList);

        productAdapter.setOnItemClickListener(new onClickProductListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClickAddProduct(Product product) {
                var cart = getCart(cartList, product);
                if (cart != null) {
                    cart.setSoLuong(getSoLuong(cartList, product) + 1);
                } else {
                    cartList.add(new Cart(product, 1));
                }
                luuData(cartList);
                txtSumProductCart.setText(String.valueOf(cartList.size()));
                productAdapter.updateCartList(cartList);            }

            @Override
            public void onClickChangeFavourite(Product product) {
                product.setFavourite(!product.isFavourite());
                luuData(cartList);
                productAdapter.updateCartList(cartList);
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClickRemoveProduct(Product product) {
                var cart = getCart(cartList, product);
                if (cart != null) {
                    int soLuongHienTai = cart.getSoLuong();
                    cart.setSoLuong(soLuongHienTai - 1);
                    if (cart.getSoLuong() == 0) {
                        cartList.remove(cart);
                    }
                }
                luuData(cartList);
                txtSumProductCart.setText(String.valueOf(cartList.size()));
                productAdapter.updateCartList(cartList);            }
        });

        recyclerView.setLayoutManager(new
                GridLayoutManager(this, 2));
        recyclerView.setAdapter(productAdapter);
    }

    private List<Cart> layData() {
        Gson gson = new Gson();
        SharedPreferences preferences = getSharedPreferences(Contains.KEY_PREFERENCES, MODE_PRIVATE);
        String json = preferences.getString(Contains.KEY_JSON_CART, null);
        if (json == null) return new ArrayList<Cart>();
        var list = new TypeToken<ArrayList<Cart>>() {
        }.getType();
        ArrayList<Cart> carts = gson.fromJson(json, list);
        return carts;
    }

    private void luuData(List<Cart> carts) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(Contains.KEY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(carts);
        editor.putString(Contains.KEY_JSON_CART, json);
        editor.apply();
    }

    private Cart getCart(List<Cart> carts, Product product) {
        return carts.stream().filter(cart -> cart.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }

    private int getSoLuong(List<Cart> carts, Product product) {
        var cart = getCart(carts, product);
        return cart == null ? 0 : cart.getSoLuong();
    }

    @Override
    protected void onResume() {
        super.onResume();
        var cartList = layData();
        txtSumProductCart.setText(String.valueOf(cartList.size()));
        productAdapter.updateCartList(cartList);
    }
}