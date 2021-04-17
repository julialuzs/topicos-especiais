package com.ifsul.exercicio2topicos.interfaces;

import com.ifsul.exercicio2topicos.models.Product;
import com.ifsul.exercicio2topicos.models.ProductListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("product")
    Call<ProductListResponse> getAll();
}
