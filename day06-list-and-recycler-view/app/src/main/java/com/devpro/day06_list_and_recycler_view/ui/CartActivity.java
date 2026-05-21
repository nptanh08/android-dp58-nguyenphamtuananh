package com.devpro.day06_list_and_recycler_view.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.day06_list_and_recycler_view.R;
import com.devpro.day06_list_and_recycler_view.adapter.CartAdapter;
import com.devpro.day06_list_and_recycler_view.model.Cart;
import com.devpro.day06_list_and_recycler_view.model.Product;
import com.devpro.day06_list_and_recycler_view.utils.Contains;
import com.devpro.day06_list_and_recycler_view.utils.onClickProductListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class CartActivity extends AppCompatActivity {
    TextView txt_sumItem;
    TextView txt_subTotal;
    TextView txt_total;
    RecyclerView recyclerView;
    ImageButton imgb_return;
    CartAdapter cartAdapter;
    List<Cart> cartList;
    ImageView img_noItem;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_sumItem = findViewById(R.id.txt_sum_item);
        txt_subTotal = findViewById(R.id.txt_sub_total);
        txt_total = findViewById(R.id.txt_total);
        recyclerView = findViewById(R.id.recycle_cart);
        imgb_return = findViewById(R.id.imgb_return);
        img_noItem = findViewById(R.id.img_no_item);

        cartList = layData();
        cartAdapter = new CartAdapter(cartList, R.layout.cart_item);


        cartAdapter.setOnClickCart(new onClickProductListener() {
            @Override
            public void onClickAddProduct(Product product) {
                var cart = findProduct(product, cartList);
                cart.setSoLuong(cart.getSoLuong() + 1);
                luuData(cartList);
                cartAdapter.updateCartList(cartList);
                txt_subTotal.setText(String.valueOf(getTotal(cartList)));
                @SuppressLint("DefaultLocale") String result = String.format("%2f", getTotal(cartList) + getTotal(cartList) * 0.1);
                txt_total.setText(result);
            }

            @Override
            public void onClickChangeFavourite(Product product) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onClickRemoveProduct(Product product) {
                var cart = findProduct(product, cartList);
                cart.setSoLuong(cart.getSoLuong() - 1);
                if (cart.getSoLuong() == 0) {
                    cartList.remove(cart);
                    txt_sumItem.setText(cartList.size() + " Item(s)");
                }
                if (cartList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    img_noItem.setVisibility(View.VISIBLE);
                }
                luuData(cartList);
                cartAdapter.updateCartList(cartList);
                txt_subTotal.setText(String.valueOf(getTotal(cartList)));
                txt_total.setText(String.valueOf(getTotal(cartList) + getTotal(cartList) * 0.1));
            }
        });
        if (cartList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            img_noItem.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            img_noItem.setVisibility(View.GONE);
            recyclerView.setAdapter(cartAdapter);
        }
        imgb_return.setOnClickListener(v -> finish());
        txt_sumItem.setText(cartList.size() + " Item(s)");
        txt_subTotal.setText(String.valueOf(getTotal(cartList)));
        @SuppressLint("DefaultLocale") String result = String.format("%2f", getTotal(cartList) + getTotal(cartList) * 0.1);
        txt_total.setText(result);

    }

    private double getTotal(List<Cart> carts) {
        double total = 0;
        for (Cart cart : carts) {
            total += cart.getSoLuong() * cart.getProduct().getPrice();
        }
        return total;
    }

    private List<Cart> layData() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(Contains.KEY_PREFERENCES, MODE_PRIVATE);
        String json = sharedPreferences.getString(Contains.KEY_JSON_CART, null);
        if (json == null) return null;
        var typeToken = new TypeToken<ArrayList<Cart>>() {
        }.getType();
        return gson.<ArrayList<Cart>>fromJson(json, typeToken);
    }

    private void luuData(List<Cart> carts) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(Contains.KEY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(carts);
        editor.putString(Contains.KEY_JSON_CART, json);
        editor.apply();

    }

    private Cart findProduct(Product product, List<Cart> carts) {
        return carts.stream().filter(cart -> cart.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartList = layData();
        cartAdapter.updateCartList(cartList);
    }
}