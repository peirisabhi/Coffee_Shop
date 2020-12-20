package com.abhipeiris.coffee_shop;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhipeiris.coffee_shop.databinding.FragmentProductDetailBinding;
import com.abhipeiris.coffee_shop.model.ProductModel;
import com.abhipeiris.coffee_shop.ui.shop.HomeFragmentDirections;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends Fragment {

    FragmentProductDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments()!=null){
            ProductDetailFragmentArgs args=ProductDetailFragmentArgs.fromBundle(getArguments());
            ProductModel productModel=args.getProductInfo();

            binding.textView.setText(productModel.getProduct_name());
            binding.textView2.setText(productModel.getProduct_description());
            binding.textView3.setText("Rs." + String.valueOf(productModel.getPrice()));
//            Picasso.get().load("http://192.168.1.3:8080/images/" + productModel.getImage()).into(binding.imageProduct);

            Glide.with(requireActivity())
                    .load(Uri.parse("http://192.168.1.3:8080/images/" + productModel.getImage()))
                    .into(binding.imageProduct);

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductDetailFragmentDirections.ActionProductDetailFragmentToCheckOutFragment action =
                            ProductDetailFragmentDirections.actionProductDetailFragmentToCheckOutFragment(productModel.getProduct_id());
                    Navigation.findNavController(requireView()).navigate(action);
                }
            });

        }
    }
}