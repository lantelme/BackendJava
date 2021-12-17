package ru.service;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.dto.Category;
import ru.dto.Product;

public interface CategoryService {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Integer id);

}
