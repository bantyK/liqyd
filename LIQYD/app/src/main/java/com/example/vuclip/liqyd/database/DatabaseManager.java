package com.example.vuclip.liqyd.database;

import com.example.vuclip.liqyd.models.Order;
import com.example.vuclip.liqyd.models.Product;

/**
 * Created by Banty on 26/01/18.
 */

public class DatabaseManager {
    public static void insert(Product product, long purchaseTime) {
        final Order order = new Order();
        order.setProductName(product.getName());
        order.setPurchaseDate(String.valueOf(purchaseTime));
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getAppDatebase().orderDao().insert(order);
            }
        }).start();
    }

}
