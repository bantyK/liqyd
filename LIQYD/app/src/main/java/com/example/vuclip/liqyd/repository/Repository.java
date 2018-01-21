package com.example.vuclip.liqyd.repository;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Banty on 15/01/18.
 */

public class Repository {

    public DataProvider getDataProvider() {
        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setDrawableImage(R.drawable.bottle);
        product.setName("Bisleri 20 Ltr");
        product.setPrice("70");
        productList.add(product);

        Product product2 = new Product();
        product2.setDrawableImage(R.drawable.bottle);
        product2.setName("Aquaravi 20 Ltr");
        product2.setPrice("55");
        productList.add(product2);

        productList.add(product);

        return new DataProvider(productList);
    }
}
