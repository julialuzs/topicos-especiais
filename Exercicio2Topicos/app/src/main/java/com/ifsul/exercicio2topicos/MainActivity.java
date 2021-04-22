package com.ifsul.exercicio2topicos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifsul.exercicio2topicos.interfaces.ProductService;
import com.ifsul.exercicio2topicos.models.Product;

import java.util.List;
import java.util.stream.Collectors;

import static com.ifsul.exercicio2topicos.Constants.EDIT_PRODUCT;
import static com.ifsul.exercicio2topicos.Constants.EDIT_PRODUCT_BUNDLE;

public class MainActivity extends ListActivity {

    public List<Product> products;
    public FloatingActionButton fabAddProduct;
    public ListView lvProducts;
    public ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeComponents();

        Call<List<Product>> call = this.productService.getAll();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    products = response.body();
                    lvProducts = setListView(products);

                    lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                            Product product = products.get(index);

                            Intent intent = new Intent(MainActivity.this, ProductActivity.class);

                            Bundle args = new Bundle();
                            args.putSerializable(EDIT_PRODUCT, product);
                            intent.putExtra(EDIT_PRODUCT_BUNDLE, args);
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Could not get data. " + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could not get data. Try again later", Toast.LENGTH_LONG).show();
            }
        });

        this.fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);

                startActivity(intent);
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

    private void initializeComponents() {
        this.fabAddProduct = findViewById(R.id.fabAddProduct);

        RetrofitConfig config = new RetrofitConfig();
        this.productService = config.getProductService();
    }
}