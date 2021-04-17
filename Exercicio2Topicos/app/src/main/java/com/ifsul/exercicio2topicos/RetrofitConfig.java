package com.ifsul.exercicio2topicos;

import com.ifsul.exercicio2topicos.interfaces.ProductService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitConfig {
    private static final String URL = "https://guisfco-online-shopping-api.herokuapp.com/api/online-shopping/";

    Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ProductService getProductService() {
        return this.retrofit.create(ProductService.class);
    }
}
