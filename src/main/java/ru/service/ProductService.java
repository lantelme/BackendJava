package ru.service;

import retrofit2.Call;
import retrofit2.http.*;
import ru.dto.Product;

import java.util.ArrayList;

public interface ProductService {

    @GET("products")
    Call<ArrayList<Product>> getProducts(ArrayList<Product> sss);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") Integer id);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product product);


}
