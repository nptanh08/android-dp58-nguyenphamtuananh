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
import com.devpro.day06_list_and_recycler_view.model.Product;
import com.devpro.day06_list_and_recycler_view.utils.onClickProductListener;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHoder> {
    private List<Product> product;
    private List<Cart> cartList = new ArrayList<>();
    private int layout;
    private onClickProductListener onItemClickListener;

    public ProductAdapter(List<Product> product, int layout) {
        this.product = product;
        this.layout = layout;
    }

    public void setOnItemClickListener(onClickProductListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHoder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.imgProduct.setImageResource(product.get(position).getImage());
        holder.txtNameProduct.setText(product.get(position).getNameProduct());
        holder.txtPrice.setText("$" + product.get(position).getPrice());

        if (product.get(position).isFavourite()) {
            holder.imgbHeart.setImageResource(R.drawable.pic_heart_red);
        } else {
            holder.imgbHeart.setImageResource(R.drawable.pic_heart);
        }

        var sanPhamTrongGioHang = cartList.stream().filter(
                cart ->
                cart.getProduct().getId().equals(product.get(position).getId()))
                .findFirst().
                orElse(null);
        if(sanPhamTrongGioHang != null){
            holder.imgbSubCart.setEnabled(true);
            holder.txtSum.setText(String.valueOf(sanPhamTrongGioHang.getSoLuong()));
        } else {
            holder.imgbSubCart.setEnabled(false);
            holder.txtSum.setText("0");
        }

        holder.imgbHeart.setOnClickListener(v -> {
            onItemClickListener.onClickChangeFavourite(product.get(position));
        });

        holder.imgbAddCart.setOnClickListener(v -> {
            onItemClickListener.onClickAddProduct(product.get(position));
        });
        holder.imgbSubCart.setOnClickListener(v -> {
            onItemClickListener.onClickRemoveProduct(product.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateCartList(List<Cart> cartList) {
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    static class ViewHoder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        ImageButton imgbHeart;
        TextView txtNameProduct;
        TextView txtPrice;
        ImageButton imgbSubCart;
        TextView txtSum;
        ImageButton imgbAddCart;


        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_prodcut);
            imgbHeart = itemView.findViewById(R.id.imgb_heart);
            txtNameProduct = itemView.findViewById(R.id.txt_name_product);
            txtPrice = itemView.findViewById(R.id.txt_price);
            imgbSubCart = itemView.findViewById(R.id.imgb_sub_cart);
            txtSum = itemView.findViewById(R.id.txt_sum);
            imgbAddCart = itemView.findViewById(R.id.imgb_add_cart);
        }
    }
}
