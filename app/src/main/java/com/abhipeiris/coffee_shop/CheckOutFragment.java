package com.abhipeiris.coffee_shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhipeiris.coffee_shop.api.ApiClient;
import com.abhipeiris.coffee_shop.databinding.FragmentCheckOutBinding;
import com.abhipeiris.coffee_shop.databinding.FragmentProductDetailBinding;
import com.abhipeiris.coffee_shop.model.OrderModel;
import com.abhipeiris.coffee_shop.model.ProductModel;
import com.abhipeiris.coffee_shop.services.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutFragment extends Fragment {

    FragmentCheckOutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCheckOutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getArguments()!=null) {
                    System.out.println(getArguments().getInt("getProductId"));
//

                    OrderModel orderModel = new OrderModel();
                    orderModel.setFname(binding.txtFname.getText().toString());
                    orderModel.setLname(binding.txtLname.getText().toString());
                    orderModel.setAddress(binding.txtAddress.getText().toString());
                    orderModel.setEmail(binding.txtEmail.getText().toString());
                    orderModel.setPhone(binding.txtMobileNumber.getText().toString());
                    orderModel.setNote(binding.txtNote.getText().toString());
                    orderModel.setProduct_master_id(getArguments().getInt("getProductId"));
                    orderModel.setStatus("Pending");

                    Call<OrderModel> order = ApiClient.getOrderService().createOrder(orderModel);

                    order.enqueue(new Callback<OrderModel>() {
                        @Override
                        public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                            System.out.println("ok");
                            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<OrderModel> call, Throwable t) {
                            System.out.println("error");
                            Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}