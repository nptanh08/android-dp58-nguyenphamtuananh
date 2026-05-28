package com.devpro.btvn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.btvn.R;
import com.devpro.btvn.model.Product;
import com.devpro.btvn.viewmodel.ProductViewModel;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private onClickItem onClickItem;

    public void setOnClickItem(onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    private int layout;
    private ProductViewModel viewModel;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    public ProductAdapter(int layout, ProductViewModel viewModel) {
        this.layout = layout;
        this.viewModel = viewModel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (viewModel.getProducts() == null || viewModel.getProducts().getValue() == null) {
            return;
        }
        List<Product> products = viewModel.getProducts().getValue();
        Product product = products.get(position);
        holder.img_product.setImageResource(product.getImageProduct());
        holder.txt_tenProduct.setText(product.getNameProduct());
        holder.txt_thoiGian.setText(product.getThoiGianNau());
        holder.txt_rating.setText(product.getMucDo());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (viewModel.getProducts() != null && viewModel.getProducts().getValue() != null) {
            return viewModel.getProducts().getValue().size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView txt_tenProduct;
        TextView txt_thoiGian;
        TextView txt_rating;
        ImageButton imageButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            img_product = itemView.findViewById(R.id.imageView2);
            txt_tenProduct = itemView.findViewById(R.id.textView2);
            txt_thoiGian = itemView.findViewById(R.id.textView3);
            txt_rating = itemView.findViewById(R.id.textView4);
        }
    }
}
