package com.abhipeiris.coffee_shop.ui.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhipeiris.coffee_shop.R;
import com.abhipeiris.coffee_shop.adapter.ProductAdapter;
import com.abhipeiris.coffee_shop.api.ApiClient;
import com.abhipeiris.coffee_shop.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        recyclerView = root.findViewById(R.id.product_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        Call<List<ProductModel>> productList = ApiClient.getProductService().getAllProducts();

        productList.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (response.isSuccessful()){
                    Log.e("Success", response.body().toString());

                    List<ProductModel> productModels = response.body();

                    ProductAdapter productAdapter = new ProductAdapter(getContext(), productModels);

                    recyclerView.setAdapter(productAdapter);

                    productAdapter.setListener(new ProductAdapter.Listener() {
                        @Override
                        public void onClick(int position) {
                            HomeFragmentDirections.ActionNavigationHomeToProductDetailFragment action=
                                    HomeFragmentDirections.actionNavigationHomeToProductDetailFragment(productAdapter.getSelected(position));
                            Navigation.findNavController(requireView()).navigate(action);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.e("error", t.getLocalizedMessage());
            }
        });


        return root;
    }
}