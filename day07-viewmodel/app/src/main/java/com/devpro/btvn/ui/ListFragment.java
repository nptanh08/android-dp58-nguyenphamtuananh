package com.devpro.btvn.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devpro.btvn.R;
import com.devpro.btvn.adapter.ProductAdapter;
import com.devpro.btvn.adapter.onClickItem;
import com.devpro.btvn.model.FakeData;
import com.devpro.btvn.model.Product;
import com.devpro.btvn.repository.ProductRepository;
import com.devpro.btvn.viewmodel.ProductViewModel;

public class ListFragment extends Fragment {

   private ProductViewModel model;
   ProductRepository repository;
   private ProductAdapter productAdapter;
   RecyclerView recyclerView;

    public ListFragment() {
    }

     @SuppressLint("NotifyDataSetChanged")
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        model.getProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.notifyDataSetChanged();
            }
        });
        productAdapter.setOnClickItem(new onClickItem() {
            @Override
            public void onClick(Product product) {
                model.setTempProduct(product);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView,new DetailFragment())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onClickBack() {

            }

            @Override
            public void onClickFavorite(Product product) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productAdapter = new ProductAdapter(R.layout.item_product, model);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}