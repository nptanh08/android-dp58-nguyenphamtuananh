package com.devpro.btvn.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devpro.btvn.R;
import com.devpro.btvn.model.Product;
import com.devpro.btvn.viewmodel.ProductViewModel;

public class DetailFragment extends Fragment {
    private ProductViewModel model;

    ImageView img_product;
    TextView txt_tenProduct;
    TextView txt_moTaProduct;
    TextView txt_timeCook;
    TextView txt_difficulty;
    TextView txt_servings;
    ImageView tmgb_return;
    ImageView imgb_heart;
    CardView cv_return;
    CardView cv_heart;


    public DetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_product = view.findViewById(R.id.img_product);
        txt_tenProduct = view.findViewById(R.id.txt_tenProduct);
        txt_moTaProduct = view.findViewById(R.id.txt_moTaProduct);
        txt_timeCook = view.findViewById(R.id.txt_timeCook);
        txt_difficulty = view.findViewById(R.id.txt_difficulty);
        txt_servings = view.findViewById(R.id.txt_servings);
        cv_heart = view.findViewById(R.id.cv_heart);
        cv_return = view.findViewById(R.id.cv_return);
        imgb_heart = view.findViewById(R.id.imgb_heart);
        tmgb_return = view.findViewById(R.id.tmgb_return);

        cv_return.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        Product product = model.getTempProduct().getValue();

        if (product != null) {
            img_product.setImageResource(product.getImageProduct());
            txt_tenProduct.setText(product.getNameProduct());
            txt_moTaProduct.setText(product.getMoTa());
            txt_timeCook.setText(product.getThoiGianNau());
            txt_difficulty.setText(product.getMucDo());
            txt_servings.setText(product.getServings());
            setFavourite(product.isFavorite());
        }

        cv_heart.setOnClickListener(v -> {
            if (product != null) {
                product.setFavorite(!product.isFavorite());
                setFavourite(product.isFavorite());
                model.updateItem(product);

            }
        });
    }

    private void setFavourite(boolean favorite) {
        if (favorite) {
            imgb_heart.setImageResource(R.drawable.pic_heart_red);
        } else {
            imgb_heart.setImageResource(R.drawable.pic_heart);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}