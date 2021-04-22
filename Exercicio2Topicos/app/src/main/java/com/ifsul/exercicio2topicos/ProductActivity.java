package com.ifsul.exercicio2topicos;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ifsul.exercicio2topicos.interfaces.ProductService;
import com.ifsul.exercicio2topicos.models.Product;
import com.ifsul.exercicio2topicos.models.ProductRequest;

import static com.ifsul.exercicio2topicos.Constants.EDIT_PRODUCT;
import static com.ifsul.exercicio2topicos.Constants.EDIT_PRODUCT_BUNDLE;

public class ProductActivity extends AppCompatActivity {

    private Button btSave;
    private EditText etName;
    private EditText etDescription;
    private EditText etImageURL;
    private EditText etPrice;
    private EditText etStockLevel;

    public Product product;

    public ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        this.initializeComponents();

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra(EDIT_PRODUCT_BUNDLE);

        if (args != null) {
            this.product = (Product) args.getSerializable(EDIT_PRODUCT);
            this.setProduct(this.product);
        }

        this.setOnClickListener();
    }

    private void setOnClickListener() {
        this.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productId = product.getId();
                ProductRequest request = getProduct();

                Call<Product> callProduct = productId > 0 ?
                        productService.update(productId, request)
                        : productService.add(request);

                callProduct.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ProductActivity.this, "Item saved successfully", Toast.LENGTH_LONG);
                            Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(ProductActivity.this, "An error occurred", Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }

    private void initializeComponents() {
        this.btSave = findViewById(R.id.btSave);
        this.etName = findViewById(R.id.etName);
        this.etDescription = findViewById(R.id.etDescription);
        this.etImageURL = findViewById(R.id.etURL);
        this.etPrice = findViewById(R.id.etPrice);
        this.etStockLevel = findViewById(R.id.etStockLevel);

        RetrofitConfig config = new RetrofitConfig();
        this.productService = config.getProductService();
    }

    public ProductRequest getProduct() {
        ProductRequest request = new ProductRequest();

        request.setName(this.etName.getText().toString());
        request.setDescription(this.etDescription.getText().toString());
        request.setImageUrl(this.etImageURL.getText().toString());

        Float price = Float.parseFloat(this.etPrice.getText().toString());
        int stock = Integer.parseInt(this.etStockLevel.getText().toString());

        request.setPrice(price);
        request.setStockLevel(stock);

        return request;
    }

    public void setProduct(Product product) {
        this.etName.setText(product.getName());
        this.etDescription.setText(product.getDescription());
        this.etImageURL.setText(product.getImageUrl());
        this.etPrice.setText(String.valueOf(product.getPrice()));
        this.etStockLevel.setText(String.valueOf(product.getStockLevel()));
    }
}