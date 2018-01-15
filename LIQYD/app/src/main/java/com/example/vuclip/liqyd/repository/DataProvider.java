package com.example.vuclip.liqyd.repository;

import com.example.vuclip.liqyd.models.Product;

import java.util.List;

/**
 * Created by Banty on 15/01/18.
 */

public class DataProvider {
    private final List<Product> mProductList;

    public DataProvider(List<Product> productList) {
        mProductList = productList;
    }

    public int getProductCount() {
        return mProductList.size();
    }

    public List<Product> getProductList() {
        return mProductList;
    }
}
