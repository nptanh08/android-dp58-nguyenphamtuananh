package com.devpro.day06_list_and_recycler_view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.day06_list_and_recycler_view.R;
import com.devpro.day06_list_and_recycler_view.model.Cart;
import com.devpro.day06_list_and_recycler_view.utils.onClickProductListener;

import java.util.List;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<Cart> cartList;
    int layout;
    private onClickProductListener onClick;

    public void setOnClickCart(onClickProductListener clickCart) {
        this.onClick = clickCart;
    }

    public CartAdapter(List<Cart> cartList, int layout) {
        this.cartList = cartList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        var productCart = cartList.get(position).getProduct();
        holder.imageView.setImageResource(productCart.getImage());
        holder.txt_title.setText(productCart.getNameProduct());
        holder.txt_rate.setText(String.valueOf(productCart.getRating()));
        holder.txt_price.setText("$" + productCart.getPrice());
        holder.imgb_sub.setEnabled(true);
        holder.txt_sum.setText(String.valueOf(cartList.get(position).getSoLuong()));

        holder.imgb_add.setOnClickListener(v -> {
            onClick.onClickAddProduct(productCart);
        });
        holder.imgb_sub.setOnClickListener(v -> {
            onClick.onClickRemoveProduct(productCart);
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
    @SuppressLint("NotifyDataSetChanged")
     public void updateCartList(List<Cart> carts) {
        this.cartList = carts;
        notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_title;
        TextView txt_rate;
        TextView txt_price;
        ImageButton imgb_sub;
        TextView txt_sum;
        ImageButton imgb_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_cart_product);
            txt_title = itemView.findViewById(R.id.txt_title_cart);
            txt_rate = itemView.findViewById(R.id.txt_rate_cart);
            txt_price = itemView.findViewById(R.id.txt_price_cart);
            imgb_sub = itemView.findViewById(R.id.imgb_sub_cart);
            txt_sum = itemView.findViewById(R.id.txt_sum);
            imgb_add = itemView.findViewById(R.id.imgb_add_cart);
        }
    }
}
