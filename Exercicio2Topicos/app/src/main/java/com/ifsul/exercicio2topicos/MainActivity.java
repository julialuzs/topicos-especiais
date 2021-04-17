package com.ifsul.exercicio2topicos;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ifsul.exercicio2topicos.interfaces.ProductService;
import com.ifsul.exercicio2topicos.models.Product;
import com.ifsul.exercicio2topicos.models.ProductListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends ListActivity {

    public List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitConfig config = new RetrofitConfig();
        ProductService productService = config.getProductService();

        Call<ProductListResponse> call = productService.getAll();

        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {

                if (response.isSuccessful()) {
                    products = response.body().getProducts();
                    ListView listView = setListView(products);

                } else {
                    Toast.makeText(getApplicationContext(), "Could not get data. " + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could not get data. Try again later", Toast.LENGTH_LONG).show();
            }
        });
    }

    public ListView setListView(List<Product> products) {
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                products.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );

        ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        return listView;
    }
}