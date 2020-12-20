package com.abhipeiris.coffee_shop.services;

import com.abhipeiris.coffee_shop.model.OrderModel;
import com.abhipeiris.coffee_shop.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Services {
    @GET("product/")
    Call<List<ProductModel>> getAllProducts();


    @POST("order/")
    Call<OrderModel> createOrder(@Body OrderModel orderModel);


}
