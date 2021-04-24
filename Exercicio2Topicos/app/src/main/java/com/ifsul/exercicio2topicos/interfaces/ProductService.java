package com.ifsul.exercicio2topicos.interfaces;

import com.ifsul.exercicio2topicos.models.Product;
import com.ifsul.exercicio2topicos.models.ProductListResponse;
import com.ifsul.exercicio2topicos.models.ProductRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    @GET("product")
    Call<List<Product>> getAll();

    @POST("product")
    Call<Product> add(@Body ProductRequest product);

    @PUT("product/{id}")
    Call<Product> update(@Path("id") int id, @Body ProductRequest product);

    @DELETE("product/{id}")
    Call<Void> delete(@Path("id") int id);
}
