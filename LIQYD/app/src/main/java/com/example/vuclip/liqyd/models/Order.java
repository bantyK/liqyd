package com.example.vuclip.liqyd.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Banty on 26/01/18.
 */

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int orderId;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "purchase_date")
    private String purchaseDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
